package devint.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class AimObjectView implements GameObjectView {
    private Image aimImage;
    private Point imageCorrection;
    private Point location;

    private Boolean doFireAnimation;
    private List<Image> fireAnimation;
    private Integer fireAnimationCurrentFrame;

    public AimObjectView(){
        try {
            imageCorrection = new Point(-385, -63);
            aimImage = ImageIO.read(new File("resources\\\\aim.png"));
            location = new Point();

            doFireAnimation = false;
            fireAnimation = new LinkedList<>();
            fireAnimation.add(ImageIO.read(new File("resources\\\\fire1.png")));
            fireAnimation.add(ImageIO.read(new File("resources\\\\fire2.png")));
            fireAnimation.add(ImageIO.read(new File("resources\\\\fire3.png")));
            fireAnimation.add(ImageIO.read(new File("resources\\\\fire4.png")));
            fireAnimation.add(ImageIO.read(new File("resources\\\\fire5.png")));
            fireAnimationCurrentFrame = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics2D g) {
        Point w = new Point(location.x + imageCorrection.x, location.y + imageCorrection.y);
        if(!this.doFireAnimation){
            g.drawImage(aimImage, w.x, w.y, null);
        } else {
            g.drawImage(fireAnimation.get(fireAnimationCurrentFrame), w.x, w.y, null);
            if(fireAnimationCurrentFrame == fireAnimation.size() - 1){
                fireAnimationCurrentFrame = 0;
                doFireAnimation = false;
            } else {
                fireAnimationCurrentFrame++;
            }
        }
    }

    @Override
    public void setLabel(String name) {

    }

    @Override
    public String getLabel() {
        return "crosshair";
    }

    @Override
    public void setId(Integer id) {

    }

    @Override
    public Integer getId() {
        return -1;
    }

    @Override
    public String getType() {
        return "ironsight";
    }

    @Override
    public void setPosition(Dimension location) {
        this.location.setLocation(location.width, location.height);
    }

    @Override
    public void setMovement(Dimension delta) {
        this.location.setLocation(this.location.x + delta.width, this.location.y + delta.height);
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
        this.doFireAnimation = true;
    }

    @Override
    public Boolean isFlaggedForRemoval() {
        return false;
    }

    @Override
    public Boolean isAnimationPending() {
        return this.doFireAnimation;
    }

    @Override
    public void flagForRemoval() {
        return;
    }
}
