package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Map.Entry;

import model.Model;
import model.item.ItemGenre;
import model.item.Language;
import model.user.UserImpl;
import view.View;

/**
 * Class which implements the controller interface.
 *
 * @author
 *
 */

public class ControllerImpl implements Controller {
	private View v;
	private Model m;
	private UserImpl actualUser;
	private FileManager fm = new FileManager();
	private String username;
	private String password;

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
	 * @throws Exception
	 */
	public ControllerImpl() throws Exception {
		// this.init("archivio.utenti", "archivio.oggetti", this.m);

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1994);
		calendar.set(Calendar.MONTH, 3);
		calendar.set(Calendar.DAY_OF_MONTH, 6);
		try {

			this.m.registerUser("Enrico", "Casanova", calendar, "Dakaiden", "Arctica64", "enrico.casanova@dadas.it",
					"334534534534", new ArrayList<ItemGenre>(), new ArrayList<ItemGenre>());
			this.m.registerBook("Il signore degli anelli", 1945, "J.R.R. Tolkien", Language.ENGLISH, "23123121",
					ItemGenre.ADVENTURE_HISTORY, "Gesù", 0011, 100000);
			this.m.registerMovie("Star Trek", 2009, "Bad Robot", "J.J. Abrams", Language.ENGLISH, ItemGenre.FANTASY,
					120, true, 1000000);

			this.fm.write("archivio.utenti", this.m);
			this.fm.write("archivio.oggetti", this.m);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method for the initialization of the model.
	 *
	 * @param fileUser
	 * @param fileBooks
	 * @param fileMovies
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void init(final String fileUser, final String fileItem, final Model model)
			throws FileNotFoundException, IOException {

		this.fm.read(fileUser, fileItem, model);

	}

	@Override
	public void login() {
		String username = this.v.getUsername();
		String password = this.v.getPassword();
		Map<Integer, UserImpl> map = this.m.getUserArchive();
		for (Entry<Integer, UserImpl> entry : map.entrySet()) {
			if ((entry.getValue().getUsername() == username) && (entry.getValue().getPassword() == password)) {
				this.actualUser = entry.getValue();
				break;
			}
		}
		// lancia messaggio cattivo

	}

	public void sendMessage(final String string) {

	}

	@Override
	public void getUserUsername() {
		// TODO Auto-generated method stub

	}

	public void getUserPassword() {

	}

	/**
	 * Method who sets the View for the Controller.
	 *
	 */
	@Override
	public void setView(final view.View v) {
		// TODO Auto-generated method stub
		this.v = v;
	}

	@Override
	public void setUserUsername() {
		// TODO Auto-generated method stub

	}
}
