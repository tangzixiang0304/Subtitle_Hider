import javax.swing.*;
import java.awt.*;

public class ButtonRect {
    int left;
    int top;
    int width;
    int height;
    boolean isOverlapped;

    public ButtonRect(int left,int top ,int width,int height,String title){
        this.left=left;
        this.top=top;
        this.width=width;
        this.height=height;
    }

    public void setBound(int left,int top ,int width,int height){
        this.left=left;
        this.top=top;
        this.width=width;
        this.height=height;
    }

    public boolean pointInRect(int x,int y){
        if(x>left && x<left+width && y>top && y<top+height){
            return true;
        }
        return false;
    }


    public void paintRect(Graphics2D g,Color color,boolean hide){
        g.setColor(color);
        if(!hide){
            if(isOverlapped){
                g.setColor(Color.white);
            }
            g.fillRect(left,top,width,height);
        }

        if(hide){
            if(isOverlapped){
                g.setColor(Color.black);
                g.drawRect(left,top,width,height);
            }
        }


    }

}
