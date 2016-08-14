package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.UserScreenImpl.UserScreenType;
import view.ViewImpl.CardName;

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
	private JFileChooser imageChooseF;
	private JComboBox itemTypeF;
	private JLabel titleL;
	private JLabel authorL;
	private JLabel manifacturerL;
	private JLabel yearL;
	private JLabel imagePathL;
	private JLabel presentation;
	private JButton discarge;
	private JButton send;
	private ImageIcon imageF;

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

		this.yearF = new JTextField();
		this.yearF.setColumns(10);
		this.yearF.setBounds(208, 157, 116, 22);
		this.add(this.yearF);

		this.imageChooseF = new JFileChooser();
		this.imageChooseF.setBounds(322, 34, 116, 145);
		this.add(this.imageChooseF);
		/*
		 * Image image = this.imageChooseF.getSelectedFile();
		 * this.imageF.loadImage(image); this.add(this.imageF);
		 */
		this.itemTypeF = new JComboBox();
		this.itemTypeF.setBounds(208, 34, 116, 22);
		this.add(this.itemTypeF);

		this.genreF = new JComboBox();
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
			// this.send.addActionListener(e -> v.sendItemCreate());
		} else if (type.equals(UserScreenType.MODIFY)) {
			this.presentation = new JLabel("Modifica qui il tuo oggetto:");
			// v.giveMeItemInfo();
			this.send = new JButton("Invio");
			// this.send.addActionListener(e -> v.sendItemModify());
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
	/*
	 * @Override public void setField(final String name, final String surname,
	 * final String username, final String password, final String birthDate,
	 * final String email, final String telephone) { this.nameF.setText(name);
	 * this.surnameF.setText(surname); this.usernameF.setText(username);
	 * this.passwordF.setText(password); this.birthF.setText(birthDate);
	 * this.emailF.setText(email); this.cellF.setText(telephone); }
	 * 
	 * @Override public String getInfo(final UserInfo info) { for (UserInfo i :
	 * UserInfo.values()) { switch (info) { case name: return
	 * this.nameF.getText(); case surname: return this.surnameF.getText(); case
	 * username: return this.usernameF.getText(); case password: return
	 * this.passwordF.getText(); case birthDate: return this.birthF.getText();
	 * case email: return this.emailF.getText(); case telephone: return
	 * this.cellF.getText(); default: break;
	 * 
	 * } } return null; }
	 */
}