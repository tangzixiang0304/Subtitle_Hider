import java.awt.*;

public class CloseButtonRect {
    int BX;
    int BY;
    int BR;
    boolean isOverlapped;
    public String title;

    public CloseButtonRect(int BX,int BY ,int BR,String title){
        this.BX=BX;
        this.BY=BY;
        this.BR=BR;
        this.title=title;
    }

    public void setBound(int BX,int BY ,int BR){
        this.BX=BX;
        this.BY=BY;
        this.BR=BR;
    }

    public boolean pointInRect(int x,int y){
        if((x-BX)*(x-BX)+(y-BY)*(y-BY)<BR*BR){
            return true;
        }
        return false;
    }


    public void paintRect(Graphics2D g, Color color, boolean hide){
        g.setColor(color);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                0.3f));
        if(!hide){
            if(isOverlapped){
                g.setColor(Color.white);
            }
            g.fillOval(BX-BR,BY-BR,2*BR,2*BR);
            g.setColor(Color.gray);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    0.7f));
            g.setFont(new Font("Times New Roman",Font.BOLD,18));
        }

        if(hide){
            if(isOverlapped){
                g.setColor(Color.black);
                g.drawOval(BX-BR,BY-BR,2*BR,2*BR);
            }
        }


    }
}
