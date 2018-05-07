import java.lang.Math.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class BrickBreaker
{
    private GameArena g = new GameArena(675,450);
    private JFrame frame = g.getWindow();
    private JPanel layoutPanel = new JPanel();

    private Menu m = new Menu();
    private Line arrow = new Line(287,450,287,410,4,"WHITE");
    private Ball[] balls = new Ball[50];
    private Rectangle[][] bricks = new Rectangle[6][10];
    private Text[][] text = new Text[6][10];
    private Text levelOver = new Text("LEVEL FAILED!", 100, 100,50,"WHITE");

    private int i = 0;


    private boolean isInside(double xPos, double yPos)
    {
        for(int i=0; i<bricks.length; i++){
            for(int c=0; c<bricks[i].length; c++){
                if( (xPos < (bricks[i][c].getXPosition() + bricks[i][c].getWidth()/2)) && (xPos > (bricks[i][c].getXPosition() - bricks[i][c].getWidth()/2)) ){
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


    private void collisionDetection(Ball ball)
    {
        if(ball.getXPosition() >= 575){
            ball.setxDirection(-1 * ball.getxDirection());
        }else if(ball.getXPosition() <= 0){
            ball.setxDirection(-1 * ball.getxDirection());
        }

        if(ball.getYPosition()+ball.getyDirection() <= 0){
            ball.setyDirection(Math.abs(ball.getyDirection()));
        }

        if(isInside(ball.getXPosition()+ball.getxDirection(), ball.getYPosition()) == true){
            ball.setxDirection(-1 * ball.getxDirection());
        }
        if(isInside(ball.getXPosition(), ball.getYPosition()+ball.getyDirection()) == true){
            ball.setyDirection(-1 * ball.getyDirection());
        }

        if(ball.getYPosition() < 0){
            g.removeBall(ball);
        }

    }


    private void movement(int i)
    {
        for(int c=0; c<i; c++){
            collisionDetection(balls[c]);
            balls[c].setXPosition(balls[c].getXPosition()+balls[c].getxDirection());
            balls[c].setYPosition(balls[c].getYPosition()+balls[c].getyDirection());
        }
    }


    private void input()
    {
        int resistance = 5;

        if(g.rightPressed() == true){
            if(arrow.getEndX() >= 287){
                arrow.setEnd(arrow.getEndX()+1, arrow.getEndY()+1);
            }else{
                arrow.setEnd(arrow.getEndX()+1, arrow.getEndY()-1);
            }
        }else if(g.leftPressed() == true){
            if(arrow.getEndX() <= 287){
                arrow.setEnd(arrow.getEndX()-1, arrow.getEndY()+1);
            }else{
                arrow.setEnd(arrow.getEndX()-1, arrow.getEndY()-1);
            }
        }else if(g.upPressed() == true){
            if(i < balls.length){
                balls[i] = new Ball(arrow.getEndX(), arrow.getEndY(), 3, "RED");
                balls[i].setxDirection((arrow.getEndX() - arrow.getStartX())/resistance);
                balls[i].setyDirection((arrow.getEndY() - arrow.getStartY())/resistance);
                g.addBall(balls[i]);
                //System.out.println("xDir: " + balls[i].getxDirection() + "   yDir: " + balls[i].getyDirection());
                m.decrementBalls();
                i++;
            }
        }
        arrow.setStart(arrow.getStartX(), arrow.getStartY());
        movement(i);
    }


    private void gameLoop()
    {
        while(true){
            if(i == 50){
                if(roundOver()==1){
                    if(m.getRound() == 5){
                        g.addText(levelOver);
                        levelOver();
                        i = 0;
                    }else{
                        nextRound();
                        i = 0;
                    }
                }
            }

            if(m.getLevel() == 4){
                m.menuPanel();
            }

            if(m.getLevel() == 0){
                input();
            }

            if(m.getLevel() == 1)
            {
                m.setLevel(0);
                initLevelOne();
            }
            g.update();
        }
    }


    private void levelOver()
    {
        for(int v=0; v<bricks.length; v++){
            for(int c=0; c<bricks[v].length; c++){
                g.removeRectangle(bricks[v][c]);
                g.removeText(text[v][c]);
            }
        }

        for(int i=0; i<balls.length; i++){
            g.removeBall(balls[i]);
        }

        i = 0;
        m.resetBallCount();
        m.setRound(1);
        m.setLevel(4);
    }


    private int roundOver()
    {
        for(int i=0; i<balls.length; i++){
            if(balls[i].getYPosition() > 0 && balls[i].getYPosition() < 450){
                return 0;
            }
        }
        return 1;
    }


    private void nextRound()
    {
        for(int v=0; v<bricks.length; v++){
            for(int c=0; c<bricks[v].length; c++){
                bricks[v][c].setYPosition(bricks[v][c].getYPosition() + bricks[v][c].getHeight()+5);
                text[v][c].setYPosition(text[v][c].getYPosition() + bricks[v][c].getHeight()+5);
            }
        }
        m.incrementRound();
        m.resetBallCount();
    }


    private void initLevelOne()
    {
        g.removeText(levelOver);

        for(int v=0; v<bricks.length; v++){
            for(int c=0; c<bricks[v].length; c++){
                int yPos;
                if(v < bricks.length/2){
                    yPos = -1 * ((v*25)+10);
                }else{
                    yPos = ((v-bricks.length/2) * 25)+15;
                }

                int xPos = ((575/10) * (c+1))-20;
                bricks[v][c] = new Rectangle(xPos, yPos, 27, 20, "WHITE");
                text[v][c] = new Text("20",xPos-6,yPos+4,10,"RED");
                g.addRectangle(bricks[v][c]);
                g.addText(text[v][c]);
            }
        }
    }


    public BrickBreaker()
    {
        frame.setLayout(new BorderLayout());
        JPanel panel = m.initJComponents();
        layoutPanel.add(panel);
        frame.add(layoutPanel,BorderLayout.EAST);

        g.addLine(arrow);
        gameLoop();
    }


   public static void main(String args[])
   {

   }

}
