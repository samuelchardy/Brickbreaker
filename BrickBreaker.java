import java.lang.Math.*;

public class BrickBreaker
{

    void input(GameArena g, Line arrow, Ball[] balls)
    {
        int i = 0;
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
                int xDir = Math.abs((int)(arrow.getEndX() - arrow.getStartX()));
                int yDir = Math.abs((int)(arrow.getEndY() - arrow.getStartY()));
                if(i < balls.length){
                    balls[i] = new Ball(arrow.getEndX(), arrow.getEndY(), 3, "RED");
                    g.addBall(balls[i]);
                }
                i++;
            }
            arrow.setStart(arrow.getStartX(), arrow.getStartY());
            g.update();
        }

    }


   public static void main(String args[])
   {
       BrickBreaker b = new BrickBreaker();

       GameArena g = new GameArena(300,450);
       Line arrow = new Line(150,450,150,410,2,"WHITE");
       Ball[] balls = new Ball[20];
       //Rectangle[][] bricks = new Rectangle[2][10];

       g.addLine(arrow);
       //b.initRectangles(g, bricks);
       g.update();



       b.input(g, arrow, balls);
   }






}
