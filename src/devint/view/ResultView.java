package devint.view;

import java.awt.*;
import java.util.LinkedList;

public class ResultView implements GameObjectView {
    private String label;
    private Boolean isFailGameOver;

    private Integer framePersistency;

    private java.util.List<NextQuestionListener> listeners;

    public ResultView(){
        this.label = "---";
        this.framePersistency = 20;
        this.listeners = new LinkedList<>();
        this.isFailGameOver = false;
    }

    public ResultView(NextQuestionListener listener){
        this.label = "---";
        this.framePersistency = 20;
        this.listeners = new LinkedList<>();
        this.listeners.add(listener);
        this.isFailGameOver = false;
    }

    @Override
    public void paint(Graphics2D g) {
        if(this.label.equals("Mauvaise réponse")){
            g.setColor(new Color(0x88, 0x00, 000, 0x0A * this.framePersistency));
        } else if(this.isFailGameOver){
            g.setColor(new Color(0x88, 0x00, 000, 0xFF));
        } else {
            g.setColor(new Color(0x00, 0xDD, 0x00, 0xFF));
            if(this.framePersistency == 1){
                for(NextQuestionListener l : this.listeners){
                    l.onNextQuestion();
                }
            }
        }
        if(this.framePersistency > 0){
            this.framePersistency--;
        }
        g.fill(g.getClip());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 120));
        g.drawString(this.label.toUpperCase(), g.getClipBounds().width / 2 - (this.label.length() * 39),  g.getClipBounds().height / 2 - 30);
    }

    @Override
    public void setLabel(String name) {
        this.label = name;
        if(this.label.equals("Bonne réponse")){
            this.framePersistency = 10000;
        } else if(this.label.equals("Partie terminée")){
            this.framePersistency = 10000;
        }
    }

    @Override
    public String getLabel() {return this.label;}

    @Override
    public void setId(Integer id) {this.isFailGameOver = id == -11;}

    @Override
    public Integer getId() {return -10;}

    @Override
    public String getType() {return "result";}

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
