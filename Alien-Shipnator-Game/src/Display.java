/**
 * @author 		Saif Mahmud
 * @version     March 20, 2019	
 */
import javax.swing.*; //Needed for windows
import java.awt.*;    //Needed for graphics
import java.awt.event.*; //Needed for the mouse events
import java.util.ArrayList;

public class Display extends JFrame { //JFrame means "window", in effect
	
	// some hints about other colours that are available for use
	// This is the same code that is in StdDraw.java
	public static final Color BLACK      = Color.BLACK;
    public static final Color BLUE       = Color.BLUE;
    public static final Color CYAN       = Color.CYAN;
    public static final Color DARK_GRAY  = Color.DARK_GRAY;
    public static final Color GRAY       = Color.GRAY;
    public static final Color GREEN      = Color.GREEN;
    public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
    public static final Color MAGENTA    = Color.MAGENTA;
    public static final Color ORANGE     = Color.ORANGE;
    public static final Color PINK       = Color.PINK;
    public static final Color RED        = Color.RED;
    public static final Color WHITE      = Color.WHITE;
    public static final Color YELLOW     = Color.YELLOW;
    private static final Color red = new Color(197, 41, 28);
	private static final Color green = new Color(92,87,25);
	private static final Color yellow = new Color(237, 172, 60);
	
    
    // shape for an alien you can use
	public static final Color [][] ALIEN_SHAPE = 
		{
				{null, null, Display.BLACK, null, null, null, null, null, Display.BLACK, null, null},
				{null, null, null, Display.BLACK, null, null, null, Display.BLACK, null, null, null},
				{null,null,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,null,null},
				{null,Display.BLACK,Display.BLACK,null,Display.BLACK,Display.BLACK,Display.BLACK,null,Display.BLACK,Display.BLACK,null},
				{Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK},
				{Display.BLACK,null,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,null,Display.BLACK},
				{Display.BLACK,null,Display.BLACK,null,null,null,null,null,Display.BLACK,null,Display.BLACK},
				{null,null,null,Display.BLACK,Display.BLACK,null,Display.BLACK,Display.BLACK,null,null,null}
		};
	
	// shape for the ship you can use
	public static final Color [][] SHIP_SHAPE = 
		{
				{null,null,null,null,null,Display.BLACK,null,null,null,null,null},
				{null,null,null,null,Display.BLACK,Display.BLACK,Display.BLACK,null,null,null,null},
				{null,null,null,null,Display.BLACK,Display.BLACK,Display.BLACK,null,null,null,null},
				{null,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,null},
				{Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK},
				{Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK},
				{Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK},
				{Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK,Display.BLACK},
				
				
		};
	
	
	// constants used to control the game
	public final static int MOVE_LEFT = 1, MOVE_RIGHT = 2;
	public final static int CONTINUE = 0, WIN = 1, LOSE = 2;
	
	public static final double SCALE = 3;
	public static final long REFRESH_INTERVAL = 33; 

	private static final long serialVersionUID = 1L; //eliminates warnings
	private JPanel wholeWindow; //A JPanel is the content within a window
	private SpaceInvaders myApp; //A reference to the SpaceInvaders object handling this window.
	private Graphics myGraphics; //Saved reference to the graphics environment
	private static Display game;

	//Constructor
	public Display(int wide, int high) {
		setTitle("Space Invaders");
		setMaximumSize(new Dimension(wide, high));

		setSize(wide,high);
		wholeWindow = new GraphicsPanel(); //GraphicsPanel is defined below
		Container pane = getContentPane();
		pane.add(wholeWindow);

		wholeWindow.addKeyListener(new HandleKeys());
		setVisible(true);
		myApp = new SpaceInvaders(pane.getHeight(), pane.getWidth()); //Set up the user's app

		wholeWindow.setFocusable(true);
		wholeWindow.requestFocusInWindow();
	}//PathWindow constructor


	public static void main(String[] args){
		game = new Display(880,780);
		// Game loop. Run while the game is still going
		while (game.status() == CONTINUE) {
			game.draw();
			
			try {
				game.repaint();
				
				Thread.sleep(REFRESH_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// one last re-render
		game.repaint();

	}//main
	
	public int status() {
		return myApp.status();
	}
	
	/*
	 * Instance method to update the sprites, and update the canvas
	 */
	public void draw() {
		if (myGraphics!=null) {
			myGraphics.setFont(new Font("Helvetica",Font.BOLD,24));
			if (myApp.status() == WIN)
				myGraphics.drawString("Win!", 175, 190);
			else if (myApp.status() == LOSE)
				myGraphics.drawString("Lose!", 175, 190);
			else
			{
				myApp.update();
		
				ArrayList<Sprite> items = myApp.getItems();
				for(int i = 0; i < items.size(); i++ ) {
					drawItem(items.get(i));
				}
			}
		}
	}
	
	/*
	 * Draws all the pixel art
	 */
	private void drawItem(Sprite sprite) {
		double x = sprite.getX();
		double y = sprite.getY();
		Color [][] item = sprite.getColorGrid();
		
		// draws the 'pixels'
		for (int i = 0; i < item.length; i++) {
			for(int j = 0; j < item[i].length; j++) {
				if (item[i][j] != null) {
					square( x - item[0].length /2 * SCALE + SCALE/2 + j * SCALE,
							y - item.length /2 * SCALE - SCALE/2 + i * SCALE,
							SCALE,
							item[i][j]
							);
				}
			}
		}
	}

	//Provide some simple access to the graphics environment
	public void circle(int x, int y, int size, boolean selected){
		if(selected)
			myGraphics.setColor(Color.RED);
		else
			myGraphics.setColor(Color.BLACK);
		myGraphics.fillOval(x-size/2,y-size/2,size,size);
	}
	public void line(int x1, int y1, int x2, int y2){
		myGraphics.setColor(Color.BLACK);
		myGraphics.drawLine(x1,y1,x2,y2);
	}
	
	public void square(double x, double y, double width, Color color) {
		if(myGraphics != null) {
			myGraphics.setColor(color);
			myGraphics.fillRect((int)x, (int)y, (int)width, (int)width);
		}
	}


	private class GraphicsPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			/* This is where all the drawing commands go.
			 * Whenever the window needs to be drawn, or redrawn,
			 * this method will automatically be called.
			 */
			myGraphics = g; //Just remember the Graphics environment
			
			if (game != null) {
				game.draw(); //And then let the app do what it wants
			}
		}//paintComponent method
	}//private inner class graphicsPanel

	//A private inner class to take care of all mouse actions
	private class HandleKeys implements KeyListener {

		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == ' ')
  				myApp.shoot();

		}
		public void keyPressed(KeyEvent e) {
			// Only relevant for movement
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT)
				myApp.move(MOVE_LEFT);
			if (key == KeyEvent.VK_RIGHT)
				myApp.move(MOVE_RIGHT);

		}
		public void keyReleased(KeyEvent e) {
			// Do nothing
		}

	}


}
