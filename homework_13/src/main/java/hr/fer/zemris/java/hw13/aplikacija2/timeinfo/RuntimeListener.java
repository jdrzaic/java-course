package hr.fer.zemris.java.hw13.aplikacija2.timeinfo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Razred implementira sucelje {@link ServletContextListener}.
 * Pri pokretanju aplikacije, postavlja atribut mapiran na 'startTime'
 * na vrijednost trentnog vremena, u milisekundama.
 * @author jelena
 *
 */
public class RuntimeListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("startTime", System.currentTimeMillis());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
