import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = -8255319694373975038L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setMaximumSize(new Dimension(width + 15, height + 37));
		frame.setMinimumSize(new Dimension(width + 15, height + 37));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //game window centered on screen
		frame.add(game);
		frame.setVisible(true);
		game.start();   
	}
	
}
