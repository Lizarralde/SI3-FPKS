package devint.view;

import java.awt.*;

public class QuestionView implements GameObjectView {
    private Integer id;
    private String questionText;

    private Point location;
    // x,y range [-1, 1], relative to size of screen and direction
    private Dimension size;

    public QuestionView(){
        location = new Point(0, 800);
        size = new Dimension(1, 1);
    }

    @Override
    public void paint(Graphics2D g) {
        Rectangle w = g.getClipBounds();
        g.drawRect(location.x, location.y, (int)(size.width * w.getWidth()), (int)(size.height * w.getHeight()));
        g.setFont(new Font("Segoe UI Light", Font.BOLD, 60));
        g.drawString(questionText, location.x + 10, location.y+10);
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
    public boolean isOverlay() {
        return true;
    }

    @Override
    public boolean isHit(Point location) {
        return false;
    }

    @Override
    public void doAnimation(String animation) {
        return;
    }
}
