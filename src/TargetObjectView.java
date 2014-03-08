import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TargetObjectView implements GameObjectView {
    private BufferedImage img;
    private Integer x;
    private Integer y;
    private Integer id;
    private String name;

    public TargetObjectView(){
        try {
            img = ImageIO.read(new File("resources\\\\untitled.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics2D g) {
        g.drawImage(img, null, x, y);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
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
    public String getType(){
        return "target";
    }

    @Override
    public void setPosition(Dimension location) {
        x = location.width;
        y = location.height;
    }

    @Override
    public void setMovement(Dimension delta) {
        x+=delta.width;
        y+=delta.height;
    }
}
