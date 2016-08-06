package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.text.View;

import model.Model;

/**
 * Class which implements the controller interface.
 *
 * @author
 *
 */

public class ControllerImpl implements Controller {
	private View v;
	private Model m;

	/*
	 * template per i futuri getter
	 *
	 * public void getValore(){ view.setValore("valore"); }
	 */

	/**
	 * Constructor for ControllerImpl.
	 *
	 * @param inputM
	 *            Model to be initialized
	 */
	public ControllerImpl(final Model inputM) {
		this.m = inputM;
		try {
			this.init("archivio.utente", "archivio.libri", "archivio.film");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method for the initialization of the model
	 *
	 * @param fileUser
	 * @param fileBooks
	 * @param fileMovies
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void init(final String fileUser, final String fileBooks, final String fileMovies)
			throws FileNotFoundException, IOException {
		FileManager fm = new FileManager();
		fm.read(fileUser, this.m);
		fm.read(fileBooks, this.m);
		fm.read(fileMovies, this.m);
	}

	/**
	 * Method who sets the View for the Controller.
	 *
	 */
	@Override
	public void setView(final View inputView) {
		this.v = inputView;
	}

	@Override
	public void login(final String userName, final String password) {

	}

	@Override
	public void getUserUsername() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setUserUsername() {
		// TODO Auto-generated method stub

	}
}
