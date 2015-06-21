package hr.fer.zemris.java.hw14.aplikacija5.voting;

import hr.fer.zemris.java.hw14.aplikacija5.dao.DAOProvider;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollEntry;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollOptionsEntry;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * ucitava podatke o dostupnim bandovima iz datoteke,
 * te proslijeduje posao jsp-u '/WEB-INF/pages/glasanjeIndex.jsp'.
 * @author jelena
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje"})
public class GlasanjeServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		//trenutna anketa-id
		Long id = Long.valueOf((String) req.getParameter("pollID"));
		PollEntry poll = DAOProvider.getDao().getPoll(id);

		//dohvati opcije ankete
		List<PollOptionsEntry> options = DAOProvider.getDao().getOptions(id);
		
		req.getSession().setAttribute("pollID", id);
		req.setAttribute("options", options);
		req.setAttribute("poll", poll);
		
		req.getRequestDispatcher("/WEB-INF/pages/glasanjeIndex.jsp").forward(req, resp);
	}
}
