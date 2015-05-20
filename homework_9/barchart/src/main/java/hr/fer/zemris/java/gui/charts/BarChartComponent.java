package hr.fer.zemris.java.gui.charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.JComponent;

/**
 * Razred nasljeduje razred JComponent
 * Razred reprezentira implementaciju grafickog sucelja
 * za prikaz histograma, instance {@link BarChart}.
 * @author jelena
 *
 */
public class BarChartComponent extends JComponent {

	private static final long serialVersionUID = 1L;

	/**
	 * Praznina izmedu komponenti grafa
	 */
	private static final int FREE = 20;
	
	/**
	 * Razmak izmedu stupaca grafa
	 */
	private static final int COL_GAP = 1;
	/**
	 * Debljina osi 
	 */
	private static final int WIDTH_AXIS = 3;

	private static final int ARROW_HEIGHT = 8;
	
	private List<XYValue> param;
	private String xText;
	private String yText;
	private int yMin;
	private int yMax;
	private int gap;
	private int step;
	
	/**
	 * Konstruktor razreda
	 * @param b instanca BarChart
	 */
	public BarChartComponent(BarChart b) {
		this.param = b.getParam();
		this.xText = b.getxText();
		this.yText = b.getyText();
		this.yMin = b.getyMin();
		this.gap = b.getGap();
		this.yMax = (b.getyMax() - yMin) % gap == 0 ? b.getyMax() : b.getyMax() + 
				(gap - (b.getyMax() - yMin) % gap); 
	}
	
	@Override
	protected void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D)gr.create();
		FontMetrics fm = getFontMetrics(new Font("Serif", Font.BOLD, 12)); 
		Font font = g.getFont();
	    FontRenderContext context = g.getFontRenderContext();
		int widthNum = (int)(font.getStringBounds(Integer.toString(yMax), context).getWidth());
		int yPos = getSize().height - getInsets().bottom - 2 * fm.getHeight() -
				2 * FREE - FREE / 2+ fm.getAscent() / 2;
		int xPos = fm.getAscent() + getInsets().left + FREE + widthNum + FREE ;
		int lengthX = getSize().width - getInsets().right - getInsets().left
				- xPos - 3 * FREE;
		//sirina prostora za jedan stupac grafa
		int stepX = lengthX / param.size();
			//razmak izmedu dvije vrijednosti na y osi
		int stepY = (getSize().height - getInsets().top - getInsets().bottom - 
				2 * fm.getHeight() - 4 * FREE) / ((yMax  - yMin) / gap);
		System.out.println(widthNum);
		AffineTransform defaultAt = g.getTransform(); 
		drawNames(g, fm);
		g.setTransform(defaultAt);
		drawScale(g, widthNum, fm, yPos);
		drawAxes(g, fm, widthNum, xPos, yPos);
		drawColumns(g, yPos, xPos, fm, stepX, stepY, lengthX);
		g.dispose();
	}

	/**
	 * Razred crta skalu brojeva uz y os, te komponente koordinatne mreze
	 * paralelne s x osi.
	 * @param g Graphics2D
	 */
	private void drawScale(Graphics2D g, int widthNum, FontMetrics fm, int yPos) {
		
		
		g.setColor(Color.BLACK);
		//koliko mjesta trebamo dolje
		int xPos = fm.getAscent() + getInsets().left + FREE;
		
		step = (getSize().height - getInsets().top - getInsets().bottom - 
				2 * fm.getHeight() - 4 * FREE) / ((yMax  - yMin) / gap) ;
		
		//ispisujemo parametre uz y os i linije paralelne s x osi
		for(int y = yMin; y <= yMax; y += gap) {
			//pomaknemo broj tko da budu desno poravnati
			xPos += (widthNum - fm.stringWidth(Integer.toString(y)));
			g.drawString(Integer.toString(y), xPos, yPos + fm.getAscent() / 2);
			//vratimo na staro
			xPos -= (widthNum - fm.stringWidth(Integer.toString(y)));
			//boja za crtanje koordinatne mreze
			g.setColor(new Color(255, 230, 190));
			
			//crtamo horizontalnu mrezu
			g.drawLine(xPos + widthNum + FREE / 2, yPos, 
					getSize().width - getInsets().right - FREE, yPos);
			g.setColor(Color.BLACK);
			//pomaknemo se po y osi
			yPos -= step;
		}
	}

	/**
	 * Metoda sluzi za prikaz stupaca grafa,
	 * te komponente koordinatne mreze paralelne s y osi.
	 * @param g Graphics2D
	 * @param yPos y pozicija lijevog donjeg kuta grafa
	 * @param xPos x pozicija lijevog donjeg kuta grafa.
	 * @param fm FontMetrics
	 * @param stepX sirina stupca grafa
	 * @param stepY razlika izmedu brojeva na skali histograma,
	 * u pikselima.
	 * @param lengthX sirina koju popunjavamo stupcima grafa.
	 */
	private void drawColumns(Graphics2D g, int yPos, int xPos, FontMetrics fm,
			int stepX, int stepY, int lengthX) {
		
		
		g.setColor(Color.BLACK);
		int i;
		for(i = 0; i < param.size(); ++i) {
			//pravokutnici koji bacaju sjenu
			Rectangle rectShad = new Rectangle(xPos + WIDTH_AXIS - 1 + stepX * i, 
					yPos - param.get(i).y * stepY / gap + 4, 
					stepX - COL_GAP + 6, param.get(i).y * stepY / gap - 4);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(rectShad.x, rectShad.y, rectShad.width, rectShad.height);
			
			//stupac grafa
			Rectangle rect = new Rectangle(xPos + WIDTH_AXIS - 1 + stepX * i,
					yPos - param.get(i).y * stepY / gap, stepX - COL_GAP, param.get(i).y * stepY / gap - 2);
			g.setColor(new Color(255, 117, 71));
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			
			//crtamo koordinatnu mrezu, osi y osi
			g.setColor(new Color(255, 230, 204));
			if(i != 0) {
				g.drawLine(xPos + WIDTH_AXIS - 1 + stepX * i - 1,  yPos - fm.getAscent() / 2 + FREE / 2, 
						xPos + WIDTH_AXIS - 1 + stepX * i - 1, getInsets().top + FREE / 2);
			}
			
			//broj ispod x osi, ispod stupca
			g.setColor(Color.BLACK);
			g.drawString(param.get(i).getX() + "", xPos + WIDTH_AXIS - 1 + stepX * i + stepX / 2
					- fm.stringWidth("" + param.get(i).getX()) / 2, getSize().height - 
					2 * FREE - fm.getHeight());
		}
		
		//najdesnija linija koordinatne mreze
		g.setColor(new Color(255, 230, 204));
		g.drawLine(xPos + WIDTH_AXIS - 1 + stepX * i - 1,  yPos - fm.getAscent() / 2 + FREE / 2, 
				xPos + WIDTH_AXIS - 1 + stepX * i - 1, getInsets().top + FREE / 2);
	}

	/**
	 * Metoda sluzi za prikaz imena uz x i y os.
	 * @param g Graphics2D
	 * @param fm FontMetrics
	 */
	private void drawNames(Graphics2D g, FontMetrics fm) {
		
		int widthx = fm.stringWidth(xText);
		g.setColor(Color.BLACK);
		g.drawString(xText, getInsets().left + getSize().width / 2 - widthx / 2,
				getSize().height - getInsets().bottom - 2 * fm.getDescent()); 
		int widthy = fm.stringWidth(yText);
		AffineTransform at = new AffineTransform();
		at.rotate(-Math.PI / 2); 
		g.setTransform(at);
		g.drawString(yText, -getInsets().top - getSize().height / 2 - widthy / 2, 
				fm.getAscent() + getInsets().left);
	}
	
	/**
	 * Metoda sluzi za crtanje "koordinatnih osi" histograma.
	 * @param g Graphics2D
	 * @param fm FontMetrics
	 * @param widthNum sirina najsire vrijednosti, broja na y osi grafa.
	 * @param yPos y pozicija lijevog donjeg kuta grafa
	 * @param xPos x pozicija lijevog donjeg kuta grafa.
	 */
	private void drawAxes(Graphics2D g, FontMetrics fm, int widthNum, int xPos, int yPos) {
		g.setColor(Color.LIGHT_GRAY);
		g.setStroke(new BasicStroke(WIDTH_AXIS));
		//isti y ko i za pisanje y vrijednosti
		g.drawLine(xPos, yPos - fm.getAscent() + FREE , xPos, getInsets().top + FREE / 2);
		g.drawLine(xPos - FREE / 2, yPos, 
				getSize().width - getInsets().right - FREE, yPos);
		g.setStroke(new BasicStroke(1));
		int xArrow = getSize().width - getInsets().right - FREE;
		int yArrow = yPos;
		int[] xPoly = {xArrow, xArrow, xArrow + ARROW_HEIGHT};
		int[] yPoly = {yArrow - ARROW_HEIGHT / 2, yArrow + ARROW_HEIGHT / 2, yArrow};
		Polygon arrow = new Polygon(xPoly, yPoly, 3);
		g.fillPolygon(arrow);
		xArrow = xPos;
		yArrow = getInsets().top + FREE / 2;
		xPoly[0] = xArrow - ARROW_HEIGHT / 2; xPoly[1] = xArrow + ARROW_HEIGHT / 2;
		xPoly[2] = xArrow; yPoly[0] = yArrow; yPoly[1] = yArrow; yPoly[2] = yArrow - ARROW_HEIGHT;
		arrow = new Polygon(xPoly, yPoly, 3);
		g.fillPolygon(arrow);
	}
	
}
