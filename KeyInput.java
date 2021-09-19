import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	private boolean W = false;
	private boolean A = false;
	private boolean S = false;
	private boolean D = false;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//MUST ENSURE PLAYER IS FIRST ITEM IN LIST
		GameObject player = handler.object.getFirst();
		if(key == KeyEvent.VK_W) {
			W = true;
			player.setVelY(-player.getSpeed());
		}
		if(key == KeyEvent.VK_S) {
			S = true;
			player.setVelY(player.getSpeed());
		}
		if(key == KeyEvent.VK_D) { 
			D = true;
			player.setVelX(player.getSpeed());
		
		}
		if(key == KeyEvent.VK_A) { 
			A = true;
			player.setVelX(-player.getSpeed());
		
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		//MUST ENSURE PLAYER IS FIRST ITEM IN LIST
		GameObject player = handler.object.getFirst();
		if(key == KeyEvent.VK_W) { 
			W = false;
			if(S) {
				player.setVelY(player.getSpeed());
			}
			else {
				player.setVelY(0);
			}
		}
		if(key == KeyEvent.VK_S) { 
			S = false;
			if(W) {
				player.setVelY(-player.getSpeed());
			}
			else {
				player.setVelY(0);
			}
		}
		if(key == KeyEvent.VK_D) { 
			D = false;
			if(A) {
				player.setVelX(-player.getSpeed()); 
			}
			else {
				player.setVelX(0);
			}
		}
		if(key == KeyEvent.VK_A) { 
			A = false;
			if(D) {
				player.setVelX(player.getSpeed());
			}
			else {
				player.setVelX(0);
			}
		}
	}
}
