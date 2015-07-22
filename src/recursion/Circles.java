package recursion;
import javax.swing.*;
import java.awt.*;

public class Circles extends JApplet{
    public void init(){
    	getContentPane().setBackground(Color.WHITE);
    }
    
    /**paint method
    @param 
    */
    public void paint(Graphics g){
    	//draw 10 concentric circles
    	//The outermost circle's enclosing rectangle should be at location(5,5)
    	//and 300 pixels high
    	//color of circles will change as color get mixed
    	int redColor,greenColor, blueColor;
    	redColor =0;
    	greenColor =0;
    	blueColor =205;
    	drawCircles(g,10,5,300,redColor,greenColor,blueColor);
       	
    }
    
    
    /** 
     *   
     * 
     * @param g  - graphics object
     * @param n  - number of circles to draw
     * @param TopXY - top left coordinates of the outermost circle's enclosing rectangle
     * @param size  - width and height of outermost circle's enclosing rectangle
     */
    private void drawCircles(Graphics g, int n, int topXY, int size,int red, int green, int blue){
    	if (n >0){
    		Color mixColor = new Color(red,green,blue);
    		g.setColor(mixColor);
    		g.drawOval(topXY, topXY,size, size);
    		drawCircles(g,n-1, topXY+ 15, size -30,red +25, green +25, blue -25);
    		
    	}
    	
    } 
}