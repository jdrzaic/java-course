package hr.fer.zemris.java.hw13.aplikacija2.voting;

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
 * Biljezi glas za zeljeni band te proslijeduje korisnika na
 * stranicu '/glasanje-rezultati'.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje-glasaj"})
public class GlasanjeGlasajServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fileName = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
		String id = req.getParameter("id");
		TreeMap<String, Integer> votes = BandsUtility.readVotes(fileName, id);
		BandsUtility.writeVotes(votes, fileName);
		resp.sendRedirect(req.getContextPath() + "/glasanje-rezultati");
	}
}
