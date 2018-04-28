import java.lang.Math.*;

public class BrickBreaker
{




    void movement(int i, Projectile[] balls)
    {
        for(int c=0; c<i; c++){
            Ball b = balls[c].getBall();
            if(b.getXPosition() < 150){
                b.setXPosition(b.getXPosition()-balls[c].getxDirection());
            }else{
                b.setXPosition(b.getXPosition()+balls[c].getxDirection());
            }
            b.setYPosition(b.getYPosition()-balls[c].getyDirection());
            //System.out.println("c: " + c + "   x speed: " + ballSpeeds[c] + "   y speed: " + ballSpeeds[c+1]);
        }
    }


    void input(GameArena g, Line arrow, Projectile[] balls)
    {
        int i = 0;
        int resistance = 5;
        while(true){
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

                    balls[i].setxDirection(Math.abs((int)(arrow.getEndX() - arrow.getStartX()))/resistance);
                    balls[i].setyDirection(Math.abs((int)(arrow.getEndY() - arrow.getStartY()))/resistance);
                    i++;
                }
            }
            arrow.setStart(arrow.getStartX(), arrow.getStartY());

            movement(i, balls);
            g.update();
        }

    }

    void initRectangles(GameArena g, Rectangle[][] bricks)
    {
        for(int i=0; i<bricks.length; i++){
            for(int c=0; c<bricks[i].length; c++){
                int xPos = (c*29)+20;
                int yPos = (i*25)+15;
                bricks[i][c] = new Rectangle(xPos, yPos, 27, 20, "WHITE");
                g.addRectangle(bricks[i][c]);
            }
        }
    }


   public static void main(String args[])
   {
       BrickBreaker b = new BrickBreaker();

       GameArena g = new GameArena(300,450);
       Line arrow = new Line(150,450,150,410,2,"WHITE");
       Projectile[] balls = new Projectile[20];
       Rectangle[][] bricks = new Rectangle[2][10];

       g.addLine(arrow);
       b.initRectangles(g, bricks);
       g.update();

       //Projectile p = new Projectile(150,150,10,"RED",g);
       //p.getBall().setYPosition(400);

       b.input(g, arrow, balls);
   }






}
