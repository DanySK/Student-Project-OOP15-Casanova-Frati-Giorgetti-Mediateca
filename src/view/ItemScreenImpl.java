package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	private JComboBox<?> genreF;

	private JComboBox<?> languageF;
	private final JTextField yearF;
	// private final JFileChooser imageChoose = new JFileChooser();

	private JComboBox<?> itemTypeF;
	private final JTextField durationF;

	private JComboBox<?> colorF;
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
	 *            the calling view name
	 * @param type
	 *            the type of screen (create or modify)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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

		this.setLayout(null);
		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);

		this.titleF = new JTextField();
		this.titleF.setBounds(338, 104, 230, 30);
		this.add(this.titleF);
		this.titleF.setColumns(10);

		this.authorF = new JTextField();
		this.authorF.setBounds(338, 144, 230, 30);
		this.authorF.setColumns(10);
		this.add(this.authorF);

		this.manifacturerF = new JTextField();
		this.manifacturerF.setBounds(338, 184, 230, 30);
		this.manifacturerF.setColumns(10);
		this.add(this.manifacturerF);

		presentation = new JLabel();
		send = new JButton();
		send.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));

		this.yearF = new JTextField();
		this.yearF.setColumns(10);
		this.yearF.setBounds(338, 224, 230, 30);
		this.add(this.yearF);

		this.durationF = new JTextField();
		this.durationF.setColumns(10);
		this.durationF.setBounds(338, 344, 230, 30);
		this.add(this.durationF);

		this.colorF = new JComboBox(utils.TypeColor.values());
		this.colorF.setBounds(338, 424, 230, 30);
		this.add(this.colorF);

		this.languageF = new JComboBox(utils.Language.values());
		this.languageF.setBounds(338, 384, 230, 30);
		this.add(this.languageF);

		this.isbnF = new JTextField();
		this.isbnF.setColumns(10);
		this.isbnF.setBounds(338, 304, 230, 30);
		this.add(this.isbnF);

		this.itemTypeF = new JComboBox(utils.TypeItem.values());
		this.itemTypeF.setToolTipText("Tipo");
		this.itemTypeF.setBounds(338, 64, 230, 30);
		this.add(this.itemTypeF);

		this.genreF = new JComboBox(utils.ItemGenre.values());
		this.genreF.setToolTipText("Genere");
		this.genreF.setBounds(338, 264, 230, 30);
		this.add(this.genreF);

		titleL = new JLabel("Titolo:");
		titleL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		titleL.setBounds(159, 104, 167, 30);
		this.add(titleL);

		durationL = new JLabel("Durata:");
		durationL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		durationL.setBounds(159, 304, 167, 30);
		this.add(durationL);

		isbnL = new JLabel("ISBN:");
		isbnL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		isbnL.setBounds(159, 344, 167, 30);
		this.add(isbnL);

		authorL = new JLabel("Autore:");
		authorL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		authorL.setBounds(159, 144, 167, 30);
		this.add(authorL);
		this.numCopiesF = new JTextField();
		this.numCopiesF.setSize(78, 30);
		this.numCopiesF.setLocation(338, 464);
		this.numReleaseF = new JTextField();
		this.numReleaseF.setLocation(338, 504);
		this.numReleaseF.setSize(78, 30);
		this.add(this.numCopiesF);
		this.add(this.numReleaseF);
		discarge = new JButton("Annulla");
		discarge.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		if (type.equals(ItemScreenType.CREATE)) {
			presentation = new JLabel("Inserisci il nuovo oggetto");
			send = new JButton("Crea");
			discarge.addActionListener(e -> v.swapView(CardName.MANAGER_MENU));
			send.addActionListener(e -> {
				v.sendItemCreate();
				v.giveMeItemList();
				v.swapView(CardName.MANAGER_MENU);
			});
		} else if (type.equals(ItemScreenType.MODIFY)) {
			presentation = new JLabel("Modifica qui il tuo oggetto:");
			send = new JButton("Invio");
			send.addActionListener(e -> {
				v.sendItemModify();
				v.swapView(CardName.MANAGER_MENU);
			});
			this.itemTypeF.setEnabled(false);
			this.durationF.setEditable(false);
			this.colorF.setEnabled(false);
			this.isbnF.setEnabled(false);
			this.numCopiesF.setEnabled(false);
			this.numReleaseF.setEnabled(false);
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
		presentation
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(presentation);

		discarge.setBounds(474, 504, 143, 53);
		discarge.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(discarge);

		send.setBounds(629, 504, 143, 53);
		send.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(send);

		manifacturerL = new JLabel("Produttore:");
		manifacturerL.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		manifacturerL.setBounds(159, 184, 167, 30);
		this.add(manifacturerL);

		yearL = new JLabel("Anno:");
		yearL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		yearL.setBounds(159, 224, 167, 30);
		this.add(yearL);

		JLabel numCopiesL = new JLabel("Copie:");
		numCopiesL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		numCopiesL.setBounds(159, 464, 167, 30);
		this.add(numCopiesL);

		JLabel numReleaseL = new JLabel("Release:");
		numReleaseL
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		numReleaseL.setBounds(159, 504, 167, 30);
		this.add(numReleaseL);

	}

	/*
	 * @Override public ImageIcon resizeImage(final String imagePath) {
	 * ImageIcon myImage = new ImageIcon(imagePath); Image img =
	 * myImage.getImage(); Image newImg =
	 * img.getScaledInstance(this.imageSpace.getWidth(),
	 * this.imageSpace.getHeight(), Image.SCALE_SMOOTH); ImageIcon image = new
	 * ImageIcon(newImg); return image; }
	 */
	private void setCommonField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final utils.Language language,
			final int copies, final int release) {
		this.titleF.setText(title);
		this.authorF.setText(author);
		this.manifacturerF.setText(manifacturer);
		this.yearF.setText(year);
		this.genreF.setSelectedItem(genre);
		this.languageF.setSelectedItem(language);
		this.numCopiesF.setText(Integer.toString(copies));
		this.numReleaseF.setText(Integer.toString(release));
	}

	@Override
	public void setFilmField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final String duration,
			final utils.TypeColor color, final utils.Language language,
			final int copies, final int release) {
		this.setCommonField(title, author, manifacturer, year, genre, language,
				copies, release);
		this.durationF.setText(duration);
		this.colorF.setSelectedItem(color);
		this.isbnF.setText(null);
	}

	@Override
	public void setBookField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final String isbn,
			final utils.Language language, final int copies, final int release) {
		this.setCommonField(title, author, manifacturer, year, genre, language,
				copies, release);
		this.durationF.setText(null);
		this.colorF.setSelectedItem(null);
		this.isbnF.setText(isbn);
	}

	@Override
	public String getItemInfo(final utils.TypeItemInfo info) {
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
			return this.genreF.getSelectedItem().toString();
		case TYPE:
			return this.itemTypeF.getSelectedItem().toString();
		case DURATION:
			return this.durationF.getText();
		case COLOR:
			return this.colorF.getSelectedItem().toString();
		case ISBN:
			return this.isbnF.getText();
		case LANGUAGE:
			return this.languageF.getSelectedItem().toString();
		default:
			break;

		}

		return null;
	}

	@Override
	public String getItemInfo(final ViewImpl.OtherItemFilter info2) {
		switch (info2) {
		case RELEASE_NUMBER:
			return String.valueOf(this.numReleaseF);
		case COPIES_NUMBER:
			return String.valueOf(this.numCopiesF);
		default:
			break;
		}
		return null;
	}
	/*
	 * @Override public ImageIcon resizeImage(String imagePath);
	 */
}