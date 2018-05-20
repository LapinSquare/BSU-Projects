import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer; 



/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates...something. I guess. Also, Portal! And rabbits are cute! 
 *
 * @author BSU CS 121 Instructors
 * @author Kim Huynh 
 * @version 10/30/2017
 */
@SuppressWarnings("serial") 

public class HuynhKim_TA extends JPanel
{

	static Image img; 
	static Image img2;

	
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 60; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = 0; 
	private int xOffsetNeg = 0;
	private int yOffset = 0;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 10; 
	private int stepSizeNeg = -1000; 

	private final Color BACKGROUND_COLOR = Color.GRAY;

	// Background picture
	//private Image bg;
	
	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */  



	public void paintComponent(Graphics g)
	{ 

		// Get the current width and height of the window.
		int width = getWidth(); // panel width (HORIZONTAL)
		int height = getHeight(); // panel height

		g.drawImage(img, 0, 0, width, height,null); 
		

		
		//BACKGROUND RABBIT 
		g.setColor(Color.WHITE); 
		g.fillOval( 2*width/10, 4*height/10 , width/10 , height/8 ); 
		g.fillOval( 3*width/14, 5*height/15 , width/14 , height/12 ); 
		g.fillOval( 3*width/12, 4*height/14 ,width/35 , height/15);
		g.fillOval( 3*width/14, 4*height/14 ,width/35 , height/15); 
		g.fillOval( 6*width/32, 5*height/11 , width/50 , height/60 ); 
		g.drawString("WOAH", xOffset*2, yOffset);
		g.setColor(Color.BLACK); 
		g.fillOval( 4*width/16, 6*height/17 , width/100 , height/80 ); 
		g.fillOval( 4*width/15, 6*height/17 ,width/100 , height/80); 
			
		
		//TRACK
		g.setColor(Color.GRAY); 
		g.fillRect( 0, 1*height/10 ,width, 3*height/20); 
		Color lightBlue = new Color(135,206,250);  

		//SHINY STUFF
		g.setColor(lightBlue); 
		g.fillRect( xOffset*5, 1*height/10 ,width/10, 3*height/20);
		g.fillRect( xOffset*4, 1*height/10 ,width/10, 3*height/20);
		g.fillRect( xOffset*3, 1*height/10 ,width/10, 3*height/20);
		g.fillRect( xOffset*2, 1*height/10 ,width/10, 3*height/20);
		 

		// Calculate the new xOffset position of the moving object.
		xOffset  = (xOffset + stepSize) % width;
		xOffsetNeg = (xOffset + stepSizeNeg) % width; 
		yOffset  = (yOffset + stepSize) % height;

		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen

		// This draws a green square. Replace it with your own object.
		int squareSide = height/5;
		int squareY = height/2 - squareSide/2;
		//g.fillRect(xOffset, squareY, squareSide, squareSide); 




		//SPINNING RABBIT
		g.setColor(Color.WHITE); 
		//g.fillOval( xOffset*2, height/10 ,width/6 , height/6);  
		g.drawImage(img2, (2)*xOffset, height/10, width/10, 3*height/20, null); 
		
		//WEIRD BACKWARDS THING
		g.setColor(lightBlue);
		g.fillOval(xOffsetNeg*-2, 1*height/110, 4*squareSide/10, 4*squareSide/15);
		g.setColor(Color.ORANGE); 
		g.fillOval(xOffsetNeg*-2, 2*height/40, 4*squareSide/10, 4*squareSide/15);   
		Toolkit.getDefaultToolkit().sync();

	}  
	

	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================

	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
	    img = new ImageIcon("Image/background.jpg").getImage();
	    img2 = new ImageIcon("Image/rabbit3.gif").getImage();


	    // DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("A Rabbit in Portal. What am I doing? I don't know either. Haha");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new HuynhKim_TA());
		frame.pack();
		frame.setVisible(true); 
		

	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public HuynhKim_TA()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	} 
}
