import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu implements ActionListener
{
    private JPanel menuPanel = new JPanel();
    private JPanel gamePanel = new JPanel();

    private JLabel title = new JLabel("Brick Breaker");
    private JLabel numOfBalls = new JLabel("Balls left: 50");
    private JLabel levelNumber = new JLabel("Level No. ");

    private JButton levelOne = new JButton("1");
    private JButton levelTwo = new JButton("2");
    private JButton levelThree = new JButton("3");

    public JPanel initJComponents()
    {
        //panel
        menuPanel.setLayout(new GridLayout(8,3));

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

        return menuPanel;
    }


    private void gamePanel()
    {
        //Removing components
        levelOne.setVisible(false);
        levelTwo.setVisible(false);
        levelThree.setVisible(false);

        //levelNumber
        levelNumber.setLocation(8,2);
        menuPanel.add(levelNumber);

        //numOfBalls
        numOfBalls.setLocation(7,2);
        menuPanel.add(numOfBalls);
    }


    public void decrementBalls()
    {
        String[] currentText = numOfBalls.getText().split(" ");
        int ballsLeft = Integer.parseInt(currentText[2])-1;
        String newText = currentText[0] + " " + currentText[1] + " " + String.valueOf(ballsLeft);
        numOfBalls.setText(newText);
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == levelOne){
            gamePanel();
        }
    }



}
