import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu implements ActionListener
{
    private JPanel menuPanel = new JPanel();
    private JPanel gamePanel = new JPanel();

    private JLabel title = new JLabel("Brick Breaker");
    private JLabel numOfBalls = new JLabel("Balls left: 50");
    private JLabel levelNumber = new JLabel("Level: 0");
    private JLabel roundNumber = new JLabel("Round: 1 of 5");

    private JButton levelOne = new JButton("1");
    private JButton levelTwo = new JButton("2");
    private JButton levelThree = new JButton("3");
    private JButton back = new JButton("BACK");

    private int level = 4;
    private int round = 1;


    /**
     * Retreives the current level of the game.
     * @return The value of int level.
     **/
    public int getLevel()
    {
        return level;
    }

    /**
     * Retreives the current round of the game.
     * @return The value of int round.
     **/
    public int getRound()
    {
        return round;
    }

    /**
     * Sets the current level of the game.
     * @param lvl The int value of the new level of the game.
     **/
    public void setLevel(int lvl)
    {
        level = lvl;
    }

    /**
     * Sets the current displayed level number of the game.
     * @param lvl The int value of the new level number to be displayed.
     **/
    public void displayLevel(int lvl){
            levelNumber.setText("Level No. " + String.valueOf(lvl));
    }


    /**
     * Sets the current round of the game and sets the text of roundNumber to include new round.
     * @param rnd The int value of the new round of the game.
     **/
    public void setRound(int rnd)
    {
        round = rnd;
        String[] currentText = roundNumber.getText().split(" ");
        String newText = currentText[0] + " " + round + " " + currentText[2] + " " + currentText[3];
        roundNumber.setText(newText);
    }

    /**
     * Decrements the number representing the number of balls, in the text of the label numOfBalls.
     **/
    public void decrementBalls()
    {
        String[] currentText = numOfBalls.getText().split(" ");
        int ballsLeft = Integer.parseInt(currentText[2])-1;
        String newText = currentText[0] + " " + currentText[1] + " " + String.valueOf(ballsLeft);
        numOfBalls.setText(newText);
    }

    /**
     * Sets the number representing the number of balls, in the text of the label numOfBalls.
     * @param balls The new number of balls left.
     **/
    public void setBallCount(int balls)
    {
        String[] currentText = numOfBalls.getText().split(" ");
        String newText = currentText[0] + " " + currentText[1] + " " + Integer.toString(balls);
        numOfBalls.setText(newText);
    }

    /**
     * Increments the current round number and calls the setRound method.
     **/
    public void incrementRound()
    {
        round++;
        setRound(round);
    }

    /**
     * Initialises all JComponents and their necessary details, adds ActionListeners, and adds all components to the JPanel menuPanel.
     * @return The JPanel menuPanel containing all JComponents.
     **/
    public JPanel initJComponents()
    {
        //panel
        menuPanel.setLayout(new GridLayout(100,100));

        //title
        title.setSize(75,75);
        menuPanel.add(title);

        //levelOne
        levelOne.addActionListener(this);
        menuPanel.add(levelOne);

        //levelTwo
        levelTwo.addActionListener(this);
        menuPanel.add(levelTwo);

        //levelThree
        levelThree.addActionListener(this);
        menuPanel.add(levelThree);

        //levelNumber
        levelNumber.setText("Level No. " + String.valueOf(level));
        menuPanel.add(levelNumber);

        //numOfBalls
        menuPanel.add(numOfBalls);

        //roundNumber
        menuPanel.add(roundNumber);

        //back
        back.addActionListener(this);
        menuPanel.add(back);

        menuPanel();
        return menuPanel;
    }

    /**
     * Uses the setVisible method of JComponents to hide all components not required during the games active (playable) state.
     **/
    public void gamePanel()
    {
        //Removing components
        levelOne.setVisible(false);
        levelTwo.setVisible(false);
        levelThree.setVisible(false);

        levelNumber.setVisible(true);
        numOfBalls.setVisible(true);
        roundNumber.setVisible(true);
        back.setVisible(true);
    }

    /**
     * Uses the setVisible method of JComponents to hide all components not required during the games inactive (menu) state.
     **/
    public void menuPanel()
    {
        levelOne.setVisible(true);
        levelTwo.setVisible(true);
        levelThree.setVisible(true);

        levelNumber.setVisible(false);
        numOfBalls.setVisible(false);
        roundNumber.setVisible(false);
        back.setVisible(false);
    }

    /**
     * Triggered upon any button press. Sets the variable level to determine game level and calls gamePanel method to set menu to game mode.
     **/
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == levelOne){
            level = 1;
            gamePanel();
        }else if(e.getSource() == levelTwo){
            level = 2;
            gamePanel();
        }else if(e.getSource() == levelThree){
            level = 3;
            gamePanel();
        }

        if(e.getSource() == back){
            level = 4;
        }

    }
}
