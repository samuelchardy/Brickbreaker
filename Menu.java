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

    private int level = 4;
    private int round = 1;


    public int getLevel()
    {
        return level;
    }


    public int getRound()
    {
        return round;
    }


    public void setLevel(int lvl)
    {
        level = lvl;
        levelNumber.setText("Level No. " + String.valueOf(level));
    }


    public void setRound(int rnd)
    {
        round = rnd;
        String[] currentText = roundNumber.getText().split(" ");
        String newText = currentText[0] + " " + round + " " + currentText[2] + " " + currentText[3];
        roundNumber.setText(newText);
    }


    public void decrementBalls()
    {
        String[] currentText = numOfBalls.getText().split(" ");
        int ballsLeft = Integer.parseInt(currentText[2])-1;
        String newText = currentText[0] + " " + currentText[1] + " " + String.valueOf(ballsLeft);
        numOfBalls.setText(newText);
    }


    public void resetBallCount()
    {
        String[] currentText = numOfBalls.getText().split(" ");
        String newText = currentText[0] + " " + currentText[1] + " 50";
        numOfBalls.setText(newText);
    }


    public void incrementRound()
    {
        round++;
        setRound(round);
    }


    public JPanel initJComponents()
    {
        //panel
        menuPanel.setLayout(new GridLayout(100,100));

        //title
        title.setLocation(2,2);
        title.setSize(75,75);
        menuPanel.add(title);

        //levelOne
        //levelOne.setSize(100,75);
        levelOne.setLocation(4,2);
        levelOne.addActionListener(this);
        menuPanel.add(levelOne);

        //levelTwo
        //levelTwo.setSize(100,75);
        levelTwo.setLocation(5,2);
        levelTwo.addActionListener(this);
        menuPanel.add(levelTwo);

        //levelThree
        //levelThree.setSize(100,75);
        levelThree.setLocation(6,2);
        levelThree.addActionListener(this);
        menuPanel.add(levelThree);

        //levelNumber
        levelNumber.setText("Level No. " + String.valueOf(level));
        levelNumber.setLocation(6,2);
        menuPanel.add(levelNumber);

        //numOfBalls
        numOfBalls.setLocation(8,2);
        menuPanel.add(numOfBalls);

        //roundNumber
        roundNumber.setLocation(8,2);
        menuPanel.add(roundNumber);

        menuPanel();
        return menuPanel;
    }


    public void gamePanel()
    {
        //Removing components
        levelOne.setVisible(false);
        levelTwo.setVisible(false);
        levelThree.setVisible(false);

        levelNumber.setVisible(true);
        numOfBalls.setVisible(true);
        roundNumber.setVisible(true);
    }


    public void menuPanel()
    {
        levelOne.setVisible(true);
        levelTwo.setVisible(true);
        levelThree.setVisible(true);

        levelNumber.setVisible(false);
        numOfBalls.setVisible(false);
        roundNumber.setVisible(false);
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == levelOne){
            level = 1;
            gamePanel();
        }else if(e.getSource() == levelTwo){
            level = 2;
            gamePanel();
        }

    }
}
