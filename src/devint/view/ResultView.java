package devint.view;

import java.awt.*;
import java.util.LinkedList;

public class ResultView implements GameObjectView {
    private String label;

    private Integer framePersistency;

    private java.util.List<NextQuestionListener> listeners;

    public ResultView(NextQuestionListener listener){
        this.label = "---";
        this.framePersistency = 20;
        this.listeners = new LinkedList<>();
        this.listeners.add(listener);
    }

    @Override
    public void paint(Graphics2D g) {
        if(this.label.equals("yay")){
            g.setColor(new Color(0xCC, 0x00, 0xCC, 0x02 * this.framePersistency));
            if(this.framePersistency == 1){
                for(NextQuestionListener l : this.listeners){
                    l.onNextQuestion();
                }
            }
        } else {
            g.setColor(new Color(0x88, 0x88, 0x88, 0x0A * this.framePersistency));
        }
        if(this.framePersistency > 0){
            this.framePersistency--;
        }
        g.fill(g.getClip());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Segoe UI", Font.BOLD, 50));
        g.drawString(this.label, 400, 250);
    }

    @Override
    public void setLabel(String name) {
        this.label = name;
        if(this.label.equals("yay")){
            this.framePersistency = 100;
        }
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
    public Boolean isFlaggedForRemoval() {return this.framePersistency <= 0;}

    @Override
    public Boolean isAnimationPending() {return Boolean.FALSE;}

    @Override
    public void flagForRemoval() {}

    @Override
    public int compareTo(GameObjectView o) {
        return this.getZOrder() - o.getZOrder();
    }

    public void addNextQuestionListener(NextQuestionListener listener){
        this.listeners.add(listener);
    }
}
