package hr.fer.zemris.java.gui.charts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Razred nasljeduje razred JFrame.
 * Glavni program za crtanje histograma.
 * Podaci se predaju u tekstualnoj datoteci,
 * u argumentima komandne linije predajemo file u kojem
 * se nalaze podaci.
 * Primjer legalnog filea:
 * Number of people in the car
 * Frequency
 * 1,8 2,20 3,22 4,10 5,4
 * 0
 * 22
 * 2
 * 
 * @author jelena
 *
 */
public class BarChartDemo extends JFrame{

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor razreda.
	 * @param b instanca razreda BarChart, koja predstavlja histogram.
	 * @param fName put do filea
	 */
	public BarChartDemo(BarChart b, String fName) {
		setLocation(100, 100);
		setSize(600, 700);
		setTitle("BarChart");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		initGUI(b, fName);
	}
	
	/**
	 * Metoda postavlja GUI u potrebno stanje.
	 * @param b histogram koji prikazujemo
	 * @param fName ime datoteke iz koje su podatci
	 */
	private void initGUI(BarChart b, String fName) {
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
		
		BarChartComponent component = new BarChartComponent(b);
		JLabel label = new JLabel(fName);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		component.setOpaque(true);
		component.setBackground(Color.PINK);
		component.setForeground(Color.BLACK);
		getContentPane().add(label, BorderLayout.PAGE_START);
		getContentPane().add(component, BorderLayout.CENTER);
	}
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * @param args argumenti komandne linije, put do filea s podatcima
	 */
	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.err.println("Path to a file required.");
			System.exit(-1);
		}
		BarChart b;
		try {
			b = readFile(args[0]);
			SwingUtilities.invokeLater(()-> {
				JFrame frame = new BarChartDemo(b, args[0]);
				frame.setVisible(true);
			});
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			System.err.println("Error reading from file");
			System.exit(-1);
		}		
	}

	/**
	 * Metoda cita file s podatcima,
	 * te ih sprema u instancu razreda BarChart.
	 * @param string put do filea
	 * @return Podatke za prikaz na grafu
	 * @throws IOException u slucaju problema s citanjem iz filea.
	 */
	private static BarChart readFile(String string) throws IOException{
		List<XYValue> comp = new ArrayList<XYValue>();
		BufferedReader br = new BufferedReader(
			new InputStreamReader(
			new BufferedInputStream(
			new FileInputStream(string)),StandardCharsets.UTF_8));
		String xText = br.readLine().trim();
		String yText = br.readLine().trim();
		String[] parameters = br.readLine().trim().split(" ");
		int startY;
		int endY;
		int step;
		try {
			startY = Integer.parseInt(br.readLine().trim());
			endY = Integer.parseInt(br.readLine().trim());
			step = Integer.parseInt(br.readLine().trim());
		}catch(NumberFormatException e) {
			br.close();
			throw new IOException();
		}
		for(int i = 0; i < parameters.length; ++i) {
			try {
				String[] value = parameters[i].split(",");
				if(value.length != 2) throw new IOException();
				int xV = Integer.parseInt(value[0]);
				int yV = Integer.parseInt(value[1]);
				comp.add(new XYValue(xV, yV));
			}catch(NumberFormatException e) {
				br.close();
				throw new IOException();
			}
		}
		br.close();
		return new BarChart(comp, xText, yText, startY, endY, step);
	}
}
