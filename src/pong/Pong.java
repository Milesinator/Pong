package pong;
//copyright Miles Blue, 2013
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Pong extends JComponent implements MouseMotionListener
{
    JFrame pongField;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    int ballXpos = 100;
    int ballYpos = 100;
    int ballDiameter = 50;
    int ballXSpeed = 3;
    int ballYSpeed = 3;
    int paddleHeight = 100;
    int paddleWidth = 15;
    int paddleXpos = 10;
    int paddleYpos = 100;
    int score = 0;
    Ellipse2D.Double ball;
    Rectangle2D.Double paddle;

    public static void main(String[] args)
    {
        new Pong().getGoing();
    }

    public void getGoing()
    {
        pongField = new JFrame("Let's Play The Best Pong Game Ever!");
        pongField.setVisible(true);
        pongField.setSize(width, height);
        pongField.add(this);
        pongField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pongField.addMouseMotionListener(this);
        while (true)
        {
            repaint();
            if (ballXpos > width - ballDiameter)
            {
                if (ballXSpeed > 0)
                {
                    ballXSpeed = -ballXSpeed;
                }
            }

            if (ballYpos > height - 200 - ballDiameter)
            {
                if (ballYSpeed > 0)
                {
                    ballYSpeed = -ballYSpeed;
                }
            }
            if (ballYpos < 0 - ballDiameter)
            {
                if (ballYSpeed < 0)
                {
                    ballYSpeed = -ballYSpeed;
                }
            }
        }
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        ball = new Ellipse2D.Double(ballXpos, ballYpos, ballDiameter, ballDiameter);
        paddle = new Rectangle2D.Double(paddleXpos, paddleYpos, paddleWidth, paddleHeight);
        g2.setColor(Color.YELLOW);
        g2.fill(ball);
        g2.setColor(Color.MAGENTA);
        g2.draw(ball);
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(8f));
        g2.draw(paddle);
        g2.setColor(Color.GREEN);
        g2.fill(paddle);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Bank Gothic", Font.BOLD, 25));
        g2.drawString("Your score is", width - 500, 100 );
        g2.setFont(new Font("Bank Gothic", Font.BOLD, 50));
        g2.drawString(score + "", width - 300 - 25, 100);
        ballXpos = ballXpos + ballXSpeed;
        ballYpos = ballYpos + ballYSpeed;
        if (ball.intersects(paddle))
        {
            if (ballXSpeed < 0)
            {
                ballXSpeed = -ballXSpeed;
                score = score + 1;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
    }

    @Override
    public void mouseMoved(MouseEvent joe)
    {
        System.out.println(joe.getY());
        paddleYpos = joe.getY();
    }
}
