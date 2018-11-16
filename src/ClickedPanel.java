import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ClickedPanel extends JPanel {
    private int left = 50;
    private int top = 200;
    private int width = 800;
    private int height = 100;
    private int leftToMouse;
    private int upToMouse;
    private int moveOrChangeSizeFlag_Drag = 0;
    private boolean is_hide;
    private boolean hide_and_overlapped;
    private ButtonRect back_and_hide = new ButtonRect(left + width / 3, top, 2 * width / 15, height, "back and hide");
    private ButtonRect hide = new ButtonRect(left + width / 3 + width * 2 / 15, top, width * 2 / 15, height, "hide");
    private ButtonRect close = new ButtonRect(left + width / 3 + width * 4 / 15, top, width / 15, height / 2, "close");
    private ButtonRect calibration = new ButtonRect(left + width / 3 + 4 * width / 15, top + height / 2, width / 15, height / 2, "calibrate");

    private void back_and_hide_clicked() {

    }

    private void hide_clicked() {
        is_hide = true;
    }

    private void close_clicked() {

    }

    private void calibration_clicked() {

    }

    private void resizeAndPaintAllButtons(Graphics2D g2d) {
        back_and_hide.setBound(left + width / 3, top, 2 * width / 15, height);
        hide.setBound(left + width / 3 + width * 2 / 15, top, width * 2 / 15, height);
        close.setBound(left + width / 3 + width * 4 / 15, top, width / 15, height / 2);
        calibration.setBound(left + width / 3 + 4 * width / 15, top + height / 2, width / 15, height / 2);

        back_and_hide.paintRect(g2d, Color.darkGray, is_hide);
        hide.paintRect(g2d, Color.lightGray, is_hide);
        close.paintRect(g2d, Color.black, is_hide);
        calibration.paintRect(g2d, Color.darkGray, is_hide);
    }


    private int judgeFlag(int x, int y) {
        int changeSizeBorder = 10;
        if (Math.abs(x - left) < changeSizeBorder) {
            return 1;
        }

        if (Math.abs(x - (left + width)) < changeSizeBorder) {
            return 2;
        }

        if (Math.abs(y - top) < changeSizeBorder) {
            return 3;
        }

        if (Math.abs(y - (top + height)) < changeSizeBorder) {
            return 4;
        }

        if (back_and_hide.pointInRect(x, y)) {
            return 5;
        }

        if (hide.pointInRect(x, y)) {
            return 6;
        }

        if (close.pointInRect(x, y)) {
            return 7;
        }

        if (calibration.pointInRect(x, y)) {
            return 8;
        }
        moveOrChangeSizeFlag_Drag = 0;
        leftToMouse = x - left;
        upToMouse = y - top;
        return 0;
    }

    MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            int x = e.getX();
            int y = e.getY();
            switch (moveOrChangeSizeFlag_Drag) {
                case 0:
                    left = x - leftToMouse;
                    top = y - upToMouse;
                    break;
                case 1:
                    width += left - x;
                    left = x;
                    break;
                case 2:
                    width = x - left;
                    break;
                case 3:
                    height += top - y;
                    top = y;
                    break;
                case 4:
                    height = y - top;
                    break;
            }
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            switch (judgeFlag(x, y)) {
                case 0:
                    setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    break;
                case 1:
                case 2:
                    setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                    break;
                case 3:
                case 4:
                    setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    break;

            }

            hide.isOverlapped = hide.pointInRect(x, y);
            back_and_hide.isOverlapped = back_and_hide.pointInRect(x, y);
            close.isOverlapped = close.pointInRect(x, y);
            calibration.isOverlapped = calibration.pointInRect(x, y);
            repaint();
        }
    };


    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (is_hide) {
                is_hide = false;
                repaint();
                return;
            }
            switch (judgeFlag(e.getX(), e.getY())) {
                case 5:
                    back_and_hide_clicked();
                    break;
                case 6:
                    hide_clicked();
                    break;
                case 7:
                    close_clicked();
                    break;
                case 8:
                    calibration_clicked();
                    break;
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (is_hide)
                hide_and_overlapped = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (is_hide){
                hide_and_overlapped = false;
                hide.isOverlapped = false;
                back_and_hide.isOverlapped = false;
                close.isOverlapped = false;
                calibration.isOverlapped = false;

            }

            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            moveOrChangeSizeFlag_Drag = judgeFlag(e.getX(), e.getY());
            System.out.println("press");
        }
    };

    public ClickedPanel() {
        this.setLayout(null);
        this.setOpaque(false);
        setBackground(Color.red);
        this.addMouseMotionListener(mouseMotionAdapter);
        this.addMouseListener(mouseAdapter);
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        super.paint(g2);
        g2.setColor(Color.gray);
        if (is_hide) {
            if (hide_and_overlapped)
                g2.setColor(Color.lightGray);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    0.5f));
        }
        g2.fillRect(left, top, width, height);
        resizeAndPaintAllButtons(g2);

    }


}
