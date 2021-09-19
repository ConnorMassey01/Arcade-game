import java.awt.Color;
import java.awt.Graphics;

public class PhoenixPlayer extends Player{
	
	public PhoenixPlayer(float x, float y, Handler handler) {
		super(x, y, handler);
		this.attackDamage = 30;
	}

	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, SIZE, SIZE);
		this.renderHealthBar(g);
		
		switch(level) {
		//outline character in light blue
		case 2:
			g.setColor(Color.yellow);
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
		float deltaX = (dirX - Fireball.SIZE/2) - (x + SIZE/2 - Fireball.SIZE/2);
		float deltaY = (dirY - Fireball.SIZE/2) - (y + SIZE/2 - Fireball.SIZE/2);
		float velX = (float) (8 * (deltaX/(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)))));;
		float velY = (float) (8 * (deltaY/(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)))));;
		handler.addObject(new Fireball(x + SIZE/2 - Fireball.SIZE/2, y + SIZE/2 - Fireball.SIZE/2, velX, velY, handler, this));
	}
}
