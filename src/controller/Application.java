package controller;

import model.Model;
import model.ModelImpl;
import view.View;
import view.ViewImpl;

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

		/*
		 * FileManager fm = new FileManager(); fm.read("archivio.utenti",
		 * "archivio.oggetti", m);
		 */
		final Controller c = new ControllerImpl();

		// things to implement
		final View v = new ViewImpl();
		v.setController(c);
		c.setView(v);
		v.startView();
	}

}
