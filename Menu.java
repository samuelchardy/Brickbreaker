import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu implements ActionListener
{
    private JPanel panel = new JPanel();
    private JLabel title = new JLabel("Brick Breaker");
    private JButton levelOne = new JButton("1");
    private JButton levelTwo = new JButton("2");
    private JButton levelThree = new JButton("3");

    JPanel initJComponents()
    {
        //panel
        panel.setLayout(new GridLayout(3,8));

        //title
        title.setLocation(2,1);
        title.setSize(75,75);
        panel.add(title);

        //levelOne
        //levelOne.setSize(100,75);
        levelOne.setLocation(1,2);
        levelOne.addActionListener(this);
        panel.add(levelOne);

        //levelTwo
        //levelTwo.setSize(100,75);
        levelTwo.setLocation(1,3);
        levelTwo.addActionListener(this);
        panel.add(levelTwo);

        //levelThree
        //levelThree.setSize(100,75);
        levelThree.setLocation(1,4);
        levelThree.addActionListener(this);
        panel.add(levelThree);

        return panel;
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == levelOne){
            System.out.println("WOW");
        }
    }



}
