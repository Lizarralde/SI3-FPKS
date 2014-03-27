package devint.view;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TestMainGameView extends Observable {
    private JFrame frame;
    private GameView gv;

    public TestMainGameView(){
        frame = new JFrame();
        gv = new GameView(null);

        frame.getContentPane().add(gv);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public void startDemo(List<Map<String, Object>> states) throws InterruptedException {
        this.addObserver(gv);
        gv.initalizeMouseInput();
        gv.initializeFrameRefreshThread();

        for(Map<String, Object> state : states){
            setChanged();
            notifyObservers(state);
            Thread.sleep(300);
        }
        gv.initializeTargetDropThread();
    }

    public static void main(String[] args) throws InterruptedException{
        TestMainGameView tmgv = new TestMainGameView();
        List<Map<String, Object>> states = new LinkedList<Map<String, Object>>();
        Map<String, Object> w = new HashMap<String, Object>();
        w.put("background", "resources\\\\bg.jpg");
        w.put("cursorStyle", "none");
        states.add(w);
        w = new HashMap<>();
        w.put("score", "1000");
        states.add(w);
        w = new HashMap<>();
        Map<String, Object> object = new HashMap<String, Object>();
        object.put("id", 0);
        object.put("label", "Richard Stallman");
        object.put("type", "target");
        object.put("position", new Dimension(290, 20));
        w.put("newObject", object);
        states.add(w);
        w = new HashMap<>();
        object = new HashMap<>();
        object.put("id", 1);
        object.put("label", "Your mom");
        object.put("type", "target");
        object.put("position", new Dimension(20, 20));
        w.put("newObject", object);
        states.add(w);
        w = new HashMap<>();
        object = new HashMap<>();
        object.put("id", 1);
        object.put("label", "Nhan");
        object.put("type", "target");
        object.put("position", new Dimension(560, 20));
        w.put("newObject", object);
        states.add(w);
        w = new HashMap<>();
        object = new HashMap<>();
        object.put("id", 1);
        object.put("label", "lel");
        object.put("type", "target");
        object.put("position", new Dimension(830, 20));
        w.put("newObject", object);
        states.add(w);
        w = new HashMap<>();
        object = new HashMap<>();
        object.put("id", -2);
        object.put("label", "La question de l'amour");
        object.put("type", "question");
        w.put("newObject", object);
        states.add(w);
        w = new HashMap<>();
        w.put("score", "10000");
        states.add(w);
        tmgv.startDemo(states);
    }
}
