package devint.view;

import java.awt.*;

public interface GameObjectView {
    public void paint(Graphics2D g);
    public void setLabel(String name);
    public String getLabel();
    public void setId(Integer id);
    public Integer getId();
    public String getType();
    public void setPosition(Dimension location);
    public void setMovement(Dimension delta);
    public boolean isOverlay();
    public boolean isHit(Point location);

    public void doAnimation(String animation);
}
