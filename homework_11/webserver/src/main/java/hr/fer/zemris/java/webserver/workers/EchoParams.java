package hr.fer.zemris.java.webserver.workers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import hr.fer.zemris.java.webserver.IWebWorker;
import hr.fer.zemris.java.webserver.RequestContext;

/**
 * Razred implementira sucelje {@link IWebWorker}.
 * Ova implementacija sluzi za vracanje parametara klijentu u
 * obliku html tablice
 * @author jelena
 *
 */
public class EchoParams implements IWebWorker{

	@Override
	public void processRequest(RequestContext context) {
		Set<String> paramNames = context.getParameterNames();
		StringBuilder sb = new StringBuilder(
				"<html>\r\n" +
				"  <head>\r\n" + 
				"    <title>Parameters</title>\r\n"+
				"  </head>\r\n" + 
				"  <body>\r\n" + 
				"    <table border='1'>\r\n");
		for(String param : paramNames) {
			System.err.println(param);
			sb.append("      <tr><td>")
				.append(param)
				.append("</td><td>")
				.append(context.getParameter(param))
				.append("</td></tr>\r\n");
		}
		sb.append(
			"    </table>\r\n" + 
			"  </body>\r\n" + 
			"</html>\r\n");
		
		byte[] tijeloOdgovora = sb.toString().getBytes(
			StandardCharsets.UTF_8
		);
		try {
			context.write(tijeloOdgovora);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
