package jeu.view;

import jeu.KeysKeeper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BulletImpactObjectView implements GameObjectView {
    private Integer id;
    private Integer x;
    private Integer y;
    private BufferedImage image;

    private static final String RES_IMG = KeysKeeper.PATH_RESSOURCES + KeysKeeper.PATH_IMG;

    public BulletImpactObjectView(){
        try {
            this.image = ImageIO.read(new File(RES_IMG + "bulletimpact.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(this.image, this.x, this.y, 16, 16, null);
    }

    @Override
    public void setLabel(String name) {}

    @Override
    public String getLabel() {return "bullet" + this.id;}

    @Override
    public void setId(Integer id) {this.id = id;}

    @Override
    public Integer getId() {return this.id;}

    @Override
    public String getType() {return "BulletImpact";}

    @Override
    public void setPosition(Dimension location) {
        this.x = location.width - 8;
        this.y = location.height - 8;
    }

    @Override
    public void setMovement(Dimension delta) {}

    @Override
    public Integer getZOrder() {
        return -1;
    }

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
