package hr.fer.zemris.java.hw13.aplikacija2.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * Postavlja parametar requesta pickedBgColor, na vrijednost 
 * mapirana ko parametar na 'color'.
 * proslijeduje posao jsp-u '/colors.jsp'
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/setcolor"})
public class BackgroundColorServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String color = (String) req.getParameter("color");
		req.getSession().setAttribute("pickedBgColor", color);
		req.getRequestDispatcher("/colors.jsp").forward(req, resp);
	}
}
