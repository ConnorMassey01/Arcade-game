import java.util.Random;
public class EnemySpawner {
	private Random rnd = new Random();
	private Handler handler;
	private Game game;
	private int numEnemies;
	private int delay;
	private boolean spawnEnemies;
	public EnemySpawner(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		this.delay = 60;
		this.numEnemies = 0;
		this.spawnEnemies = true;
	}
	public void tick() {
		if(spawnEnemies && numEnemies < game.getGameLevel()) {
			//spawn an enemy to a random location
			if(delay >= 60 ) {
				spawnEnemy();
				delay = 0;
				numEnemies++;
			}
			delay++;
		}
		else if(numEnemies == game.getGameLevel()) {
			spawnEnemies = false;
		}
		if(countEnemies() == 0 && numEnemies == game.getGameLevel()) {
			game.increaseGameLevel();
			spawnEnemies = true;
			numEnemies = 0;
		}
	}
	private int countEnemies() {
		int count = 0;
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).id == ID.Enemy) {
				count++;
			}
		}
		return count;
	}
	private void spawnEnemy() {
		int location = rnd.nextInt(3);
		int xSpawn;
		int ySpawn;
		switch (location) {
		case 0: //top of screen
			xSpawn = rnd.nextInt(Game.WIDTH + 200) - 100;
			ySpawn = -1 * (rnd.nextInt(300) + 100);
			break;
		case 1: // bottom of screen
			xSpawn = rnd.nextInt(Game.WIDTH + 200) - 100;
			ySpawn = rnd.nextInt(300) + Game.HEIGHT + 100;
			break;
		case 2: // left of screen
			xSpawn = -1 * (rnd.nextInt(300) + 100);
			ySpawn = rnd.nextInt(Game.HEIGHT + 200) - 100;
			break;
		case 3: // right of screen
			xSpawn = rnd.nextInt(300) + Game.WIDTH + 100;
			ySpawn = rnd.nextInt(Game.HEIGHT + 200) - 100;
			break;
		default:
			xSpawn = 0;
			ySpawn = 0;
		};
		//make it so it is added to random location			
		handler.addObject(new BasicEnemy(xSpawn, ySpawn, 50, handler));
	}
	
	public void resetEnemySpawner() {
		this.delay = 60;
		this.numEnemies = 0;
		this.spawnEnemies = true;
	}
}
