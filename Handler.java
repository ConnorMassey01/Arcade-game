import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

//renders and adjusts positions of all GameObjects
public class Handler {
	private Game game;
	private int enemies;
	boolean levelUp;
	public Handler(Game game) {
		this.game = game;
		this.levelUp = true;
	}
	
	//list to hold unknown number of game objects
	LinkedList<GameObject> object = new LinkedList<>();
		
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
		//increase the level of he player once they reach the fifth level
		if (game.getGameLevel() == 5 && levelUp) {
			increaseLevel();
			levelUp = false;
		}
	}
		
	public void render(Graphics g) {
		enemies = 0;
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
			//count number of enemies on screen
			if(tempObject.id == ID.Enemy) {
				enemies++;
			}
		}
		//render game level
		Font fnt = new Font("arial", 1, 30);
		g.setColor(Color.WHITE);
		g.setFont(fnt);
		g.drawString("Level: " + game.getGameLevel(), 20, 40);
		g.drawString("Enemies: " + enemies, 20, 80);
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void removeAllObjects() {
		object.clear();
	}
	
	public void increaseLevel() {
		Player player = (Player) object.getFirst();
		player.increaseLevel();
	}
}
