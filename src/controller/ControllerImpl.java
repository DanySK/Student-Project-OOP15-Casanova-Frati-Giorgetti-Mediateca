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
import java.util.stream.Collectors;

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
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.FANTASY, ItemGenre.HORROR, ItemGenre.ANIMATION)));
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
			cal2.set(Calendar.YEAR, 2016);
			cal2.set(Calendar.MONTH, 8);
			cal2.set(Calendar.DAY_OF_MONTH, 23);
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

			Random random = new Random();
			int n = 5;
			int index = 0;

			for (Integer i : this.m.getItemArchive().keySet()) {
				int k = random.nextInt(n);

				this.m.addReview(i, ((UserImpl) u).getIdUser(), k, "recensione numero " + index);
				if ((index % 2) == 0) {
					this.m.addLike(i, ((UserImpl) u).getIdUser());
				}
				index++;
			}

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
		if (this.m.getSystemPassword().equals(this.v.getMenagerPassword())) {
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
		System.out.println("itemElaboration: v.getitemfilter " + this.v.getItemFilter());
		TypeItem ty = null;
		for (TypeItem y : TypeItem.values()) {
			if (y.equals(this.v.getItemFilter())) {
				ty = y;
			}
		}
		System.out.println("itemElaboration: ty =" + ty);
		System.out.println("itemElaboration: v.getsearchfilter = " + this.v.getSearchFilter());
		TypeItemInfo ts = null;
		for (TypeItemInfo s : TypeItemInfo.values()) {
			if (s.equals(this.v.getSearchFilter())) {
				ts = s;
			}
		}
		System.out.println("itemElaboration: ts = " + ts);
		System.out.println("itemElaboration: this.v.getsearchtext =" + this.v.getSearchText());
		Object searchText = this.v.getSearchText();
		System.out.println("itemElaboration: searchtext=" + searchText);
		if ((ty == null) && ((searchText == null) || searchText.equals(""))) {
			array = new String[this.m.getItemArchive().size()];
			try {
				for (Integer i : this.m.getItemArchive().keySet()) {
					array[index] = this.m.getItemArchive().get(i).toString();
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
				for (Integer i : this.m.filtersItem(this.m.getItemArchive().keySet(), ts, searchText)) {
					array[index] = this.m.getItemArchive().get(i).toString();
					index++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.v.showError("Filtraggio oggetti fallito");
			}
		} else {
			array = new String[this.m.getAllItemId(ty).size()];
			try {
				for (Integer i : this.m.filtersItem(this.m.getAllItemId(ty), ts, searchText)) {
					array[index] = this.m.getItemArchive().get(i).toString();
					index++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block

			}
		}

		this.v.setFilteredList(array);
		System.out.println("itemElaboration: restituita lista");
	}

	// first draft
	@Override
	public void addLike() {
		// DA CONTROLLARE
		System.out.println("addLike: entrato");
		System.out.println("addLike: " + this.v.getItemSelectedByUser());

		this.m.getItemArchive().keySet().stream()
				.filter(i -> this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser()))
				.forEach(i -> {
					try {
						this.m.addLike(i, this.actualUser.getIdUser());
						this.v.showMessage("Oggetto " + this.m.getItemArchive().get(i) + " messo in wishlist");
						System.out.println("addLike: entrato nel try");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						this.v.showError("Errore inserimento oggetto in wishlist");
					}
				});
		/*
		 * for (Integer i : this.m.getItemArchive().keySet()) { if
		 * (this.m.getItemArchive().get(i).toString().equals(this.v.
		 * getItemSelectedByUser())) { try { this.m.addLike(i,
		 * this.actualUser.getIdUser()); this.v.showMessage("Oggetto " +
		 * this.m.getItemArchive().get(i) + " messo in wishlist"); } catch
		 * (Exception e) { // TODO Auto-generated catch block this.v.showError(
		 * "Errore inserimento oggetto in wishlist"); } } }
		 */
	}

	@Override
	public void addReview() {
		// DA CONTROLLARE
		System.out.println("addReview: entrato");
		System.out.println("addReview: " + this.v.getItemSelectedByUser());
		System.out.println("addReview: score e review " + this.v.getScore() + this.v.getReview());
		this.m.getItemArchive().keySet().stream()
				.filter(i -> this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser()))
				.forEach(i -> {
					try {
						this.m.addReview(i, this.actualUser.getIdUser(), this.v.getScore(), this.v.getReview());
						this.v.showMessage("Recensione per l'oggetto " + this.m.getItemArchive().get(i) + "inserita");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						this.v.showError("Errore inserimento recensione oggetto");
					}
				});
		/*
		 * for (Integer i : this.m.getItemArchive().keySet()) { if
		 * (this.m.getItemArchive().get(i).toString().equals(this.v.
		 * getItemSelectedByUser())) { try { this.m.addReview(i,
		 * this.actualUser.getIdUser(), this.v.getScore(), this.v.getReview());
		 * this.v.showMessage("Recensione per l'oggetto " +
		 * this.m.getItemArchive().get(i) + "inserita"); } catch (Exception e) {
		 * // TODO Auto-generated catch block this.v.showError(
		 * "Errore inserimento recensione oggetto"); } } }
		 */

	}

	@Override
	public void borrowList() {
		System.out.println("borrowList: entrato");
		System.out.println("borrowList: dimensione archivio prestiti=" + this.actualUser.getLoanArchive().size());
		try {
			this.actualLoanArchive = this.actualUser.getLoanArchive();
			String[] array = new String[this.actualLoanArchive.size()];
			int index = 0;

			array = this.m.getItemBorrowed(this.actualUser.getIdUser()).stream()
					.map(i -> this.m.getItemArchive().get(i).getFirst().toString()).collect(Collectors.toList())
					.toArray(array);

			/*
			 * for (Integer i :
			 * this.m.getItemBorrowed(this.actualUser.getIdUser())) {
			 * array[index] =
			 * this.m.getItemArchive().get(i).getFirst().toString(); index++; }
			 */
			this.v.setBorrowedItemList(array);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore ritrovamento oggetto prestato");
		}
	}

	@Override
	public void borrowItem() {
		System.out.println("borrowItem: entrato");
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser())) {
				try {
					this.m.borrowItem(i, this.actualUser.getIdUser());
					System.out.println("userModify: entrato nel try");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.v.showError("Errore! itemId o userId non presente nell'archivio");
				}
			}
		}
		System.out.println("userModify: " + this.v.getItemSelectedByUser());
	}

	@Override
	public void userModify() {
		// for per ogni tipo di userinfo e fare changeUser totale
		GregorianCalendar cal = new GregorianCalendar();

		System.out.println("userModify: entrato");
		Arrays.stream(UserInfo.values())
				.filter(ui -> !ui.equals(UserInfo.BIRTHDATE) && !ui.equals(UserInfo.BOOK_PREF1)
						&& !ui.equals(UserInfo.BOOK_PREF2) && !ui.equals(UserInfo.BOOK_PREF3)
						&& !ui.equals(UserInfo.FILM_PREF1) && !ui.equals(UserInfo.FILM_PREF2)
						&& !ui.equals(UserInfo.FILM_PREF3))
				.forEach(ui -> {
					System.out.println("userModify: " + ui + " " + this.v.getModifiedInfo(ui));

					if (ui.equals(UserInfo.BIRTHDATE_DAY)) {
						cal.set(Calendar.DAY_OF_MONTH,
								Integer.parseInt(this.v.getModifiedInfo(UserInfo.BIRTHDATE_DAY)));
					} else if (ui.equals(UserInfo.BIRTHDATE_MONTH)) {
						cal.set(Calendar.MONTH, Integer.parseInt(this.v.getModifiedInfo(UserInfo.BIRTHDATE_MONTH)));
					} else if (ui.equals(UserInfo.BIRTHDATE_YEAR)) {
						cal.set(Calendar.YEAR, Integer.parseInt(this.v.getModifiedInfo(UserInfo.BIRTHDATE_YEAR)));
					}

					if ((this.v.getModifiedInfo(ui) != null) && (this.v.getModifiedInfo(ui) != "")
							&& !ui.equals(UserInfo.BIRTHDATE_DAY) && !ui.equals(UserInfo.BIRTHDATE_MONTH)
							&& !ui.equals(UserInfo.BIRTHDATE_YEAR)) {
						try {
							this.m.changeUser(ui, this.actualUser.getIdUser(), this.v.getModifiedInfo(ui));
						} catch (Exception e) {
							this.v.showError("Utente non presente nell'archivio per la modifica");
						}
					}
				});

		// cambio inserito all'esterno dello stream per BIRTHDATE
		try {
			this.m.changeUser(UserInfo.BIRTHDATE, this.actualUser.getIdUser(), cal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Utente non presente nell'archivio per la modifica");
		}
		/*
		 * for (UserInfo ui : listU) { try { ; } } catch (Exception e) { // TODO
		 * Auto-generated catch block this.v.showError("Errore nell'UserInfo");
		 * } }
		 */
	}

	@Override
	public void itemModify() {
		// getItemToRemoveModify ottiene la stringa da setAllItemList!!!
		System.out.println("itemModify: entrato");
		Integer itemId = null;
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemToRemoveModify())) {
				itemId = i;
			}
		}
		System.out.println("itemModify: " + this.v.getItemToRemoveModify());
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.v.showError("Errore nell'TypeItemInfo o itemId");
			}
		}
	}

	@Override
	public void setItemInfo() {
		System.out.println("setItemInfo: entrato");
		// getItemSelectedByuSer lo prendo da setFilteredList
		System.out.println("setItemInfo: getItemSelectedByUser=" + this.v.getItemSelectedByUser());
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
		String isbn = this.v.getItemInfo(TypeItemInfo.ISBN);
		ItemGenre genre = this.m.getItemArchive().get(itemId).getFirst().getGenre();
		String publisher = this.m.getItemArchive().get(itemId).getFirst().getPublisher();
		Integer numRelease = Integer.parseInt(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.RELEASE_NUMBER));
		Integer numCopy = Integer.parseInt(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER));
		String duration = this.v.getItemInfo(TypeItemInfo.DURATION);

		TypeColor color = CastManager.castToTypeColor(this.v.getItemInfo(TypeItemInfo.COLOR));

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
	public void setUserInfo() {
		System.out.println("setUserInfo: entrato");

		if (this.actualUser == null) {
			this.v.showError("Errore! Utente corrente non ancora inizializzato");
		} else {
			this.v.setUserModifyField(this.actualUser.getName(), this.actualUser.getSurname(),
					this.actualUser.getUsername(), this.actualUser.getPassword(),
					String.valueOf(this.actualUser.getBirthdate().get(Calendar.DAY_OF_MONTH)),
					String.valueOf(this.actualUser.getBirthdate().get(Calendar.MONTH)),
					String.valueOf(this.actualUser.getBirthdate().get(Calendar.YEAR)), this.actualUser.getEmail(),
					this.actualUser.getTelephoneNumber(), this.actualUser.getBookPreferences().get(0).toString(),
					this.actualUser.getBookPreferences().get(1).toString(),
					this.actualUser.getBookPreferences().get(2).toString(),
					this.actualUser.getMoviePreferences().get(0).toString(),
					this.actualUser.getMoviePreferences().get(1).toString(),
					this.actualUser.getMoviePreferences().get(2).toString());
			try {
				this.m.setReccomandedList(this.actualUser.getIdUser());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.v.showError("Errore! Impossibile aggiornare lista dopo modifica utente");
			}
		}
	}

	// Modificare questi due metodi e renderlo uno solo con un parametro
	@Override
	public void suggestedBooks() {
		// stampa 3 libri di 3 preferenze
		System.out.println("suggestedBooks: entrato");
		String[] array = new String[this.actualUser.getBookPreferences().size()];
		System.out.println("suggestedBooks: getBooksPreferences.size=" + this.actualUser.getBookPreferences().size());
		int index = 0;
		for (Integer i : this.actualUser.getRecommendedList()) {
			System.out.println("suggestedBooks: " + i);
			if (this.m.getAllItemId(TypeItem.BOOK).contains(i)) {
				System.out.println("suggestedBooks: entrato nell if grazie a " + i);
				array[index] = this.m.getItemArchive().get(i).toString();
				System.out.println("suggestedBooks: inserito elemento " + index);
				index++;
			}
		}
		this.v.setSuggestedBooks(array);
	}

	@Override
	public void suggestedFilms() {
		System.out.println("suggestedFilms: entrato");
		int index = 0;
		String[] array = new String[this.actualUser.getMoviePreferences().size()];
		System.out.println("suggestedFilms: getMoviePreferences.size=" + this.actualUser.getMoviePreferences().size());
		for (Integer i : this.actualUser.getRecommendedList()) {
			System.out.println("suggestedFilms: reccomended list=" + this.m.getItemArchive().get(i).toString());
			if (this.m.getAllItemId(TypeItem.MOVIE).contains(i)) {
				array[index] = this.m.getItemArchive().get(i).toString();
				System.out.println("suggestedFilms: inserito elemento " + index);
				index++;
			}
		}
		this.v.setSuggestedMovies(array);
	}

	@Override
	public void registerNewUser() {
		System.out.println("registerNewUser: entrato");
		String name = this.v.getUserRegistration(UserInfo.NAME);
		String surname = this.v.getUserRegistration(UserInfo.SURNAME);
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		String username = this.v.getUserRegistration(UserInfo.USERNAME);
		String password = this.v.getUserRegistration(UserInfo.PASSWORD);
		String email = this.v.getUserRegistration(UserInfo.EMAIL);
		String telephoneNumber = this.v.getUserRegistration(UserInfo.TELEPHONE_NUMBER);
		ItemGenre bookPref1 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.BOOK_PREF1));
		ItemGenre bookPref2 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.BOOK_PREF2));
		ItemGenre bookPref3 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.BOOK_PREF3));
		List<ItemGenre> bookList = new ArrayList<>(Arrays.asList(bookPref1, bookPref2, bookPref3));
		ItemGenre moviePref1 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.FILM_PREF1));
		ItemGenre moviePref2 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.FILM_PREF2));
		ItemGenre moviePref3 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.FILM_PREF3));
		List<ItemGenre> movieList = new ArrayList<>(Arrays.asList(moviePref1, moviePref2, moviePref3));
		try {
			System.out.println("registerNewUser: prima di aver registrato e salvato");
			this.m.registerUser(name, surname, day, username, password, email, telephoneNumber, bookList, movieList);
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEUSER, this.m);
			this.v.showMessage("Utente " + username + " registrato con successo!");
		} catch (IOException e2) {
			this.v.showError("File " + ControllerImpl.FILENAMEUSER + " non trovato per il salvataggio");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Utente già presente nell'archivio");
		}
	}

	/**
	 * Method which adds a new Book to the archive.
	 */
	public void registerNewBook() {
		System.out.println("registerNewBook: entrato");
		String title = this.v.getItemInfo(TypeItemInfo.TITLE);
		int releaseYear = Integer.parseInt(this.v.getItemInfo(TypeItemInfo.RELEASE_YEAR));
		String author = this.v.getItemInfo(TypeItemInfo.AUTHOR);
		Language language = CastManager.castToLanguage(this.v.getItemInfo(TypeItemInfo.LANGUAGE));
		String isbn = this.v.getItemInfo(TypeItemInfo.ISBN);
		ItemGenre genre = CastManager.castToItemGenre(this.v.getItemInfo(TypeItemInfo.GENRE));
		String publisher = this.v.getItemInfo(TypeItemInfo.PRODUCER);
		System.out.println(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.RELEASE_NUMBER));
		System.out.println(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER));
		Integer numRelease = Integer.parseInt(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.RELEASE_NUMBER));
		Integer numCopy = Integer.parseInt(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER));
		try {
			System.out.println("registerNewBook: prima di aver registrato e salvato");
			this.m.registerBook(title, releaseYear, author, language, isbn, genre, publisher, numRelease, numCopy);
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEITEM, this.m);
			System.out.println("registerNewBook: registrato e salvato");
		} catch (IOException e2) {
			this.v.showError("File " + ControllerImpl.FILENAMEITEM + " non trovato per il salvataggio");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Numero di copie inferiori a 0");
		}
	}

	/**
	 * Method which adds a new Movie to the archive.
	 */
	public void registerNewMovie() {
		System.out.println("registerNewMovie: entrato");
		String title = this.v.getItemInfo(TypeItemInfo.TITLE);
		int releaseYear = Integer.parseInt(this.v.getItemInfo(TypeItemInfo.RELEASE_YEAR));
		String publisher = this.v.getItemInfo(TypeItemInfo.PRODUCER);
		String author = this.v.getItemInfo(TypeItemInfo.AUTHOR);
		Language language = CastManager.castToLanguage(this.v.getItemInfo(TypeItemInfo.LANGUAGE));
		ItemGenre genre = CastManager.castToItemGenre(this.v.getItemInfo(TypeItemInfo.GENRE));
		Integer duration = Integer.parseInt(this.v.getItemInfo(TypeItemInfo.DURATION));
		Boolean color = Boolean.valueOf(this.v.getItemInfo(TypeItemInfo.COLOR));
		Integer numCopy = Integer.parseInt(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER));
		try {
			System.out.println("registerNewMovie: prima di aver registrato e salvato");
			this.m.registerMovie(title, releaseYear, publisher, author, language, genre, duration, color, numCopy);
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMEITEM, this.m);
			System.out.println("registerNewMovie: registrato e salvato");
		} catch (IOException e2) {
			this.v.showError("File " + ControllerImpl.FILENAMEITEM + " non trovato per il salvataggio");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Numero di copie inferiori a 0");
		}
	}

	@Override
	public void itemCreate() {
		System.out.println("itemCreate: entrato");
		if (this.v.getItemInfo(TypeItemInfo.TYPE).equals(TypeItem.BOOK.toString())) {
			System.out.println("itemCreate: dentro al libro");
			this.registerNewBook();

		} else if (this.v.getItemInfo(TypeItemInfo.TYPE).equals(TypeItem.MOVIE.toString())) {
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
			map.keySet().stream().forEach(i -> {
				if (map.get(i) > 60) {
					this.v.showGiveBackMessage(this.m.getItemArchive().get(i).getFirst().toString());
				} else if (map.get(i) > 30) {
					this.v.showGiveBackOptionMessage(this.m.getItemArchive().get(i).getFirst().toString());
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Utente non presente nell'archivio");
		}
	}

	@Override
	public void giveBackItem(final String item) {
		// DA RIVEDERE, edit 22 agosto, dovrebbe essere corretto
		// tolto getFirst().getTitle() e sostituito con getFirst().toString()
		System.out.println("giveBackItem: entrato");
		for (Integer i : this.actualUser.getLoanArchive().keySet()) {
			if (this.m.getItemArchive().get(i).getFirst().toString().equals(item)) {
				try {
					this.m.returnItem(i, this.actualUser.getIdUser());
					this.v.showMessage("Oggetto " + this.m.getRequiredItem(i));
					System.out.println("giveBackItem: try eseguito");
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
		System.out.println("giveBackItemSelectedByUser: entrato");
		System.out.println("giveBackItemSelectedByUser: " + this.v.getItemSelectedByUser());
		this.giveBackItem(this.v.getItemSelectedByUser());
	}

	@Override
	public void setTakenSitsList() {
		System.out.println("setTakenSitsList: entrato");

		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		String[] array = new String[100];
		Integer[] arrayInt = new Integer[100];
		System.out.println(arrayInt.length);
		arrayInt = this.m.getStudyRoom().get(day).toArray(arrayInt);
		for (int index = 0; index < (arrayInt.length - 1); index++) {
			if ((arrayInt[index] == null) || (arrayInt[index] == 0)) {
				array[index] = "0";
			} else if (arrayInt[index].equals(this.actualUser.getIdUser())) {
				array[index] = "1";
			} else {
				try {
					array[index] = this.m.getRequiredUser(arrayInt[index]).getUsername();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.v.showError("Errore! Utente non presente nell'archivio");
				}
			}
		}
		this.v.setStudyRoomStatus(array);
		// array di stringhe per i posto a sedere dove il posto vuoto è 0 e il
		// posto occupato è username

		// ritorna array di stringhe che mostra data e posto occupati
	}

	@Override
	public void takeSit() {
		System.out.println("takeSit: entrato");
		GregorianCalendar day = new GregorianCalendar();
		day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
				this.v.getStudyRoomSelectedDay());
		System.out.println("takeSit: " + this.v.getTakenSits());
		try {
			this.m.bookSit(day, this.v.getTakenSits(), this.actualUser.getIdUser());
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMESTUDYROOM, this.m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Posto o Id utente non validi");
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
		GregorianCalendar day = new GregorianCalendar();

		try {
			day.set(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
					this.v.getStudyRoomSelectedDay());
			this.m.cancelSit(day, this.v.getSelectedSit(), this.actualUser.getIdUser());
			this.fm.writeObjectIntoFile(ControllerImpl.FILENAMESTUDYROOM, this.m);
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
		System.out.println("setWishList: entrato");
		String[] array = new String[this.actualUser.getWishlist().size()];
		System.out.println("setWishList: getWishList.size=" + this.actualUser.getWishlist().size());
		int index = 0;
		for (Integer i : this.actualUser.getWishlist()) {
			array[index] = this.m.getItemArchive().get(i).toString();
			System.out.println("setWishList: aggiunta elemento " + i);
			index++;
		}
		this.v.setWishlist(array);
		System.out.println("setWishList: array restituito");
	}

	@Override
	public void removeFromWishList() {
		System.out.println("removeFromWishlist: entrato");
		for (Integer i : this.m.getItemArchive().keySet()) {
			System.out.println("removeFromWishList: entrato nel for");
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemToRemoveFromLikeBorrowWish())) {
				try {
					this.m.removeLike(i, this.actualUser.getIdUser());
					System.out.println("removeFromWishlist: entrato dopo op");
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
		System.out.println("setAllUserList: entrato, restituisco array");
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
		System.out.println("setAllItemList: entrato, restituisco array");
		this.v.setItemList(array);
	}

	@Override
	public void deleteItem() {
		int itemIdReceived = 0;
		System.out.println("deleteItem: entrato");
		System.out.println("deleteItem: " + this.v.getItemToRemoveModify() + " restituito");
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemToRemoveModify())) {
				System.out.println("deleteItem: entrato nell'if e init la var");
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
	public void extendBorrow() {
		// fai comparire solo uan finestra che dice che è stato esteso

		// se block user è true, manda la schermata solo per restituire
		this.v.showMessage("Prestito esteso per l'oggetto");

	}

	@Override
	public void allItemReviews() {
		int index = 0;
		int id = 0;
		System.out.println("AllItemReviews: entrato");
		System.out.println("AllItemReviews: " + this.v.getItemSelectedByUser());
		for (Integer i : this.m.getItemArchive().keySet()) {
			if (this.m.getItemArchive().get(i).toString().equals(this.v.getItemSelectedByUser())) {
				id = i;
				System.out.println("AllItemReviews: id registrato");
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
			System.out.println("AllItemReviews: try passato");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.v.showError("Errore! Oggetto con recensione non trovato nell'archivio");
		}
	}

	@Override
	public void giveOtherUserInfo() {

		this.v.setUserModifyField(name, surname, username, password, birthDate_day, birthDate_month, birthDate_year,
				email, telephone, bPref1, bPref2, bPref3, fPref1, fPref2, fPref3);
	}

	@Override
	public void setView(final view.View v) {
		this.v = v;
		// this.writeOnFile();
	}
}
