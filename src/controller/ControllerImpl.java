package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import model.Model;
import model.ModelImpl.TypeSearch;
import model.item.ArchiveImpl;
import model.item.ArchiveImpl.TypeItem;
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
		this.init("archivio.utenti", "archivio.oggetti", this.m);

		/*
		 * GregorianCalendar calendar = new GregorianCalendar();
		 * calendar.set(Calendar.YEAR, 1994); calendar.set(Calendar.MONTH, 3);
		 * calendar.set(Calendar.DAY_OF_MONTH, 6);
		 *
		 * try {
		 *
		 * /* this.m.registerUser("Enrico", "Casanova", calendar, "Dakaiden",
		 * "Arctica64", "enrico.casanova@dadas.it", "334534534534", new
		 * ArrayList<ItemGenre>(), new ArrayList<ItemGenre>());
		 * this.m.registerBook("Il signore degli anelli", 1945, "J.R.R. Tolkien"
		 * , Language.ENGLISH, "23123121", ItemGenre.ADVENTURE_HISTORY, "Gesù",
		 * 0011, 100000); this.m.registerMovie("Star Trek", 2009, "Bad Robot",
		 * "J.J. Abrams", Language.ENGLISH, ItemGenre.FANTASY, 120, true,
		 * 1000000);
		 *
		 *
		 * this.fm.write("archivio.utenti", this.m);
		 * this.fm.write("archivio.oggetti", this.m);
		 *
		 * } catch (FileNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
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
		final String username = this.v.getUsername();
		final String password = this.v.getPassword();
		Map<Integer, UserImpl> map = this.m.getUserArchive();
		for (Entry<Integer, UserImpl> entry : map.entrySet()) {
			if ((entry.getValue().getUsername() == username) && (entry.getValue().getPassword() == password)) {
				this.actualUser = entry.getValue();
				break;
			}
		}
		// lancia messaggio cattivo

	}

	public void itemElaboration() throws Exception {
		TypeItem ty = null;
		for (ArchiveImpl.TypeItem y : ArchiveImpl.TypeItem.values()) {
			if (y.toString().equals(this.v.getItemFilter())) {
				ty = y;
			}
		}

		TypeSearch ts = null;
		for (TypeSearch s : TypeSearch.values()) {
			if (s.toString().equals(this.v.getSearchFilter())) {
				ts = s;

			}
		}

		Object searchText = this.v.getSearchText();

		Set<String> set = new HashSet<>();

		for (Integer i : this.m.filtersItem(this.m.getAllItemId(ty), ts, searchText)) {
			set.add(this.m.getRequiredItem(i).toString());
		}

		this.v.setFilteredList(set);
	}

	public void sendMessage(final String string) {

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
}
