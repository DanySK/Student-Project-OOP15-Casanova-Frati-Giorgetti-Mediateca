package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.ViewImpl.CardName;

/**
 * Class which implements the main screen for manager.
 *
 * @author Luca Giorgetti
 *
 */
public class ManagerScreenImpl extends JPanel implements ManagerScreen {

	private static final long serialVersionUID = 1L;

	/**
	 * Builder for ManagerScreenImpl.
	 */
	public ManagerScreenImpl(final View v) {
		this.setSize(1280, 920);
		this.setLayout(null);

		JButton modifyItem = new JButton("Modifica Oggetto");
		modifyItem.setBounds(847, 115, 147, 25);
		this.add(modifyItem);

		JButton newItem = new JButton("Crea Nuovo Oggetto");
		newItem.addActionListener(arg0 -> {
		});
		newItem.setBounds(847, 77, 147, 25);
		this.add(newItem);

		JButton deleteItem = new JButton("Elimina Oggetto");
		deleteItem.setBounds(847, 153, 147, 25);
		this.add(deleteItem);

		newItem.addActionListener(e -> v.swapView(CardName.ITEM_CREATE));
	}
}
