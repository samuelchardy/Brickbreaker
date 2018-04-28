import java.lang.Math.*;

public class BrickBreaker
{

    void input(GameArena g, Line arrow, Ball[] balls)
    {
        int i = 0;
        double[] ballSpeeds = new double[(balls.length)*2];
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
                    balls[i] = new Ball(arrow.getEndX(), arrow.getEndY(), 3, "RED");
                    g.addBall(balls[i]);

                    ballSpeeds[i*2] = Math.abs((int)(arrow.getEndX() - arrow.getStartX()));
                    ballSpeeds[(i*2)+1] = Math.abs((int)(arrow.getEndY() - arrow.getStartY()));
                    i++;
                }
            }
            arrow.setStart(arrow.getStartX(), arrow.getStartY());

            for(int c=0; c<i; c++){
                if(balls[c].getXPosition() < 150){
                    balls[c].setXPosition(balls[c].getXPosition()-ballSpeeds[c*2]);
                }else{
                    balls[c].setXPosition(balls[c].getXPosition()+ballSpeeds[c*2]);
                }
                balls[c].setYPosition(balls[c].getYPosition()-ballSpeeds[(c*2)+1]);
                //System.out.println("c: " + c + "   x speed: " + ballSpeeds[c] + "   y speed: " + ballSpeeds[c+1]);
            }



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
       Ball[] balls = new Ball[20];
       Rectangle[][] bricks = new Rectangle[2][10];

       g.addLine(arrow);
       b.initRectangles(g, bricks);
       g.update();



       b.input(g, arrow, balls);
   }






}
