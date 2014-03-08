import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TestMainGameView extends Observable {
    private JFrame frame;
    private GameView gv;

    public TestMainGameView(){
        frame = new JFrame();
        gv = new GameView();

        frame.getContentPane().add(gv);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public void startDemo(List<Map<String, Object>> states) throws InterruptedException {
        for(Map<String, Object> state : states){
            notifyObservers(state);
            setChanged();
            gv.repaint();
            Thread.sleep(300);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        TestMainGameView tmgv = new TestMainGameView();
        List<Map<String, Object>> states = new LinkedList<Map<String, Object>>();
        Map<String, Object> w = new HashMap<String, Object>();
        w.put("background", "resources\\\\bg.jpg");
        states.add(w);
        w = new HashMap<String, Object>();
        Map<String, Object> object = new HashMap<String, Object>();
        object.put("id", 0);
        object.put("name", "bite");
        object.put("type", "target");
        object.put("position", new Dimension(100, 100));
        w.put("newObject", object);
        states.add(w);
        tmgv.startDemo(states);
    }
}
