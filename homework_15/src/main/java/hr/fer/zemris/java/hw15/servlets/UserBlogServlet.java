package hr.fer.zemris.java.hw15.servlets;

import hr.fer.zemris.java.hw15.dao.DAOProvider;
import hr.fer.zemris.java.hw15.formulars.BlogEntryForm;
import hr.fer.zemris.java.tecaj_14.model.BlogEntry;
import hr.fer.zemris.java.tecaj_14.model.BlogUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet koji obavlja poslove vezane uz blog nekog korisnika.
 * Provjerava se url, te se posao preusmjerava na odgovarajuci servlet;
 * za unos novog unosa bloga, editiranje unosa.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/servleti/author/*"})
public class UserBlogServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.removeAttribute("errors");

		List<String> errors = new ArrayList<String>();
		String pathTrigger = req.getPathInfo().substring(1).trim();
		String[] parts = pathTrigger.split("/");
		
		String nick = parts[0].trim();
		BlogUser author = DAOProvider.getDAO().getBlogUser(nick);
		
		if(author == null) {
			errors.add("Author does not exist!");
		} else {
			req.setAttribute("author", author);
			List<BlogEntry> entries = DAOProvider.getDAO().getBlogEntries(author);
			req.setAttribute("entries", entries);

			if (parts.length >= 2) {
				Long entryId = null;
				try {
					entryId = Long.parseLong(parts[1]);
				} catch (NumberFormatException e) {
				}

				if(entryId != null) {
					BlogEntry be = DAOProvider.getDAO().getBlogEntry(entryId);
					if(be == null) {
						errors.add("Entry with id=" + entryId + " does not exist");
					} else if (!be.getCreator().equals(author)) {
						errors.add("Entry with id=" + entryId + " is not from author" + author);
					}
					req.getSession().setAttribute("entry", be);
					req.setAttribute("errors", errors);
					req.getRequestDispatcher("/WEB-INF/pages/showEntry.jsp").forward(req, resp);
					return;
				} else 	if(pathTrigger.endsWith("new") || pathTrigger.endsWith("new/")) {
					BlogEntryForm ef = new BlogEntryForm();
					req.setAttribute("zapis2", ef);
					req.getRequestDispatcher("/WEB-INF/pages/newEntry.jsp").forward(req, resp);
					return;
				} else if(pathTrigger.endsWith("edit") || pathTrigger.endsWith("edit/")) {
					Long id = Long.parseLong(req.getParameter("id"));
					BlogEntry be = DAOProvider.getDAO().getBlogEntry(id);
					req.setAttribute("zapis2", be);
					req.getRequestDispatcher("/WEB-INF/pages/editEntry.jsp").forward(req,resp);
					return;
				}
			} 

		}
		req.setAttribute("errors", errors);
		req.getRequestDispatcher("/WEB-INF/pages/blogView.jsp").forward(req, resp);
	}
}
