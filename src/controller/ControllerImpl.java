package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Optional;

import model.Model;
import model.ModelImpl;
import model.Pair;
import model.item.ArchiveImpl;
import model.item.ArchiveImpl.TypeItem;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.user.User;
import model.user.UserImpl;
import utils.ItemGenre;
import utils.Language;
import utils.TypeSearch;
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
	// after the login, the corrispondent user will be saved here
	private UserImpl actualUser;
	private Map<Integer, Pair<Boolean, Optional<Integer>>> actualLoanArchive;

	// constants for I/O
	private static final String FILENAMEUSER = "archivio.utenti";
	private static final String FILENAMEITEM = "archivio.oggetti";
	private static final String FILENAMESTUDYROOM = "archivio.aulastudio";

	private FileManager fm = new FileManager();

	/*
	 * template per i futuri getter
	 *
	 * public void getValore(){ view.setValore("valore"); }
	 */

	/**
	 * Constructor for ControllerImpl.
	 *
	 * @throws Exception
	 */
	public ControllerImpl() throws Exception {
		File fileItem = new File(this.fm.getPath() + ControllerImpl.FILENAMEITEM);
		File fileUser = new File(this.fm.getPath() + ControllerImpl.FILENAMEUSER);
		File fileStudyRoom = new File(this.fm.getPath() + ControllerImpl.FILENAMESTUDYROOM);

		Map<Integer, UserImpl> userArchive2 = this.m.getUserArchive();

		if ((fileItem.exists() && !fileItem.isDirectory()) && (fileUser.exists() && !fileUser.isDirectory())
				&& (fileStudyRoom.exists() && !fileStudyRoom.isDirectory())) {
			Map<Integer, UserImpl> userArchive = this.fm.readArchiveUserFromFile(ControllerImpl.FILENAMEUSER);
			Map<Integer, Pair<ItemImpl, ItemInfo>> itemArchive = this.fm
					.readArchiveItemFromFile(ControllerImpl.FILENAMEITEM);
			Map<GregorianCalendar, ArrayList<Integer>> studyRoomArchive = this.fm
					.readStudyRoomFromFile(ControllerImpl.FILENAMESTUDYROOM);
			this.m = new ModelImpl(itemArchive, userArchive, studyRoomArchive);
		} else {
			this.m = new ModelImpl();
			this.writeOnFile();
		}
	}

	public void writeOnFile() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1994);
		calendar.set(Calendar.MONTH, 3);
		calendar.set(Calendar.DAY_OF_MONTH, 6);

		try {
			this.m.registerUser("Enrico", "Casanova", calendar, "asd", "asd", "enrico.casanova@dadas.it",
					"334534534534", new ArrayList<ItemGenre>(), new ArrayList<ItemGenre>());
			this.m.registerBook("Il signore degli anelli", 1945, "J.R.R. Tolkien", Language.ENGLISH, "23123121",
					ItemGenre.ADVENTURE_HISTORY, "Ges�", 0011, 100000);
			this.m.registerMovie("Star Trek", 2009, "Bad Robot", "J.J. Abrams", Language.ENGLISH, ItemGenre.FANTASY,
					120, true, 1000000);

			User u = new UserImpl("Enrico", "Casanova", calendar, "asd", "asd", "enrico.casanova@dadas.it",
					"334534534534", new ArrayList<ItemGenre>(), new ArrayList<ItemGenre>());

			this.m.bookSit(calendar, 1, ((UserImpl) u).getIdUser());
			this.fm.writeObjectIntoFile("archivio.utenti", this.m);
			this.fm.writeObjectIntoFile("archivio.oggetti", this.m);
			this.fm.writeObjectIntoFile("archivio.aulastudio", this.m);

		} catch (Exception e) { // TODO Auto-generated catch
			e.printStackTrace();
		}
	}

	@Override
	public void login() {
		final String username = this.v.getUsername();
		final String password = this.v.getPassword();
		boolean check = false;
		Map<Integer, UserImpl> map = this.m.getUserArchive();
		for (Entry<Integer, UserImpl> entry : map.entrySet()) {
			if ((entry.getValue().getUsername().equals(username))
					&& (entry.getValue().getPassword().equals(password))) {
				this.actualUser = entry.getValue();
				check = true;
				try {
					this.m.setReccomandedList(this.actualUser.getIdUser());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		if (!check) {
			this.v.showError("Utente non trovato");
		}
	}

	/**
	 *
	 * Method who elaborates inputs from the user and set the list with items
	 * filtered.
	 *
	 * @throws Exception
	 */
	public void itemElaboration() throws Exception {
		int index = 0;
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
		String[] array = new String[this.m.getAllItemId(ty).size()];
		for (Integer i : this.m.filtersItem(this.m.getAllItemId(ty), ts, searchText)) {
			array[index] = this.m.getRequiredItem(i).toString();
			index++;
		}
		this.v.setFilteredList(array);
	}

	// first draft
	public void addLike(final int itemId) {
		try {
			this.m.addLike(itemId, this.actualUser.getIdUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addReview(final int itemId, final Integer vote, final String note) {
		try {
			this.m.addReview(itemId, this.actualUser.getIdUser(), vote, note);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void borrowList() {

		try {
			this.actualLoanArchive = this.m.getRequiredUser(this.actualUser.getIdUser()).getLoanArchive();
			String[] array = new String[this.actualLoanArchive.size()];
			int index = 0;

			for (Integer i : this.m.getItemBorrowed(this.actualUser.getIdUser())) {
				array[index] = this.m.getRequiredItem(i).toString();
				index++;
			}
			this.v.setBorrowedItemList(array);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * for (Entry<Integer, Pair<Boolean, Optional<Integer>>> entry :
		 * this.actualLoanArchive.entrySet()) {
		 *
		 * array[index] =
		 * this.m.getItemArchive().get(entry.getKey()).toString(); }
		 */
	}

	public void registerNewUser() {
		// this.m.registerUser(this.v., initSurname, initBirthdate,
		// initUsername, initPassword, initEmail, initTelephoneNumber,
		// initBookPref, initMoviePref);
	}

	public void sendMessage(final String string) {
		// this.v.setMessage(string);
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
