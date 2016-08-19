package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import view.ViewImpl.CardName;

/**
 * Class which implements the main screen for manager.
 *
 * @author Luca Giorgetti
 *
 */
public class ManagerScreenImpl extends JPanel implements ManagerScreen {

	private static final long serialVersionUID = 3947236683472052024L;
	private JList<String> list = new JList<String>();
	private TypeList type = TypeList.USER;

	/**
	 * Enum for the type of list can be.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum TypeList {
		ITEM, USER;
	}

	/**
	 * Builder for ManagerScreenImpl.
	 */
	public ManagerScreenImpl(final View v) {

		this.setSize(1280, 920);
		this.setLayout(null);

		JButton newItem = new JButton("Crea Nuovo Oggetto");
		newItem.setFont(new Font("Tahoma", Font.PLAIN, 30));
		newItem.addActionListener(arg0 -> {
		});
		newItem.setBounds(822, 92, 376, 45);
		this.add(newItem);

		JButton showUserList = new JButton("Lista Utenti");
		showUserList.setBounds(822, 262, 376, 45);
		showUserList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(showUserList);
		showUserList.addActionListener(e -> {
			v.giveMeUserList();
			this.type = TypeList.USER;
		});

		JButton showItemList = new JButton("Lista Oggetti");
		showItemList.setBounds(822, 320, 376, 45);
		showItemList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(showItemList);
		showItemList.addActionListener(e -> {
			v.giveMeItemList();
			this.type = TypeList.ITEM;
		});

		JButton delete = new JButton("Elimina");
		delete.setFont(new Font("Tahoma", Font.PLAIN, 30));
		JButton modify = new JButton("Modifica");
		modify.setFont(new Font("Tahoma", Font.PLAIN, 30));
		delete.setBounds(822, 208, 376, 45);
		this.add(delete);
		modify.setBounds(822, 150, 376, 45);
		this.add(modify);
		if (this.type.equals(TypeList.USER)) {
			delete.setEnabled(false);
			modify.setEnabled(false);
		} else if (this.type.equals(TypeList.ITEM)) {
			delete.addActionListener(e -> {
				v.deleteItem();
			});
			modify.addActionListener(e -> {
				v.swapView(CardName.ITEM_MODIFY);
			});

		}

		this.list.setBounds(114, 877, 523, -782);
		this.add(this.list);

		JButton exit = new JButton("Esci");
		exit.setFont(new Font("Tahoma", Font.PLAIN, 30));
		exit.setBounds(1015, 799, 183, 72);
		this.add(exit);
		exit.addActionListener(e -> v.swapView(CardName.MAIN));

		newItem.addActionListener(e -> v.swapView(CardName.ITEM_CREATE));

	}

	@Override
	public void setUserList(final String[] userList) {
		this.list = new JList<String>(userList);
	}

	@Override
	public void setItemList(final String[] itemList) {
		this.list = new JList<String>(itemList);
	}

	@Override
	public String getSelected() {
		return this.list.getSelectedValue();
	}

}
