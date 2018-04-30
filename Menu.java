import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu implements ActionListener
{
    private GameArena g = new GameArena(300,450);
    private JFrame frame = g.getWindow();

    private JButton levelOne = new JButton("1");
    private JButton levelTwo = new JButton("2");
    private JButton levelThree = new JButton("3");

    void initJComponents()
    {
        //levelOne
        levelOne.setSize(100,75);
        levelOne.setLocation(150,100);
        levelOne.addActionListener(this);
        frame.add(levelOne);

        //levelTwo
        levelTwo.setSize(100,75);
        levelTwo.setLocation(150,200);
        levelTwo.addActionListener(this);
        frame.add(levelTwo);

        //levelThree
        levelThree.setSize(100,75);
        levelThree.setLocation(150,300);
        levelThree.addActionListener(this);
        frame.add(levelThree);
    }



    public Menu()
    {
        initJComponents();
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == levelOne){
            //BrickBreaker b = new BrickBreaker(g);
            //setUpGame();
        }
    }



}
