import java.awt.*;

public interface GameObjectView {
    public void paint(Graphics2D g);
    public void setName(String name);
    public String getName();
    public void setId(Integer id);
    public Integer getId();
    public String getType();
    public void setPosition(Dimension location);
    public void setMovement(Dimension delta);
}
