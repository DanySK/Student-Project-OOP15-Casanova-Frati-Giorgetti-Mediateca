package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;

import model.Model;
import model.ModelImpl;
import model.Pair;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.item.ReviewImpl;
import model.user.User;
import model.user.UserImpl;
import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.TypeItem;
import utils.TypeItemInfo;
import utils.UserInfo;
import view.View;
import view.ViewImpl;

/**
 * Class which implements the controller interface.
 *
 * @author
 *
 */

public class ControllerImpl implements Controller {
	private View v;
	private Model m;
	// after the login, the corrispondent user will be saved here...
	private UserImpl actualUser;
	// ...and its LoanArchive will be saved here
	private Map<Integer, Pair<Boolean, Optional<Integer>>> actualLoanArchive;

	// constants for I/O
	private static final String FILENAMEUSER = "archivio.utenti";
	private static final String FILENAMEITEM = "archivio.oggetti";
	private static final String FILENAMESTUDYROOM = "archivio.aulastudio";

	private FileManager fm = new FileManager();

	/**
	 * Constructor for ControllerImpl.
	 *
	 * @throws Exception
	 *             in the case which singleton already exist.
	 */
	public ControllerImpl() throws Exception {

		File fileItem = new File(this.fm.getPath() + ControllerImpl.FILENAMEITEM);
		File fileUser = new File(this.fm.getPath() + ControllerImpl.FILENAMEUSER);
		File fileStudyRoom = new File(this.fm.getPath() + ControllerImpl.FILENAMESTUDYROOM);

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
		}
	}

	@Override
	public void writeOnFile() {

		try {

			GregorianCalendar cal = new GregorianCalendar();
			cal.set(Calendar.YEAR, 1994);
			cal.set(Calendar.MONTH, 3);
			cal.set(Calendar.DAY_OF_MONTH, 6);
			this.m.registerUser("Enrico", "Casanova", cal, "csharplover", "graffeallineate", "enrico.casanova@gmail.it",
					"334534534534",
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY, ItemGenre.HORROR)),
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.SCI_FI, ItemGenre.ADVENTURE, ItemGenre.HUMOR)));
			this.m.registerUser("Edoardo", "Frati", cal, "animefan", "fullmetalalchemist", "edoardo.frati@gmail.it",
					"321342111",
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY, ItemGenre.HORROR)),
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.SCI_FI, ItemGenre.ADVENTURE, ItemGenre.ANIMATION)));
			this.m.registerBook("Il signore degli anelli", 1945, "J.R.R. Tolkien", Language.ENGLISH, "23123121",
					ItemGenre.ADVENTURE_HISTORY, "Mondadori", 0011, 100);
			this.m.registerBook("Lo hobbit", 1953, "J.R.R. Tolkien", Language.ENGLISH, "23123100",
					ItemGenre.ADVENTURE_HISTORY, "Mondadori", 0012, 50);
			this.m.registerBook("Il Silmarillion", 1939, "J.R.R. Tolkien", Language.ENGLISH, "23123000",
					ItemGenre.ADVENTURE_HISTORY, "Mondadori", 0013, 30);
			this.m.registerBook("Shining", 1960, "Stephen King", Language.ENGLISH, "23121000", ItemGenre.HORROR,
					"Mondadori", 0017, 1);
			this.m.registerBook("Misery non deve morire", 1966, "Stephen King", Language.ENGLISH, "23121007",
					ItemGenre.HORROR, "Mondadori", 0111, 2);
			this.m.registerBook("La macchina fantasma", 1970, "Stephen Cronenberg", Language.ENGLISH, "23111007",
					ItemGenre.HORROR, "Mondadori", 0110, 3);
			this.m.registerBook("Il vecchio e il mare", 1956, "Ernest Hemingway", Language.ENGLISH, "23521007",
					ItemGenre.FANTASY, "Mondadori", 0101, 2);
			this.m.registerBook("Il vecchio e il mare 2", 1986, "Ernest Hemingway", Language.ENGLISH, "33521007",
					ItemGenre.FANTASY, "Mondadori", 0301, 12);
			this.m.registerBook("Misery non deve morire", 1966, "Stephen King", Language.ENGLISH, "23121007",
					ItemGenre.HORROR, "Mondadori", 0111, 2);
			this.m.registerBook("Misery non deve morire", 1966, "Stephen King", Language.ENGLISH, "23121007",
					ItemGenre.HORROR, "Mondadori", 0111, 2);
			this.m.registerMovie("Star Trek", 2009, "Bad Robot", "J.J. Abrams", Language.ENGLISH, ItemGenre.FANTASY,
					120, true, 10);
			this.m.registerMovie("Star Trek: Into Darkness", 2012, "Bad Robot", "J.J. Abrams", Language.ENGLISH,
					ItemGenre.FANTASY, 130, true, 8);
			this.m.registerMovie("Star Trek: Beyond", 2015, "Bad Robot", "J.J. Abrams", Language.ENGLISH,
					ItemGenre.FANTASY, 144, true, 2);
			this.m.registerMovie("Non aprite quella porta", 2006, "Legendary", "John Nispel", Language.ENGLISH,
					ItemGenre.HORROR, 100, true, 10);
			this.m.registerMovie("Saw 80: la fine", 2012, "Medusa film", "Carlo Vanzina", Language.ENGLISH,
					ItemGenre.HORROR, 180, true, 1);
			this.m.registerMovie("Mamma mia!", 2008, "Warner Bros", "Meryl Streep", Language.ENGLISH, ItemGenre.MUSICAL,
					100, true, 1000);
			this.m.registerMovie("The Rocky Horror Picture Show", 1977, "01 Distribution", "Tim Curry",
					Language.ENGLISH, ItemGenre.MUSICAL, 120, true, 7);
			this.m.registerMovie("Tarzan", 1966, "Disney Pictures", "Walt Disney", Language.ENGLISH,
					ItemGenre.ANIMATION, 90, true, 1);
			this.m.registerMovie("Aladdin", 1986, "Disney Pictures", "Walt Disney", Language.ENGLISH,
					ItemGenre.ANIMATION, 100, true, 2);
			User u = new UserImpl("Enrico", "Casanova", cal, "csharplover", "graffeallineate",
					"enrico.casanova@gmail.it", "334534534534",
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY, ItemGenre.HORROR)),
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.SCI_FI, ItemGenre.FANTASY, ItemGenre.MUSICAL)));
			User u2 = new UserImpl("Edoardo", "Frati", cal, "animefan", "fullmetalalchemist", "edoardo.frati@gmail.it",
					"321342111",
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY, ItemGenre.HORROR)),
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.SCI_FI, ItemGenre.ADVENTURE, ItemGenre.ANIMATION)));
			GregorianCalendar cal2 = new GregorianCalendar();
			cal.set(Calendar.YEAR, 1994);
			cal.set(Calendar.MONTH, 3);
			cal.set(Calendar.DAY_OF_MONTH, 7);
			this.m.bookSit(cal, 1, ((UserImpl) u).getIdUser());
			this.m.bookSit(cal, 2, ((UserImpl) u2).getIdUser());
			this.m.bookSit(cal2, 1, ((UserImpl) u).getIdUser());
			this.m.bookSit(cal2, 2, ((UserImpl) u).getIdUser());
			this.m.bookSit(cal2, 3, ((UserImpl) u).getIdUser());
			this.m.bookSit(cal2, 4, ((UserImpl) u).getIdUser());
			this.m.bookSit(cal2, 6, ((UserImpl) u2).getIdUser());
			this.m.bookSit(cal2, 7, ((UserImpl) u2).getIdUser());
			this.m.bookSit(cal2, 8, ((UserImpl) u2).getIdUser());
			this.m.bookSit(cal2, 9, ((UserImpl) u2).getIdUser());
			this.v.showMessage("Utenti creati");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Utente già presente nell'archivio");
		}
		this.fm.writeObjectIntoFile("archivio.utenti", this.m);
		this.fm.writeObjectIntoFile("archivio.oggetti", this.m);
		this.fm.writeObjectIntoFile("archivio.aulastudio", this.m);
	}

	@Override
	public void userLogin() {
		final String username = this.v.getUsername();
		final String password = this.v.getPassword();
		Map<Integer, UserImpl> map = this.m.getUserArchive();
		java.util.Optional<UserImpl> user = map.entrySet().stream()
				.filter(e -> e.getValue().getUsername().equals(username))
				.filter(e -> e.getValue().getPassword().equals(password)).map(e -> e.getValue()).findFirst();
		if (user.isPresent()) {
			this.actualUser = user.get();
			this.v.goodLogin();
			try {
				this.m.setReccomandedList(this.actualUser.getIdUser());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.elaborateLoans();
		} else {
			this.v.showError("Utente non trovato");
		}

		/*
		 * for (Entry<Integer, UserImpl> entry : map.entrySet()) { if
		 * ((entry.getValue().getUsername().equals(username)) &&
		 * (entry.getValue().getPassword().equals(password))) { this.actualUser
		 * = entry.getValue(); this.v.goodLogin(); // inserire
		 * showGiveBackOption check = true; try {
		 * this.m.setReccomandedList(this.actualUser.getIdUser()); } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } break; } } if (!check) { this.v.showError(
		 * "Utente non trovato"); }
		 */
	}

	@Override
	public void managerLogin() {
		if (this.m.getSystemPassword().equals(this.v.getMenagerPassword())) {
			// esegui login manager
			this.v.goodManagerLogin();
			this.v.showMessage("Login manager effettuato");
			// inserire metodo
		} else {
			this.v.showMessage("Password errata");
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
	public void itemElaboration() {
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
		try {
			for (Integer i : this.m.filtersItem(this.m.getAllItemId(ty), ts, searchText)) {
				array[index] = this.m.getItemArchive().get(i).toString();
				index++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Filtraggio oggetti fallito");
		}
		this.v.setFilteredList(array);
	}

	// first draft
	@Override
	public void addLike() {
		// DA CONTROLLARE
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser())) {
				try {
					this.m.addLike(i, this.actualUser.getIdUser());
					this.v.showMessage("Oggetto " + this.m.getItemArchive().get(i) + " messo in wishlist");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.v.showError("Errore inserimento oggetto in wishlist");
				}
			}
		}
	}

	@Override
	public void addReview() {
		// DA CONTROLLARE
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser())) {
				try {
					this.m.addReview(i, this.actualUser.getIdUser(), this.v.getScore(), this.v.getReview());
					this.v.showMessage("Recensione per l'oggetto " + this.m.getItemArchive().get(i) + "inserita");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.v.showError("Errore inserimento recensione oggetto");
				}
			}
		}
	}

	@Override
	public void borrowList() {
		try {
			this.actualLoanArchive = this.actualUser.getLoanArchive();
			String[] array = new String[this.actualLoanArchive.size()];
			int index = 0;

			for (Integer i : this.m.getItemBorrowed(this.actualUser.getIdUser())) {
				array[index] = this.m.getItemArchive().get(i).getFirst().toString();
				index++;
			}
			this.v.setBorrowedItemList(array);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore ritrovamento oggetto prestato");
		}
	}

	@Override
	public void borrowItem() {
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser())) {
				try {
					this.m.borrowItem(i, this.actualUser.getIdUser());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.v.showError("Errore! itemId o userId non presente nell'archivio");
				}
			}
		}
	}

	@Override
	public void userModify() {
		// for per ogni tipo di userinfo e fare changeUser totale
		for (UserInfo ui : UserInfo.values()) {
			try {
				if ((ui != UserInfo.USERNAME) && (ui != UserInfo.BIRTHDATE_DAY) && (ui != UserInfo.BIRTHDATE_MONTH)
						&& (ui != UserInfo.BIRTHDATE_YEAR) && (ui != UserInfo.BOOK_PREF1) && (ui != UserInfo.BOOK_PREF2)
						&& (ui != UserInfo.BOOK_PREF3) && (ui != UserInfo.FILM_PREF1) && (ui != UserInfo.FILM_PREF2)
						&& (ui != UserInfo.FILM_PREF3)) {
					this.m.changeUser(ui, this.actualUser.getIdUser(), this.v.getModifiedInfo(ui));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.v.showError("Errore nell'UserInfo");
			}
		}
	}

	@Override
	public void itemModify() {
		// getItemToRemoveModify ottiene la stringa da setAllItemList!!!
		Integer itemId = null;
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemToRemoveModify())) {
				itemId = i;
			}
		}
		for (TypeItemInfo ti : TypeItemInfo.values()) {
			try {
				switch (ti) {
				case TITLE:
					this.m.changeItem(ti, itemId, this.v.getItemInfo(ti));
					break;
				case RELEASE_YEAR:
					this.m.changeItem(ti, itemId, (int) this.v.getItemInfo(ti));
					break;
				case AUTHOR:
					this.m.changeItem(ti, itemId, this.v.getItemInfo(ti));
					break;
				case LANGUAGE:
					this.m.changeItem(ti, itemId, this.v.getItemInfo(ti));
					break;
				case GENRE:
					this.m.changeItem(ti, itemId, this.v.getItemInfo(ti));
					break;
				case PRODUCER:
					this.m.changeItem(ti, itemId, this.v.getItemInfo(ti));
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.v.showError("Errore nell'TypeItemInfo o itemId");
			}
		}
	}

	@Override
	public void setItemInfo() {
		// getItemSelectedByuSer lo prendo da setFilteredList
		Integer itemId = 0;
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser())) {
				itemId = i;
			}
		}
		String title = this.m.getItemArchive().get(itemId).getFirst().getTitle();
		int releaseYear = this.m.getItemArchive().get(itemId).getFirst().getReleaseYear();
		String author = this.m.getItemArchive().get(itemId).getFirst().getAuthor();
		Language language = this.m.getItemArchive().get(itemId).getFirst().getCurrentLanguage();
		String isbn = (String) this.v.getItemInfo(TypeItemInfo.ISBN);
		ItemGenre genre = this.m.getItemArchive().get(itemId).getFirst().getGenre();
		String publisher = this.m.getItemArchive().get(itemId).getFirst().getPublisher();
		Integer numRelease = (Integer) this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.RELEASE_NUMBER);
		Integer numCopy = (Integer) this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER);
		String duration = (String) this.v.getItemInfo(TypeItemInfo.DURATION);
		TypeColor color = (TypeColor) this.v.getItemInfo(TypeItemInfo.COLOR);

		if (this.m.getAllItemId(TypeItem.BOOK).contains(itemId)) {
			this.v.setBookField(title, author, publisher, Integer.toString(releaseYear), genre, isbn, language, numCopy,
					numRelease);
		} else if (this.m.getAllItemId(TypeItem.MOVIE).contains(itemId)) {
			this.v.setFilmField(title, author, publisher, Integer.toString(releaseYear), genre, duration, color,
					language, numCopy, numRelease);
		} else {
			this.v.showError("Errore! Id oggetto non presente nell'archivio");
		}
	}

	@Override
	public void suggestedBooks() {
		// stampa 3 libri di 3 preferenze
		String[] array = new String[this.actualUser.getBookPreferences().size()];
		int index = 0;
		for (Integer i : this.actualUser.getRecommendedList()) {
			if (this.m.getAllItemId(TypeItem.BOOK).contains(i)) {
				array[index] = i.toString();
				index++;
			}
		}
		this.v.setSuggestedBooks(array);
	}

	@Override
	public void suggestedFilms() {
		int index = 0;
		String[] array = new String[this.actualUser.getRecommendedList().size()];
		for (Integer i : this.actualUser.getRecommendedList()) {
			if (this.m.getAllItemId(TypeItem.MOVIE).contains(i)) {
				array[index] = i.toString();
				index++;
			}
		}
		this.v.setSuggestedMovies(array);
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
		ItemGenre bookPref1 = (ItemGenre) this.v.getUserRegistration(UserInfo.BOOK_PREF1);
		ItemGenre bookPref2 = (ItemGenre) this.v.getUserRegistration(UserInfo.BOOK_PREF2);
		ItemGenre bookPref3 = (ItemGenre) this.v.getUserRegistration(UserInfo.BOOK_PREF3);
		List<ItemGenre> bookList = new ArrayList<>(Arrays.asList(bookPref1, bookPref2, bookPref3));
		ItemGenre moviePref1 = (ItemGenre) this.v.getUserRegistration(UserInfo.FILM_PREF1);
		ItemGenre moviePref2 = (ItemGenre) this.v.getUserRegistration(UserInfo.FILM_PREF2);
		ItemGenre moviePref3 = (ItemGenre) this.v.getUserRegistration(UserInfo.FILM_PREF3);
		List<ItemGenre> movieList = new ArrayList<>(Arrays.asList(moviePref1, moviePref2, moviePref3));
		try {
			this.m.registerUser(name, surname, day, username, password, email, telephoneNumber, bookList, movieList);
			this.v.showMessage("Utente " + username + " registrato con successo!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Utente già presente nell'archivio");
		}
	}

	/**
	 * Method which adds a new Book to the archive.
	 */
	public void registerNewBook() {
		String title = (String) this.v.getItemInfo(TypeItemInfo.TITLE);
		int releaseYear = (int) this.v.getItemInfo(TypeItemInfo.RELEASE_YEAR);
		String author = (String) this.v.getItemInfo(TypeItemInfo.AUTHOR);
		Language language = (Language) this.v.getItemInfo(TypeItemInfo.LANGUAGE);
		String isbn = (String) this.v.getItemInfo(TypeItemInfo.ISBN);
		ItemGenre genre = (ItemGenre) this.v.getItemInfo(TypeItemInfo.GENRE);
		String publisher = (String) this.v.getItemInfo(TypeItemInfo.PRODUCER);
		Integer numRelease = (Integer) this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.RELEASE_NUMBER);
		Integer numCopy = (Integer) this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER);
		try {
			this.m.registerBook(title, releaseYear, author, language, isbn, genre, publisher, numRelease, numCopy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Numero di copie inferiori a 0");
		}
	}

	/**
	 * Method which adds a new Movie to the archive.
	 */
	public void registerNewMovie() {
		String title = (String) this.v.getItemInfo(TypeItemInfo.TITLE);
		int releaseYear = (int) this.v.getItemInfo(TypeItemInfo.RELEASE_YEAR);
		String publisher = (String) this.v.getItemInfo(TypeItemInfo.PRODUCER);
		String author = (String) this.v.getItemInfo(TypeItemInfo.AUTHOR);
		Language language = (Language) this.v.getItemInfo(TypeItemInfo.LANGUAGE);
		ItemGenre genre = (ItemGenre) this.v.getItemInfo(TypeItemInfo.GENRE);
		Integer duration = (Integer) this.v.getItemInfo(TypeItemInfo.DURATION);
		Boolean color = (Boolean) this.v.getItemInfo(TypeItemInfo.COLOR);
		Integer numCopy = (Integer) this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER);
		try {
			this.m.registerMovie(title, releaseYear, publisher, author, language, genre, duration, color, numCopy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Numero di copie inferiori a 0");
		}
	}

	@Override
	public void itemCreate() {
		if (this.v.getItemInfo(TypeItemInfo.TYPE) == TypeItem.BOOK) {
			this.registerNewBook();
		} else if (this.v.getItemInfo(TypeItemInfo.TYPE) == TypeItem.MOVIE) {
			this.registerNewMovie();
		}
	}

	@Override
	public void elaborateLoans() {
		Map<Integer, Double> map;
		try {
			map = this.m.checkDeadlineas(this.actualUser.getIdUser());

			/*
			 * map.keySet().stream().filter(i -> map.get(i) > 30).forEach( i ->
			 * this.v.showGiveBackOptionMessage(this.m.getItemArchive().get(i).
			 * getFirst().getTitle()));
			 */
			for (Integer i : map.keySet()) {
				if (map.get(i) > 60) {
					this.v.showGiveBackMessage(this.m.getItemArchive().get(i).getFirst().getTitle());
				} else if (map.get(i) > 30) {
					this.v.showGiveBackOptionMessage(this.m.getItemArchive().get(i).getFirst().getTitle());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Utente non presente nell'archivio");
		}
	}

	@Override
	public void giveBackItem(final String item) {
		// DA RIVEDERE
		for (Integer i : this.actualUser.getLoanArchive().keySet()) {
			if (this.m.getItemArchive().get(i).getFirst().getTitle().equals(item)) {
				try {
					this.m.returnItem(i, this.actualUser.getIdUser());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.v.showError("Errore! Id utente e oggetto non associati");
				}
			} else {
				this.v.showMessage("Oggetto da restituire non trovato!");
			}
		}
	}

	@Override
	public void giveBackItemSelectedByUser() {
		this.giveBackItem(this.v.getItemSelectedByUser());
	}

	@Override
	public void setTakenSitsList() {
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		String[] array = new String[50];
		int index = 0;
		for (Integer i : this.m.getStudyRoom().get(day)) {
			if ((i == null) || (i == 0)) {
				array[index] = "0";
				index++;
			} else {
				try {
					array[index] = this.m.getRequiredUser(i).getUsername();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.v.showError("Errore! Utente non presente nell'archivio");
				}
				index++;
			}
		}
		this.v.setStudyRoomStatus(array);
		// array di stringhe per i posto a sedere dove il posto vuoto è 0 e il
		// posto occupato è username

		// ritorna array di stringhe che mostra data e posto occupati
	}

	@Override
	public void takeSit() {
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		try {
			this.m.bookSit(day, this.v.getTakenSits(), this.actualUser.getIdUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Posto o Id utente non validi");
		}
	}

	@Override
	public void cancelSit() {

		this.v.getSelectedSit(); // convertire per ottenere nel posto da
									// cancellare e il giorno
		// mi ritorna un pair con posto, giorno e devo fare il cast inverso

		// elaborare la stringa per ottenere la data
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());

		try {
			this.m.cancelSit(day, this.v.getTakenSits(), this.actualUser.getIdUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Posto selezionato non valido per la cancellazione");
		}
	}

	/*
	 * @Override public void studyRoomStatus() { GregorianCalendar day = new
	 * GregorianCalendar(); day.set(this.v.getStudyRoomSelectedYear(),
	 * this.v.getStudyRoomSelectedMonth(), this.v.getStudyRoomSelectedDay());
	 * int[] arrayint = new int[this.m.getAllUserSit(day).size()]; int index =
	 * 0; for (Integer i : this.m.getAllUserSit(day)) { arrayint[index] = i; }
	 * this.v.setStudyRoomStatus(arrayint); }
	 */

	@Override
	public void setWishlist() {
		String[] array = new String[this.actualUser.getWishlist().size()];
		int index = 0;
		for (Integer i : this.actualUser.getWishlist()) {
			array[index] = this.m.getItemArchive().get(i).toString();
			index++;
		}
		this.v.setWishlist(array);
	}

	@Override
	public void removeFromWishList() {
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemToRemoveFromLikeBorrowWish())) {
				try {
					this.m.removeLike(i, this.actualUser.getIdUser());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.v.showError("Errore! ID utente o oggetto non valido");
				}
			}
		}
	}

	@Override
	public void setAllUserList() {
		// forse sbagliato
		// manca v.setAllUserList
		int index = 0;
		String[] array = new String[this.m.getUserArchive().size()];
		for (Integer i : this.m.getUserArchive().keySet()) {
			array[index] = this.m.getUserArchive().get(i).toString();
			index++;
		}
		this.v.setUserList(array);
	}

	@Override
	public void setAllItemList() {
		// forse sbagliato
		// manca v.setAllItemList
		// manca v.getItemType
		int index = 0;
		String[] array = new String[this.m.getItemArchive().size()];
		for (Integer i : this.m.getItemArchive().keySet()) {
			array[index] = this.m.getItemArchive().get(i).toString();
			index++;
		}
		this.v.setItemList(array);
	}

	@Override
	public void deleteItem() {
		int itemIdReceived = 0;
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemToRemoveModify())) {
				itemIdReceived = i;
			}
		}
		try {
			this.m.deleteItem(itemIdReceived);
			this.v.showMessage("Oggetto " + itemIdReceived + " cancellato");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Oggetto non presente nell'archivio");
		}
	}

	@Override
	public void deleteUser() {
		try {
			this.m.deleteUser(this.actualUser.getIdUser());
			this.v.showMessage("Utente " + this.actualUser.getIdUser() + " cancellato");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Utente non presente nell'archivio");
		}
	}

	@Override
	public void checkDeadlines() {
		try {
			Map<Integer, Double> map = this.m.checkDeadlineas(this.actualUser.getIdUser());
			for (Integer i : this.actualUser.getLoanArchive().keySet()) {
				// DA COMPLETARE
				if (map.get(i) > 30) {

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void extendBorrow() {
		// fai comparire solo uan finestra che dice che è stato esteso

		// se block user è true, manda la schermata solo per restituire
		this.v.showMessage("Prestito esteso per l'oggetto");

	}

	@Override
	public void allItemReviews() {
		int index = 0;
		int id = 0;
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser())) {
				id = i;
			}
		}
		String[] array;
		try {
			array = new String[this.m.getAllItemReview(id).size()];
			for (ReviewImpl r : this.m.getAllItemReview(id)) {
				array[index] = r.toString();
				index++;
			}
			this.v.setItemReviewsList(array);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Oggetto con recensione non trovato nell'archivio");
		}
	}

	@Override
	public void setView(final view.View v) {
		this.v = v;
		this.writeOnFile();
	}
}
