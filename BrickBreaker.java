import java.lang.Math.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BrickBreaker
{
    private GameArena g = new GameArena(675,450);
    private JFrame frame = g.getWindow();
    private JPanel layoutPanel = new JPanel();

    private Menu m = new Menu();
    private Line arrow = new Line(287,450,287,410,4,"WHITE");
    private Ball[] balls = new Ball[50];
    private Ball[] dupBalls = new Ball[100];
    private Rectangle[][] bricks = new Rectangle[6][10];
    private Text[][] text = new Text[6][10];
    private Text levelOver = new Text("LEVEL FAILED!", 100, 100,50,"WHITE");

    private Random rand = new Random();
    private int i = 0, maxBalls = 50;
    private int rowOfSpecial;
    private int colOfSpecial;


    /**
     * Checks if a the coordinates passed in (of a ball) are inside any existing brick, and if so determines which aciton must be taken.
     * @param ball A instance of the Ball class.
     * @param xPos X coordinate within the gameArena.
     * @param yPos Y coordinate within the gameArena.
     **/
    private boolean isInside(Ball ball, double xPos, double yPos)
    {
        for(int i=0; i<bricks.length; i++){
            for(int c=0; c<bricks[i].length; c++){
                if( (xPos < (bricks[i][c].getXPosition() + bricks[i][c].getWidth()/2)) && (xPos > (bricks[i][c].getXPosition() - bricks[i][c].getWidth()/2)) ){
                    if( (yPos > bricks[i][c].getYPosition() -  bricks[i][c].getHeight()/2) && (yPos < (bricks[i][c].getYPosition() + bricks[i][c].getHeight()/2)) ){
                        if(text[i][c].getText().equals("^")){
                            int posOrNeg = rand.nextInt(10);
                            int newXDir = rand.nextInt(16);
                            int newYDir = rand.nextInt(10)-10;

                            if(posOrNeg % 2 == 0){
                                newXDir = -newXDir;
                            }
                            ball.setxDirection(newXDir);
                            ball.setyDirection(newYDir);
                        }else if(text[i][c].getText().equals("!")){
                            g.removeText(text[i][c]);
                            g.removeRectangle(bricks[i][c]);
                            triggerLaser();
                        }else if(! text[i][c].getText().equals("1")){
                            g.removeText(text[i][c]);
                            int textNum;
                            try{
                                textNum = Integer.parseInt(text[i][c].getText()) - 1;
                            }catch(Exception e){
                                textNum = 1;
                            }
                            Text newText = new Text(Integer.toString(textNum), text[i][c].getXPosition(), text[i][c].getYPosition(), 10, "WHITE");
                            g.removeText(text[i][c]);
                            text[i][c] = newText;
                            g.addText(text[i][c]);
							bricks[i][c].changeColour(textNum);
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

    /**
     * Checks if a ball is within the gameArena height and width, and passes in the coordinates of the ball into isInside.
     * @param ball A instance of the Ball class.
     **/
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

        if(isInside(ball, ball.getXPosition()+ball.getxDirection(), ball.getYPosition()) == true){
            ball.setxDirection(-1 * ball.getxDirection());
        }
        if(isInside(ball, ball.getXPosition(), ball.getYPosition()+ball.getyDirection()) == true){
            ball.setyDirection(-1 * ball.getyDirection());
        }

        if(ball.getYPosition() < -10){
            g.removeBall(ball);
        }

    }

    /**
     * Checks if a the coordinates passed in (of a ball) are inside any existing brick, and if so determines which aciton must be taken.
     * @param balls An array of instances of the Ball class.
     * @param i Current number of balls that have been fired in a given round.
     **/
    private void movement(Ball[] balls, int i)
    {
        for(int c=0; c<i; c++){
            collisionDetection(balls[c]);
            balls[c].setXPosition(balls[c].getXPosition()+balls[c].getxDirection());
            balls[c].setYPosition(balls[c].getYPosition()+balls[c].getyDirection());
        }
    }

    /**
     * Part of the main game loop, used to retrieve users key press inputs and create new balls/ change firing angle accordingly.
     **/
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
        movement(balls, i);
    }

    /**
     * Main game loop, runs methods to: get input, check if a round/ level is over and create specific levels.
     **/
    private void gameLoop()
    {
        while(true){

            if(i == maxBalls){
                if(roundOver()==true){
		        if(didWin() == true){
		            levelOver.setText("You Win!");
			    clearLevel();
		        }else if(didLose() == true){
			    levelOver.setText("You Lose!");
			    clearLevel();
			}
		        nextRound();
		        i = 0;
                }
            }

            if(m.getLevel() == 4){
                m.menuPanel();
                clearLevel();
                g.removeText(levelOver);
            }else if(m.getLevel() == 0){
                input();
                if(didWin() == true){
                    levelOver.setText("You Win!");
                    clearLevel();
                }
            }else if(m.getLevel() == 1)
            {
                maxBalls = 50;
                m.setLevel(0);
                m.displayLevel(1);
                initLevel(1);
            }else if(m.getLevel() == 2){
                maxBalls = 50;
                m.setLevel(0);
                m.displayLevel(2);
                initLevel(2);
            }else if(m.getLevel() == 3){
                maxBalls = 50;
                m.setBallCount(50);
                m.setLevel(0);
                m.displayLevel(3);
                initLevel(3);
            }


            g.update();
        }
    }

    /**
     * Loops over all instances of bricks/ text and balls created in a given level and removes them from the gameArena. Also calls methods to reset the text based menu items.
     **/
    private void clearLevel()
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
        m.setBallCount(50);
        m.setRound(1);
        m.setLevel(5);
        g.addText(levelOver);
    }

    /**
     * Loops over all bricks and checks the value of each, if all indicate they have been destroyed, then the game is confirmed as won.
     **/
    private boolean didWin()
    {
        for(int v=0; v<bricks.length; v++){
            for(int c=0; c<bricks[v].length; c++){
                if(!text[v][c].getText().equals("1") && !text[v][c].getText().equals("!")){
                    return false;
                }
            }
        }

        return true;
    }

	/**
	 * Loops over all bricks and checks the lower side of a bricks y position against the bottom of the game arena
	 **/
    private boolean didLose()
    {
        for(int v=0; v<bricks.length; v++){
            for(int c=0; c<bricks[v].length; c++){
                if( (bricks[v][c].getYPosition()+bricks[v][c].getHeight()/2) >= g.getArenaHeight() ){
                    return true;
                }
            }
        }
	return false;
    }



    /**
     * Checks if all balls available to the user in a given round have been fired and left the screen (have a negative y coordinate).
     **/
    private boolean roundOver()
    {
        for(int i=0; i<maxBalls; i++){
            if(balls[i].getYPosition() > -20 && balls[i].getYPosition() < 450){
                return false;
            }
        }
        return true;
    }

    /**
     * Loops over all bricks, shifting each down by a constant value added to the height of the brick. Also calls methods to change the displayed round number and ball count.
     **/
    private void nextRound()
    {
        for(int v=0; v<bricks.length; v++){
            for(int c=0; c<bricks[v].length; c++){
                bricks[v][c].setYPosition(bricks[v][c].getYPosition() + bricks[v][c].getHeight()+5);
                text[v][c].setYPosition(text[v][c].getYPosition() + bricks[v][c].getHeight()+5);
            }
        }
        m.incrementRound();
        m.setBallCount(50);
    }

    /**
     * Generates the bricks of a level and sets their details dependant on the level parameter.
     * @param level The level selected by the user, between 1 and 3.
     **/
    private void initLevel(int level)
    {
        int xPos=0, yPos=0;
        String health = "20";

        g.removeText(levelOver);
        rowOfSpecial = rand.nextInt(bricks.length);
        colOfSpecial = rand.nextInt(bricks[rowOfSpecial].length);

        for(int v=0; v<bricks.length; v++){
            for(int c=0; c<bricks[v].length; c++){


                if(v < bricks.length/2){
                    yPos = -1 * ((v*25)+10);
                }else{
                    yPos = ((v-bricks.length/2) * 25)+15;
                }

                if(level == 1){
                    xPos = ((575/10) * (c+1))-20;
                }else if(level == 2 ){
                    if((v % 2) == 0){
                        xPos = ((575/10) * (c+1))-20;
                    }else{
                        xPos = ((575/10) * (c+1));
                    }
                }else{
                    health = "40";
                    xPos = ((575/10) * (c+1))-20;
                }

                if((v == rowOfSpecial) && (c == colOfSpecial)){
                    bricks[v][c] = new Rectangle(xPos, yPos, 55, 20, "GOLD");
                    text[v][c] = new Text("!",xPos-6,yPos+4,10,"WHITE");
                }else{
                    bricks[v][c] = new Rectangle(xPos, yPos, 55, 20, "GREEN");
                    text[v][c] = new Text(health,xPos-6,yPos+4,10,"WHITE");
                }
                g.addRectangle(bricks[v][c]);
                g.addText(text[v][c]);
            }
        }

        if(level == 3){
            for(int col=0; col<bricks[5].length; col++){
                if(col != 0 && col != 9){
                    bricks[5][col].setYPosition(bricks[5][col].getYPosition()+60);
                    bricks[5][col].setColour("GOLD");
                    text[5][col].setYPosition(bricks[5][col].getYPosition());
                    text[5][col].setText("^");
                }else{
                    text[5][col].setText("1");
                    g.removeText(text[5][col]);
                    g.removeRectangle(bricks[5][col]);
                }
            }
        }

    }

    /**
     * Creates two 'lasers' from instances of the Line class, and deletes the row and column of the special block which caused the lasers to be triggered.
     **/
    private void triggerLaser()
    {
        Line horLaser = new Line(0,bricks[rowOfSpecial][colOfSpecial].getYPosition(),575,bricks[rowOfSpecial][colOfSpecial].getYPosition(),10,"GOLD");
        Line verLaser = new Line(bricks[rowOfSpecial][colOfSpecial].getXPosition(),0,bricks[rowOfSpecial][colOfSpecial].getXPosition(),450,10,"GOLD");
        g.addLine(verLaser);
        g.addLine(horLaser);

        for(int i=0; i<bricks.length; i++){
            text[i][colOfSpecial].setText("1");
            g.removeText(text[i][colOfSpecial]);
            g.removeRectangle(bricks[i][colOfSpecial]);
        }

        for(int i=0; i<bricks[rowOfSpecial].length; i++){
            text[rowOfSpecial][i].setText("1");
            g.removeText(text[rowOfSpecial][i]);
            g.removeRectangle(bricks[rowOfSpecial][i]);
        }
        g.update();

        try{
            TimeUnit.SECONDS.sleep(1);
            g.removeLine(horLaser);
            g.removeLine(verLaser);
        }catch(InterruptedException ie){
            System.out.println("InterruptedException: " + ie);
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    /**
     * Builds the basic components of the gui layout and starts the main game loop after adding the firing line to the game arena.
     **/
    public BrickBreaker()
    {
        frame.setLayout(new BorderLayout());
        JPanel panel = m.initJComponents();
        layoutPanel.add(panel);
        frame.add(layoutPanel,BorderLayout.EAST);

        g.addLine(arrow);
        gameLoop();
    }
}
