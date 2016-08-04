package controller;

import java.io.File;

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
		// init(fileuser, filebook);
	}

	public void init(final File file1, final File file2) {
		// filemanager.readfile(file1)
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
