package hr.fer.zemris.java.hw13.aplikacija2.servlets;

import hr.fer.zemris.java.hw13.aplikacija2.trigonometry.TrigonometryUtility;
import hr.fer.zemris.java.hw13.aplikacija2.trigonometry.TrigonometryUtility.Pair;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Razred je izveden iz razreda {@link HttpServlet}.
 * Izracunava vrijednosti sinusa i kosinusa za brojeve izmedu vrijednost 
 * mapiranih u requestu na 'a' te 'b'.
 * Ako a vece od b, zamijenjuje ih, ako b vece od a+720, b je a+720.
 * Defaultna vrijednost parametara je a 0, b 360.
 * Proslijeduje posao jsp-u mapiranom na '/WEB-INF/pages/trigonometric.jsp'
 * @author jelena
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/trigonometric"})
public class TrigonometricServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer a = null;
		Integer b = null;
		try {
			a = Integer.parseInt(req.getParameter("a"));
			b = Integer.parseInt(req.getParameter("b"));
		}catch(Exception e) {
			a = 0;
			b = 360;
		}
		
		List<Pair> results = TrigonometryUtility.calculate(a, b);
		
		req.setAttribute("results", results);
		req.getRequestDispatcher("/WEB-INF/pages/trigonometric.jsp").forward(req, resp);
	}
}
