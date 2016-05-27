package controller;

import javax.swing.text.View;

/**
 * Interface for a generic controller
 *
 * @author
 *
 */

public interface Controller {
	public void login(String userName, String password);

	public void getUserUsername();

	public void setUserUsername();

	public void setView(View inputView);
}
