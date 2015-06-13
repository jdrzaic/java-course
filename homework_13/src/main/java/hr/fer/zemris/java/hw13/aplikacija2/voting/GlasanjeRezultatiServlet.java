package hr.fer.zemris.java.hw13.aplikacija2.voting;


import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.BandResult;
import hr.fer.zemris.java.hw13.aplikacija2.voting.utility.BandsUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * Generira stranicu s informacijama o rezultatima glasanja, koji su
 * pohranjeni u fileovima '/WEB-INF/glasanje-rezultati.txt'.
 * proslijeduje posao jsp-u '/WEB-INF/glasanje-definicija.txt'.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje-rezultati"})
public class GlasanjeRezultatiServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String bandsFile = req.getServletContext().
				getRealPath("/WEB-INF/glasanje-definicija.txt");
		String votesFile = req.getServletContext().
				getRealPath("/WEB-INF/glasanje-rezultati.txt");
		List<BandResult> voteResults = BandsUtility.generateResults(bandsFile, votesFile);
		req.setAttribute("voteResults", voteResults);
		int maxVotes = voteResults.get(0).getVotes();
		List<BandResult> bestResults = new ArrayList<BandResult>();
		for(BandResult result : voteResults) {
			if(result.getVotes() == maxVotes) {
				bestResults.add(result);
			}else {
				break;
			}
		}
		req.setAttribute("bestResults", bestResults);
		req.getRequestDispatcher("/WEB-INF/pages/glasanjeRez.jsp").forward(req, resp);
	}
}