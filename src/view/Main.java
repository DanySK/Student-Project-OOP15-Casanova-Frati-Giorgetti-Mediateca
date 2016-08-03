package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	private final static int SCREEN_LENGHT = 1280;
	private final static int SCREEN_WIDTH = 920;

	public static void main(final String[] args) {
		final JFrame mainFrame = new JFrame();
		final Menu menu = new MenuImpl(Main.SCREEN_LENGHT, Main.SCREEN_WIDTH,
				mainFrame);
		final SelectAccountType choose = new SelectAccountTypeImpl();
		final UserLogin login = new UserLoginImpl();

		mainFrame.setTitle("Mediateca");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(Main.SCREEN_LENGHT, Main.SCREEN_WIDTH);
		mainFrame.add(menu.getPanel());

		mainFrame.setVisible(true);
	}
}
