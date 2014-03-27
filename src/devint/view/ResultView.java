package devint.view;

import java.awt.*;

public class ResultView implements GameObjectView {
    private String label="empty";

    @Override
    public void paint(Graphics2D g) {
        if(this.label.equals("yay")){
            g.setColor(new Color(0xCC, 0x00, 0xCC, 0x66));
        } else {
            g.setColor(new Color(0x88, 0x88, 0x88, 0x66));
        }
        g.fill(g.getClip());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Segoe UI", 10, Font.BOLD));
        g.drawString(this.label, 400, 250);
    }

    @Override
    public void setLabel(String name) {
        this.label = name;
    }

    @Override
    public String getLabel() {return this.label;}

    @Override
    public void setId(Integer id) {}

    @Override
    public Integer getId() {return -10;}

    @Override
    public String getType() {return "ResultView";}

    @Override
    public void setPosition(Dimension location) {}

    @Override
    public void setMovement(Dimension delta) {}

    @Override
    public Integer getZOrder() {return 2;}

    @Override
    public boolean isHit(Point location) {return Boolean.FALSE;}

    @Override
    public void doAnimation(String animation) {}

    @Override
    public Boolean isFlaggedForRemoval() {return Boolean.FALSE;}

    @Override
    public Boolean isAnimationPending() {return Boolean.FALSE;}

    @Override
    public void flagForRemoval() {}

    @Override
    public int compareTo(GameObjectView o) {
        return this.getZOrder() - o.getZOrder();
    }
}
