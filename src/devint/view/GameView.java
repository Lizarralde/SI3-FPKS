package devint.view;

import devint.controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameView extends JPanel implements Observer, TargetDropListener {
    private BufferedImage imgBackground;
    private final List<GameObjectView> gameObjects;

    private Controller hook;

    private Thread frameRefreshThread;
    private Thread targetDropThread;

    public GameView(Controller hook){
        super();
        this.hook = hook;
        this.setPreferredSize(new Dimension(1350, 700));
        gameObjects = new LinkedList<>();
        this.setBackground("resources\\\\bg.jpg");
        this.setCursorStyle("none");
        this.processScoreOperation("0");
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
            for(GameObjectView w : gameObjects){
                if(w.isFlaggedForRemoval() && !w.isAnimationPending()){
                    toRemove.add(w);
                } else {
                    w.paint(g2d);
                }
            }
            gameObjects.removeAll(toRemove);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO check observable class
        Map<String, Object> state = (Map<String, Object>) arg;
        // controller messages
        if(state.containsKey("question")){
            Map<String, Object> w = new HashMap<>();
            w.put("id", -2);
            w.put("label", state.get("question"));
            w.put("type", "question");
            this.addNewObject(w);
            Integer id = 0;
            List<String> answers = (List<String>) state.get("answers");
            Collections.shuffle(answers);
            for(String response : answers){
                w = new HashMap<>();
                w.put("id", id);
                w.put("label", response);
                w.put("type", "target");
                w.put("position", new Dimension(id * 270, 20));
                this.addNewObject(w);
                id++;
            }
        }

        if(state.containsKey("state")){
            this.stopTargetDropThread();
            Map<String, Object> w = new HashMap<>();
            w.put("id", -10);
            w.put("label", (Boolean)state.get("state")?"yay":"nay");
            w.put("type", "result");
            this.addNewObject(w);
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
                GameObjectView gov = gameObjectViewIterator.next();
                if((gov.getZOrder() == 0) && gov.getType().equals("target") && gov.isHit(w)){
                    this.hook.validate(gov.getLabel());
                    gov.doAnimation("hit");
                    gov.flagForRemoval();
                    return;
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
        AimListener listener = new AimListener();
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
        this.targetDropThread = new Thread(new TargetDropThread(this.gameObjects, 16, this.getSize()));
        this.targetDropThread.start();
    }

    public void stopTargetDropThread(){
        this.targetDropThread.interrupt();
    }

    @Override
    public void targetDropped(TargetObjectView target) {
        target.doAnimation("hit");
        target.flagForRemoval();
        // TODO check with controller and model
    }
}
