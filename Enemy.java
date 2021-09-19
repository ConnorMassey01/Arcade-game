import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy extends GameObject{
	
	protected Handler handler;
	protected int attackDamage;
	protected int size;
	private int count;
	private Player player;

	//health automatically set to 100
	public Enemy(float x, float y, int size, Handler handler) {
		super(x, y, ID.Enemy, 100);
		this.size = size;
		this.handler = handler;
		this.count = 40;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		//updates velX and velY to travel towards player
		this.move();
		//attack every 2 seconds
		if(count == 60) {
			for(int i = 0; i < handler.object.size(); i++) {
				if(handler.object.get(i).getID() == ID.Player) {
					player = (Player) handler.object.get(i);
					break;
				}
			}
			this.attack((int) player.x, (int) player.y);
			count = 0;
		}
		
		
		//x = Game.clamp((int)x, 0, Game.WIDTH - size);
		//y = Game.clamp((int)y, 0, Game.HEIGHT - size);
		
		count++;
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, size, size);
	}
	
	public abstract void render(Graphics g);
	
	public void renderHealthBar(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y - 10, size, 7);
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y - 10, (int)(health*size/100), 7);
	}
	
	public abstract void move();

}
