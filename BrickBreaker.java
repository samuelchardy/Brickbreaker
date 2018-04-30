import java.lang.Math.*;
import javax.swing.*;

public class BrickBreaker
{

    private GameArena g = new GameArena(300,450);
    private Line arrow = new Line(150,450,150,410,2,"WHITE");
    private Projectile[] balls = new Projectile[20];
    private Rectangle[][] bricks = new Rectangle[2][10];
    private int i = 0;


    boolean isInside(double xPos, double yPos)
    {
        for(int i=0; i<bricks.length; i++){
            for(int c=0; c<bricks[i].length; c++){
                if( (xPos < (bricks[i][c].getXPosition() + bricks[i][c].getWidth()/2)) && (xPos > (bricks[i][c].getXPosition() - bricks[i][c].getWidth())) ){
                    if( (yPos > bricks[i][c].getYPosition()) && (yPos < (bricks[i][c].getYPosition() + bricks[i][c].getHeight()/2)) ){
                        System.out.println("i: " + i + "  c: " + c);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void collisionDetection(Projectile ball)
    {
        Ball b = ball.getBall();
        if(b.getXPosition() >= 300){
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


    void movement(int i)
    {
        for(int c=0; c<i; c++){
            Ball b = balls[c].getBall();
            collisionDetection(balls[c]);
            b.setXPosition(b.getXPosition()+balls[c].getxDirection());
            b.setYPosition(b.getYPosition()+balls[c].getyDirection());
        }
    }


    void input()
    {
        int resistance = 5;

        if(g.rightPressed() == true){
            if(arrow.getEndX() >= 150){
                arrow.setEnd(arrow.getEndX()+1, arrow.getEndY()+1);
            }else{
                arrow.setEnd(arrow.getEndX()+1, arrow.getEndY()-1);
            }
        }else if(g.leftPressed() == true){
            if(arrow.getEndX() <= 150){
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
                i++;
            }
        }
        arrow.setStart(arrow.getStartX(), arrow.getStartY());
        movement(i);
    }


    void startUp()
    {
        while(true){
            input();
            g.update();
        }
    }

    void initRectangles()
    {
        for(int v=0; v<bricks.length; v++){
            for(int c=0; c<bricks[v].length; c++){
                int xPos = (c*29)+20;
                int yPos = (v*25)+15;
                bricks[v][c] = new Rectangle(xPos, yPos, 27, 20, "WHITE");
                g.addRectangle(bricks[v][c]);
            }
        }
    }


    public BrickBreaker()
    {
        g.addLine(arrow);
        initRectangles();
        g.update();

        startUp();
    }


   public static void main(String args[])
   {

   }






}
