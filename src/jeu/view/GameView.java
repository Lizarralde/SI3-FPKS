package jeu.view;

import jeu.KeysKeeper;
import jeu.controller.FPKSController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.security.Key;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameView extends JPanel implements Observer, TargetDropListener, NextQuestionListener, AudioDoneListener {
    private BufferedImage imgBackground;
    private final List<GameObjectView> gameObjects;

    private FPKSController hook;

    private Thread frameRefreshThread;
    private Thread targetDropThread;

    private Random r;

    // TODO do something because that's dirty as fuck
    private Boolean isDropHit;
    private AimListener listener;

    private static final String RES = KeysKeeper.PATH_RESSOURCES;
    private static final String RES_IMG = KeysKeeper.PATH_RESSOURCES + KeysKeeper.PATH_IMG;
    private static final String RES_SONS = KeysKeeper.PATH_RESSOURCES + KeysKeeper.PATH_SONS;

    public GameView(FPKSController hook){
        super();
        this.hook = hook;
        this.setPreferredSize(new Dimension(1350, 700));
        gameObjects = new LinkedList<>();
        this.setBackground(RES_IMG + "/bg.jpg");
        this.setCursorStyle("none");
        this.processScoreOperation("0");
        this.isDropHit = false;
        this.r = new Random(232465612);
    }

    public final boolean setBackground(File path){
        try{
            imgBackground = ImageIO.read(path);
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setBackground(String path){
        return setBackground(new File(path));
    }

    public void addNewGameObject(GameObjectView objectView){
        synchronized (gameObjects){
            gameObjects.add(objectView);
            Collections.sort(gameObjects);
        }
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imgBackground, 0, 0, this.getWidth(), imgBackground != null ? Math.round(1.0f * this.getWidth() / this.imgBackground.getWidth() * this.imgBackground.getHeight()) : this.getHeight(), null);

        List<GameObjectView> toRemove = new LinkedList<>();

        synchronized (gameObjects){
            try{
                for(GameObjectView w : gameObjects){
                    if(w.isFlaggedForRemoval() && !w.isAnimationPending()){
                        toRemove.add(w);
                    } else {
                        w.paint(g2d);
                    }
                }
                gameObjects.removeAll(toRemove);
            } catch(ConcurrentModificationException e){
                // TODO LOL
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO check observable class
        if(arg == null){
            Map<String, Object> w = new HashMap<>();
            w.put("id", -10);
            w.put("label", "Partie terminée");
            w.put("type", "result");
            this.addNewObject(w);
            new Thread(new AudioPlayerThread(RES_SONS + "/game_win.wav")).start();
        } else {
            Map<String, Object> state = (Map<String, Object>) arg;
            // controller messages
            if(state.containsKey("question")){
                synchronized (this.gameObjects){
                    List<GameObjectView> toRemove = new LinkedList<>();
                    for(GameObjectView gov : this.gameObjects){
                        if(gov.getType().equals("target") || gov.getType().equals("question") || gov.getType().equals("result")){
                            toRemove.add(gov);
                        }
                    }
                    this.gameObjects.removeAll(toRemove);
                }
                Map<String, Object> w = new HashMap<>();
                w.put("id", -2);
                w.put("label", state.get("question"));
                w.put("type", "question");
                this.addNewObject(w);
                Integer id = 0;
                List<String> answers = new LinkedList<>((List<String>) state.get("answers"));
                Collections.shuffle(answers, r);
                for(String response : answers){
                    w = new HashMap<>();
                    w.put("id", id);
                    w.put("label", response);
                    w.put("type", "target");
                    w.put("position", new Dimension(id * 270, (int)Math.round(10 + (20 * Math.random()))));
                    this.addNewObject(w);
                    id++;
                }
                AudioPlayerThread apt = new AudioPlayerThread((String)state.get("path"));
                apt.addListener(this);
                new Thread(apt).start();
            }

            if(state.containsKey("state")){
                if(this.isDropHit){
                    if((Boolean)state.get("state")){
                        Map<String, Object> w = new HashMap<>();
                        w.put("id", -11);
                        w.put("label", "Partie terminée");
                        w.put("type", "result");
                        this.stopTargetDropThread();
                        this.addNewObject(w);
                        new Thread(new AudioPlayerThread(RES_SONS + "game_lose.wav")).start();
                    }
                } else {
                    Boolean isOk = (Boolean)state.get("state");
                    Map<String, Object> w = new HashMap<>();
                    w.put("id", -10);
                    w.put("label", isOk?"Bonne réponse":"Mauvaise réponse");
                    w.put("type", "result");
                    this.stopTargetDropThread();
                    if (!isOk) {
                        this.hook.malus();
                    }
                    this.addNewObject(w);
                    new Thread(new AudioPlayerThread(RES_SONS + (isOk?"success.wav":"failure.wav"))).start();
                }
            }

            // old "debug" messages
            if(state.containsKey("newObject")){
                addNewObject((Map<String, Object>) state.get("newObject"));
            }

            if(state.containsKey("background")){
                setBackground((String) state.get("background"));
            }

            if(state.containsKey("move")){
                moveObjects(state);
            }

            if(state.containsKey("cursorStyle")){
                setCursorStyle((String) state.get("cursorStyle"));
            }

            if(state.containsKey("fire")){
                processFireEvent((Point)state.get("fire"), this.getParent().getLocationOnScreen());
            }

            if(state.containsKey("score")){
                processScoreOperation(state.get("score").toString());
            }

            if(state.containsKey("animation")){
                doAnimation((String) state.get("animation"));
            }
        }
    }

    private void processScoreOperation(String score) {
        synchronized (gameObjects){
            for(GameObjectView gov : gameObjects){
                if(gov.getId() == -10){
                    gov.setLabel(score);
                    return;
                }
            }
            // no score object, make a new one
            GameObjectView gov = new ScoreObjectView();
            gov.setLabel(score);
            gameObjects.add(gov);
        }
    }

    private void doAnimation(String animation) {
        if(animation.equals("fire")){
            synchronized (gameObjects){
                for(GameObjectView gov : gameObjects){
                    if(gov.getType().equals("ironsight")){
                        gov.doAnimation("fire");
                        return;
                    }
                }
            }
        }
    }

    private void processFireEvent(Point fire, Point locationOnScreen) {
        Point w = new Point(fire.x - locationOnScreen.x, fire.y - locationOnScreen.y);
        synchronized (gameObjects){
            Iterator<GameObjectView> gameObjectViewIterator = gameObjects.iterator();
                while(gameObjectViewIterator.hasNext()){
                         //TODO FIXME
                    /*
                    --> L'exception ne se déclenche UNIQUEMENT quand on bourine le click sur LA BONNE REPONSE
                    --> un synchronized sur l'iterator fait carrement bugué l'écran MAUVAISE REPONSE (=> tjr vert)
                    Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
                        at java.util.LinkedList$ListItr.checkForComodification(LinkedList.java:953)
                        at java.util.LinkedList$ListItr.next(LinkedList.java:886)
                        at jeu.view.GameView.processFireEvent(GameView.java:226)
                        at jeu.view.GameView.update(GameView.java:180)
                        |||||||
                     */
                    GameObjectView gov = gameObjectViewIterator.next();
                    if(this.targetDropThread != null && (gov.getZOrder() == 0) && gov.getType().equals("target") && gov.isHit(w)){
                        this.hook.validate(gov.getLabel());
                        gov.doAnimation("hit");
                        gov.flagForRemoval();
                        return;
                    } else if(gov.getType().equals("result")){
                        if((gov.getLabel().equals("Partie terminée"))){
                            this.removeMouseListener(this.listener);
                            this.removeMouseMotionListener(this.listener);
                            this.hook.endGame();
                            return;
                        } else if(gov.getLabel().equals("Mauvaise réponse")) {
                            gameObjects.remove(gov);
                            this.initializeTargetDropThread();
                        } else {
                            this.onNextQuestion();
                        }
                    }

            }
        }
        // No hit, put a bullet decal on the background
        GameObjectView gov = new BulletImpactObjectView();
        gov.setPosition(new Dimension(w.x, w.y));
        gov.setId(-2);
        addNewGameObject(gov);
    }

    private void setCursorStyle(String cursor) {
        if(cursor.equals("none")){
            BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
            this.setCursor(blankCursor);
        } else if(cursor.equals("crosshair")){
            this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        } else if(cursor.equals("default")){
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private void moveObjects(Map<String, Object> state) {
        for(Map<String, Object> move : (List<Map<String, Object>>) state.get("move")){
            GameObjectView gameObject = getGameObject((Integer)move.get("id"));
            if(gameObject != null) {
                if (move.containsKey("absolute")) {
                    Dimension newPosition = (Dimension) move.get("absolute");
                    if (move.containsKey("doCorrection")) {
                        newPosition.setSize(newPosition.width - this.getParent().getLocationOnScreen().x,
                                newPosition.height - this.getParent().getLocationOnScreen().y);
                    }
                    gameObject.setPosition(newPosition);
                } else if (move.containsKey("relative")) {
                    gameObject.setMovement((Dimension) move.get("relative"));
                }
            }
        }
    }

    private void addNewObject(Map<String, Object> objectDescription) {
        GameObjectView gameObject;
        switch((String)objectDescription.get("type")){
            case "target": gameObject = new TargetObjectView(); ((TargetObjectView)gameObject).addTargetDropListener(this); break;
            case "question": gameObject = new QuestionView(); break;
            case "result": gameObject = new ResultView(); break;
            default: gameObject = null; break;
        }
        if(gameObject != null){
            gameObject.setId((Integer)objectDescription.get("id"));
            gameObject.setLabel((String) objectDescription.get("label"));
            gameObject.setPosition((Dimension)objectDescription.get("position"));
            this.addNewGameObject(gameObject);
        }
    }

    private GameObjectView getGameObject(Integer id) {
        synchronized (gameObjects){
            for(GameObjectView gov : gameObjects){
                if(gov.getId().equals(id)){
                    return gov;
                }
            }
        }
        return null;
    }

    public void initalizeMouseInput(){
        listener = new AimListener();
        this.addMouseMotionListener(listener);
        this.addMouseListener(listener);
        listener.addObserver(this);
        synchronized (gameObjects){
            gameObjects.add(new AimObjectView());
        }
    }

    public void initializeFrameRefreshThread(){
        this.frameRefreshThread = new Thread(new FrameRefreshThread(this, new Long(16)));
        this.frameRefreshThread.start();
    }

    public void stopFrameRefreshThread(){
        this.frameRefreshThread.interrupt();
    }

    public void initializeTargetDropThread(){
        synchronized (this.gameObjects){
            this.targetDropThread = new Thread(new TargetDropThread(this.gameObjects, 16, this.getSize()));
            this.targetDropThread.start();
        }
    }

    public void stopTargetDropThread(){
        try{
            this.targetDropThread.interrupt();
        }catch(Exception e){}
        this.targetDropThread = null;
    }

    @Override
    public void targetDropped(TargetObjectView target) {
        target.doAnimation("hit");
        target.flagForRemoval();
        this.isDropHit = Boolean.TRUE;
        this.hook.validate(target.getLabel());
    }

    @Override
    public void onNextQuestion() {
        synchronized (this.gameObjects){
            List<GameObjectView> toRemove = new LinkedList<>();
            for(GameObjectView gov : this.gameObjects){
                if(gov.getType().equals("target") || gov.getType().equals("question") || gov.getType().equals("result")){
                    toRemove.add(gov);
                }
            }
            this.gameObjects.removeAll(toRemove);
        }
        this.hook.next();
    }

    @Override
    public void onAudioDone() {
        System.out.println("onAudioDone called, frameHeight=" + this.getSize().height);
        this.initializeTargetDropThread();
    }
}
