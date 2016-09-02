package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.ItemException;
import model.Model;
import model.ModelImpl;
import model.Pair;
import model.UserException;
import model.item.ItemImpl;
import model.item.ItemInfo;
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
	private String itemBeforeScreenChange;

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

		File fileItem = new File(this.fm.getPath()
				+ ControllerImpl.FILENAMEITEM);
		File fileUser = new File(this.fm.getPath()
				+ ControllerImpl.FILENAMEUSER);
		File fileStudyRoom = new File(this.fm.getPath()
				+ ControllerImpl.FILENAMESTUDYROOM);

		if ((fileItem.exists() && !fileItem.isDirectory())
				&& (fileUser.exists() && !fileUser.isDirectory())
				&& (fileStudyRoom.exists() && !fileStudyRoom.isDirectory())) {
			Map<Integer, UserImpl> userArchive = this.fm
					.readArchiveUserFromFile(ControllerImpl.FILENAMEUSER);
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

			/*
			 * this.m.registerUser("Enrico", "Casanova", cal, "csharplover",
			 * "graffeallineate", "enrico.casanova@gmail.it", "334534534534",
			 * new ArrayList<ItemGenre>(
			 * Arrays.asList(ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY,
			 * ItemGenre.HORROR)), new
			 * ArrayList<ItemGenre>(Arrays.asList(ItemGenre.FANTASY,
			 * ItemGenre.HORROR, ItemGenre.ANIMATION)));
			 * this.m.registerUser("Edoardo", "Frati", cal, "animefan",
			 * "fullmetalalchemist", "edoardo.frati@gmail.it", "321342111", new
			 * ArrayList<ItemGenre>( Arrays.asList(ItemGenre.ADVENTURE_HISTORY,
			 * ItemGenre.FANTASY, ItemGenre.HORROR)), new ArrayList<ItemGenre>(
			 * Arrays.asList(ItemGenre.SCI_FI, ItemGenre.ADVENTURE,
			 * ItemGenre.ANIMATION))); System.out.println(
			 * "writeonfile: utente zxc sta per essere registrato");
			 * this.m.registerUser("Giacomo", "Giacomelli", cal, "zxc", "zxc",
			 * "edoardo.frati@gmail.it", "321342111", new ArrayList<ItemGenre>(
			 * Arrays.asList(ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY,
			 * ItemGenre.HORROR)), new ArrayList<ItemGenre>(
			 * Arrays.asList(ItemGenre.SCI_FI, ItemGenre.ADVENTURE,
			 * ItemGenre.ANIMATION))); System.out.println(
			 * "writeonfile: utente zxc registrato");
			 */
			this.m.registerBook("Il signore degli anelli", 1945,
					"J.R.R. Tolkien", Language.ENGLISH, "23123121",
					ItemGenre.ADVENTURE_HISTORY, "Mondadori", 0011, 100);
			this.m.registerBook("Lo hobbit", 1953, "J.R.R. Tolkien",
					Language.ENGLISH, "23123100", ItemGenre.ADVENTURE_HISTORY,
					"Mondadori", 0012, 50);
			this.m.registerBook("Il Silmarillion", 1939, "J.R.R. Tolkien",
					Language.ENGLISH, "23123000", ItemGenre.ADVENTURE_HISTORY,
					"Mondadori", 0013, 30);
			this.m.registerBook("Shining", 1960, "Stephen King",
					Language.ENGLISH, "23121000", ItemGenre.HORROR,
					"Mondadori", 0017, 1);
			this.m.registerBook("Misery non deve morire", 1966, "Stephen King",
					Language.ENGLISH, "231210072", ItemGenre.HORROR,
					"Mondadori", 0111, 2);
			this.m.registerBook("La macchina fantasma", 1970,
					"Stephen Cronenberg", Language.ENGLISH, "231110073",
					ItemGenre.HORROR, "Mondadori", 0110, 3);
			this.m.registerBook("Il vecchio e il mare", 1956,
					"Ernest Hemingway", Language.ENGLISH, "235210074",
					ItemGenre.FANTASY, "Mondadori", 0101, 2);
			this.m.registerBook("Il vecchio e il mare 2", 1986,
					"Ernest Hemingway", Language.ENGLISH, "335210075",
					ItemGenre.FANTASY, "Mondadori", 0301, 12);
			this.m.registerBook("IT", 1966, "Stephen King", Language.ENGLISH,
					"231210076", ItemGenre.HORROR, "Mondadori", 0111, 2);
			this.m.registerBook("Il settimo sigillo", 1966, "Stephen King",
					Language.ENGLISH, "231210077", ItemGenre.HORROR,
					"Mondadori", 0113, 2);

			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "231210078", ItemGenre.ADVENTURE,
					"Mondadori", 0114, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "231110079", ItemGenre.ADVENTURE,
					"Mondadori", 0115, 3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "123121007", ItemGenre.ANIMATION,
					"Mondadori", 0116, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "223111007", ItemGenre.ANIMATION,
					"Mondadori", 0117, 3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "323121007",
					ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Mondadori", 0001,
					2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "423111007",
					ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Mondadori", 0002,
					3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "523121007", ItemGenre.BIOGRAPHICAL,
					"Mondadori", 0003, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "623111007", ItemGenre.BIOGRAPHICAL,
					"Mondadori", 0004, 3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "723121007", ItemGenre.BIOGRAPHIES,
					"Mondadori", 0005, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "823111007", ItemGenre.BIOGRAPHIES,
					"Mondadori", 0006, 3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "923121007",
					ItemGenre.CLASSICAL_CRITICISM, "Mondadori", 0007, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "213111007",
					ItemGenre.CLASSICAL_CRITICISM, "Mondadori", 0010, 3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "223121007", ItemGenre.COMEDY,
					"Mondadori", 0011, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "233111007", ItemGenre.COMEDY,
					"Mondadori", 0012, 3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "243121007",
					ItemGenre.CURRENT_NEWS_REPORTS, "Mondadori", 0013, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "253111007",
					ItemGenre.CURRENT_NEWS_REPORTS, "Mondadori", 0014, 3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "263121007", ItemGenre.DOCUMENTARY,
					"Mondadori", 0015, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "273111007", ItemGenre.DOCUMENTARY,
					"Mondadori", 0016, 3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "283121007", ItemGenre.DRAMA,
					"Mondadori", 0017, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "293111007", ItemGenre.DRAMA,
					"Mondadori", 0020, 3);
			this.m.registerBook("Libro 1", 1945, "Stephen King",
					Language.ENGLISH, "231121007", ItemGenre.FASHION,
					"Mondadori", 0021, 2);
			this.m.registerBook("Libro 2", 1946, "Stephen Cronenberg",
					Language.ENGLISH, "231211007", ItemGenre.FASHION,
					"Mondadori", 0022, 3);

			this.m.registerMovie("Star Trek", 2009, "Bad Robot", "J.J. Abrams",
					Language.ENGLISH, ItemGenre.FANTASY, 120, TypeColor.COLOR,
					10);
			this.m.registerMovie("Star Trek: Into Darkness", 2012, "Bad Robot",
					"J.J. Abrams", Language.ENGLISH, ItemGenre.FANTASY, 130,
					TypeColor.COLOR, 8);
			this.m.registerMovie("Star Trek: Beyond", 2015, "Bad Robot",
					"J.J. Abrams", Language.ENGLISH, ItemGenre.FANTASY, 144,
					TypeColor.COLOR, 2);
			this.m.registerMovie("Non aprite quella porta", 2006, "Legendary",
					"John Nispel", Language.ENGLISH, ItemGenre.HORROR, 100,
					TypeColor.COLOR, 10);
			this.m.registerMovie("Saw 80: la fine", 2012, "Medusa film",
					"Carlo Vanzina", Language.ENGLISH, ItemGenre.HORROR, 180,
					TypeColor.COLOR, 1);
			this.m.registerMovie("Mamma mia!", 2008, "Warner Bros",
					"Meryl Streep", Language.ENGLISH, ItemGenre.MUSICAL, 100,
					TypeColor.COLOR, 1000);
			this.m.registerMovie("The Rocky Horror Picture Show", 1977,
					"01 Distribution", "Tim Curry", Language.ENGLISH,
					ItemGenre.MUSICAL, 120, TypeColor.COLOR, 7);
			this.m.registerMovie("Tarzan", 1966, "Disney Pictures",
					"Walt Disney", Language.ENGLISH, ItemGenre.ANIMATION, 90,
					TypeColor.COLOR, 1);
			this.m.registerMovie("Aladdin", 1986, "Disney Pictures",
					"Walt Disney", Language.ENGLISH, ItemGenre.ANIMATION, 100,
					TypeColor.COLOR, 2);

			User u = new UserImpl("Enrico", "Casanova", cal, "csharplover",
					"graffeallineate", "enrico.casanova@gmail.it",
					"334534534534", new ArrayList<ItemGenre>(Arrays.asList(
							ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY,
							ItemGenre.HORROR)), new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.SCI_FI, ItemGenre.FANTASY,
									ItemGenre.MUSICAL)));
			User u2 = new UserImpl("Edoardo", "Frati", cal, "animefan",
					"fullmetalalchemist", "edoardo.frati@gmail.it",
					"321342111", new ArrayList<ItemGenre>(Arrays.asList(
							ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY,
							ItemGenre.HORROR)), new ArrayList<ItemGenre>(
									Arrays.asList(ItemGenre.SCI_FI,
									ItemGenre.ADVENTURE, ItemGenre.ANIMATION)));
			GregorianCalendar cal2 = new GregorianCalendar();
			cal2.set(Calendar.YEAR, 2016);
			cal2.set(Calendar.MONTH, 8);
			cal2.set(Calendar.DAY_OF_MONTH, 23);

			// this.m.bookSit(cal, 1, this.m.ge);
			// this.m.bookSit(cal, 2, this.m.getUserArchive().);
			this.m.bookSit(cal2, 1, u.getIdUser());
			this.m.bookSit(cal2, 2, u.getIdUser());
			this.m.bookSit(cal2, 3, u.getIdUser());
			this.m.bookSit(cal2, 4, u.getIdUser());
			this.m.bookSit(cal2, 6, u2.getIdUser());
			this.m.bookSit(cal2, 7, u2.getIdUser());
			this.m.bookSit(cal2, 8, u2.getIdUser());
			this.m.bookSit(cal2, 9, u2.getIdUser());

			System.out.println(this.m.getAllUserSit(cal2));
			System.out.println(this.m.getAllUserSit(cal));

			Random random = new Random();
			int n = 5;
			int index = 0;

			for (Integer i : this.m.getItemArchive().keySet()) {
				int k = random.nextInt(n);

				this.m.addReview(i, ((UserImpl) u).getIdUser(), k,
						"recensione numero " + index);
				if ((index % 2) == 0) {
					this.m.addLike(i, ((UserImpl) u).getIdUser());
				}
				index++;
			}

			this.v.showMessage("Utenti creati");
		} catch (ItemException e) {
			// TODO Auto-generated catch block
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
		System.out.println("writeonfile: dati quasi salvati");
		this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEUSER, this.m);
		this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEITEM, this.m);
		this.fm.writeObjectIntoFile(ControllerImpl.FILENAMESTUDYROOM, this.m);
		System.out.println("writeonfile: dati salvati");
	}

	@Override
	public void userLogin() {
		final String username = this.v.getUsername();
		final String password = this.v.getPassword();
		Map<Integer, UserImpl> map = this.m.getUserArchive();
		java.util.Optional<UserImpl> user = map.entrySet().stream()
				.filter(e -> e.getValue().getUsername().equals(username))
				.filter(e -> e.getValue().getPassword().equals(password))
				.map(e -> e.getValue()).findFirst();
		if (user.isPresent()) {
			this.actualUser = user.get();
			try {
				this.m.setReccomandedList(this.actualUser.getIdUser());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.v.showError("Lista consigli utente corrente non inizializzata");
			}
			this.elaborateLoans();
			this.v.goodLogin();
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
	public void logOut() {
		System.out.println("logOut: entrato");
		this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEITEM, this.m);
		this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEUSER, this.m);
		this.fm.writeObjectIntoFile(ControllerImpl.FILENAMESTUDYROOM, this.m);
		this.actualUser = null;
		System.out.println("logOut operazioni eseguite");
	}

	@Override
	public void managerLogin() {
		if (this.m.getSystemPassword().equals(this.v.getManagerPassword())) {
			this.v.goodManagerLogin();
		} else {
			this.v.showMessage("Password manager errata");
		}
	}

	/**
	 * Method who elaborates inputs from the user and set the list with items
	 * filtered.
	 */
	@Override
	public void itemElaboration() {
		System.out.println("itemElaboration: entrato");

		int index = 0;
		String[] array = null;

		System.out.println("itemElaboration: v.getitemfilter "
				+ this.v.getItemFilter());

		TypeItem ty = null;
		for (TypeItem y : TypeItem.values()) {
			if (y.toString().equals(this.v.getItemFilter())) {
				ty = y;
			}
		}

		System.out.println("itemElaboration: ty =" + ty);
		System.out.println("itemElaboration: v.getsearchfilter = "
				+ this.v.getSearchFilter());

		TypeItemInfo ts = null;
		for (TypeItemInfo s : TypeItemInfo.values()) {
			if (s.toString().equals(this.v.getSearchFilter())) {
				ts = s;
			}
		}
		System.out.println("itemElaboration: ts = " + ts);
		System.out.println("itemElaboration: this.v.getsearchtext ="
				+ this.v.getSearchText());

		String searchText = this.v.getSearchText();

		System.out.println("itemElaboration: searchtext=" + searchText);

		if ((ty == null) && ((searchText == null) || searchText.equals(""))) {
			array = new String[this.m.getItemArchive().size()];
			try {
				for (Integer i : this.m.getItemArchive().keySet()) {
					array[index] = this.m.getRequiredItem(i).toString();
					index++;
				}
				System.out.println("itemElaboration: dentro al try");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.v.showError("Filtraggio oggetti fallito");
			}
		} else if (ty == null) {
			array = new String[this.m.getItemArchive().size()];
			try {
				for (Integer i : this.m.filtersItem(this.m.getItemArchive()
						.keySet(), ts, searchText)) {
					array[index] = this.m.getRequiredItem(i).toString();
					index++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.v.showError("Filtraggio oggetti fallito");
			}
		} else {
			array = new String[this.m.getAllItemId(ty).size()];
			try {
				for (Integer i : this.m.filtersItem(this.m.getAllItemId(ty),
						ts, searchText)) {
					array[index] = this.m.getRequiredItem(i).toString();
					index++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block

			}
		}

		System.out.println("itemElaboration: sta per restituire lista");
		this.v.setFilteredList(array);
		System.out.println("itemElaboration: restituita lista");
	}

	// first draft
	@Override
	public void addLike() {
		// DA CONTROLLARE
		System.out.println("addLike: entrato");
		System.out.println("addLike: " + this.v.getItemSelectedByUser());

		/*
		 * this.m.getItemArchive().keySet().stream().filter((Integer) i -> { try
		 * { this.m.getRequiredItem(i).toString().equals(this.v.
		 * getItemSelectedByUser()); } catch (Exception e) { this.v.showError(
		 * "Oggetto non presente nell'archivio"); } }).forEach(i -> { try {
		 * this.m.addLike(i, this.actualUser.getIdUser()); } catch (Exception e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 * this.v.showMessage("Oggetto " + this.m.getItemArchive().get(i) +
		 * " messo in wishlist"); System.out.println("addLike: entrato nel try"
		 * ); });
		 */

		for (Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString()
						.equals(this.v.getItemSelectedByUser())) {
					this.m.addLike(i, this.actualUser.getIdUser());
					this.v.showMessage("Oggetto "
							+ this.m.getItemArchive().get(i)
							+ " messo in wishlist");
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}

	}

	@Override
	public void addReview() {
		// DA CONTROLLARE
		System.out.println("addReview: entrato");
		System.out.println("addReview: "
				+ this.v.getItemToRemoveFromLikeBorrowWish());
		System.out.println("addReview: score " + this.v.getScore()
				+ " e review " + this.v.getReview());
		/*
		 * this.m.getItemArchive().keySet().stream() .filter(i ->
		 * this.m.getRequiredItem(i).toString().equals(this.v.
		 * getItemSelectedByUser())).forEach(i -> { try { this.m.addReview(i,
		 * this.actualUser.getIdUser(), this.v.getScore(), this.v.getReview());
		 * this.v.showMessage("Recensione per l'oggetto " +
		 * this.m.getItemArchive().get(i) + "inserita"); } catch (Exception e) {
		 * // TODO Auto-generated catch block this.v.showError(
		 * "Errore inserimento recensione oggetto"); } });
		 */

		for (Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString()
						.equals(this.v.getItemToRemoveFromLikeBorrowWish())) {
					this.m.addReview(i, this.actualUser.getIdUser(),
							this.v.getScore(), this.v.getReview());
					this.v.showMessage("Recensione per l'oggetto "
							+ this.m.getRequiredItem(i).toString() + "inserita");
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}

	}

	@Override
	public void borrowList() {
		System.out.println("borrowList: entrato");
		this.borrowListFromUser(this.actualUser);
	}

	/**
	 * Method which takes the list of the items borrowed by the selected user.
	 *
	 * @param user
	 *            selected user.
	 */
	public void borrowListFromUser(final UserImpl user) {
		System.out.println("borrowListFromUser: entrato");
		System.out.println("borrowListFromUser: dimensione archivio prestiti="
				+ user.getLoanArchive().size());
		try {
			String[] array = new String[user.getLoanArchive().size()];
			int index = 0;

			/*
			 * array =
			 * this.m.getItemBorrowed(this.actualUser.getIdUser()).stream()
			 * .map(i ->
			 * this.m.getRequiredUser(i).toString()).collect(Collectors.toList()
			 * ).toArray(array);
			 */
			for (Integer i : this.m.getItemBorrowed(user.getIdUser())) {
				array[index] = this.m.getRequiredItem(i).toString();
				index++;
			}
			this.v.setBorrowedItemList(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void borrowItem() {
		System.out.println("borrowItem: entrato");
		for (Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString()
						.equals(this.v.getItemSelectedByUser())) {
					try {
						this.m.borrowItem(i, this.actualUser.getIdUser());
						this.v.showMessage("Oggetto "
								+ this.m.getRequiredItem(i).toString()
								+ " preso in prestito!");
						System.out.println("borrowItem: entrato nel try");
					} catch (ItemException e) {
						this.v.showError(e.getMessage());
					} catch (UserException e1) {
						this.v.showError(e1.getMessage());
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						this.v.showError(e2.getMessage());
					}
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}
		System.out.println("borrowItem: " + this.v.getItemSelectedByUser());
	}

	@Override
	public void userModify() {
		// DA CONTROLLARE 30 agosto
		// for per ogni tipo di userinfo e fare changeUser totale
		GregorianCalendar cal = new GregorianCalendar();

		System.out.println("userModify: entrato");
		Arrays.stream(UserInfo.values())
		.filter(ui -> !ui.equals(UserInfo.BIRTHDATE)
						&& !ui.equals(UserInfo.BOOK_PREF1)
				&& !ui.equals(UserInfo.BOOK_PREF2)
						&& !ui.equals(UserInfo.BOOK_PREF3)
				&& !ui.equals(UserInfo.FILM_PREF1)
						&& !ui.equals(UserInfo.FILM_PREF2)
				&& !ui.equals(UserInfo.FILM_PREF3))
				.forEach(
						ui -> {
							System.out.println("userModify: " + ui + " "
									+ this.v.getModifiedInfo(ui));

							if (ui.equals(UserInfo.BIRTHDATE_DAY)) {
								cal.set(Calendar.DAY_OF_MONTH,
										Integer.parseInt(this.v
												.getModifiedInfo(UserInfo.BIRTHDATE_DAY)));
							} else if (ui.equals(UserInfo.BIRTHDATE_MONTH)) {
								cal.set(Calendar.MONTH,
										Integer.parseInt(this.v
												.getModifiedInfo(UserInfo.BIRTHDATE_MONTH)));
							} else if (ui.equals(UserInfo.BIRTHDATE_YEAR)) {
								cal.set(Calendar.YEAR,
										Integer.parseInt(this.v
												.getModifiedInfo(UserInfo.BIRTHDATE_YEAR)));
							}

							if ((this.v.getModifiedInfo(ui) != null)
									&& (this.v.getModifiedInfo(ui) != "")
									&& !ui.equals(UserInfo.BIRTHDATE_DAY)
									&& !ui.equals(UserInfo.BIRTHDATE_MONTH)
									&& !ui.equals(UserInfo.BIRTHDATE_YEAR)) {
								try {
									this.m.changeUser(ui,
											this.actualUser.getIdUser(),
											this.v.getModifiedInfo(ui));
								} catch (ItemException e) {
									this.v.showError(e.getMessage());
								} catch (UserException e1) {
									this.v.showError(e1.getMessage());
								} catch (Exception e2) {
									// TODO Auto-generated catch block
									this.v.showError(e2.getMessage());
								}
							}
						});

		// cambio inserito all'esterno dello stream per BIRTHDATE
		try {
			this.m.changeUser(UserInfo.BIRTHDATE, this.actualUser.getIdUser(),
					cal);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
		/*
		 * for (UserInfo ui : listU) { try { ; } } catch (Exception e) { // TODO
		 * Auto-generated catch block this.v.showError("Errore nell'UserInfo");
		 * } }
		 */
	}

	@Override
	public void itemModify() {
		// DA CONTROLLARE 30 agosto
		// getItemToRemoveModify ottiene la stringa da setAllItemList!!!
		System.out.println("itemModify: entrato");
		Integer itemId = null;
		for (Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString()
						.equals(this.v.getUserItemSelectedByManager())) {
					itemId = i;
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}
		System.out.println("itemModify: "
				+ this.v.getUserItemSelectedByManager());
		for (TypeItemInfo ti : TypeItemInfo.values()) {
			try {
				switch (ti) {
				case TITLE:
					this.m.changeItem(ti, itemId, this.v.getItemInfo(ti));
					break;
				case RELEASE_YEAR:
					int var = Integer.parseInt(this.v.getItemInfo(ti));
					this.m.changeItem(ti, itemId, var);
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
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}
	}

	@Override
	public void setItemInfoMediateca() {
		this.setSelectedItemInfo(this.v.getDoubleClickedItemInMediateca());
	}

	/**
	 * Method which returns all the infos for the item passed as string.
	 *
	 * @param string
	 *            Item passed as string
	 */
	public void setSelectedItemInfo(final String string) {
		System.out.println("setSelectedItemInfo: entrato");
		// getItemSelectedByuSer lo prendo da setFilteredList
		System.out.println("setSelectedItemInfo: getItemSelectedByUser="
				+ string);
		Integer itemId = 0;
		for (Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString().equals(string)) {
					itemId = i;
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}

		try {
			String title = this.m.getRequiredItem(itemId).getTitle();
			int releaseYear = this.m.getRequiredItem(itemId).getReleaseYear();
			String author = this.m.getRequiredItem(itemId).getAuthor();
			Language language = this.m.getRequiredItem(itemId)
					.getCurrentLanguage();

			ItemGenre genre = this.m.getRequiredItem(itemId).getGenre();
			String publisher = this.m.getRequiredItem(itemId).getPublisher();

			Integer numCopy = this.m.getItemArchive().get(itemId).getSecond()
					.getQuantity();

			if (this.m.getAllItemId(TypeItem.BOOK).contains(itemId)) {
				String isbn = this.m.getRequiredItem(itemId).getIsbn();
				System.out.println("setSelectedItemInfo: mostra info libro");
				Integer numRelease = this.m.getRequiredItem(itemId)
						.getNumRelease().get();
				this.v.setBookInfoDoubleClick(title, author, publisher, Integer
						.toString(releaseYear), genre.toString(), Float
						.toString(this.m.getRequiredItem(itemId)
								.getAverageVote()), Integer.toString(numCopy),
						isbn, language.toString());
			} else if (this.m.getAllItemId(TypeItem.MOVIE).contains(itemId)) {
				System.out.println("setSelectedItemInfo: mostra info film");
				String duration = Integer.toString(this.m.getRequiredItem(
						itemId).getDuration());
				TypeColor color = this.m.getRequiredItem(itemId).getColour();
				this.v.setFilmInfoDoubleClick(title, author, publisher, Integer
						.toString(releaseYear), genre.toString(), Float
						.toString(this.m.getRequiredItem(itemId)
								.getAverageVote()), Integer.toString(numCopy),
						duration, color.toString(), language.toString());
			} else {
				System.out.println("setSelectedItemInfo: mostra errore");
				this.v.showError("Item " + Integer.toString(itemId)
						+ " not found in the archive!");
			}

		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void setUserInfo() {
		this.setSelectedUserInfo(this.actualUser);
	}

	public void setSelectedUserInfo(final UserImpl user) {
		System.out.println("setUserInfo: entrato");

		if (user == null) {
			this.v.showError("Errore! Utente corrente non ancora inizializzato");
		} else {
			this.v.setUserModifyField(
					user.getName(),
					user.getSurname(),
					user.getUsername(),
					user.getPassword(),
					String.valueOf(user.getBirthdate().get(
							Calendar.DAY_OF_MONTH)),
					String.valueOf(user.getBirthdate().get(Calendar.MONTH)),
					String.valueOf(user.getBirthdate().get(Calendar.YEAR)),
					user.getEmail(), user.getTelephoneNumber(), user
							.getBookPreferences().get(0).toString(), user
							.getBookPreferences().get(1).toString(), user
							.getBookPreferences().get(2).toString(), user
							.getMoviePreferences().get(0).toString(), user
							.getMoviePreferences().get(1).toString(), user
							.getMoviePreferences().get(2).toString());
			try {
				this.m.setReccomandedList(user.getIdUser());
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}
	}

	// Modificare questi due metodi e renderlo uno solo con un parametro
	@Override
	public void suggestedBooks() {
		// stampa 3 libri di 3 preferenze
		System.out.println("suggestedBooks: entrato");
		String[] array = new String[this.actualUser.getBookPreferences().size()];
		System.out.println("suggestedBooks: getBooksPreferences.size="
				+ this.actualUser.getBookPreferences().size());
		int index = 0;
		try {
			for (Integer i : this.actualUser.getRecommendedList()) {
				System.out.println("suggestedBooks: " + i);
				if (this.m.getAllItemId(TypeItem.BOOK).contains(i)) {
					System.out
							.println("suggestedBooks: entrato nell if grazie a "
									+ i);

					array[index] = this.m.getRequiredItem(i).toString();

					System.out.println("suggestedBooks: inserito elemento "
							+ index);
					index++;
				}
			}
			this.v.setSuggestedBooks(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void suggestedFilms() {
		System.out.println("suggestedFilms: entrato");
		int index = 0;
		String[] array = new String[this.actualUser.getMoviePreferences()
				.size()];
		System.out.println("suggestedFilms: getMoviePreferences.size="
				+ this.actualUser.getMoviePreferences().size());
		try {
			for (Integer i : this.actualUser.getRecommendedList()) {
				System.out.println("suggestedFilms: reccomended list="
						+ this.m.getItemArchive().get(i).toString());
				if (this.m.getAllItemId(TypeItem.MOVIE).contains(i)) {
					array[index] = this.m.getRequiredItem(i).toString();
					System.out.println("suggestedFilms: inserito elemento "
							+ index);
					index++;
				}
			}
			this.v.setSuggestedMovies(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void registerNewUser() {
		System.out.println("registerNewUser: entrato");
		String name = this.v.getUserRegistration(UserInfo.NAME);
		String surname = this.v.getUserRegistration(UserInfo.SURNAME);
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(),
				this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		String username = this.v.getUserRegistration(UserInfo.USERNAME);
		String password = this.v.getUserRegistration(UserInfo.PASSWORD);
		String email = this.v.getUserRegistration(UserInfo.EMAIL);
		String telephoneNumber = this.v
				.getUserRegistration(UserInfo.TELEPHONE_NUMBER);
		ItemGenre bookPref1 = CastManager.castToItemGenre(this.v
				.getUserRegistration(UserInfo.BOOK_PREF1));
		ItemGenre bookPref2 = CastManager.castToItemGenre(this.v
				.getUserRegistration(UserInfo.BOOK_PREF2));
		ItemGenre bookPref3 = CastManager.castToItemGenre(this.v
				.getUserRegistration(UserInfo.BOOK_PREF3));
		List<ItemGenre> bookList = new ArrayList<>(Arrays.asList(bookPref1,
				bookPref2, bookPref3));
		ItemGenre moviePref1 = CastManager.castToItemGenre(this.v
				.getUserRegistration(UserInfo.FILM_PREF1));
		ItemGenre moviePref2 = CastManager.castToItemGenre(this.v
				.getUserRegistration(UserInfo.FILM_PREF2));
		ItemGenre moviePref3 = CastManager.castToItemGenre(this.v
				.getUserRegistration(UserInfo.FILM_PREF3));
		List<ItemGenre> movieList = new ArrayList<>(Arrays.asList(moviePref1,
				moviePref2, moviePref3));
		try {
			System.out
					.println("registerNewUser: prima di aver registrato e salvato");
			this.m.registerUser(name, surname, day, username, password, email,
					telephoneNumber, bookList, movieList);
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEUSER, this.m);
			this.v.showMessage("Utente " + username
					+ " registrato con successo!");
		} catch (IOException e4) {
			this.v.showError(e4.getMessage());
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	/**
	 * Method which adds a new Book to the archive.
	 */
	public void registerNewBook() {
		System.out.println("registerNewBook: entrato");

		String title = this.v.getItemInfo(TypeItemInfo.TITLE);
		int releaseYear = Integer.parseInt(this.v
				.getItemInfo(TypeItemInfo.RELEASE_YEAR));
		String author = this.v.getItemInfo(TypeItemInfo.AUTHOR);
		Language language = CastManager.castToLanguage(this.v
				.getItemInfo(TypeItemInfo.LANGUAGE));
		String isbn = this.v.getItemInfo(TypeItemInfo.ISBN);
		ItemGenre genre = CastManager.castToItemGenre(this.v
				.getItemInfo(TypeItemInfo.GENRE));
		String publisher = this.v.getItemInfo(TypeItemInfo.PRODUCER);
		System.out.println(this.v
				.getOtherItemInfo(ViewImpl.OtherItemFilter.RELEASE_NUMBER));
		System.out.println(this.v
				.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER));
		Integer numRelease = Integer.parseInt(this.v
				.getOtherItemInfo(ViewImpl.OtherItemFilter.RELEASE_NUMBER));
		Integer numCopy = Integer.parseInt(this.v
				.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER));
		try {
			System.out
					.println("registerNewBook: prima di aver registrato e salvato");
			this.m.registerBook(title, releaseYear, author, language, isbn,
					genre, publisher, numRelease, numCopy);
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEITEM, this.m);
			System.out.println("registerNewBook: registrato e salvato");
		} catch (IOException e4) {
			this.v.showError(e4.getMessage());
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	/**
	 * Method which adds a new Movie to the archive.
	 */
	public void registerNewMovie() {
		System.out.println("registerNewMovie: entrato");

		String title = this.v.getItemInfo(TypeItemInfo.TITLE);
		int releaseYear = Integer.parseInt(this.v
				.getItemInfo(TypeItemInfo.RELEASE_YEAR));
		String publisher = this.v.getItemInfo(TypeItemInfo.PRODUCER);
		String author = this.v.getItemInfo(TypeItemInfo.AUTHOR);
		Language language = CastManager.castToLanguage(this.v
				.getItemInfo(TypeItemInfo.LANGUAGE));
		ItemGenre genre = CastManager.castToItemGenre(this.v
				.getItemInfo(TypeItemInfo.GENRE));
		Integer duration = Integer.parseInt(this.v
				.getItemInfo(TypeItemInfo.DURATION));
		TypeColor color = CastManager.castToTypeColor((this.v
				.getItemInfo(TypeItemInfo.COLOR)));
		Integer numCopy = Integer.parseInt(this.v
				.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER));
		try {
			System.out
					.println("registerNewMovie: prima di aver registrato e salvato");
			this.m.registerMovie(title, releaseYear, publisher, author,
					language, genre, duration, color, numCopy);
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEITEM, this.m);
			System.out.println("registerNewMovie: registrato e salvato");
		} catch (IOException e4) {
			this.v.showError(e4.getMessage());
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void itemCreate() {
		System.out.println("itemCreate: entrato");
		if (this.v.getItemInfo(TypeItemInfo.TYPE).equals(
				TypeItem.BOOK.toString())) {
			System.out.println("itemCreate: dentro al libro");
			this.registerNewBook();

		} else if (this.v.getItemInfo(TypeItemInfo.TYPE).equals(
				TypeItem.MOVIE.toString())) {
			System.out.println("itemCreate: dentro al film");
			this.registerNewMovie();

		}
	}

	@Override
	public void elaborateLoans() {
		System.out.println("elaborateLoans: entrato");
		Map<Integer, Double> map;
		try {
			map = this.m.checkDeadlineas(this.actualUser.getIdUser());
			System.out.println("elaborateLoans: dentro al try");
			map.keySet()
					.stream()
					.forEach(
							i -> {
								if (map.get(i) > 60) {
									try {
										this.v.showGiveBackMessage(this.m
												.getRequiredItem(i).toString());
									} catch (Exception e) {
										// TODO Auto-generated catch block
										this.v.showError("Errore! Oggetto non presente nell'archivio");
									}
								} else if (map.get(i) > 30) {
									try {
										this.v.showGiveBackOptionMessage(this.m
												.getRequiredItem(i).toString());
									} catch (ItemException e) {
										this.v.showError(e.getMessage());
									} catch (UserException e1) {
										this.v.showError(e1.getMessage());
									} catch (Exception e2) {
										// TODO Auto-generated catch block
										this.v.showError(e2.getMessage());
									}
								}
							});
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void giveBackItem(final String item) {
		// DA RIVEDERE, edit 22 agosto, dovrebbe essere corretto
		// tolto getFirst().getTitle() e sostituito con getFirst().toString()
		System.out.println("giveBackItem: entrato");
		try {
			for (Integer i : this.actualUser.getLoanArchive().keySet()) {
				if (this.m.getRequiredItem(i).toString().equals(item)) {
					this.m.returnItem(i, this.actualUser.getIdUser());
					this.v.showMessage("Oggetto " + this.m.getRequiredItem(i)
							+ " restituito!");
					System.out.println("giveBackItem: try eseguito");
					this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEITEM,
							this.m);
					return;
				}
			}
			this.v.showMessage("Oggetto da restituire non trovato!");
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void giveBackItemSelectedByUser() {
		System.out.println("giveBackItemSelectedByUser: entrato");
		System.out
				.println("giveBackItemSelectedByUser: getitemtoremoveformlikeborrwwish="
						+ this.v.getItemToRemoveFromLikeBorrowWish());
		this.giveBackItem(this.v.getItemToRemoveFromLikeBorrowWish());
	}

	@Override
	public void setTakenSitsList() {
		System.out.println("setTakenSitsList: entrato");

		/*
		 * GregorianCalendar day = new GregorianCalendar();
		 * day.set(this.v.getStudyRoomSelectedYear(),
		 * this.v.getStudyRoomSelectedMonth(),
		 * this.v.getStudyRoomSelectedDay());
		 */

		// String[] array = new String[this.m.getStudyRoomSit()];

		int[] arrayInt = new int[this.m.getStudyRoomSit()];
		System.out
				.println("setTakenSitsList: this.m.getAllUserSit(day).size()="
						+ this.m.getStudyRoomSit());
		System.out.println("setTakenSitsList: arrayInt=" + arrayInt.length);
		for (int i = 0; i < this.m.getStudyRoomSit(); i++) {
			System.out.print(this.m
					.getAllUserSit(
							new GregorianCalendar(this.v
									.getStudyRoomSelectedYear(), this.v
									.getStudyRoomSelectedMonth(), this.v
									.getStudyRoomSelectedDay())).get(i)
					.toString()
					+ ",");
			if (this.m
					.getAllUserSit(
							new GregorianCalendar(this.v
									.getStudyRoomSelectedYear(), this.v
									.getStudyRoomSelectedMonth(), this.v
									.getStudyRoomSelectedDay())).get(i)
					.equals(this.actualUser.getIdUser())) {
				arrayInt[i] = 1;
			} else {
				arrayInt[i] = this.m.getAllUserSit(
						new GregorianCalendar(
								this.v.getStudyRoomSelectedYear(), this.v
										.getStudyRoomSelectedMonth(), this.v
										.getStudyRoomSelectedDay())).get(i);
			}
		}

		this.v.setStudyRoomStatus(arrayInt);
		/*
		 * for (int index = 0; index < (arrayInt.size()); index++) {
		 * System.out.print(arrayInt.get(index)); if ((arrayInt.get(index) ==
		 * null) || (arrayInt.get(index) == 0)) { array[index] = "0"; } else if
		 * (arrayInt.get(index).equals(this.actualUser.getIdUser())) {
		 * array[index] = "1"; System.out.println(
		 * "setTakenSitsList: assegnato posto " + index + " a 1"); } else { try
		 * { array[index] =
		 * this.m.getRequiredUser(arrayInt.get(index)).getUsername();
		 * System.out.println("setTakenSitsList: assegnato posto " + index +
		 * " a user"); } catch (ItemException e) {
		 * this.v.showError(e.getMessage()); } catch (UserException e1) {
		 * this.v.showError(e1.getMessage()); } catch (Exception e2) { // TODO
		 * Auto-generated catch block this.v.showError(e2.getMessage()); } } }
		 */

		// System.out.println(Arrays.toString(array));
	}

	@Override
	public void takeSit() {
		System.out.println("takeSit: entrato");

		/*
		 * GregorianCalendar day = new GregorianCalendar();
		 * day.set(this.v.getStudyRoomSelectedYear(),
		 * this.v.getStudyRoomSelectedMonth(),
		 * this.v.getStudyRoomSelectedDay());
		 */

		System.out.println("takeSit: " + this.v.getSelectedSit());

		try {
			System.out.println("takeSit: entrato nel try");
			System.out.println("takeSit: this.m.getAllUserSit(day)="
					+ this.m.getAllUserSit(
							new GregorianCalendar(this.v
									.getStudyRoomSelectedYear(), this.v
									.getStudyRoomSelectedMonth(), this.v
									.getStudyRoomSelectedDay())).toString());
			this.m.bookSit(
					new GregorianCalendar(this.v.getStudyRoomSelectedYear(),
							this.v.getStudyRoomSelectedMonth(), this.v
									.getStudyRoomSelectedDay()), this.v
							.getSelectedSit(), this.actualUser.getIdUser());
			System.out.println("takeSit: eseguito il book");
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMESTUDYROOM,
					this.m);
			System.out.println("takeSit: scritto nel file");
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void cancelSit() {
		// rivisto 22 agosto

		// this.v.getSelectedSit(); // convertire per ottenere nel posto da
		// cancellare e il giorno
		// mi ritorna un pair con posto, giorno e devo fare il cast inverso

		// elaborare la stringa per ottenere la data
		System.out.println("cancelSit: entrato");
		/*
		 * GregorianCalendar day = new GregorianCalendar();
		 * day.set(this.v.getStudyRoomSelectedYear(),
		 * this.v.getStudyRoomSelectedMonth(),
		 * this.v.getStudyRoomSelectedDay());
		 */

		try {
			System.out.println("cancelSit: entrato nel try");
			this.m.cancelSit(
					new GregorianCalendar(this.v.getStudyRoomSelectedYear(),
							this.v.getStudyRoomSelectedMonth(), this.v
									.getStudyRoomSelectedDay()), this.v
							.getSelectedSit(), this.actualUser.getIdUser());
			System.out.println("cancelSit: eseguito il cancel");
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMESTUDYROOM,
					this.m);
			System.out.println("cancelSit: eseguito il write");
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
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
		System.out.println("setWishList: entrato");
		String[] array = new String[this.actualUser.getWishlist().size()];
		System.out.println("setWishList: getWishList.size="
				+ this.actualUser.getWishlist().size());
		int index = 0;
		try {
			for (Integer i : this.actualUser.getWishlist()) {
				array[index] = this.m.getRequiredItem(i).toString();
				System.out.println("setWishList: aggiunta elemento " + i);
				index++;
			}
			this.v.setWishlist(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
		System.out.println("setWishList: array restituito");
	}

	@Override
	public void removeFromWishList() {
		System.out.println("removeFromWishlist: entrato");
		try {
			for (Integer i : this.actualUser.getWishlist()) {
				System.out.println("removeFromWishList: entrato nel for");
				if (this.m.getRequiredItem(i).toString()
						.equals(this.v.getItemToRemoveFromLikeBorrowWish())) {

					this.m.removeLike(i, this.actualUser.getIdUser());
					System.out.println("removeFromWishlist: entrato dopo op");

				}
			}
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void setAllUserList() {
		// forse sbagliato
		// manca v.setAllUserList
		int index = 0;
		String[] array = new String[this.m.getUserArchive().size()];
		try {
			for (Integer i : this.m.getUserArchive().keySet()) {
				array[index] = this.m.getRequiredUser(i).toString();
				index++;
			}
			System.out.println("setAllUserList: entrato, restituisco array");
			this.v.setUserList(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void setAllItemList() {
		// forse sbagliato
		// manca v.setAllItemList
		// manca v.getItemType
		int index = 0;
		String[] array = new String[this.m.getItemArchive().size()];
		try {
			for (Integer i : this.m.getItemArchive().keySet()) {
				array[index] = this.m.getRequiredItem(i).toString();
				index++;
			}
			System.out.println("setAllItemList: entrato, restituisco array");
			this.v.setItemList(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void deleteItem() {
		int itemIdReceived = 0;
		System.out.println("deleteItem: entrato");
		System.out.println("deleteItem: "
				+ this.v.getUserItemSelectedByManager() + " restituito");
		try {
			for (Integer i : this.m.getItemArchive().keySet()) {
				if (this.m.getRequiredItem(i).toString()
						.equals(this.v.getUserItemSelectedByManager())) {
					System.out
							.println("deleteItem: entrato nell'if e init la var");
					itemIdReceived = i;
				}
			}

			this.m.deleteItem(itemIdReceived);
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEITEM, this.m);
			this.v.showMessage("Oggetto " + itemIdReceived + " cancellato");
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void deleteUser() {
		try {
			this.m.deleteUser(this.actualUser.getIdUser());
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEUSER, this.m);
			this.v.showMessage("Utente " + this.actualUser.getIdUser()
					+ " cancellato");
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void extendBorrow(final String book) {
		// fai comparire solo uan finestra che dice che � stato esteso

		// se block user � true, manda la schermata solo per restituire
		this.v.showMessage("Prestito esteso per l'oggetto " + book);

	}

	@Override
	public void allItemReviews() {
		int index = 0;
		int id = 0;
		System.out.println("AllItemReviews: entrato");
		System.out.println("AllItemReviews: " + this.v.getItemSelectedByUser());
		try {
			for (Integer i : this.m.getItemArchive().keySet()) {
				if (this.m.getRequiredItem(i).toString()
						.equals(this.v.getItemSelectedByUser())) {
					id = i;
					System.out.println("AllItemReviews: id registrato");
				}
			}
			String[] array;
			System.out.println("AllItemReviews: creato array stringhe");
			array = new String[this.m.getAllItemReview(id).size()];
			/*
			 * for (Review r : this.m.getAllItemReview(id)) { array[index] =
			 * r.toString(); index++; }
			 */
			System.out.println("AllItemReviews: inizializzato stringhe");
			for (int i = 0; i < this.m.getAllItemReview(id).size(); i++) {
				array[i] = this.m.getAllItemReview(id).get(i).toString();
			}
			this.v.setItemReviewsList(array);
			System.out.println("AllItemReviews: try passato");
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void giveOtherUserInfo() {
		// forse da eliminare
		/*
		 * this.v.setUserModifyField(name, surname, username, password,
		 * birthDate_day, birthDate_month, birthDate_year, email, telephone,
		 * bPref1, bPref2, bPref3, fPref1, fPref2, fPref3);
		 */
		UserImpl user = null;
		for (Integer i : this.m.getUserArchive().keySet()) {
			try {
				if (this.m.getRequiredUser(i).toString()
						.equals(this.v.getDoubleClickedInManager())) {
					user = this.m.getRequiredUser(i);
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}
		this.v.setUserInfoDoubleClick(user.getName(), user.getSurname(), user
				.getUsername(), user.getPassword(), user.getBirthdate()
				.toString(), user.getEmail(), user.getTelephoneNumber(), user
				.getBookPreferences().get(0).toString(), user
				.getBookPreferences().get(1).toString(), user
				.getBookPreferences().get(2).toString(), user
				.getMoviePreferences().get(0).toString(), user
				.getMoviePreferences().get(1).toString(), user
				.getMoviePreferences().get(2).toString());
	}

	@Override
	public void elementSelectedInManager() {
		System.out.println("elementSelectedInManager: entrato");
		System.out
				.println("elementSelectedInManager: getdoubleclickedinmanager="
						+ this.v.getDoubleClickedInManager());
		// ANALIZZARE QUESTIONE SE UTENTE O OGGETTO
		for (Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString()
						.equals(this.v.getDoubleClickedInManager())) {
					this.setSelectedItemInfo(this.v.getDoubleClickedInManager());
					return;
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}

		for (Integer i : this.m.getUserArchive().keySet()) {
			try {
				if (this.m.getRequiredUser(i).toString()
						.equals(this.v.getDoubleClickedInManager())) {
					this.setSelectedUserInfo(this.m.getRequiredUser(i));
					return;
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				this.v.showError(e2.getMessage());
			}
		}

	}

	@Override
	public void takeItemBefore() {
		this.itemBeforeScreenChange = this.v.getItemSelectedByUser();
	}

	@Override
	public void otherUserBorrowList() {
		UserImpl user = null;
		try {
			for (Integer i : this.m.getUserArchive().keySet()) {
				if (this.m.getRequiredUser(i).toString()
						.equals(this.v.getUserItemSelectedByManager())) {
					user = this.m.getRequiredUser(i);
				}
			}
		} catch (UserException e) {
			this.v.showError(e.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
		this.borrowListFromUser(user);
	}

	@Override
	public void setView(final view.View v) {
		this.v = v;
		// this.writeOnFile();
	}

	@Override
	public void setItemModifyField() {
		try {
			for (Integer i : this.m.getItemArchive().keySet()) {

				if (this.m.getRequiredItem(i).toString()
						.equals(this.v.getUserItemSelectedByManager())) {
					if (this.m.getAllItemId(TypeItem.BOOK).contains(i)) {
						this.v.setBookField(this.m.getRequiredItem(i)
								.getTitle(), this.m.getRequiredItem(i)
								.getAuthor(), this.m.getRequiredItem(i)
								.getPublisher(), Integer.toString(this.m
								.getRequiredItem(i).getReleaseYear()), this.m
								.getRequiredItem(i).getGenre(), this.m
								.getRequiredItem(i).getIsbn(), this.m
								.getRequiredItem(i).getCurrentLanguage(),
								this.m.getItemArchive().get(i).getSecond()
										.getQuantity(),
								this.m.getRequiredItem(i).getNumRelease().get());
					} else if (this.m.getAllItemId(TypeItem.MOVIE).contains(i)) {
						this.v.setFilmField(this.m.getRequiredItem(i)
								.getTitle(), this.m.getRequiredItem(i)
								.getAuthor(), this.m.getRequiredItem(i)
								.getPublisher(), Integer.toString(this.m
								.getRequiredItem(i).getReleaseYear()), this.m
								.getRequiredItem(i).getGenre(), Integer
								.toString(this.m.getRequiredItem(i)
										.getDuration()), this.m
								.getRequiredItem(i).getColour(), this.m
								.getRequiredItem(i).getCurrentLanguage(),
								this.m.getItemArchive().get(i).getSecond()
										.getQuantity(),
								this.m.getRequiredItem(i).getNumRelease().get());
					}
				}
			}
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError(e2.getMessage());
		}
	}
}
