package hr.fer.zemris.java.gui.charts;

import java.util.List;

/**
 * Razred reprezentira histogram(stupcasti graf).
 * U konstruktoru prima listu instanci klase XYValue-podatke 
 * za prikaz, 2 stringa-nazive uz x i y os, respektivno,
 * te najmanji i najveci broj prikaziv na grafu, te
 * razmak izmedu argumenata u grafu.
 * @author jelena
 *
 */
public class BarChart {

	/**
	 * Podaci za prikaz na histogramu
	 */
	private List<XYValue> param;
	
	/**
	 * Tekst uz x os
	 */
	private String xText;
	
	/**
	 * Tekst uz y os
	 */
	private String yText;
	
	/**
	 * Minimalni prikaziv broj na y osi
	 */
	private int yMin;
	
	/**
	 * Maksimalni prikazivi broj na y osi
	 */
	private int yMax;
	
	/**
	 * Razlika susjednih brojeva na y osi
	 */
	private int gap;
	
	/**
	 * Konstruktor razreda.
	 * @param parameters lista parametara za prikaz
	 * @param xText tekst uz x os
	 * @param yText tekst uz y os
	 * @param yMin minimalni y
	 * @param yMax maksimalni y
	 * @param gap razlika izmedu brojeva na y osi
	 */
	public BarChart(List<XYValue> parameters, String xText, String yText, int yMin, 
		int yMax, int gap) {
		this.param = parameters;
		this.xText = xText;
		this.yText = yText;
		this.yMin = yMin;
		this.yMax = (yMax - yMin) % gap == 0 ? yMax : yMax + (gap - (yMax - yMin) % gap); 
		this.gap = gap;
	}

	/**
	 * Metoda sluzi za dohvat liste podataka
	 * @return podaci za histogram.
	 */
	public List<XYValue> getParam() {
		return param;
	}

	/**
	 * Metoda sluzi za dohvat teksta uz x-os
	 * @return tekst uz x-os
	 */
	public String getxText() {
		return xText;
	}

	/**
	 * Metoda sluzi za dohvat teksta uz y-os
	 * @return tekst uz y-os
	 */
	public String getyText() {
		return yText;
	}

	/**
	 * Metoda sluzi za dohvat minimalne vrijednosti y
	 * @return minimalna vrijednost y.
	 */
	public int getyMin() {
		return yMin;
	}

	/**
	 * Metoda sluzi za dohvat maximalne vrijednosti y
	 * @return maximalna vrijednost y.
	 */
	public int getyMax() {
		return yMax;
	}

	/**
	 * Metoda sluzi za dohvat razlike izmedu
	 * y vrijednosti na histogramu.
	 * @return razlika vrijednosti na y osi.
	 */
	public int getGap() {
		return gap;
	}	
	
}
