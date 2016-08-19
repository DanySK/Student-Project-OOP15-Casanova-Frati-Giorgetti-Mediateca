package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Optional;

import model.Model;
import model.ModelImpl;
import model.Pair;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.user.User;
import model.user.UserImpl;
import utils.ItemGenre;
import utils.Language;
import utils.TypeItem;
import utils.TypeItemInfo;
import utils.UserInfo;
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

		// Map<Integer, UserImpl> userArchive2 = this.m.getUserArchive();

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

	@Override
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
	@Override
	public void itemElaboration() throws Exception {
		int index = 0;
		TypeItem ty = null;
		for (TypeItem y : TypeItem.values()) {
			if (y.equals(this.v.getItemFilter())) {
				ty = y;
			}
		}
		TypeItemInfo ts = null;
		for (TypeItemInfo s : TypeItemInfo.values()) {
			if (s.equals(this.v.getSearchFilter())) {
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
	@Override
	public void addLike() {
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser())) {
				try {
					this.m.addLike(i, this.actualUser.getIdUser());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void addReview() {
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser())) {
				try {
					this.m.addReview(i, this.actualUser.getIdUser(), vote, note);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
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

	@Override
	public void registerNewUser() {
		String name = (String) this.v.getUserRegistration(UserInfo.NAME);
		String surname = (String) this.v.getUserRegistration(UserInfo.SURNAME);
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		String username = (String) this.v.getUserRegistration(UserInfo.USERNAME);
		String password = (String) this.v.getUserRegistration(UserInfo.PASSWORD);
		String email = (String) this.v.getUserRegistration(UserInfo.EMAIL);
		String telephoneNumber = (String) this.v.getUserRegistration(UserInfo.TELEPHONE_NUMBER);
		List<ItemGenre> bookList = new ArrayList<>();
		List<ItemGenre> movieList = new ArrayList<>();
		try {
			this.m.registerUser(name, surname, day, username, password, email, telephoneNumber, bookList, movieList);
			this.v.showMessage("Utente " + username + " registrato con successo!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void takeSit() throws Exception {
		Integer sit = this.v.getTakenSits();
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		this.m.bookSit(day, sit, this.actualUser.getIdUser());
	}

	@Override
	public void cancelSit() throws Exception {
		Integer sit = this.v.getTakenSits();
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		this.m.cancelSit(day, sit, this.actualUser.getIdUser());
	}

	@Override
	public void studyRoomStatus() {
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		int[] arrayint = new int[this.m.getAllUserSit(day).size()];
		int index = 0;
		for (Integer i : this.m.getAllUserSit(day)) {
			arrayint[index] = i;
		}
		this.v.setStudyRoomStatus(arrayint);
	}

	public void wishlist() {
		// manca setWishlist()
		String[] array = new String[this.actualUser.getWishlist().size()];
		int index = 0;
		for (Integer i : this.actualUser.getWishlist()) {
			array[index] = this.m.getItemArchive().get(i).toString();
			index++;
		}
		this.v.setWishlist(array);
	}

	public void removeFromWishList() {
		// this.actualUser.removeFromWishList(this.v.get);
	}

	public void setAllUserList() {
		// forse sbagliato
		// manca v.setAllUserList
		int index = 0;
		String[] array = new String[this.m.getUserArchive().size()];
		for (Integer i : this.m.getUserArchive().keySet()) {
			array[index] = this.m.getUserArchive().get(i).toString();
		}
		this.v.setUserList(array);
	}

	public void setAllItemList() {
		// forse sbagliato
		// manca v.setAllItemList
		// manca v.getItemType
		int index = 0;
		String[] array = new String[this.m.getItemArchive().size()];
		for (Integer i : this.m.getItemArchive().keySet()) {
			array[index] = this.m.getItemArchive().get(i).toString();
		}
		this.v.setItemList(array);
	}

	public void deleteItem() {
		// manca v.getItem()
		int itemIdReceived = 0;
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemToRemoveModify())) {
				itemIdReceived = i;
			}
		}
		try {
			this.m.deleteItem(itemIdReceived);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteUser() {
		// manca v.getUser()
		try {
			this.m.deleteUser(this.actualUser.getIdUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkDeadlines() {

	}

	public void extendBorrow() {

	}

	@Override
	public void setView(final view.View v) {
		this.v = v;
	}
}
