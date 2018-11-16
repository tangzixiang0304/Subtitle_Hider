import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;

public class HiderFrame extends JFrame {
    public HiderFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setUndecorated(true);
        this.add(new ClickedPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(screenSize.width, screenSize.height));
        AWTUtilities.setWindowOpaque(this, false);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }
}
