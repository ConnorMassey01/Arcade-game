import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

public class IceAttack extends GameObject{

	private Handler handler;
	private IceGolemPlayer iceGolem;
	private float maxDistance;
	private float distance;
	private int[] xPoints = {0, 0, 0, 0};
	private int[] yPoints = {0, 0, 0, 0};
	
	public IceAttack(float x, float y, float velX, float velY, Handler handler, IceGolemPlayer iceGolem) {
		super(x, y, ID.Attack);
		this.velX = velX;
		this.velY = velY;
		this.handler = handler;
		this.iceGolem = iceGolem;
		maxDistance = 150;
		distance = 0;
	}
	
	public void tick() {
		//only allow the attack to move for 150 pixels
		distance += Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
		if(distance >= maxDistance) handler.removeObject(this);
		//update the x and y positions
		x += velX;
		y += velY;
		//remove object if it exits the screen
		if(y <= 0 || y >= Game.HEIGHT) handler.removeObject(this);
		if(x <= 0 || x >= Game.WIDTH) handler.removeObject(this); 
		
		collision();
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Enemy) {
				//collision code
				if(getPolyBounds().intersects(tempObject.getBounds())) {
					tempObject.health -= iceGolem.attackDamage;
					
					if(tempObject.health <= 0) {
						handler.removeObject(tempObject);
						iceGolem.increaseNumKills();
					}
					
					handler.removeObject(this);  
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		double directionAngle = Math.atan2((double) velX, (double) velY);
		double xOffset = -10 * Math.sin(directionAngle);
		double yOffset = 10 * Math.cos(directionAngle);
		//System.out.println("angle: " + directionAngle);
		//System.out.println("xOffset: " + xOffset);
		//System.out.println("yOffset: " + yOffset);
		xPoints[0] = (int)(x - 40*Math.cos(directionAngle) + xOffset);
		xPoints[1] = (int)(x + 40*Math.cos(directionAngle) + xOffset);
		xPoints[2] = (int)(x + 40*Math.cos(directionAngle) - xOffset);
		xPoints[3] = (int)(x - 40*Math.cos(directionAngle) - xOffset);
		yPoints[0] = (int)(y + 40*Math.sin(directionAngle) - yOffset);
		yPoints[1] = (int)(y - 40*Math.sin(directionAngle) - yOffset);
		yPoints[2] = (int)(y - 40*Math.sin(directionAngle) + yOffset);
		yPoints[3] = (int)(y + 40*Math.sin(directionAngle) + yOffset);
		g.setColor(Color.cyan);
		g.fillPolygon(new Polygon(xPoints, yPoints, 4));
	}

	public Polygon getPolyBounds() {
		return new Polygon(xPoints, yPoints, 4);
	}
	
	public Rectangle getBounds() {
		return null;
	}
	public void attack(int dirX, int dirY) {
		//not required
	}
}
