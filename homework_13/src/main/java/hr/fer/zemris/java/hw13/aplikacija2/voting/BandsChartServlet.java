package hr.fer.zemris.java.hw13.aplikacija2.voting;


import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.BandResult;
import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.BandsPieChart;
import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.BandsUtility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.JFreeChart;

/**
 * Razred izveden iz razreda {@link HttpServlet}.
 * Usluzuje korisnika tortnim dijagramom na kojem su prikazane informacije
 * o broju glasova koje su dobili bandovi pohranjenu u /WEB-INF/glasanje-definicija.txt.
 * Rezultate dobiva iz /WEB-INF/glasanje-rezultati.txt.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje-grafika"})
public class BandsChartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String votesFile = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
		String bandsFile = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
		List<BandResult> results = BandsUtility.generateResults(bandsFile, votesFile);
		byte[] graphData = createGraphData(results);
		resp.setContentType("png");
		resp.setBufferSize(graphData.length);
		OutputStream out = null;
		try {
			out = resp.getOutputStream();
			out.write(graphData); 
			out.flush();
		}catch(IOException e) {}
		finally {
			if(out != null) {
				out.close();
			}
		}
	}

	/**
	 * Metoda stvara tortni dijagram s parametrima koji su joj proslijedeni.
	 * pohranjuje ga u byte array.
	 * Za kreiranje grafa koristi razred {@link BandsPieChart}.
	 * @param results podatci o bandovima i glasovima za njih.
	 * @return graf, tipa byte[]
	 */
	private byte[] createGraphData(List<BandResult> results) {
		JFreeChart chart = new BandsPieChart("", "", results).getChart();
		BufferedImage pieChart = chart.createBufferedImage(400, 400);
		
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try {
            ImageIO.write(pieChart, "png", bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] toBytes = bs.toByteArray();
        return toBytes;
	}
}
