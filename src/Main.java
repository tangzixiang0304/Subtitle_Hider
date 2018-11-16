import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;


public class Main {

    public static void main(String[] args) {
        JFrame frame=new JFrame() {
//			 @Override
//			 public void paint(Graphics g) {
//			 // TODO 自动生成方法存根  半透明
//			 Graphics2D G2=(Graphics2D) g.create();
//			 G2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
//			 0.5f));
//			 super.paint(G2);
//			 }
        };
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        frame.setUndecorated(true);
        frame.add(new ClickedPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(screenSize.width,screenSize.height));
        AWTUtilities.setWindowOpaque(frame, false);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

}





