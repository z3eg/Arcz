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
    public boolean automaticMode;
    public boolean mouseFollowingMode;

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
        this.automaticMode = false;
        this.mouseFollowingMode = false;
        this.image = image;
    }

    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(image,x,y,this.width,this.height,null);
    }

    public void followTheBall(Ball ball, int tableWidth)
    {
        mouseFollowingMode = false;
        //if (this.x >= 0 && (this.x+this.width) < tableWidth)
            this.x = ball.getX() + ball.getR() - this.width/2;
    }

    public void followTheMouse(Ball ball, int tableWidth)
    {
        automaticMode = false;
        PointerInfo PI = MouseInfo.getPointerInfo();
        Point pos = PI.getLocation();
        int mouseX = (int) pos.getX();
        //System.out.println("Mouse: " + pos.getX() + ", " + pos.getY());
        if (mouseX >= 0 && mouseX < tableWidth)
        this.x = mouseX - this.width/2;
    }
}
