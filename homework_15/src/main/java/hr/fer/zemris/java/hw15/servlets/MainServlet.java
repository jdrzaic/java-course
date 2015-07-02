package hr.fer.zemris.java.hw15.servlets;

import hr.fer.zemris.java.hw15.dao.DAOProvider;
import hr.fer.zemris.java.hw15.formulars.LogInForm;
import hr.fer.zemris.java.tecaj_14.model.BlogUser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet koji obavlja poslove vezane uz logiranje korisnika.
 * Ako postoje greske u podacima, daje mu mogucnost ponovnog logiranja.
 * Takoder, evidentira aktivne blogere.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/servleti/main"})
public class MainServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LogInForm login = new LogInForm();
		generatePageComponents(req, resp, login);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.removeAttribute("errors");
		LogInForm form = new LogInForm();
		form.fillFromHttpReq(req);
		//greske
		if(!form.checkErrors()) {
			BlogUser current = DAOProvider.getDAO().
					getBlogUser(form.getNick());
			if((current != null) && current.getPasswordHash().equals(form.getPassword())) {
				req.getSession().setAttribute("current.user", current);
				req.getSession().setAttribute("current.user.nick", current.getNick());
				req.getSession().setAttribute("current.user.id", current.getId());
				req.getSession().setAttribute("current.user.first", current.getFirstName());
				req.getSession().setAttribute("current.user.last", current.getLastName());

				req.setAttribute("status", 
						current.getFirstName() + " " + current.getLastName() + " logged in.");
				form.getErrors().put("errors", "wrong username or password, try again!");
			}
		}
		if(form.hasErrors()) {
			req.setAttribute("errors", 
					form.getErrors().values());
		}
		//opet generiraj stranicu
		generatePageComponents(req, resp, form);
	}
	
	/**
	 * Metoda postavlja request parametar vezan uz popis blogova, 
	 * te parametar vezan uz podatke dobivene iz forme.
	 * Proslijeduje  posao jsp-u mapiranom na '/WEB-INF/pages/main.jsp'.
	 * @param req instanca {@link HttpServletRequest}
	 * @param resp instanca {@link HttpServletResponse}
	 * @param login login forma
	 * @throws ServletException u slucaju problema u provodenju zahtjeva
	 * @throws IOException u slucaju problema u provodenju zahtjeva
	 */
	private void generatePageComponents(HttpServletRequest req,
			HttpServletResponse resp, LogInForm login) throws ServletException, IOException {
		req.getSession().setAttribute("zapis", login);
		List<BlogUser> authors = DAOProvider.getDAO().getBlogUsers();
		req.setAttribute("authors", authors);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(req, resp);
	}
}
