package hr.fer.zemris.java.hw14.aplikacija5.voting;

import hr.fer.zemris.java.hw14.aplikacija5.dao.DAOProvider;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * Biljezi glas za zeljenu opciju trenutno promatrane ankete
 *  te proslijeduje korisnika na stranicu '/glasanje-rezultati'.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/glasanje-glasaj"})
public class GlasanjeGlasajServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Long id = Long.valueOf((String) req.getParameter("id"));
			Long pollid = (Long) req.getSession().getAttribute("pollID");

			Integer count = DAOProvider.getDao().getOptionVotesCount(pollid, id);
			if (count > -1) {
				count += 1;
				DAOProvider.getDao().updateOptionVotesCount(pollid, id, count);
			}
		} catch (Exception ignorable) {}
		
		resp.sendRedirect(req.getContextPath() + "/glasanje-rezultati");
	}
}
