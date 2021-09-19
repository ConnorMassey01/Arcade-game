import java.awt.Color;
import java.awt.Graphics;

public class IceGolemPlayer extends Player{
	public IceGolemPlayer(float x, float y, Handler handler) {
		super(x, y, handler);
		this.attackDamage = 50;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, SIZE, SIZE);
		this.renderHealthBar(g);
		
		switch(level) {
		//outline character in light blue
		case 2:
			g.setColor(Color.magenta);
			g.drawRect((int)x, (int)y, SIZE, SIZE);
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		}
		
	}

	public void attack(int dirX, int dirY) {
		//depending on the level change design of laser
		
		float deltaX = (dirX - Laser.SIZE/2) - (x + SIZE/2 - Laser.SIZE/2);
		float deltaY = (dirY - Laser.SIZE/2) - (y + SIZE/2 - Laser.SIZE/2);
		float velX = (float) (8 * (deltaX/(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)))));;
		float velY = (float) (8 * (deltaY/(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)))));;
		handler.addObject(new IceAttack(x + SIZE/2 - Laser.SIZE/2, y + SIZE/2 - Laser.SIZE/2, velX, velY, handler, this));
	}
}
