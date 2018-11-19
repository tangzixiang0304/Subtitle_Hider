import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class HideAndBackThread extends Thread {
    private Point calibratePoint;
    private int backTime;
    private ClickedPanel clickedPanel;
    private Robot robot;
    public HideAndBackThread(Point calibratePoint, ClickedPanel clickedPanel, int backTime) {

        System.out.println("thread !");
        this.clickedPanel = clickedPanel;
        this.calibratePoint = calibratePoint;
        this.backTime = backTime;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        if(calibratePoint==null){
            JOptionPane.showMessageDialog(null,"Please press CAL to calibrate a point first","please calibrate",JOptionPane.YES_OPTION);
            return;
        }
        Point sourcePoint=MouseInfo.getPointerInfo().getLocation();
        System.out.println("is_hide=true");
        System.out.println(calibratePoint);
        clickedPanel.is_hide = true;
        robot.mouseMove(calibratePoint.x, calibratePoint.y);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_LEFT);

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        robot.keyRelease(KeyEvent.VK_LEFT);

        robot.mouseMove(sourcePoint.x, sourcePoint.y);
        try {
            Thread.sleep(backTime * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("is_hide=false");
        clickedPanel.is_hide = false;
        robot.mouseMove(sourcePoint.x, sourcePoint.y+1);
    }
}
