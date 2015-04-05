import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Created by Zeeg on 02.04.2015.
 */
public class Ball {

    private Image image;
    private int x;
    private int y;
    private int r;
    private int d;
    private double speed;
    public boolean moveUp;
    public boolean moveRight;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public Ball(Image image, int x, int y, int d, double speed) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.d = d;
        this.r = d/2;
        this.speed = speed;
        Random rand = new Random();
        this.moveUp = rand.nextBoolean();
        this.moveRight = rand.nextBoolean();
    }

    public Ball(int x, int y, int d, double speed) {
        //URL url = this.getClass().getClassLoader().getResource("ball.png");

        try {
            BufferedImage sourceImage = ImageIO.read(new FileInputStream("assets/ball.png"));
            this.image = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
        this.x = x;
        this.y = y;
        this.d = d;
        this.r = d/2;
        this.speed = speed;
        Random rand = new Random();
        this.moveUp = rand.nextBoolean();
        this.moveRight = rand.nextBoolean();
    }

    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(image,x,y,d,d,null);

    }

    public void move()
    {
        if (moveUp) this.y--;
        else this.y++;
        if (moveRight) this.x++;
        else this.x--;
    }

    public void reflectHorizontal()
    {
        moveRight = moveRight?false:true;
    }

    public void reflectVerical()
    {
        moveUp = !moveUp;
    }

    public void reset(int x, int y)
    {
        this.x = x;
        this.y = y;
        Random rand = new Random();
        this.moveUp = rand.nextBoolean();
        this.moveRight = rand.nextBoolean();
    }
}
