import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HiderFrame extends JFrame {
    public ClickedPanel clickedPanel=new ClickedPanel(this);

    public HiderFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(screenSize.width, screenSize.height));
        AWTUtilities.setWindowOpaque(this, false);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.add(clickedPanel);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    System.out.println("down");
                    clickedPanel.back_and_hide_clicked();
                    clickedPanel.repaint();
                }
                if(e.getKeyCode()==KeyEvent.VK_H){
                    clickedPanel.hide_clicked();
                    clickedPanel.repaint();
                }
            }
        });
    }
}
