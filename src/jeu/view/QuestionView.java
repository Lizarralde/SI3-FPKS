package jeu.view;

import java.awt.*;

public class QuestionView implements GameObjectView {
    private Integer id;
    private String questionText;

    // x,y range [-1, 1], relative to size of screen and direction
    private Dimension size;
    private Boolean isFlaggedForRemoval;

    public QuestionView(){
        this.size = new Dimension(1, 1);
        this.isFlaggedForRemoval = false;
    }

    @Override
    public void paint(Graphics2D g) {
        Rectangle w = g.getClipBounds();
        g.setColor(new Color(0xFF, 0xFF, 0xFF));
        g.fillRect(0, Math.round((float)w.getHeight() - 100), Math.round((float)size.width * (float)w.getWidth()), Math.round((float)size.height * (float)w.getHeight()));
        Integer f = Math.min(Math.round(w.width / questionText.length()) * 2, 75);
        g.setFont(new Font("Segoe UI Light", Font.BOLD, f));
        g.setColor(new Color(0x00, 0x00, 0x00));
        g.drawString(questionText, 70, (float)w.getHeight() - 40);
    }

    @Override
    public void setLabel(String name) {
        questionText = name;
    }

    @Override
    public String getLabel() {
        return questionText;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getType() {
        return "question";
    }

    @Override
    public void setPosition(Dimension location) {
        return;
    }

    @Override
    public void setMovement(Dimension delta) {
        return;
    }

    @Override
    public Integer getZOrder() {
        return 1;
    }
    @Override
    public boolean isHit(Point location) {
        return false;
    }

    @Override
    public void doAnimation(String animation) {return;}

    @Override
    public Boolean isFlaggedForRemoval() {return this.isFlaggedForRemoval;}

    @Override
    public Boolean isAnimationPending() {return Boolean.FALSE;}

    @Override
    public void flagForRemoval() {}

    @Override
    public int compareTo(GameObjectView o) {
        return this.getZOrder() - o.getZOrder();
    }
}
