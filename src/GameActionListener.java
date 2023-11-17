import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {

    private JButton enterNameButton;
    public GameActionListener(JButton enterNameButton){
        this.enterNameButton = enterNameButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == enterNameButton){
            System.out.println("Enter Name Button Has been pressed!");
            //Set Name in GuiKlass
            //Open Menu

        }

    }
}
