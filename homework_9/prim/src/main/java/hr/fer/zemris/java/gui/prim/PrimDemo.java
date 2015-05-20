package hr.fer.zemris.java.gui.prim;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Razred naslijeduje razred JFrame.
 * Razred implementira GUI koji u dvije liste ispisuje,
 * na zahtjev korisnika, proste brojeve uzlaznim redoslijedom.
 * Klikom na gumb 'sljedeći' prikazuje se sljedeci po velicini prosti broj.
 * @author jelena
 *
 */
public class PrimDemo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor razreda.
	 * Stvara prozor velicine 600x700 pixela.
	 */
	public PrimDemo() {
		setLocation(100, 100);
		setSize(600, 700);
		setTitle("Prime Numbers Generator");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		initGUI();
	}
	
	/**
	 * Metoda inicijalizira grafičko sučelje koje se prikazuje.
	 * Sučelje se sastoji od dvije identične liste te buttona.
	 */
	private void initGUI() {
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
		PrimListModel model = new PrimListModel();
		JScrollPane paneLeft = new JScrollPane(new JList<Integer>(model));
		JScrollPane paneRight = new JScrollPane(new JList<Integer>(model));
		JPanel lists = new JPanel(new GridLayout(1, 2));
		lists.add(paneLeft);
		lists.add(paneRight);
		JButton next = new JButton("sljedeći");
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.next();
			}
		});
		
		getContentPane().add(lists, BorderLayout.CENTER);
		
		getContentPane().add(next, BorderLayout.SOUTH);
	}
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * @param args argumenti komandne linije, zanemaruju se
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> {
			JFrame frame = new PrimDemo();
			frame.setVisible(true);
		});
	}
}
