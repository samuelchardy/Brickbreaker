import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu implements ActionListener
{
    private JPanel panel = new JPanel();
    private JButton levelOne = new JButton("1");
    private JButton levelTwo = new JButton("2");
    private JButton levelThree = new JButton("3");

    JPanel initJComponents()
    {
        //levelOne
        levelOne.setSize(100,75);
        levelOne.setLocation(150,100);
        levelOne.addActionListener(this);
        panel.add(levelOne);

        //levelTwo
        levelTwo.setSize(100,75);
        levelTwo.setLocation(150,200);
        levelTwo.addActionListener(this);
        panel.add(levelTwo);

        //levelThree
        levelThree.setSize(100,75);
        levelThree.setLocation(150,300);
        levelThree.addActionListener(this);
        panel.add(levelThree);

        return panel;
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == levelOne){
            //BrickBreaker b = new BrickBreaker(g);
            //setUpGame();
            System.out.println("WOW");
        }
    }



}
