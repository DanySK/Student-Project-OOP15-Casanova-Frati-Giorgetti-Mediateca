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
import model.Pair;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.user.User;

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
		oos.writeObject(model);
		oos.close();
	}

	@SuppressWarnings("unchecked")
	public void read(final String fileName, final Model model) throws IOException, FileNotFoundException {
		try {
			FileInputStream fin = new FileInputStream(this.path + "\\res\\" + fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object object = ois.readObject();
			if (fileName.contains("utenti")) {
				model.setUserArchive((Map<Integer, User>) object);
			} else {
				model.setItemArchive((Map<Integer, Pair<ItemImpl, ItemInfo>>) object);
			}
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
