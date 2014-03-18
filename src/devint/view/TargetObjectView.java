package devint.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class TargetObjectView implements GameObjectView {
    private BufferedImage img;
    private Integer x;
    private Integer y;
    private Integer id;
    private String label;

    private Rectangle targetArea;

    private static java.util.List<BufferedImage> targetImageArray;
    private static Random random;

    static{
        targetImageArray = new LinkedList<>();
        targetImageArray.add(ImageIO.read(new File()));
        random = new Random();
    }

    public TargetObjectView() {
        img = targetImageArray.get(random.nextInt(targetImageArray.size()));
        x = 0;
        y = 0;
        updateRectangle();
    }

    private final void updateRectangle() {
        this.targetArea = new Rectangle(x, y, x + img.getWidth(), y + img.getHeight());
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(img, null, x, y);
        if (label != null) {
            g.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
            g.setColor(new Color(0x88, 0x00, 0x88));
            Integer deltaX = (img.getWidth() - (30*label.length())) /2;
            Integer deltaY = (img.getHeight() - (30 * label.length())) /2;
            g.drawString(label, x + deltaX, y + deltaY);
        }
    }

    @Override
    public void setLabel(String s) {
        this.label = s;
    }

    @Override
    public String getLabel() {
        return label;
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
        return "target";
    }

    @Override
    public void setPosition(Dimension location) {
        x = location.width;
        y = location.height;
        updateRectangle();
    }

    @Override
    public void setMovement(Dimension delta) {
        x += delta.width;
        y += delta.height;
        updateRectangle();
    }

    @Override
    public boolean isOverlay() {
        return false;
    }

    @Override
    public boolean isHit(Point location) {
        return this.targetArea.contains(location);
    }

    @Override
    public void doAnimation(String animation) {
        return;
    }
}
