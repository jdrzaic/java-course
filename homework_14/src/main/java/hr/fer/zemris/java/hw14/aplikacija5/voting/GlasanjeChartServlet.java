package hr.fer.zemris.java.hw14.aplikacija5.voting;


import hr.fer.zemris.java.hw14.aplikacija5.dao.DAOProvider;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollOptionsEntry;
import hr.fer.zemris.java.hw14.aplikacija5.voting.utility.GlasanjePieChart;

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
 * o broju glasova koje su dobile opcije trenutno promatrane ankete.
 * Rezultate dobiva iz baze podataka s kojom radi aplikacija.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje-grafika"})
public class GlasanjeChartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long pollid = (Long) req.getSession().getAttribute("pollID");
		List<PollOptionsEntry> votes = DAOProvider.getDao().getOptions(pollid);
		byte[] graphData = createGraphData(votes);
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
	 * Za kreiranje grafa koristi razred {@link GlasanjePieChart}.
	 * @param results podatci o opcijjama iz ankete i glasovima za njih.
	 * @return graf, tipa byte[]
	 */
	private byte[] createGraphData(List<PollOptionsEntry> results) {
		JFreeChart chart = new GlasanjePieChart("", "", results).getChart();
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
