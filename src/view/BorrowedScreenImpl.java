package view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BorrowedScreenImpl implements BorrowedScreen {
	final static int FRAME_LENGHT = 1280;
	final static int FRAME_WIDTH = 920;

	private View toViewClass = new ViewImpl();
	private JLabel presentation;
	private final JList borrowedJList = new JList();

	@Override
	public void startBorrowedScreen() {
		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Oggetti in prestito");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(ReviewScreenImpl.FRAME_LENGHT,
				ReviewScreenImpl.FRAME_WIDTH);
		mainFrame.setResizable(false);
		final JPanel mainPanel = new JPanel();
		this.presentation = new JLabel("Ecco gli oggetti che hai in prestito:");
		// this.borrowedJList.setListData(this.setBorrowedList());

	}

	public List setBorrowedList() {
		return this.toViewClass.setBorrowedItemList();
	}
}