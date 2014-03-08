import org.omg.CosNaming._BindingIteratorImplBase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameView extends JPanel implements Observer {
    private BufferedImage imgTarget, imgBackground;
    private List<GameObjectView> gameObjects;

    public GameView(){
        super();
        setPreferredSize(new Dimension(1000, 700));
        gameObjects = new LinkedList<GameObjectView>();
    }

    public boolean setBackground(File path){
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
        gameObjects.add(objectView);
    }

    public void removeGameObject(String name){
        for(GameObjectView w : gameObjects){
            if(w.getName().equals(name)){
                gameObjects.remove(w);
                return;
            }
        }
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imgBackground, null, -100, -100);
        for(GameObjectView w : gameObjects){
            w.paint(g2d);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO check observable class
        System.out.println("bite");
        Map<String, Object> state = (Map<String, Object>) arg;
        if(state.containsKey("newObject")){
            Map<String, Object> object = (Map<String, Object>) state.get("newObject");
            GameObjectView gameObject;
            switch((String)object.get("type")){
                case "target": gameObject = new TargetObjectView(); break;
                default: gameObject = null; break;
            }
            if(gameObject != null){
                gameObject.setId((Integer)object.get("id"));
                gameObject.setName((String)object.get("name"));
                gameObject.setPosition((Dimension)object.get("position"));
                addNewGameObject(gameObject);
            }
        }
        if(state.containsKey("background")){
            setBackground((String) state.get("background"));

        }
    }
}
