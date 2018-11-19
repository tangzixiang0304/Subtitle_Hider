import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CalibrateFrame extends JFrame {

    public class CalibratePanel extends JPanel {
        private int width, height;
        private Point calibratePoint;
        private JFrame fatherFrame;
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                calibratePoint = e.getPoint();
                System.out.println("clicked!" + e);
                fatherFrame.setVisible(false);

            }
        };

        public Point getCalibratePoint() {
            return calibratePoint;
        }


        public CalibratePanel(CalibrateFrame frame) {
            this.width = frame.getWidth();
            this.height = frame.getHeight();
            fatherFrame = frame;
            this.addMouseListener(mouseAdapter);
        }


        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.gray);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    0.5f));
            g2.fillRect(0, 0, width, height);
        }
    }

    public CalibratePanel calibratePanel;


    public CalibrateFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setUndecorated(true);
        this.setSize(new Dimension(screenSize.width, screenSize.height));
        AWTUtilities.setWindowOpaque(this, false);
        this.setVisible(false);
        this.setAlwaysOnTop(true);
        calibratePanel=new CalibratePanel(this);
        this.add(calibratePanel);
    }
}

