import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
private static final long serialVersionUID = -1442798787354930462L;
	
	public static final int WIDTH = 800, HEIGHT = WIDTH/12 * 9;
	public static final String TITLE = "Battle Arena";
	
	//instance variables for running the game
	private Thread thread;
	private boolean running = false;
	
	//handler to render and adjust positions of all game elements
	private Handler handler;
	//class to create a menu
	private Menu menu;
	//class to handler enemy spawning
	private EnemySpawner enemySpawner;
	
	private int level;
	private int gameOverDelay;
	
	private static STATE gameState = STATE.Menu;
	
	public Game()
	{
		level = 0;
		gameOverDelay = 0;
		handler = new Handler(this);
		menu = new Menu(handler, this);
		enemySpawner = new EnemySpawner(handler, this);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, this, menu));
		
		new Window(WIDTH, HEIGHT, TITLE, this);
	}
	public synchronized void start() {
		thread = new Thread(this);
		//calls the run method to start the game
		thread.start(); 
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//request focus so any key or mouse input is registered immediately
		this.requestFocus();
		
		//run the game at 60 FPS
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(gameState == STATE.Menu) {
			menu.tick();
		}
		else if(gameState == STATE.Game) {
			//adjust the positions of all the game objects
			enemySpawner.tick();
			handler.tick();
		}
		else if(gameState == STATE.GameOver) {
			if(gameOverDelay > 120) {
				Game.setState(STATE.Menu);
				Menu.setCharacterSelectedFalse();
				enemySpawner.resetEnemySpawner();
				gameOverDelay = 0;
				level = 1;
				handler.removeAllObjects();
			}
			gameOverDelay++;
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		//Initialize the graphics variable
		Graphics g = bs.getDrawGraphics();
		//cover the screen in back
		//----------------------------MAYBE ADD BACKGROUND TERRAIN IN FUTURE----------------------
		g.setColor(Color.black);
		g.fillRect(0,  0, WIDTH + 1, HEIGHT + 1);
		
		if(gameState == STATE.Menu) {
			menu.render(g);
		}
		else if(gameState == STATE.Game) {
			//render all game objects
			handler.render(g);
		}
		else if(gameState == STATE.GameOver) {
			g.setColor(Color.WHITE);
			Font fnt = new Font("arial", 1, 50);
			g.setFont(fnt);
			g.drawString("GAME OVER", Game.WIDTH/2 - 165, 100);
			Font fnt2 = new Font("arial", 1, 30);
			Player player = (Player) handler.object.getFirst();
			g.setFont(fnt2);
			g.drawString("Enemies destroyed: " + player.getNumKills(), Game.WIDTH/2 - 170, 160);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int pos, int min, int max) {
		if(pos >= max) {
			return max;
		}
		else if(pos <= min) {
			return min;
		}
		else {
			return pos;
		}
	}
	
	public STATE getState() {
		return gameState;
	}
	public static void setState(STATE newState) {
		gameState = newState;
	}
	
	public void increaseGameLevel() {
		this.level++;
	}
	public int getGameLevel() {
		return this.level;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
