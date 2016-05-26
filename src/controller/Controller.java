package controller;

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
}
