import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Player extends GameObject{
	
	//ATTACK SPEED VARIABLE
	
	protected Handler handler;
	protected static int SIZE = 40;
	protected int level;
	protected int attackDamage;
	protected int attackDelay;
	protected int numKills;
	
	public Player(float x, float y, Handler handler) {
		super(x, y, ID.Player, 100);
		this.handler = handler;
		this.level = 1;
		this.attackDelay = 0;
		numKills = 0;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH - SIZE);
		y = Game.clamp((int)y, 0, Game.HEIGHT - SIZE);
		
		if (this.canAttack == false) {
			attackDelay++;
		}
		if(attackDelay > 20) {
			this.setCanAttack(true);
			attackDelay = 0;
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, SIZE, SIZE);
	}
	
	//specific to each character design
	public abstract void render(Graphics g);
	//specific to each character attack options
	public void renderHealthBar(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y - 10, SIZE, 7);
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y - 10, (int)(health*0.4), 7);
	}
	public void increaseLevel() {
		this.level++;
		this.health = 100;
		this.speed++;
		this.attackDamage += 10;
		Message message = new Message(0, Game.HEIGHT/2 - 25, handler, "level up!", 10);
		handler.addObject(message);
	}
	public int getLevel() {
		return level;
	}
	public void increaseNumKills() {
		numKills++;
	}
	public int getNumKills() {
		return numKills;
	}
	
}
