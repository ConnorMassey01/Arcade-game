import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Fireball extends GameObject{
	public final static int SIZE = 12;
	private Handler handler;
	private PhoenixPlayer phoenix;
	
	public Fireball(float x, float y, float velX, float velY, Handler handler, PhoenixPlayer phoenix) {
		super(x, y, ID.Attack);
		this.velX = velX;
		this.velY = velY;
		this.handler = handler;
		this.phoenix = phoenix;
	}
	public void tick() {
		x += velX;
		y += velY;
		if(y <= 0 || y >= Game.HEIGHT) handler.removeObject(this);
		if(x <= 0 || x >= Game.WIDTH) handler.removeObject(this); 
		
		collision();
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Enemy) {
				//collision code
				if(getBounds().intersects(tempObject.getBounds())) {
					tempObject.health -= phoenix.attackDamage;
					
					if(tempObject.health <= 0) {
						handler.removeObject(tempObject);
						phoenix.increaseNumKills();
					}
					
					handler.removeObject(this);  
					
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillOval((int)x, (int)y, SIZE + 4*phoenix.getLevel(), SIZE + 4*phoenix.getLevel());
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, SIZE + 4*phoenix.getLevel(), SIZE + 4*phoenix.getLevel());
	}

	public void attack(int dirX, int dirY) {
		//not required
	}
}
