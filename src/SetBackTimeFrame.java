import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetBackTimeFrame extends JFrame {

    private JButton yes;
    private JTextField textField;
    private ClickedPanel clickedPanel;
//    private int backTime;
//    public int getBackTime(){
//        if(backTime){
//
//        }
//        return backTime;
//    }

    public SetBackTimeFrame(ClickedPanel clickedPanel){
        this.clickedPanel=clickedPanel;
        this.setSize(400,200);
        this.setLocation(1000,500);
        JPanel panel=new JPanel();
        textField=new JTextField(10);
        yes=new JButton();
        yes.setLocation(190,170);
        yes.setSize(20,10);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedPanel.backTime=Integer.parseInt(textField.getText());
                System.out.println(clickedPanel.backTime);
                clickedPanel.setBackTimeFrame.setVisible(false);
            }
        });
        this.add(panel);
        panel.add(textField);
        panel.add(yes);
    }
}
