
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu{
	
	private int mouseX;
	private int mouseY;
	
	private static boolean characterSelected;
	private int character;
	private int x;
	private int velX;
	
	private Handler handler;
	private Game game;
	
	public Menu(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		characterSelected = false;
		this.x = 200;
		this.velX = 6;
	}
	
	public void render(Graphics g) {
		//NEED BUTTON TO SELECT CHARACTER, START GAME, SHOW RULES, QUIT
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		//center line for alignment
		//g.setColor(Color.white);
		//g.drawLine(Game.WIDTH/2, 0, Game.WIDTH/2, Game.HEIGHT);
		
		//game title
		g.setFont(fnt);
		g.setColor(Color.red);
		g.drawString("Battle Arena", Game.WIDTH/2 - 145, 100);
		
		g.setFont(fnt2);
		if(characterSelected) {
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2 - 50, 150, 100, 50);
			g.drawString("Play", Game.WIDTH/2 - 32, 185);
			switch(character) {
			case 1:
				g.setColor(Color.blue);
				break;
			case 2:
				g.setColor(Color.red);
				break;
			case 3:
				g.setColor(Color.white);
				break;
			}
			g.fillRect(x, 240, 80, 80);
		}
		else {
			g.setColor(Color.white);
			g.drawString("Select your champion",  Game.WIDTH/2 - 150, 180);
			
			g.drawString("Wizard", Game.WIDTH/2 - 220, 250);
			g.drawString("Phoenix", Game.WIDTH/2 - 60, 250);
			g.drawString("Ice Golem", Game.WIDTH/2 + 98, 250);
			
			//Wizard
			g.setColor(Color.blue);
			g.fillRect(Game.WIDTH/2 - 210, 270, 80, 80);
			//show abilities below, i.e health, attack
			
			//Phoenix
			g.setColor(Color.red);
			g.fillRect(Game.WIDTH/2 - 40, 270, 80, 80);
			//show abilities
			
			//ice golem
			g.setColor(Color.white);
			g.fillRect(Game.WIDTH/2 + 130, 270, 80, 80);
			
		}
		
	}
	
	public void tick() {
		
		if(characterSelected) {
			x += velX;
			if(x > Game.WIDTH - 200 || x < 200) {
				velX *= -1;
			}
			if(mouseOver(Game.WIDTH/2 - 50, 150, 100, 50)) {
				switch(character) {
				case 1:
					handler.addObject(new WizardPlayer(Game.WIDTH/2 - Player.SIZE/2, Game.HEIGHT/2 - Player.SIZE/2, handler));
					break;
				case 2:
					handler.addObject(new PhoenixPlayer(Game.WIDTH/2 - Player.SIZE/2, Game.HEIGHT/2 - Player.SIZE/2, handler));
					break;
				case 3:
					handler.addObject(new IceGolemPlayer(Game.WIDTH/2 - Player.SIZE/2, Game.HEIGHT/2 - Player.SIZE/2, handler));
					break;
				}
				game.setState(STATE.Game);
			}
		}
		else {
			if(mouseOver(Game.WIDTH/2 - 210, 270, 80, 80)) {
				//System.out.println("wizard selected");
				character = 1;
				characterSelected = true;
			}
			else if(mouseOver(Game.WIDTH/2 - 40, 270, 80, 80)) {
				//System.out.println("phoenix selected");
				character = 2;
				characterSelected = true;
			}
			else if(mouseOver(Game.WIDTH/2 + 130, 270, 80, 80)) {
				//System.out.println("ice golem selected");
				character = 3;
				characterSelected = true;
			}
		}
	}
	
	private boolean mouseOver(int x, int y, int width, int height) {
		if(mouseX > x && mouseX < x + width) {
			if(mouseY > y && mouseY < y + height) {
				return true;
			}
		}
		return false;
	}
	
	public void setMouseX(int x) {
		mouseX = x;
	}
	public void setMouseY(int y) {
		mouseY = y;
	}
	
	public static void setCharacterSelectedFalse() {
		characterSelected = false;
	}
}
