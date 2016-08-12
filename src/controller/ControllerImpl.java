package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
   *          Model to be initialized
   * @throws Exception
   */
  public ControllerImpl() throws Exception {
    Integer check = 1;
    if (check == 1) {

      Map<Integer, UserImpl> userArchive = this.fm
                  .readArchiveUserFromFile(ControllerImpl.FILENAMEUSER);
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
        this.m.registerUser("Enrico", "Casanova", calendar, "Dakaiden", "Arctica64",
                    "enrico.casanova@dadas.it", "334534534534", new ArrayList<ItemGenre>(),
                    new ArrayList<ItemGenre>());
        this.m.registerBook("Il signore degli anelli", 1945, "J.R.R. Tolkien", Language.ENGLISH,
                    "23123121", ItemGenre.ADVENTURE_HISTORY, "Ges�", 0011, 100000);
        this.m.registerMovie("Star Trek", 2009, "Bad Robot", "J.J. Abrams", Language.ENGLISH,
                    ItemGenre.FANTASY, 120, true, 1000000);

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
    // this.v.setFilteredList(set);
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
