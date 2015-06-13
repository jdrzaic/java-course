package hr.fer.zemris.java.hw13.aplikacija2.voting;

import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.BandResult;
import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.BandsExcelGenerator;
import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.BandsUtility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * Usluzuje korisnika excel dokumentom koji sadrzi rezultate o bandovima i 
 * glasovima koje su dobili isti.
 * Za stvaranje dokumenta koristi razred {@link BandsExcelGenerator}.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje-xls"})
public class BandsExcelServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		
		String bandsFile = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
		String votesFile = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
		
		List<BandResult> results = BandsUtility.generateResults(bandsFile, votesFile);
		try {
			//generiraj dokument
			HSSFWorkbook hwb = new BandsExcelGenerator().generateDocument(results);
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			hwb.write(outByteStream);
			byte [] outArray = outByteStream.toByteArray();
			//zelimo excel
			resp.setContentType("application/vnd.ms-excel");
			resp.setContentLength(outArray.length);
			resp.setHeader("Expires:", "0");
			resp.setHeader("Content-Disposition", "inline; filename=rezultati.xls");
			OutputStream outStream = resp.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		}catch(Exception e) {}
	}
}
