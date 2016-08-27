
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class SimplePaint extends Applet implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private final static int
		WHITE = 6,
   		PURPLE = 5,
   		YELLOW = 4,
		BLUE = 3,			
   		GREEN = 2,
               	RED = 1, 
               	BLACK = 0;     
	
	private int thisColor = BLACK;  
	private int oldX, oldY;   
	private boolean dragging;    
	private Graphics graphicsForDrawing;
   
   
	public void init() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

   	public void update(Graphics graphical) {
       		drawIt(graphical);
   	}
   

  	public void drawIt(Graphics graphical) {
		int sizeWidth = getSize().width;   
		int sizeHeight = getSize().height;  
		                    
		int cSpace = (sizeHeight - 56) / 7;
		
		graphical.setColor(Color.white);
		graphical.fillRect(3, 3, sizeWidth - 59, sizeHeight - 6);
		
		
		graphical.setColor(Color.gray);
		graphical.drawRect(0, 0, sizeWidth-1, sizeHeight-1);
		graphical.drawRect(1, 1, sizeWidth-3, sizeHeight-3);
		graphical.drawRect(2, 2, sizeWidth-5, sizeHeight-5);
		
		
		
		graphical.fillRect(sizeWidth - 56, 0, 56, sizeHeight);
		
		
		graphical.setColor(Color.white);
		graphical.fillRect(sizeWidth-53,  sizeHeight-53, 50, 50);
		graphical.setColor(Color.black);
		graphical.drawRect(sizeWidth-53, sizeHeight-53, 49, 49);
		graphical.drawString("Clear", sizeWidth-48, sizeHeight-23); 
		
		graphical.setColor(Color.black);
		graphical.fillRect(sizeWidth-53, 3 + 0*cSpace, 50, cSpace-3);
		graphical.setColor(Color.red);
		graphical.fillRect(sizeWidth-53, 3 + 1*cSpace, 50, cSpace-3);
		graphical.setColor(Color.green);
		graphical.fillRect(sizeWidth-53, 3 + 2*cSpace, 50, cSpace-3);
		graphical.setColor(Color.blue);
		graphical.fillRect(sizeWidth-53, 3 + 3*cSpace, 50, cSpace-3);
		graphical.setColor(Color.yellow);
		graphical.fillRect(sizeWidth-53, 3 + 4*cSpace, 50, cSpace-3);
		graphical.setColor(Color.magenta);
		graphical.fillRect(sizeWidth-53, 3 + 5*cSpace, 50, cSpace-3);
		graphical.setColor(Color.white);
		graphical.fillRect(sizeWidth-53, 3 + 6*cSpace, 50, cSpace-3);
		graphical.setColor(Color.black);
		graphical.drawString("Eraser", sizeWidth-48, (sizeHeight-50)-cSpace/2);
		
		
		
		graphical.setColor(Color.white);
		graphical.drawRect(sizeWidth-55, 1 + thisColor*cSpace, 53, cSpace);
		graphical.drawRect(sizeWidth-54, 2 + thisColor*cSpace, 51, cSpace-2);
	}
   
   	private void penChange(int y) {
		int sizeWidth = getSize().width;           
		int sizeHeight = getSize().height;         
		int cSpace = (sizeHeight - 56) / 7;  
		int newColor = y / cSpace;       
      		if (newColor < 0 || newColor > 6) return;
		Graphics graphical = getGraphics();
		graphical.setColor(Color.gray);
		graphical.drawRect(sizeWidth-55, 1 + thisColor*cSpace, 53, cSpace);
		graphical.drawRect(sizeWidth-54, 2 + thisColor*cSpace, 51, cSpace-2);
		thisColor = newColor;
		graphical.setColor(Color.white);
		graphical.drawRect(sizeWidth-55, 1 + thisColor*cSpace, 53, cSpace);
		graphical.drawRect(sizeWidth-54, 2 + thisColor*cSpace, 51, cSpace-2);
		graphical.dispose();
   	} 
   
	private void setUpDG() {
		graphicsForDrawing = getGraphics();
		switch (thisColor) {
		case BLACK:
			graphicsForDrawing.setColor(Color.black);
			break;
		case RED:
			graphicsForDrawing.setColor(Color.red);
			break;
		case GREEN:
			graphicsForDrawing.setColor(Color.green);
			break;
		case BLUE:
			graphicsForDrawing.setColor(Color.blue);
			break;	
		case YELLOW:
			graphicsForDrawing.setColor(Color.yellow);
			break;
		case PURPLE:
			graphicsForDrawing.setColor(Color.magenta);
			break;
		case WHITE:
			graphicsForDrawing.setColor(Color.white);
			break;
		}
	} 

   	public void mousePressed(MouseEvent evt) {
		int x = evt.getX();   
		int y = evt.getY(); 
		
		int sizeWidth = getSize().width;   
		int sizeHeight = getSize().height;
		
      		if (dragging == true)  return;          
      
      		if (x > sizeWidth - 53) {
           		if (y > sizeHeight - 53) repaint();      
         		else penChange(y);  
      		} else if (x > 3 && x < sizeWidth - 56 && y > 3 && y < sizeHeight - 3) {
		        oldX = x;
		        oldY = y;
         		dragging = true;
        		setUpDG();
      		}
   	} 
   

   	public void mouseReleased(MouseEvent evt) {
       		if (dragging == false) return; 
       		dragging = false;
       		graphicsForDrawing.dispose();
       		graphicsForDrawing = null;
   	}
   
   	public void mouseDragged(MouseEvent evt) {
       		if (dragging == false) return;  
       		
		int x = evt.getX();  
		int y = evt.getY(); 
		
       		if (x < 3) x = 3;                          
       		if (x > getSize().width - 57) x = getSize().width - 57;   
       		
	       	if (y < 3) y = 3;                          
       		if (y > getSize().height - 4) y = getSize().height - 4;
            
       		if(thisColor != 6) { //If it is a color
    	   		graphicsForDrawing.drawLine(oldX, oldY, x, y);  
       		} else { //If it is the eraser
			graphicsForDrawing.drawLine(oldX, oldY, x, y);  
			graphicsForDrawing.drawLine(oldX + 1, oldY, x + 1, y);
			graphicsForDrawing.drawLine(oldX + 2, oldY, x + 2, y);
			graphicsForDrawing.drawLine(oldX + 3, oldY, x + 3, y);
			graphicsForDrawing.drawLine(oldX + 4, oldY, x + 4, y);
			graphicsForDrawing.drawLine(oldX + 5, oldY, x + 5, y);
			graphicsForDrawing.drawLine(oldX - 1, oldY, x - 1, y);
			graphicsForDrawing.drawLine(oldX - 2, oldY, x - 2, y);
			graphicsForDrawing.drawLine(oldX - 3, oldY, x - 3, y);
			graphicsForDrawing.drawLine(oldX - 4, oldY, x - 4, y);
			graphicsForDrawing.drawLine(oldX - 5, oldY, x - 5, y);
			graphicsForDrawing.drawLine(oldX, oldY + 1, x, y + 1);
			graphicsForDrawing.drawLine(oldX, oldY + 2, x, y + 2);
			graphicsForDrawing.drawLine(oldX, oldY + 3, x, y + 3);
			graphicsForDrawing.drawLine(oldX, oldY + 4, x, y + 4);
			graphicsForDrawing.drawLine(oldX, oldY + 5, x, y + 5);
			graphicsForDrawing.drawLine(oldX, oldY - 1, x, y - 1);
			graphicsForDrawing.drawLine(oldX, oldY - 2, x, y - 2);
			graphicsForDrawing.drawLine(oldX, oldY - 3, x, y - 3);
			graphicsForDrawing.drawLine(oldX, oldY - 4, x, y - 4);
			graphicsForDrawing.drawLine(oldX, oldY - 5, x, y - 5);
       		}
       		oldX = x; 
       		oldY = y;
   	} 
  
	public void mouseEntered(MouseEvent evt) { }  
	public void mouseExited(MouseEvent evt) { }   
	public void mouseClicked(MouseEvent evt) { }  
	public void mouseMoved(MouseEvent evt) { }   
                
} 
