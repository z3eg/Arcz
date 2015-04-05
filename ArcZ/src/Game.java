import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Created by Zeeg on 02.04.2015.
 */
public class Game extends Canvas implements Runnable {

    private Font gameFont = new Font("Century Gothic", Font.BOLD, 32);
    public static int WIDTH = 400;
    public static int HEIGHT = 640;
    public static int X_CENTER = WIDTH/2;
    public static int Y_CENTER = HEIGHT/2;
    public static int BOTTOM_BORDER = HEIGHT-HEIGHT/40;
    public static int TOP_BORDER = HEIGHT/40;
    public static String NAME = "ArcZ";
    private boolean running;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean aPressed = false;
    private boolean dPressed = false;

   /* private static int ballX = 50;
    private static int ballY = 50;*/

    private Ball ball = new Ball(X_CENTER,Y_CENTER,25,0.25);
    private Racket Player1 = new Racket(100,10,X_CENTER,BOTTOM_BORDER,20);
//    private Racket Player2 = new Racket(100,10,X_CENTER,0,20);
    private Racket Player2 = new Racket(100,10,X_CENTER,TOP_BORDER,20);

    public int[] goalsScored = {0,0};
    //public int secondScored;

    private class KeyInputHandler extends KeyAdapter{

        public void keyPressed(KeyEvent e) { //клавиша нажата
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                dPressed = true;
            }
            //logging
            if (e.getKeyCode() == KeyEvent.VK_L) {
                System.out.println("Ball: " + ball.getX() + ", " + ball.getY());
                System.out.println("Player1: " + Player1.x + ", " +Player1.y);
                System.out.println("Player2: " + Player2.x + ", " +Player2.y);
            }
        }
        public void keyReleased(KeyEvent e) { //клавиша отпущена
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                dPressed = false;
            }
        }

    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        init();

        while (running)  {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            update(delta);
            render();
        }
    }

    public void start()
    {
        running = true;
        new Thread(this).start();
    }

    public void init()  {
        addKeyListener(new KeyInputHandler());
        goalsScored[0]=0;
        goalsScored[1]=0;
    }

    public void render()    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.lightGray);
        g.setFont(gameFont);
        g.fillRect(0,0, getWidth(), getHeight());

        ball.draw(g, ball.getX(), ball.getY());
        Player1.draw(g,Player1.x,Player1.y);
        Player2.draw(g,Player2.x,Player2.y);
        g.setColor(Color.black);
        g.drawString("TOP:", X_CENTER - 130, Y_CENTER - 30);
        g.drawString("BOTTOM:", X_CENTER - 10, Y_CENTER - 30);
        g.drawString(Integer.toString(goalsScored[1]), X_CENTER - 110, Y_CENTER + 30);
        g.drawString(Integer.toString(goalsScored[0]), X_CENTER + 40, Y_CENTER + 30);
        g.dispose();
        bs.show();
    }

    public void update(long delta)    {
        if (leftPressed == true && Player1.x>0) {
            Player1.x--;
        }
        if (rightPressed == true && (Player1.x + Player1.getWidth()*0.75)<WIDTH) {
            Player1.x++;
        }
        if (aPressed == true && Player2.x>0) {
            Player2.x--;
        }
        if (dPressed == true && (Player2.x + Player2.getWidth()*0.75)<WIDTH) {
            Player2.x++;
        }
        if (ball.getX() >= WIDTH )
            ball.moveRight = false;
        if (ball.getX() <= 0)
            ball.moveRight = true;
        if (ball.getY() >= HEIGHT)
        {
            scoreGoal(2);
        }
        if (ball.getY() <= 0)
            scoreGoal(1);
        if ((ball.getX()+ball.getR())  > Player1.x && (ball.getX()+ball.getR()) < (Player1.x+Player1.getWidth()) && (ball.getY()+ball.getD()) == Player1.y)
            ball.moveUp = true;
        if ((ball.getX()+ball.getR())  > Player2.x && (ball.getX()+ball.getR())  < (Player2.x+Player2.getWidth()) && ball.getY() == (Player2.y+Player2.getHeight()))
            ball.moveUp = false;
        if (System.currentTimeMillis()%(1/ball.getSpeed())==0)
            ball.move();

    }

    public void scoreGoal(int player)
    {
        ball.reset(X_CENTER,Y_CENTER);
        goalsScored[player-1]++;
        System.out.println("==========================");
        System.out.println("TOP: " +goalsScored[0]);
        System.out.println("BOTTOM: " +goalsScored[1]);
        System.out.println("==========================");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        JFrame frame = new JFrame(Game.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        game.start();
    }
}
