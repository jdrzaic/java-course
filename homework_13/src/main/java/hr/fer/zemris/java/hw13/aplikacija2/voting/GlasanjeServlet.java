package hr.fer.zemris.java.hw13.aplikacija2.voting;

import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.Band;
import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.BandsUtility;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * ucitava podatke o dostupnim bandovima iz datoteke,
 * te proslijeduje posao jsp-u /WEB-INF/pages/glasanjeIndex.jsp.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje"})
public class GlasanjeServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String fileName = req.getServletContext().
				getRealPath("/WEB-INF/glasanje-definicija.txt");
		TreeMap<Integer, Band> bands = BandsUtility.readBands(fileName);
		if(bands != null) {
			req.setAttribute("bands", bands);
			req.getRequestDispatcher("/WEB-INF/pages/glasanjeIndex.jsp").forward(req, resp);		
		}
	}
}
