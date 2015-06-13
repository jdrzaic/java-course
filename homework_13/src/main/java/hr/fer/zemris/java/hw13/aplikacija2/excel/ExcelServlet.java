package hr.fer.zemris.java.hw13.aplikacija2.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * Usluzuje korisnika kreiranim excel dokumentom.
 * Koristi parametre requesta, mapirane na 'a', 'b', 'n'.
 * Legalne vrijednosti su a [-100,100], b [-100,100], n [1,5]
 * Za kreiranje dokumenta koristi {@link DocumentGenerator}.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/powers"})
public class ExcelServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String as = req.getParameter("a");
		String bs = req.getParameter("b");
		String ns = req.getParameter("n");			
		
		try {
			int a = Integer.parseInt(as);
			int b = Integer.parseInt(bs);
			int n = Integer.parseInt(ns);
			if(a < -100 || a > 100 || b < -100 || b > 100 || n > 5 || n < 1) {
				req.setAttribute("error", "Invalid parameters");
				req.getRequestDispatcher("/WEB-INF/pages/errorexcel.jsp").forward(req, resp);
			}
			HSSFWorkbook hwb = new DocumentGenerator().generateDocument(a, b, n);
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			hwb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			resp.setContentType("application/ms-excel");
			resp.setContentLength(outArray.length);
			resp.setHeader("Expires:", "0");
			resp.setHeader("Content-Disposition", "attachment; filename=powers.xls");
			OutputStream outStream = resp.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
		} catch(Exception e) {
			req.setAttribute("error", "Invalid parameters");
			req.getRequestDispatcher("/WEB-INF/pages/errorexcel.jsp").forward(req, resp);
		}
	}
}
