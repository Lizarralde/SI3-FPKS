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
    private static List<List<BufferedImage>> hitAnimations;

    private static Random random;
    private Boolean doHitAnimation;
    private Integer hitAnimationFrame;
    private Integer hitAnimationIndex;

    private Integer hitAnimationPersistency = 4;

    private Boolean isFlaggedForRemoval;

    static{
        targetImageArray = new LinkedList<>();
        String[] filePaths = {"resources\\\\target1.png", "resources\\\\target2.png", "resources\\\\target3.png", "resources\\\\target4.png"};
        for (String w : filePaths) {
            try {
                targetImageArray.add(ImageIO.read(new File(w)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        hitAnimations = new LinkedList<>();
        List<BufferedImage> hitAnimation;
        List<String[]> hitPathsArray = new LinkedList<String[]>();
        hitPathsArray.add(new String[]{"resources\\\\hitanim1_1.png", "resources\\\\hitanim1_2.png", "resources\\\\hitanim1_3.png", "resources\\\\hitanim1_4.png"});
        hitPathsArray.add(new String[]{"resources\\\\hitanim2_1.png", "resources\\\\hitanim2_2.png", "resources\\\\hitanim2_3.png", "resources\\\\hitanim2_4.png"});
        hitPathsArray.add(new String[]{"resources\\\\hitanim3_1.png", "resources\\\\hitanim3_2.png", "resources\\\\hitanim3_3.png", "resources\\\\hitanim3_4.png"});
        hitPathsArray.add(new String[]{"resources\\\\hitanim4_1.png", "resources\\\\hitanim4_2.png", "resources\\\\hitanim4_3.png", "resources\\\\hitanim4_4.png"});
        for(String[] w : hitPathsArray){
            hitAnimation = new LinkedList<>();
            for (String s : w) {
                try {
                    hitAnimation.add(ImageIO.read(new File(s)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            hitAnimations.add(hitAnimation);
        }
        random = new Random();
    }

    private List<TargetDropListener> listeners;

    public TargetObjectView() {
        this.hitAnimationIndex = random.nextInt(targetImageArray.size());
        this.img = targetImageArray.get(hitAnimationIndex);
        this.x = 0;
        this.y = 0;
        this.isFlaggedForRemoval = false;
        this.listeners = new LinkedList<>();
        this.doHitAnimation = false;
        this.hitAnimationFrame = 0;
        this.updateRectangle();
    }

    private final void updateRectangle() {
        this.targetArea = new Rectangle(x, y, 250, 250);
    }

    @Override
    public void paint(Graphics2D g) {
        if(this.y + 250 > g.getClipBounds().height){
            for(TargetDropListener l : this.listeners){
                l.targetDropped(this);
            }
        }
        if(!this.doHitAnimation){
            g.drawImage(this.img, this.x, this.y, 250, 250, null);
        } else {
            g.drawImage(this.hitAnimations.get(this.hitAnimationIndex).get(hitAnimationFrame), this.x, this.y, 250, 250, null);
            if(this.hitAnimationPersistency == 0){
                if(this.hitAnimationFrame < this.hitAnimations.get(this.hitAnimationIndex).size() - 1){
                    this.hitAnimationFrame++;
                    this.hitAnimationPersistency = 4;
                } else {
                    this.hitAnimationIndex = 0;
                    this.doHitAnimation = false;
                }
            } else {
                this.hitAnimationPersistency--;
            }
        }

        if (this.label != null) {
            Integer f = Math.min(Math.round(250 / this.label.length() * 1.3f), 60);
            g.setFont(new Font("Segoe UI Light", Font.BOLD, f));
            Integer deltaX = Math.round((250-( (f==60?30:f/1.9f) * this.label.length())) /2f);
            Integer deltaY = 140;
            g.setColor(new Color(0xFF, 0xFF, 0xFF));
            g.drawString(this.label, this.x + deltaX, this.y + deltaY);
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
    public Integer getZOrder() {
        return 0;
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

    @Override
    public int compareTo(GameObjectView o) {
        return this.getZOrder() - o.getZOrder();
    }

    public void addTargetDropListener(TargetDropListener listener){
        this.listeners.add(listener);
    }
}
