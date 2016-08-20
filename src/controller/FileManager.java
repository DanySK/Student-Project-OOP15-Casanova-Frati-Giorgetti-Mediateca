package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import model.Model;
import model.Pair;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.user.UserImpl;
import view.View;
import view.ViewImpl;

/**
 * Class which does the operations of I/O.
 *
 * @author
 *
 */
public class FileManager {

	private View v = new ViewImpl();
	private String path = System.getProperty("user.dir") + "\\res\\";

	/**
	 * Empty constructor.
	 *
	 * @author
	 *
	 */
	public FileManager() {
	}

	/**
	 * Method that writes an object into a file.
	 *
	 * @param fileName
	 *            name of the file to write.
	 * @param model
	 *            implementation of the model.
	 * @throws IOException
	 *             when the file at the target path is missing
	 */
	public void writeObjectIntoFile(final String fileName, final Model model) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.path + fileName))) {
			if (fileName.contains("utenti")) {
				oos.writeObject(model.getUserArchive());
			} else if (fileName.contains("oggetti")) {
				oos.writeObject(model.getItemArchive());
			} else if (fileName.contains("aulastudio")) {
				oos.writeObject(model.getStudyRoom());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			this.v.showError("File " + fileName + " non trovato per la scrittura");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.v.showError("Errore I/O");
		}
	}

	/**
	 * Method that reads the content of the ArchiveItem's file.
	 *
	 * @param fileNameItem
	 *            name of the file to read.
	 * @return returns the content of the file casted into the right type
	 */
	public Map<Integer, Pair<ItemImpl, ItemInfo>> readArchiveItemFromFile(final String fileNameItem) {
		Map<Integer, Pair<ItemImpl, ItemInfo>> objectItem = null;
		try (ObjectInputStream oisItem = new ObjectInputStream(new FileInputStream(this.path + fileNameItem))) {
			objectItem = (Map<Integer, Pair<ItemImpl, ItemInfo>>) oisItem.readObject();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			this.v.showError("File .oggetti non trovato");
		}
		return objectItem;
	}

	/**
	 * Method that reads the content of the ArchiveUser's file.
	 *
	 * @param fileNameUser
	 *            name of the file to read.
	 * @return returns the content of the file casted into the right type
	 */
	public Map<Integer, UserImpl> readArchiveUserFromFile(final String fileNameUser) {
		Map<Integer, UserImpl> objectUser = null;
		try (ObjectInputStream oisUser = new ObjectInputStream(new FileInputStream(this.path + fileNameUser))) {
			objectUser = (Map<Integer, UserImpl>) oisUser.readObject();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError("File .utenti non trovato");
		}
		return objectUser;
	}

	/**
	 * Method that reads the content of the StudyRoom's file.
	 *
	 * @param fileNameStudyRoom
	 *            name of the file to read.
	 * @return returns the content of the file casted into the right type
	 */
	public Map<GregorianCalendar, ArrayList<Integer>> readStudyRoomFromFile(final String fileNameStudyRoom) {
		Map<GregorianCalendar, ArrayList<Integer>> objectStudyRoom = null;
		try (ObjectInputStream oisStudyRoom = new ObjectInputStream(
				new FileInputStream(this.path + fileNameStudyRoom))) {
			objectStudyRoom = (Map<GregorianCalendar, ArrayList<Integer>>) oisStudyRoom.readObject();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			this.v.showError("File .aulastudio non trovato");
		}
		return objectStudyRoom;
	}

	/**
	 * Getter for the path of the folder which contains the archive files.
	 *
	 * @return returns the path of the folder which contains the archive files
	 */
	public String getPath() {
		return this.path;
	}
}
