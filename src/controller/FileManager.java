package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import model.Model;
import model.ModelImpl;
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
	public void write(final String fileName, final Model model) throws IOException {
		File myFile = new File(fileName);
		if (!myFile.exists()) {
			myFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(this.path + "\\res\\" + fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		if (fileName.contains("utenti")) {
			oos.writeObject(model.getUserArchive());
		} else {
			oos.writeObject(model.getItemArchive());
		}
		oos.close();
	}

	public void read(final String fileNameUser, final String fileNameItem, Model model)
			throws IOException, FileNotFoundException {
		try {
			FileInputStream fisUser = new FileInputStream(this.path + "\\res\\" + fileNameUser);
			ObjectInputStream oisUser = new ObjectInputStream(fisUser);
			Object objectUser = oisUser.readObject();

			FileInputStream fisItem = new FileInputStream(this.path + "\\res\\" + fileNameItem);
			ObjectInputStream oisItem = new ObjectInputStream(fisItem);
			Object objectItem = oisItem.readObject();

			model = new ModelImpl((Map<Integer, Pair<ItemImpl, ItemInfo>>) objectItem,
					(Map<Integer, UserImpl>) objectUser);

			oisUser.close();
			oisItem.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
