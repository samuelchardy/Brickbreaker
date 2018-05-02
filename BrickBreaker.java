import java.lang.Math.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class BrickBreaker
{
    private GameArena g = new GameArena(675,450);
    private JFrame frame = g.getWindow();
    private JPanel layoutPanel = new JPanel();

    private Menu m = new Menu();
    private Line arrow = new Line(225,450,225,410,2,"WHITE");
    private Projectile[] balls = new Projectile[50];
    private Rectangle[][] bricks = new Rectangle[2][10];
    private Text[][] text = new Text[2][10];

    private int i = 0;


    private boolean isInside(double xPos, double yPos)
    {
        for(int i=0; i<bricks.length; i++){
            for(int c=0; c<bricks[i].length; c++){
                if( (xPos < (bricks[i][c].getXPosition() + bricks[i][c].getWidth()/2)) && (xPos > (bricks[i][c].getXPosition() - bricks[i][c].getWidth())) ){
                    if( (yPos > bricks[i][c].getYPosition()-bricks[i][c].getHeight()/2) && (yPos < (bricks[i][c].getYPosition() + bricks[i][c].getHeight()/2)) ){
                        if(! text[i][c].getText().equals("1")){
                            g.removeText(text[i][c]);
                            int textNum = Integer.parseInt(text[i][c].getText()) - 1;
                            Text newText = new Text(Integer.toString(textNum), text[i][c].getXPosition(), text[i][c].getYPosition(), 10, "RED");
                            g.removeText(text[i][c]);
                            text[i][c] = newText;
                            g.addText(text[i][c]);
                            return true;
                        }else{
                            g.removeText(text[i][c]);
                            g.removeRectangle(bricks[i][c]);
                        }
                    }
                }
            }
        }
        return false;
    }


    private void collisionDetection(Projectile ball)
    {
        Ball b = ball.getBall();
        if(b.getXPosition() >= 450){
            ball.setxDirection(-1 * ball.getxDirection());
        }else if(b.getXPosition() <= 0){
            ball.setxDirection(-1 * ball.getxDirection());
        }

        if(b.getYPosition() <= 0){
            ball.setyDirection(-1 * ball.getyDirection());
        }

        if(isInside(b.getXPosition()+ball.getxDirection(), b.getYPosition()) == true){
            ball.setxDirection(-1 * ball.getxDirection());
        }
        if(isInside(b.getXPosition(), b.getYPosition()+ball.getyDirection()) == true){
            ball.setyDirection(-1 * ball.getyDirection());
        }

    }


    private void movement(int i)
    {
        for(int c=0; c<i; c++){
            Ball b = balls[c].getBall();
            collisionDetection(balls[c]);
            b.setXPosition(b.getXPosition()+balls[c].getxDirection());
            b.setYPosition(b.getYPosition()+balls[c].getyDirection());
        }
    }


    private void input()
    {
        int resistance = 5;

        if(g.rightPressed() == true){
            if(arrow.getEndX() >= 225){
                arrow.setEnd(arrow.getEndX()+1, arrow.getEndY()+1);
            }else{
                arrow.setEnd(arrow.getEndX()+1, arrow.getEndY()-1);
            }
        }else if(g.leftPressed() == true){
            if(arrow.getEndX() <= 225){
                arrow.setEnd(arrow.getEndX()-1, arrow.getEndY()+1);
            }else{
                arrow.setEnd(arrow.getEndX()-1, arrow.getEndY()-1);
            }
        }else if(g.upPressed() == true){
            if(i < balls.length){
                balls[i] = new Projectile(arrow.getEndX(), arrow.getEndY(), 3, "RED",g);

                balls[i].setxDirection((arrow.getEndX() - arrow.getStartX())/resistance);
                balls[i].setyDirection((arrow.getEndY() - arrow.getStartY())/resistance);
                //System.out.println("xDir: " + balls[i].getxDirection() + "   yDir: " + balls[i].getyDirection());
                m.decrementBalls();
                i++;
            }
        }
        arrow.setStart(arrow.getStartX(), arrow.getStartY());
        movement(i);
    }


    private void startUp()
    {
        while(true){
            input();
            g.update();
        }
    }

    private void initRectangles()
    {
        for(int v=0; v<bricks.length; v++){
            for(int c=0; c<bricks[v].length; c++){
                int xPos = (c*29)+20;
                int yPos = (v*25)+15;
                bricks[v][c] = new Rectangle(xPos, yPos, 27, 20, "WHITE");
                text[v][c] = new Text("20",xPos-6,yPos+4,10,"RED");
                g.addRectangle(bricks[v][c]);
                g.addText(text[v][c]);
            }
        }
    }


    public void changePanel()
    {

    }


    public BrickBreaker()
    {
        frame.setLayout(new BorderLayout());
        JPanel panel = m.initJComponents();
        layoutPanel.add(panel);
        frame.add(layoutPanel,BorderLayout.EAST);

        g.addLine(arrow);
        initRectangles();
        g.update();

        startUp();
    }


   public static void main(String args[])
   {

   }






}
