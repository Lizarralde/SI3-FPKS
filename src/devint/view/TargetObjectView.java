package devint.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TargetObjectView implements GameObjectView {
    private BufferedImage img;
    private Integer x;
    private Integer y;
    private Integer id;
    private String label;

    private Rectangle targetArea;

    private static List<BufferedImage> targetImageArray;
    private static Random random;

    private Boolean doHitAnimation;
    private Integer hitAnimationFrame;
    private Integer hitAnimationIndex;
    private static List<List<BufferedImage>> hitAnimations;

    private Boolean isFlaggedForRemoval;

    static{
        targetImageArray = new LinkedList<>();
        String[] filePaths = {"resources\\\\target1.jpg", "resources\\\\target2.jpg", "resources\\\\target3.jpg"};
        for (String w : filePaths) {
            try {
                targetImageArray.add(ImageIO.read(new File(w)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        hitAnimations = new LinkedList<>();
        List<BufferedImage> hitAnimation = null;
        List<String[]> hitPathsArray = new LinkedList<String[]>();
        for (String w : filePaths) {
            hitAnimation = new LinkedList<>();
            try {
                hitAnimation.add(ImageIO.read(new File(w)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        hitAnimations.add(hitAnimation);
        random = new Random();
    }

    public TargetObjectView() {
        this.img = targetImageArray.get(random.nextInt(targetImageArray.size()));
        this.x = 0;
        this.y = 0;
        this.isFlaggedForRemoval = false;
        this.doHitAnimation = false;
        this.hitAnimationFrame = 0;
        this.updateRectangle();
    }

    private final void updateRectangle() {
        this.targetArea = new Rectangle(x, y, x + 250, y + 250);
    }

    @Override
    public void paint(Graphics2D g) {
        if(!this.doHitAnimation){
            g.drawImage(this.img, null, this.x, this.y);
        } else {
            g.drawImage(this.hitAnimations.get(this.hitAnimationIndex).get(hitAnimationFrame), null, this.x, this.y);
            if(hitAnimationIndex < this.hitAnimations.get(this.hitAnimationIndex).size() - 1){
                
            }
        }

        if (this.label != null) {
            g.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
            g.setColor(new Color(0x88, 0x00, 0x88));
            Integer deltaX = (30*this.label.length()) /2;
            Integer deltaY = (30 * this.label.length()) /2;
            g.drawString(this.label, x + deltaX, y + deltaY);
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
        if(animation.equals("hit")){
            doHitAnimation = true;
        }
    }

    @Override
    public Boolean isFlaggedForRemoval() {
        return isFlaggedForRemoval;
    }

    @Override
    public Boolean isAnimationPending() {
        return doHitAnimation;
    }

    @Override
    public void flagForRemoval() {
        this.isFlaggedForRemoval = true;
    }
}
