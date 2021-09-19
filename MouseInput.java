import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	private Game game;
	private Menu menu;
	
	public MouseInput(Handler handler, Game game, Menu menu) {
		this.handler = handler;
		this.game = game;
		this.menu = menu;
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(game.getState() == STATE.Game) {
			Player player = (Player) handler.object.getFirst();
			if(player.canAttack()) {
				player.attack(mouseX, mouseY);
				player.setCanAttack(false);
			}
		}
		else if(game.getState() == STATE.Menu) {
			menu.setMouseX(mouseX);
			menu.setMouseY(mouseY);
		}
	}
	
}
