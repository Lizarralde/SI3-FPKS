package devint.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

public class AimListener extends Observable implements MouseMotionListener, MouseListener {
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        HashMap<String, Object> w = new HashMap<>();
        HashMap<String, Object> move1 = new HashMap<>();
        move1.put("absolute", new Dimension(e.getLocationOnScreen().x, e.getLocationOnScreen().y));
        move1.put("id", -1);
        move1.put("doCorrection", Boolean.TRUE);
        java.util.List<Map<String, Object>> move = new LinkedList<>();
        move.add(move1);
        w.put("move", move);

        setChanged();
        notifyObservers(w);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e){
        HashMap<String, Object> w = new HashMap<>();
        w.put("animation", "fire");
        w.put("fire", e.getLocationOnScreen());

        setChanged();
        notifyObservers(w);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
