import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Laser extends GameObject {
	
	public final static int SIZE = 16;
	private Handler handler;
	private WizardPlayer wizard;

	public Laser(float x, float y, float velX, float velY, Handler handler, WizardPlayer wizard) {
		super(x, y, ID.Attack);
		this.velX = velX;
		this.velY = velY;
		this.handler = handler;
		this.wizard = wizard;
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
					
					tempObject.health -= wizard.attackDamage;
					
					if(tempObject.health <= 0) {
						handler.removeObject(tempObject);
						wizard.increaseNumKills();
					}
					
					handler.removeObject(this);  
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, SIZE, SIZE);
		if(wizard.getLevel() == 2) {
			g.setColor(Color.magenta);
			g.fillRect((int)((x + 2) -  1.8*velX), (int) ((y + 2) - 1.8*velY) , SIZE-4, SIZE-4);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, SIZE, SIZE);
	}
	public Rectangle getBounds(int x, int y, int size) {
		return new Rectangle((int)x, (int)y, size, size);
	}

	public void attack(int dirX, int dirY) {
		//not required
	}

}
