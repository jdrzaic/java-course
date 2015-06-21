package hr.fer.zemris.java.hw14.aplikacija5.voting;


import hr.fer.zemris.java.hw14.aplikacija5.dao.DAOProvider;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollOptionsEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * Generira stranicu s informacijama o rezultatima glasanja.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje-rezultati"})
public class GlasanjeRezultatiServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		List<PollOptionsEntry> voteResults = null;
		try {
			Long pollid = (Long) req.getSession().getAttribute("pollID");
			voteResults = DAOProvider.getDao().getOptions(pollid);
		} catch (Exception ex) {
			voteResults = new ArrayList<>();
		}

		//sortiramo da dobijemo najbolje rangirane opcije
		Collections.sort(voteResults, new Comparator<PollOptionsEntry>() {
			
			@Override
			public int compare(PollOptionsEntry o1, PollOptionsEntry o2) {
				return -Integer.compare(o1.getVotesCount(),
						o2.getVotesCount());
			}
		});

		List<PollOptionsEntry> bestResults = new ArrayList<>();
		
		if (voteResults.size() != 0) {
			int firstCount = voteResults.get(0).getVotesCount();
			for (PollOptionsEntry d : voteResults) {
				if (d.getVotesCount() == firstCount) {
					bestResults.add(d);
				}
			}
		}

		req.setAttribute("bestResults", bestResults);
		req.setAttribute("voteResults", voteResults);
		
		try {
			req.getRequestDispatcher("/WEB-INF/pages/glasanjeRez.jsp").forward(req,
					resp);
		} catch (ServletException | IOException e) {}	
	}
}