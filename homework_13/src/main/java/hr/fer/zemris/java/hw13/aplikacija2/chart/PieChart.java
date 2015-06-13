package hr.fer.zemris.java.hw13.aplikacija2.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 * Razred sluzi za generiranje instanci {@link JFreeChart},
 * s zadanim specifikacijama izgleda.
 * U trenutnoj verziji, instanca koju dobivamo je 
 * tortni dijagram  s podatcima o koristenju operacijskih sustava.
 * @author jelena
 *
 */
public class PieChart {
	
	/**
	 * referenca na instancu {@link PieChart}
	 */
	private static PieChart pieChart = null;
	
	/**
	 * Instanca {@link JFreeChart}-graf
	 */
	private JFreeChart chart;

	/**
	 * Konstruktor razreda
	 * @param applicationTitle info o grafu
	 * @param chartTitle naslov grafa
	 */
	private PieChart(String applicationTitle, String chartTitle) {
	  PieDataset dataset = createDataset();
	  chart = createChart(dataset, chartTitle);
	}
  
	/**
	 * Metoda vraca clansku varijablu chart.
	 * @return graf koji je pohranjen u this 
	 */
	public JFreeChart getChart() {
		return chart;
	}
	
	/**
	 * Metoda stvara podatke koristene u ovoj implementaciji klase.
	 * Podatci su informacije o broju korisnika OS Linux, Mac i Windows.
	 * @return {@link PieDataset} podatke koji se koriste ne grafu
	 */
    private  PieDataset createDataset() {
	    DefaultPieDataset result = new DefaultPieDataset();
	    result.setValue("Linux", 29);
	    result.setValue("Mac", 20);
	    result.setValue("Windows", 51);
	    return result;
    }
	
    /**
     * Metoda vraca instancu klase.
     * @return instanca klase
     */
    public static PieChart getInstance() {
    	if(pieChart == null) pieChart = new PieChart("", "");
    	return pieChart;
    }
    
    /**
     * Metoda stvara graf, postavlja mu graficke atribute.
     * Podatci za graf dobiveni su metodom createDataset()
     * @param dataset podatci za graf
     * @param title naslov grafa
     * @return {@link JFreeChart}, konstruirani graf
     */
	private JFreeChart createChart(PieDataset dataset, String title) {
	    JFreeChart chart = ChartFactory.createPieChart3D(title,          
	        dataset,                
	        true,                   
	        true,
	        false);
	
	    PiePlot3D plot = (PiePlot3D) chart.getPlot();
	    plot.setStartAngle(290);
	    plot.setDirection(Rotation.CLOCKWISE);
	    plot.setForegroundAlpha(0.5f);
	    return chart;
	}
} 