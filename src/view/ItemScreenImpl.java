package view;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.UserScreenImpl.UserScreenType;
import view.ViewImpl.CardName;
import view.ViewImpl.UserInfo;

/**
 * Class which implements the UserModify interface.
 *
 * @author Luca Giorgetti
 *
 */
public class ItemScreenImpl extends JPanel implements ItemScreen {

	private static final long serialVersionUID = 1L;

	private JTextField titleF;
	private JTextField authorF;
	private JTextField manifacturerF;
	private JComboBox genreF;
	private JTextField yearF;
	private JFileChooser imageChoose;
	private JComboBox itemTypeF;
	private JLabel titleL;
	private JLabel authorL;
	private JLabel manifacturerL;
	private JLabel yearL;
	private JLabel imageSpace;
	private JLabel presentation;
	private JButton discarge;
	private JButton send;
	private JButton browse;
	private String imagePath;
	public int imageLenght = 140;
	public int imageWidth = 100;

	/**
	 * enum for type of Item screen to show.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum ItemScreenType {
		/**
		 *
		 */
		CREATE, MODIFY
	}

	/**
	 * enum for Item Information.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum ItemInfo {
		title, author, manifacturer, year, genre, type, image
	}

	/**
	 * Create the panel.
	 *
	 * @param v
	 * @param screenWidth
	 * @param screenLenght
	 */
	public ItemScreenImpl(final View v, final ItemScreenType type,
			final int screenLenght, final int screenWidth) {

		this.setLayout(null);

		this.titleF = new JTextField();
		this.titleF.setBounds(208, 69, 116, 22);
		this.add(this.titleF);
		this.titleF.setColumns(10);

		this.authorF = new JTextField();
		this.authorF.setBounds(208, 98, 116, 22);
		this.authorF.setColumns(10);
		this.add(this.authorF);

		this.manifacturerF = new JTextField();
		this.manifacturerF.setBounds(208, 130, 116, 22);
		this.manifacturerF.setColumns(10);
		this.add(this.manifacturerF);

		this.browse = new JButton("Scegli Immagine");
		this.browse.setBounds(336, 154, 100, 53);
		this.add(this.browse);

		this.presentation = new JLabel();
		this.send = new JButton();

		this.yearF = new JTextField();
		this.yearF.setColumns(10);
		this.yearF.setBounds(208, 157, 116, 22);
		this.add(this.yearF);
		this.imageSpace = new JLabel();

		this.imageSpace.setBounds(336, 11, 100, 140);
		this.add(this.imageSpace);
		// http://1bestcsharp.blogspot.it/2015/04/java-how-to-browse-image-file-and-And-Display-It-Using-JFileChooser-In-Java.html
		this.browse.addActionListener(e -> {
			this.imageChoose.setCurrentDirectory(new File(System
					.getProperty("user.home")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"*.images", "jpg", "gif", "png");
			this.imageChoose.addChoosableFileFilter(filter);
			int result = this.imageChoose.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedImage = this.imageChoose.getSelectedFile();
				this.imagePath = selectedImage.getAbsolutePath();
				this.imageSpace.setIcon(this.resizeImage(this.imagePath));
			} else if (result == JFileChooser.CANCEL_OPTION) {
				this.imageSpace.setIcon(null);
			}
		});

		this.itemTypeF = new JComboBox();
		this.itemTypeF.setToolTipText("Tipo");
		this.itemTypeF.setBounds(208, 34, 116, 22);
		this.add(this.itemTypeF);

		this.genreF = new JComboBox();
		this.genreF.setToolTipText("Genere");
		this.genreF.setBounds(208, 217, 116, 22);
		this.add(this.genreF);

		this.titleL = new JLabel("Titolo:");
		this.titleL.setBounds(91, 75, 94, 16);
		this.add(this.titleL);

		this.authorL = new JLabel("Autore:");
		this.authorL.setBounds(91, 101, 94, 16);
		this.add(this.authorL);

		this.discarge = new JButton("Annulla");
		if (type.equals(UserScreenType.CREATE)) {
			this.presentation = new JLabel("Inserisci il nuovo oggetto");
			this.send = new JButton("Crea");
			this.discarge.addActionListener(e -> v
					.swapView(CardName.MANAGER_MENU));
			this.send.addActionListener(e -> v.sendItemCreate());
		} else if (type.equals(UserScreenType.MODIFY)) {
			this.presentation = new JLabel("Modifica qui il tuo oggetto:");
			v.giveMeItemInfo();
			this.send = new JButton("Invio");
			this.send.addActionListener(e -> v.sendItemModify());
		}
		this.presentation.setBounds(104, 11, 181, 16);
		this.add(this.presentation);

		this.discarge.setBounds(12, 262, 97, 25);
		this.add(this.discarge);

		this.send.setBounds(341, 262, 97, 25);
		this.add(this.send);

		this.manifacturerL = new JLabel("Produttore:");
		this.manifacturerL.setBounds(91, 133, 94, 16);
		this.add(this.manifacturerL);

		this.yearL = new JLabel("Anno:");
		this.yearL.setBounds(91, 160, 94, 16);
		this.add(this.yearL);

	}

	@Override
	public ImageIcon resizeImage(final String imagePath) {
		ImageIcon myImage = new ImageIcon(imagePath);
		Image img = myImage.getImage();
		Image newImg = img.getScaledInstance(this.imageSpace.getWidth(),
				this.imageSpace.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}

	@Override
	public void setItemField(final String title, final String author,
			final String manifacturer, final String year, final String genre,
			final String type, final String imagePath) {
		this.titleF.setText(title);
		this.authorF.setText(author);
		this.manifacturerF.setText(manifacturer);
		this.yearF.setText(year);
		this.genreF.setSelectedItem(genre);
		this.itemTypeF.setSelectedItem(type);
		this.imageSpace.setIcon(this.resizeImage(imagePath));
	}

	@Override
	public Object getItemInfo(final ItemInfo info) {
		for (UserInfo i : UserInfo.values()) {
			switch (info) {
			case title:
				return this.titleF.getText();
			case author:
				return this.authorF.getText();
			case manifacturer:
				return this.manifacturerF.getText();
			case year:
				return this.yearF.getText();
			case genre:
				return this.genreF.getSelectedItem();
			case type:
				return this.itemTypeF.getSelectedObjects();
			case image:
				return this.imagePath;
			default:
				break;

			}
		}
		return null;
	}
}