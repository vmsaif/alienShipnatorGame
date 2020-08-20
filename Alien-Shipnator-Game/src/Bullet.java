import java.awt.Color;

public class Bullet extends Sprite {
	public static final Color [][] BULLET = 
		{
				{Display.RED, Display.RED, Display.RED },
				{Display.RED, Display.RED, Display.RED },
				{Display.RED, Display.RED, Display.RED },
				{Display.RED, Display.RED, Display.RED },
				{Display.RED, Display.RED, Display.RED },
				{Display.RED, Display.RED, Display.RED },
		};
	
	public Bullet(int x, int y) {
		this.setxDot(x);
		this.setyDot(y);
	}
	public Color[][] getColorGrid(){
		return BULLET;
	}
}
