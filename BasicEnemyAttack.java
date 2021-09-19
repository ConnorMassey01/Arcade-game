import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class BasicEnemyAttack extends GameObject{
	public final static int SIZE = 16;
	private Handler handler;
	private Scanner keyboard;
	private int attackDamage;
	
	public BasicEnemyAttack(float x, float y, float velX, float velY, Handler handler) {
		super(x, y, ID.Attack);
		this.velX = velX;
		this.velY = velY;
		this.handler = handler;
		this.attackDamage = 20;
		keyboard = new Scanner(System.in);
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
			if(tempObject.getID() == ID.Player) {
				//collision code
				if(getBounds().intersects(tempObject.getBounds())) {
		
					tempObject.health -= attackDamage;
					
					if(tempObject.health <= 0) {
						//show game over and wait for input to go back to menu
						Game.setState(STATE.GameOver);
						break;
						
					}
					
					handler.removeObject(this); 
				}
			}
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, SIZE, SIZE);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, SIZE, SIZE);
	}
	
	public void attack(int dirX, int dirY) {
		//not required
	}
}
