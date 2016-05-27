package controller;

import javax.swing.text.View;

import model.Model;

/**
 * Class which implements the controller interface
 *
 * @author
 *
 */

public class ControllerImpl implements Controller {
	View v;
	Model m;

	/*
	 * template per i futuri getter
	 *
	 * public void getValore(){ view.setValore("valore"); }
	 */

	public ControllerImpl(final Model m) {
		this.m = m;
	}

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
