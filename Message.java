import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Message extends GameObject{
	private String message;
	private Handler handler;
	private int speed;
	public Message(float x, float y, Handler handler, String message, int speed) {
		super(x, y, ID.Message);
		this.handler = handler;
		this.message = message;
		this.speed = speed;
	}

	public void tick() {
		x += speed;
		if(x > Game.WIDTH + 50) {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 40);
		g.setFont(fnt);
		g.setColor(Color.WHITE);
		g.drawString(message, (int)x, (int)y);
	}
	
	//not required
	public Rectangle getBounds() {
		
		return null;
	}
	//not required
	public void attack(int dirX, int dirY) {
		
	}
	

}
