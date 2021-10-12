/**
 * @author 		Saif Mahmud
 * @version     March 20, 2019	
 */
import java.util.ArrayList;

public class SpaceInvaders {
	private int height;
	private int width;
	private int speed = 2;
	private int direction = 1;
	private ArrayList<Sprite> thisList = new ArrayList<Sprite>();
	private ArrayList<Sprite> theAliens  = new ArrayList<Sprite>();
	private ArrayList<Sprite> theBullet = new ArrayList<Sprite>();
	private ArrayList<Sprite> aliensBulletArray = new ArrayList<Sprite>();
	private ArrayList<Sprite> shipArray = new ArrayList<Sprite>();
	Ship ship;
	private int offset = 30;

	public SpaceInvaders(int height, int width) {
		this.height = height;
		this.width = width;
		//ship draw
		ship = new Ship(width/2,height-40);
		thisList.add(ship);
		shipArray.add(ship);
		//_________________________alien draw_________________________
		for(int i = 40; i<121; i = i+40) {
			int distance = 30;
				for(int j = 0; j < width/80; j++) {
					Alien aliens = new Alien(distance, i);
					distance += 50;
					thisList.add(aliens);
					theAliens.add(aliens);
				}
		}
	}
	public void update() {
		for(int i = 0; i<theAliens.size(); i++) {
			int curr = theAliens.get(i).getX();
				if(curr-offset+(speed*direction)<0) {
					direction = 1;
													//move the aliens by y axis
					for(int j = 0; j<theAliens.size(); j++) {
						int tempY = theAliens.get(j).getY();
						theAliens.get(j).setyDot(tempY+25);
					}
				}
				if(curr+offset+(speed*direction)>width) {
					direction = -1;
													//move the aliens by y axis
					for(int j = 0; j<theAliens.size(); j++) {
						int tempY = theAliens.get(j).getY();
						theAliens.get(j).setyDot(tempY+25);
					}
				}
		}
													//move the aliens by x axis
		for(int i = 0; i<theAliens.size(); i++) {
			Sprite temp = theAliens.get(i);
			temp.setxDot(temp.getX()+speed*direction);
		}
													//move the bullet by y axis
		if(theBullet.size()>0) {
			for(int i = 0; i<theBullet.size(); i++) {
				if(theBullet.get(i).getY()>0 ) {
					theBullet.get(i).setyDot(theBullet.get(i).getY()-5);
				}
				else {
					thisList.remove(theBullet.get(i));
					theBullet.remove(i);
				}
			}
			checkCollision();
		}
													//move the alien bullet by y axis
		AlienShootBack();
		
		if(aliensBulletArray.size()>0) {
			if(aliensBulletArray.get(0).getY()<height) {
				aliensBulletArray.get(0).setyDot(aliensBulletArray.get(0).getY()+4);
			}
			else {
				thisList.remove(aliensBulletArray.get(0));
				aliensBulletArray.remove(0);
			}
		}
		alienBulletCollide();
	}
	public ArrayList<Sprite> getItems(){
		return thisList;
	}
	public int status() {
		if(theAliens.size() == 0) {
			return Display.WIN;
		}
		if(shipArray.size()<1) {
			return Display.LOSE;
		}
		for(int i = 0; i < theAliens.size(); i++) {
			if(theAliens.get(i).getY() > height-(offset*2)) {
				return Display.LOSE;
			}
		}
		return Display.CONTINUE;
	}
	public void move(int direction) {
		if(direction == Display.MOVE_LEFT) {
			if(ship.getX()>(0+offset)) {
			ship.setxDot(ship.getX()-(speed+20));
			}
		}
		if(direction == Display.MOVE_RIGHT) {
			if(ship.getX()<(width-offset)) {
			ship.setxDot(ship.getX()+(speed+20));
			}
		}
	}
	public void shoot() {
		if(theBullet.size() < 2) { // change this 2 to have burst fires........
			Bullet myBullet1 = new Bullet(ship.getX(),ship.getY()-15);
			thisList.add(myBullet1);
			theBullet.add(myBullet1);
		}
	}
	private void checkCollision() {
		//for bullet 1 and 2
		if(theBullet.size()>0) {
		int bullet1x = theBullet.get(0).getX();
		int bullet1y = theBullet.get(0).getY();
		int bullet2x , bullet2y ;
		//get the values of aliens
			for (int i = 0; i < theAliens.size(); i++) {
				int alienX = theAliens.get(i).getX();
				int alienY = theAliens.get(i).getY();
				double distance1 = Math.sqrt(Math.pow((bullet1x-alienX), 2)+Math.pow((bullet1y-alienY), 2));
				
				if(theBullet.size()>1) {
					bullet2x = theBullet.get(1).getX();
					bullet2y = theBullet.get(1).getY();
					double distance2 = Math.sqrt(Math.pow((bullet2x-alienX), 2)+Math.pow((bullet2y-alienY), 2));
					if(distance2<25) {
						thisList.remove(theBullet.get(1));
						thisList.remove(theAliens.get(i));
						theBullet.remove(1);
						theAliens.remove(i);
					}
				}
				if(distance1<25) {
					thisList.remove(theBullet.get(0));
					thisList.remove(theAliens.get(i));
					theBullet.remove(0);
					theAliens.remove(i);
				}
			}
		}
	}
	private void AlienShootBack() {
		int size = theAliens.size();
		double random = size*Math.random();
			if(aliensBulletArray.size()<1) {
				AlienBullet alienBulletOBJ = new AlienBullet(theAliens.get((int)random).getX(),theAliens.get((int)random).getY());
				thisList.add(alienBulletOBJ);
				aliensBulletArray.add(alienBulletOBJ);
			}
	}
	private void alienBulletCollide() {
		if(aliensBulletArray.size()>0) {
			int alienBullet1x = aliensBulletArray.get(0).getX();
			int alienBullet1y = aliensBulletArray.get(0).getY();
			int shipX = ship.getX();
			int shipY = ship.getY();
			double distance = Math.sqrt(Math.pow((alienBullet1x-shipX), 2)+Math.pow((alienBullet1y-shipY), 2));
				if(distance<20) {
					thisList.remove(aliensBulletArray.get(0));
					thisList.remove(ship);
					shipArray.remove(ship);
					aliensBulletArray.remove(0);
				}
		}
	}
}