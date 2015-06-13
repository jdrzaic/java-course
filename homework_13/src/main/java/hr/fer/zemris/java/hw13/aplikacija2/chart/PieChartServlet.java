package hr.fer.zemris.java.hw13.aplikacija2.chart;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.JFreeChart;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * Implementira usluzivanje korisnika instancom {@link PieChart},
 * velicine 600*400 piksela.
 * @author jelena
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/reportImage"})
public class PieChartServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		byte[] graphData = createGraphData();
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
	 * Metoda stvara graf koristeci {@link PieChart},
	 * zapisuje ga u byte[] te ga vraca u tom obliku.
	 * @return byte[], zapis grafa
	 */
	private byte[] createGraphData() {
		JFreeChart chart = PieChart.getInstance().getChart();
		BufferedImage pieChart = chart.createBufferedImage(600, 400);
		
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try {
            ImageIO.write(pieChart, "png", bs);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        byte[] toBytes = bs.toByteArray();
        try {
			bs.close();
		} catch (IOException ignorable) {}
        return toBytes;
	}
}
