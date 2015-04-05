import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Zeeg on 02.04.2015.
 */
public class Racket {

    private int width;
    private int height;
    public int x;
    public int y;
    private double speed;
    private Image image;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Racket() {

    }

    public Racket(int width, int height, int x, int y, double speed) {
        try {
            BufferedImage sourceImage = ImageIO.read(new FileInputStream("assets/racket.png"));
            this.image = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = image;
    }

    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(image,x,y,width,height,null);

    }
}
