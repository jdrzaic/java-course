package hr.fer.zemris.java.webserver.workers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import hr.fer.zemris.java.webserver.IWebWorker;
import hr.fer.zemris.java.webserver.RequestContext;

/**
 * Razred implementira sucelje {@link IWebWorker}.
 * Implementacija sluzi za crtanje kruga i davanje klijentu
 * istog.
 * @author jelena
 *
 */
public class CircleWorker implements IWebWorker{

	/**
	 * sirina slike
	 */
	private static final int WIDTH = 200;
	
	/**
	 * visina slike
	 */
	private static final int HEIGHT = 200;
	
	@Override
	public void processRequest(RequestContext context) {
		context.setMimeType("png");
		BufferedImage bim = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = bim.createGraphics();
		g2d.setColor(Color.green);
		g2d.fillOval(0, 0, WIDTH, HEIGHT);
		g2d.dispose();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bim, "png", bos);
			context.write(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
