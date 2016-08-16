package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Optional;

import model.Model;
import model.ModelImpl;
import model.ModelImpl.TypeSearch;
import model.Pair;
import model.item.ArchiveImpl;
import model.item.ArchiveImpl.TypeItem;
import model.item.ItemGenre;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.item.Language;
import model.user.User;
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
	private Map<Integer, Pair<Boolean, Optional<Integer>>> actualLoanArchive = this.m
			.getRequiredUser(this.actualUser.getIdUser()).getLoanArchive();
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
	 * @param inputM
	 *            Model to be initialized
	 * @throws Exception
	 */
	public ControllerImpl() throws Exception {
		Integer check = 1;
		if (check == 1) {

			Map<Integer, UserImpl> userArchive = this.fm.readArchiveUserFromFile(ControllerImpl.FILENAMEUSER);
			Map<Integer, Pair<ItemImpl, ItemInfo>> itemArchive = this.fm
					.readArchiveItemFromFile(ControllerImpl.FILENAMEITEM);
			Map<GregorianCalendar, ArrayList<Integer>> studyRoomArchive = this.fm
					.readStudyRoomFromFile(ControllerImpl.FILENAMESTUDYROOM);
			this.m = new ModelImpl(itemArchive, userArchive, studyRoomArchive);

		} else {

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(Calendar.YEAR, 1994);
			calendar.set(Calendar.MONTH, 3);
			calendar.set(Calendar.DAY_OF_MONTH, 6);

			try {
				this.m = new ModelImpl();
				this.m.registerUser("Enrico", "Casanova", calendar, "Dakaiden", "Arctica64", "enrico.casanova@dadas.it",
						"334534534534", new ArrayList<ItemGenre>(), new ArrayList<ItemGenre>());
				this.m.registerBook("Il signore degli anelli", 1945, "J.R.R. Tolkien", Language.ENGLISH, "23123121",
						ItemGenre.ADVENTURE_HISTORY, "Gesù", 0011, 100000);
				this.m.registerMovie("Star Trek", 2009, "Bad Robot", "J.J. Abrams", Language.ENGLISH, ItemGenre.FANTASY,
						120, true, 1000000);

				User u = new UserImpl("Enrico", "Casanova", calendar, "Dakaiden", "Arctica64",
						"enrico.casanova@dadas.it", "334534534534", new ArrayList<ItemGenre>(),
						new ArrayList<ItemGenre>());

				this.m.bookSit(calendar, 1, ((UserImpl) u).getIdUser());
				this.fm.writeObjectIntoFile("archivio.utenti", this.m);
				this.fm.writeObjectIntoFile("archivio.oggetti", this.m);
				this.fm.writeObjectIntoFile("archivio.aulastudio", this.m);

			} catch (FileNotFoundException e) { // TODO Auto-generated catch
				e.printStackTrace();
			} catch (IOException e) { // TODO
				e.printStackTrace();
			}
		}
	}

	public void metodo() {
		TypeSearch.values();
	}

	@Override
	public void login() {
		final String username = this.v.getUsername();
		final String password = this.v.getPassword();
		Map<Integer, UserImpl> map = this.m.getUserArchive();
		for (Entry<Integer, UserImpl> entry : map.entrySet()) {
			if ((entry.getValue().getUsername().equals(username))
					&& (entry.getValue().getPassword().equals(password))) {
				this.actualUser = entry.getValue();
				break;
			}
		}
		this.sendMessage("User not found");
		// lancia messaggio cattivo

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

	public void borrowList() {
		int index = 0;
		String[] array = new String[this.actualLoanArchive.size()];

		for (Entry<Integer, Pair<Boolean, Optional<Integer>>> entry : this.actualLoanArchive.entrySet()) {

			array[index] = this.m.getItemArchive().get(entry.getKey()).toString();
		}

		this.v.setBorrowedItemList(array);
	}

	public void sendMessage(final String string) {
		// v.setMessage(string);
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
