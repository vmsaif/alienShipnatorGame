import java.awt.Color;

public class Alien extends Sprite {
	public static int ran;
	private static final Color green2 = new Color(34,139,34);
	public static final Color [][] ALIEN_SHAPE_RED = 
		{
				{null, null, Display.RED, null, null, null, null, null, Display.RED, null, null},
				{null, null, null, Display.RED, null, null, null, Display.RED, null, null, null},
				{null,null,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,null,null},
				{null,Display.RED,Display.RED,null,Display.RED,Display.RED,Display.RED,null,Display.RED,Display.RED,null},
				{Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED},
				{Display.RED,null,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,Display.RED,null,Display.RED},
				{Display.RED,null,Display.RED,null,null,null,null,null,Display.RED,null,Display.RED},
				{null,null,null,Display.RED,Display.RED,null,Display.RED,Display.RED,null,null,null}
		};
	public static final Color [][] ALIEN_SHAPE_BLUE = 
		{
				{null, null, Display.BLUE, null, null, null, null, null, Display.BLUE, null, null},
				{null, null, null, Display.BLUE, null, null, null, Display.BLUE, null, null, null},
				{null,null,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,null,null},
				{null,Display.BLUE,Display.BLUE,null,Display.BLUE,Display.BLUE,Display.BLUE,null,Display.BLUE,Display.BLUE,null},
				{Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE},
				{Display.BLUE,null,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,Display.BLUE,null,Display.BLUE},
				{Display.BLUE,null,Display.BLUE,null,null,null,null,null,Display.BLUE,null,Display.BLUE},
				{null,null,null,Display.BLUE,Display.BLUE,null,Display.BLUE,Display.BLUE,null,null,null}
		};
	public static final Color [][] ALIEN_SHAPE_green2 = 
		{
				{null, null, green2, null, null, null, null, null, green2, null, null},
				{null, null, null, green2, null, null, null, green2, null, null, null},
				{null,null,green2,green2,green2,green2,green2,green2,green2,null,null},
				{null,green2,green2,null,green2,green2,green2,null,green2,green2,null},
				{green2,green2,green2,green2,green2,green2,green2,green2,green2,green2,green2},
				{green2,null,green2,green2,green2,green2,green2,green2,green2,null,green2},
				{green2,null,green2,null,null,null,null,null,green2,null,green2},
				{null,null,null,green2,green2,null,green2,green2,null,null,null}
		};
	
	public Alien(int x, int y) {
		this.setxDot(x);
		this.setyDot(y);
		ran = (int) (4*Math.random());
	}
	public static final Color[][][] spriteArray = {ALIEN_SHAPE_RED, ALIEN_SHAPE_BLUE, 
													ALIEN_SHAPE_green2, Display.ALIEN_SHAPE};
	
	public Color[][] getColorGrid(){
		return spriteArray[ran];
	}
}