/**
 * @author 		Saif Mahmud
 * @version     March 20, 2019	
 */
import java.awt.Color;

public class AlienBullet extends Sprite{
	public static final Color [][] ALIEN_BULLET = 
//		{
//				{null, null, null, Display.BLUE, Display.BLUE },
//				{null, null, Display.BLUE, Display.BLUE, null },
//				{null, Display.BLUE, Display.BLUE, null, null },
//				{Display.BLUE, Display.BLUE, null, null, null },
//				{Display.BLUE, Display.BLUE, null, null, null },
//				{null, Display.BLUE, Display.BLUE, null, null },
//				{null, null, Display.BLUE, Display.BLUE, null },
//				{null, null, null, Display.BLUE, Display.BLUE },
//				{null, null, null, Display.BLUE, Display.BLUE },
//				{null, null, Display.BLUE, Display.BLUE, null },
//				{null, Display.BLUE, Display.BLUE, null, null },
//				{Display.BLUE, Display.BLUE, null, null, null },
//		};
			
			 //throw alien
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
	
	public AlienBullet(int x, int y) {
		this.setxDot(x);
		this.setyDot(y);
	}
	public Color[][] getColorGrid(){
		return Alien.spriteArray[Alien.ran];
	}
}
