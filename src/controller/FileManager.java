package controller;

import java.io.FileInputStream;
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

/**
 * Class which does the operations of I/O.
 *
 * @author
 *
 */
public class FileManager {

	private String path = System.getProperty("user.dir");

	/**
	 * Empty constructor
	 *
	 * @author
	 *
	 */
	public FileManager() {
	}

	/**
	 * Method that writes an object into a file
	 *
	 *
	 * @param fileName
	 * @param object
	 * @throws IOException
	 */
	public void writeObjectIntoFile(final String fileName, final Model model) throws IOException {
		/*
		 * File myFile = new File(fileName); if (!myFile.exists()) {
		 * myFile.createNewFile(); }
		 */
		FileOutputStream fos = new FileOutputStream(this.path + "\\res\\" + fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		if (fileName.contains("utenti")) {
			oos.writeObject(model.getUserArchive());
		} else if (fileName.contains("oggetti")) {
			oos.writeObject(model.getItemArchive());
		} else if (fileName.contains("aulastudio")) {
			oos.writeObject(model.getStudyRoom());
		}
		oos.close();
	}

	public Map<Integer, Pair<ItemImpl, ItemInfo>> readArchiveItemFromFile(final String fileNameItem) {
		Map<Integer, Pair<ItemImpl, ItemInfo>> objectItem = null;
		try (FileInputStream fisItem = new FileInputStream(this.path + "\\res\\" + fileNameItem)) {
			ObjectInputStream oisItem = new ObjectInputStream(fisItem);
			objectItem = (Map<Integer, Pair<ItemImpl, ItemInfo>>) oisItem.readObject();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return objectItem;
	}

	public Map<Integer, UserImpl> readArchiveUserFromFile(final String fileNameUser) {
		Map<Integer, UserImpl> objectUser = null;
		try (FileInputStream fisUser = new FileInputStream(this.path + "\\res\\" + fileNameUser)) {
			ObjectInputStream oisUser = new ObjectInputStream(fisUser);
			objectUser = (Map<Integer, UserImpl>) oisUser.readObject();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return objectUser;
	}

	public Map<GregorianCalendar, ArrayList<Integer>> readStudyRoomFromFile(final String fileNameStudyRoom) {
		Map<GregorianCalendar, ArrayList<Integer>> objectStudyRoom = null;
		try (FileInputStream fisStudyRoom = new FileInputStream(this.path + "\\res\\" + fileNameStudyRoom)) {
			ObjectInputStream oisStudyRoom = new ObjectInputStream(fisStudyRoom);
			objectStudyRoom = (Map<GregorianCalendar, ArrayList<Integer>>) oisStudyRoom.readObject();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		return objectStudyRoom;
	}
}
