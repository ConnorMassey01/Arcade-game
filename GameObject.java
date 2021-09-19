import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected float x, y, velX, velY;
	protected ID id;
	protected int health;
	protected boolean canAttack;
	protected int speed;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.canAttack = true;
		this.speed = 5;
	}
	
	public GameObject(float x, float y, ID id, int health) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.health = health;
		this.canAttack = true;
		this.speed = 5;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds(); 
	public abstract void attack(int dirX, int dirY);
	
	public void setX(int x) {
		this.x = x; 
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.x;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public ID getID() {
		return this.id;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public int getSpeed() {
		return this.speed;
	}
	public float getVelX() {
		return this.velX;
	}
	public float getVelY() {
		return this.velY;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getHealth() {
		return this.health;
	}
	public boolean canAttack() {
		return canAttack;
	}
	public void setCanAttack(boolean attackDelay) {
		this.canAttack = attackDelay;
	}
}
