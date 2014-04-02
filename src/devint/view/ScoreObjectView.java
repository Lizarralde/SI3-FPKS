package devint.view;

import java.awt.*;

public class ScoreObjectView implements GameObjectView {
    private Integer score;
    private Integer scoreDiff;

    public ScoreObjectView(){
        this.score = 0;
        this.scoreDiff = 0;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(g.getClipBounds().width - 170, 0, 170, 60);
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Segoe UI", Font.PLAIN, 60));
        g.drawString(Integer.toString(this.score), g.getClipBounds().width - 165, 50);
        if(this.scoreDiff > 0){
            this.score++;
            this.scoreDiff--;
        } else if(this.scoreDiff < 0){
            this.score--;
            this.scoreDiff++;
        }
    }

    @Override
    public void setLabel(String name) {
        this.scoreDiff += Integer.parseInt(name);
    }

    @Override
    public String getLabel() {
        return Integer.toString(this.score + this.scoreDiff);
    }

    @Override
    public void setId(Integer id) {}

    @Override
    public Integer getId() {
        return -10;
    }

    @Override
    public String getType() {return "scoreview";}

    @Override
    public void setPosition(Dimension location) {}

    @Override
    public void setMovement(Dimension delta) {}

    @Override
    public Integer getZOrder() {return 10;}

    @Override
    public boolean isHit(Point location) {return false;}

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
