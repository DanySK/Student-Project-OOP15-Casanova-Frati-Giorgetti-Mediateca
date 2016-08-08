package controller;

import model.Model;
import model.ModelImpl;

/**
 * Class designed to start the program.
 */

public final class Application {

	private Application() {
	}

	/**
	 *
	 * @param args
	 *            Arguments passed to the main method
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		// application starter
		final Model m = new ModelImpl();
		final Controller c = new ControllerImpl(m);
		// System.out.println(System.getProperty("user.dir"));
		// things to implement
		// final View v = new ViewImpl(c);
		// c.setView(v);
		// v.startView();
	}

}
