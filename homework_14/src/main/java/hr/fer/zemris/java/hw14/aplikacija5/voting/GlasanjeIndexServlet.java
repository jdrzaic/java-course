package hr.fer.zemris.java.hw14.aplikacija5.voting;

import hr.fer.zemris.java.hw14.aplikacija5.dao.DAOProvider;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollEntry;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Razred naslijeduje razred {@link HttpServlet}.
 * Dohvaca sve dostupne ankete iz baze te proslijeduje
 * ispis istih jsp-u '/WEB-INF/pages/index.jsp' 
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/index.html"})
public class GlasanjeIndexServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<PollEntry> polls = DAOProvider.getDao().getPolls();
		req.setAttribute("polls", polls);
		req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
	}
}
