import java.awt.Color;
import java.awt.Graphics;

public class BasicEnemy extends Enemy{
	
	private static final int speed = 3;

	public BasicEnemy(float x, float y, int size, Handler handler) {
		super(x, y, size, handler);
		this.attackDamage = 10;
	}

	public void render(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect((int)x, (int)y, size, size);
		this.renderHealthBar(g);
	}

	public void attack(int dirX, int dirY) {
		float deltaX = (dirX - BasicEnemyAttack.SIZE/2) - (x + size/2 - BasicEnemyAttack.SIZE/2);
		float deltaY = (dirY - BasicEnemyAttack.SIZE/2) - (y + size/2 - BasicEnemyAttack.SIZE/2);
		float velX = (float) (8 * (deltaX/(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)))));;
		float velY = (float) (8 * (deltaY/(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)))));;
		handler.addObject(new BasicEnemyAttack(x + size/2 - BasicEnemyAttack.SIZE/2, y + size/2 - BasicEnemyAttack.SIZE/2, velX, velY, handler));
	}
	
	public void move() {
		Player player = null;
		float xVector;
		float yVector;
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Player) {
				player = (Player) handler.object.get(i);
				break;
			}
		}
		//ADD TRY CATCH LATER
		if(player == null) System.out.println("error");
		
		//calculate the distance the enemy is away from the player
		float distance = (float) Math.sqrt(Math.pow(player.x - x, 2) + Math.pow(player.y - y, 2));
		//move towards the player if further than 100 pixels away
		if(distance > 250) {
			xVector = (player.x - x)/distance;
			yVector = (player.y - y)/distance;
			velX = xVector * speed;
			velY = yVector * speed;
		}
		else {
			velX = 0;
			velY = 0;
		}
		
	}

}
