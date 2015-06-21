package hr.fer.zemris.java.hw14.aplikacija5.voting;

import hr.fer.zemris.java.hw14.aplikacija5.dao.DAOProvider;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollOptionsEntry;
import hr.fer.zemris.java.hw14.aplikacija5.voting.utility.GlasanjeExcelGenerator;

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
 * Usluzuje korisnika excel dokumentom koji sadrzi rezultate o opcijama ankete i 
 * glasovima koje su dobili isti.
 * Za stvaranje dokumenta koristi razred {@link GlasanjeExcelGenerator}.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje-xls"})
public class GlasanjeExcelServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		Long pollid = (Long) req.getSession().getAttribute("pollID");
		List<PollOptionsEntry> votes = DAOProvider.getDao().getOptions(pollid);

		try {
			//generiraj dokument
			HSSFWorkbook hwb = new GlasanjeExcelGenerator().generateDocument(votes);
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
