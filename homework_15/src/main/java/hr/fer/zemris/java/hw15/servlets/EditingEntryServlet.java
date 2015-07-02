package hr.fer.zemris.java.hw15.servlets;

import hr.fer.zemris.java.hw15.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_14.model.BlogEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet daje mogucnost editiranja nekog unosa bloga.
 * Proslijeduje daljnji posao servletu mapiranom na '/servleti/author/nick_autora'.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/servleti/entry/edit"})
public class EditingEntryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//postavljen je sigurno, ne treba try
		Long entryid = Long.parseLong(req.getParameter("id"));
		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(entryid);
		
		if(req.getParameter("metoda").equals("Save")) {
			entry.setLastModifiedAt(new Date(System.currentTimeMillis()));

			entry.setTitle(req.getParameter("title"));
			entry.setText(req.getParameter("text"));			
		}else {
			List<String> errors = new ArrayList<String>();
			errors.add("Changes are not saved!");
			req.setAttribute("errors", errors);
		}
		
		req.getRequestDispatcher("/servleti/author/" + entry.getCreator().getNick())
		.forward(req, resp);
	}
}
