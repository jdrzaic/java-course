package hr.fer.zemris.java.hw15.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Razred naslieduje {@link HttpServlet}.
 * Redirecta zahtjev servletu mapiranom na '/servleti/main'
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet("/index.jsp")
public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("servleti/main");
	}
	
}
