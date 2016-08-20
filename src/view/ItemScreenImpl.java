package view;

import java.awt.Font;
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

import utils.TypeItem;
import view.ViewImpl.CardName;

/**
 * Class which implements the UserModify interface.
 *
 * @author Luca Giorgetti
 *
 */
public class ItemScreenImpl extends JPanel implements ItemScreen {

	private static final long serialVersionUID = 1L;

	private final JTextField titleF;
	private final JTextField authorF;
	private final JTextField manifacturerF;
	private JComboBox<?> genreF = new JComboBox(utils.ItemGenre.values());
	private JComboBox<?> languageF = new JComboBox(utils.Language.values());
	private final JTextField yearF;
	private String imagePath;
	private final JFileChooser imageChoose = new JFileChooser();
	private JComboBox<?> itemTypeF = new JComboBox(utils.TypeItem.values());
	private final JLabel imageSpace;
	private final JTextField durationF;
	private JComboBox<?> colorF = new JComboBox(utils.TypeColor.values());
	private final JTextField isbnF;
	private final JTextField numCopiesF;
	private final JTextField numReleaseF;

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
	 * Create the panel.
	 *
	 * @param v
	 * @param screenWidth
	 * @param screenLenght
	 */
	public ItemScreenImpl(final View v, final ItemScreenType type) {
		final JLabel titleL;
		final JLabel authorL;
		final JLabel manifacturerL;
		final JLabel yearL;
		final JLabel durationL;
		final JLabel isbnL;
		JLabel presentation;
		final JButton discarge;
		JButton send;
		final JButton browse;

		this.setLayout(null);
		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);

		this.titleF = new JTextField();
		this.titleF.setBounds(229, 107, 230, 30);
		this.add(this.titleF);
		this.titleF.setColumns(10);

		this.authorF = new JTextField();
		this.authorF.setBounds(229, 150, 230, 30);
		this.authorF.setColumns(10);
		this.add(this.authorF);

		this.manifacturerF = new JTextField();
		this.manifacturerF.setBounds(229, 193, 232, 30);
		this.manifacturerF.setColumns(10);
		this.add(this.manifacturerF);

		browse = new JButton("Scegli Immagine");
		browse.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		browse.setBounds(493, 405, 272, 39);
		this.add(browse);

		presentation = new JLabel();
		send = new JButton();
		send.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));

		this.yearF = new JTextField();
		this.yearF.setColumns(10);
		this.yearF.setBounds(229, 233, 230, 30);
		this.add(this.yearF);
		this.imageSpace = new JLabel();

		this.durationF = new JTextField();
		this.durationF.setColumns(10);
		this.durationF.setBounds(229, 341, 230, 30);
		this.add(this.durationF);

		this.colorF = new JComboBox();
		this.colorF.setBounds(229, 414, 230, 30);
		this.add(this.colorF);

		this.languageF = new JComboBox();
		this.languageF.setBounds(229, 378, 230, 30);
		this.add(this.languageF);

		this.isbnF = new JTextField();
		this.isbnF.setColumns(10);
		this.isbnF.setBounds(229, 306, 230, 30);
		this.add(this.isbnF);

		this.imageSpace.setBounds(510, 79, 100, 140);
		this.add(this.imageSpace);
		// http://1bestcsharp.blogspot.it/2015/04/java-how-to-browse-image-file-and-And-Display-It-Using-JFileChooser-In-Java.html
		browse.addActionListener(e -> {
			this.imageChoose.setCurrentDirectory(new File(System
					.getProperty("user.home")));
			final FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"*.images", "jpg", "gif", "png");
			this.imageChoose.addChoosableFileFilter(filter);
			final int result = this.imageChoose.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				final File selectedImage = this.imageChoose.getSelectedFile();
				this.imagePath = selectedImage.getAbsolutePath();
				this.imageSpace.setIcon(this.resizeImage(this.imagePath));
			} else if (result == JFileChooser.CANCEL_OPTION) {
				this.imageSpace.setIcon(null);
			}
		});

		this.itemTypeF = new JComboBox();
		this.itemTypeF.setToolTipText("Tipo");
		this.itemTypeF.setBounds(229, 64, 230, 30);
		this.add(this.itemTypeF);

		this.genreF = new JComboBox();
		this.genreF.setToolTipText("Genere");
		this.genreF.setBounds(229, 271, 230, 30);
		this.add(this.genreF);

		titleL = new JLabel("Titolo:");
		titleL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		titleL.setBounds(50, 107, 167, 30);
		this.add(titleL);

		durationL = new JLabel("Durata:");
		durationL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		durationL.setBounds(50, 306, 167, 30);
		this.add(durationL);

		isbnL = new JLabel("ISBN:");
		isbnL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		isbnL.setBounds(50, 341, 167, 30);
		this.add(isbnL);

		authorL = new JLabel("Autore:");
		authorL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		authorL.setBounds(50, 150, 167, 30);
		this.add(authorL);
		this.numCopiesF = new JTextField();
		this.numCopiesF.setSize(78, 30);
		this.numCopiesF.setLocation(229, 450);
		this.numReleaseF = new JTextField();
		this.numReleaseF.setLocation(229, 485);
		this.numReleaseF.setSize(78, 30);
		this.add(this.numCopiesF);
		this.add(this.numReleaseF);
		discarge = new JButton("Annulla");
		discarge.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		if (type.equals(ItemScreenType.CREATE)) {
			presentation = new JLabel("Inserisci il nuovo oggetto");
			send = new JButton("Crea");
			discarge.addActionListener(e -> v.swapView(CardName.MANAGER_MENU));
			send.addActionListener(e -> v.sendItemCreate());
		} else if (type.equals(ItemScreenType.MODIFY)) {
			presentation = new JLabel("Modifica qui il tuo oggetto:");
			v.giveMeItemInfo();
			send = new JButton("Invio");
			send.addActionListener(e -> v.sendItemModify());
		}
		this.itemTypeF
				.addActionListener(e -> {
					if (this.itemTypeF.getSelectedItem().equals(TypeItem.BOOK)) {
						this.colorF.setEnabled(false);
						this.colorF.setVisible(false);
						this.durationF.setEnabled(false);
						this.durationF.setVisible(false);
						durationL.setVisible(false);
						this.isbnF.setEnabled(true);
						this.isbnF.setVisible(true);
						isbnL.setVisible(true);

					} else if (this.itemTypeF.getSelectedItem().equals(
							TypeItem.MOVIE)) {
						this.colorF.setEnabled(true);
						this.colorF.setVisible(true);
						this.durationF.setEnabled(true);
						this.durationF.setVisible(true);
						durationL.setVisible(true);
						this.isbnF.setEnabled(false);
						this.isbnF.setVisible(false);
						isbnL.setVisible(false);
					}
				});
		presentation.setBounds(50, 13, 692, 38);
		this.add(presentation);

		discarge.setBounds(28, 531, 125, 30);
		this.add(discarge);

		send.setBounds(582, 508, 178, 53);
		this.add(send);

		manifacturerL = new JLabel("Produttore:");
		manifacturerL.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		manifacturerL.setBounds(50, 193, 167, 30);
		this.add(manifacturerL);

		yearL = new JLabel("Anno:");
		yearL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		yearL.setBounds(50, 233, 167, 30);
		this.add(yearL);

		JLabel numCopiesL = new JLabel("Copie:");
		numCopiesL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		numCopiesL.setBounds(50, 450, 167, 30);
		this.add(numCopiesL);

		JLabel numReleaseL = new JLabel("Release:");
		numReleaseL
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		numReleaseL.setBounds(50, 485, 167, 30);
		this.add(numReleaseL);

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
	public void setCommonField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final String imagePath,
			final utils.Language language) {
		this.titleF.setText(title);
		this.authorF.setText(author);
		this.manifacturerF.setText(manifacturer);
		this.yearF.setText(year);
		this.genreF.setSelectedItem(genre);
		this.imagePath = imagePath;
		this.languageF.setSelectedItem(language);
	}

	@Override
	public void setFilmField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final String imagePath,
			final String duration, final utils.TypeColor color,
			final utils.Language language) {
		this.setCommonField(title, author, manifacturer, year, genre,
				imagePath, language);
		this.durationF.setText(duration);
		this.colorF.setSelectedItem(color);
		this.isbnF.setText(null);
	}

	@Override
	public void setBookField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final String imagePath,
			final String isbn, final utils.Language language) {
		this.setCommonField(title, author, manifacturer, year, genre,
				imagePath, language);
		this.durationF.setText(null);
		this.colorF.setSelectedItem(null);
		this.isbnF.setText(isbn);
	}

	@Override
	public Object getItemInfo(final utils.TypeItemInfo info) {
		switch (info) {
		case TITLE:
			return this.titleF.getText();
		case AUTHOR:
			return this.authorF.getText();
		case PRODUCER:
			return this.manifacturerF.getText();
		case RELEASE_YEAR:
			return this.yearF.getText();
		case GENRE:
			return this.genreF.getSelectedItem();
		case TYPE:
			return this.itemTypeF.getSelectedObjects();
		case IMAGE:
			return this.imagePath;
		case DURATION:
			return this.durationF.getText();
		case COLOR:
			return this.colorF.getSelectedItem();
		case ISBN:
			return this.isbnF.getText();
		case LANGUAGE:
			return this.languageF.getSelectedItem();
		case RELEASE_NUMBER:
			return this.languageF.getSelectedItem();
		case COPIES_NUMBER:
			return this.languageF.getSelectedItem();
		default:
			break;

		}
		return null;
	}
}