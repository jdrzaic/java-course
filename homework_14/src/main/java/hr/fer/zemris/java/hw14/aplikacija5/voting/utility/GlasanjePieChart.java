package hr.fer.zemris.java.hw14.aplikacija5.voting.utility;

import hr.fer.zemris.java.hw14.aplikacija5.model.PollOptionsEntry;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 * Razred sluzi za kreiranje tortnog dijagrama s zadanim podatcima.
 * Koristi instancu JFreeChart-a.
 * @author jelena
 *
 */
public class GlasanjePieChart {
	
	/**
	 * Varijabla u koju pohranjujemo graf
	 */
	private JFreeChart chart;

	/**
	 * Konstruktor razreda.
	 * @param applicationTitle info o grafu
	 * @param chartTitle info o grafu
	 * @param results podatci iz kojih stvaramo graf
	 */
	public GlasanjePieChart(String applicationTitle, String chartTitle, List<PollOptionsEntry> results) {
	  PieDataset dataset = createDataset(results);
	  chart = createChart(dataset, chartTitle);
	}
	
	/**
	 * Getter clanske varijable chart.
	 * @return generirani graf
	 */
	public JFreeChart getChart() {
		return chart;
	}
	
	/**
	 * Metoda kreira skup podataka od kojih se stvara graf, tipa 
	 * {@link DefaultPieDataset}.
	 * @param votes podatci za graf
	 * @return podatci pohranjeni u instancu {@link DefaultPieDataset}
	 */
    private  PieDataset createDataset(List<PollOptionsEntry> votes) {
	    DefaultPieDataset result = new DefaultPieDataset();
	    for(PollOptionsEntry vote : votes) {
	    	result.setValue(vote.getOptionTitle(), vote.getVotesCount());
	    }
	    return result;
    }
	
    /**
     * Metoda kreira tortni graf iz proslijedenih podataka.
     * @param dataset podatci koje prikazujemo
     * @param title info grafa
     * @return kreirani graf, tipa {@link JFreeChart}.
     */
	private JFreeChart createChart(PieDataset dataset, String title) {
	    JFreeChart chart = ChartFactory.createPieChart3D(title,
	        dataset,        
	        true,              
	        true,
	        false);
	
	    PiePlot3D plot = (PiePlot3D) chart.getPlot();
	    plot.setStartAngle(400);
	    plot.setDirection(Rotation.ANTICLOCKWISE);
	    plot.setForegroundAlpha(0.8f);
	    return chart;
	}
} 