package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.item.Item;

/**
 * Class which does the operations of I/O.
 *
 * @author
 *
 */
public class FileManager {

	private String fileName;
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
	public void write(final String fileName, final Object object) throws IOException {

		File myFile = new File(fileName);
		if (!myFile.exists()) {
			myFile.createNewFile();
		}

		FileOutputStream fos = new FileOutputStream(this.path + "\\res\\" + fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(object);
		oos.close();
	}

	public void read(final String fileName, final Item item) throws IOException, FileNotFoundException {
		try {
			FileInputStream fin = new FileInputStream(this.path + "\\res\\" + fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object object = ois.readObject();
			ois.close();
			// return object;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
