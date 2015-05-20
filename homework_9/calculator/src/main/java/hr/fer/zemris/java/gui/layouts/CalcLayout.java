package hr.fer.zemris.java.gui.layouts;

import hr.fer.zemris.java.gui.layouts.component.RCPosition;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.List;


/**
 * Razred implementira sucelje {@link LayoutManager2}
 * Razred se koristi za implementaciju kalkulatora
 * @author jelena
 *
 */
public class CalcLayout implements LayoutManager2 {

	/**
	 * Maksimalna velicina layouta
	 */
	private int maxWidth = 0, maxHeight = 0;
	
	/**
	 * Minimalna velicina layouta
	 */
	private int minWidth = 0, minHeight = 0;
    
	/**
	 * Preferirana velicina layouta
	 */
	private int preferredWidth = 0, preferredHeight = 0;
	
    /**
	 * Broj redaka s kojima radi CalcLayout
	 */
	public static final int ROWS = 5;
	
	/**
	 * Broj stupaca s kojima radi CalcLayout
	 */
	public static final int COLUMNS = 7;
	
	/**
	 * Lista s pohranjenim pozicijama komponenti
	 */
	List<RCPosition> positions;
	
	/**
	 * Razmak izmedu redaka i stupaca
	 */
	int  gap;
	
	/**
	 * Konstruktor razreda 
	 * Stvara layout s gap-om 0.
	 */
	public CalcLayout() {
		this(0);
	}
	
	/**
	 * Konstruktor razreda
	 * @param gap zeljeni gap
	 */
	public CalcLayout(int gap) {
		positions = new ArrayList<>();
		this.gap = gap;
	}
	
	@Override
	public void addLayoutComponent(String name, Component comp) {}
	
	@Override
	public void removeLayoutComponent(Component comp) {}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		setSizes(parent);
		return new Dimension(preferredWidth + parent.getInsets().left +
				parent.getInsets().right, preferredHeight + parent.getInsets().bottom
				+ parent.getInsets().top);
	}
	
	/**
	 * Metoda postavlja velicinu layouta
	 * @param parent Container u kojem se nalazi layout
	 */
	private void setSizes(Container parent) {
		setPrefferedSize(parent);
		setMaximumSize(parent);
		setMinimumSize(parent);
	}
	
	/**
	 * Metoda postavlja minimalnu dozvoljenu velicinu layouta
	 * @param parent Container u kojem se nalazi layout
	 */
	private void setMinimumSize(Container parent) {
		//najvece vrijednosti na razini komponenta
		int maxCWidth = 0;
		int maxCHeight = 0;
		int nOfComp = parent.getComponentCount();
		
		for(int i = 0; i < nOfComp; ++i) {
			Component c = parent.getComponent(i);
			if(c.getMaximumSize() == null) continue;
			if(positions.get(i).getRow() == 1 && positions.get(i).getColumn() == 1) {
				maxCWidth = Math.max(c.getMinimumSize().width / 5, maxCWidth);
			}else {
				maxCWidth = Math.max(c.getMinimumSize().width, maxCWidth);
			}
			maxCHeight = Math.max(c.getMinimumSize().height, maxCHeight);
		}
		minHeight = maxCHeight * ROWS + (ROWS - 1) * gap;
		minWidth = maxCWidth * COLUMNS + (COLUMNS - 1) * gap;
	}

	/**
	 * Metoda postavlja maksimalnu dozvoljenu velicinu layouta
	 * @param parent Container u kojem se nalazi layout
	 */
	private void setMaximumSize(Container parent) {
		//najvece vrijednosti na razini komponenta
		int maxCWidth = 0;
		int maxCHeight = 0;
		int nOfComp = parent.getComponentCount();			
		for(int i = 0; i < nOfComp; ++i) {
			Component c = parent.getComponent(i);
			if(c.getMaximumSize() == null) continue;
			if(positions.get(i).getRow() == 1 && positions.get(i).getColumn() == 1) {
				maxCWidth = Math.max(c.getMaximumSize().width / 5, maxCWidth);
			}else {
				maxCWidth = Math.max(c.getMaximumSize().width, maxCWidth);
			}
			maxCHeight = Math.max(c.getMaximumSize().height, maxCHeight);
		}
		maxHeight = maxCHeight * ROWS + (ROWS - 1) * gap;
		maxWidth = maxCWidth * COLUMNS + (COLUMNS - 1) * gap;
	}

	/**
	 * Metoda postavlja preferiranu dozvoljenu velicinu layouta
	 * @param parent Container u kojem se nalazi layout
	 */
	private void setPrefferedSize(Container parent) {
		//najvece vrijednosti na razini komponenta
		int maxCWidth = 0;
		int maxCHeight = 0;
		int nOfComp = parent.getComponentCount();			
		for(int i = 0; i < nOfComp; ++i) {
			Component c = parent.getComponent(i);
			if(c.getMaximumSize() == null) continue;
			if(positions.get(i).getRow() == 1 && positions.get(i).getColumn() == 1) {
				maxCWidth = Math.max(c.getPreferredSize().width / 5, 
						maxCWidth);
			}else {
				maxCWidth = Math.max(c.getPreferredSize().width / 5, 
						maxCWidth);
				maxCWidth = Math.max(c.getPreferredSize().width, maxCWidth);
			}
				maxCHeight = Math.max(c.getPreferredSize().height, maxCHeight);
		}
		preferredHeight = maxCHeight * ROWS + (ROWS - 1) * gap;
		preferredWidth = maxCWidth * COLUMNS + (COLUMNS - 1) * gap;		
	}

	/**
	 * Metoda vraca minimalnu dozvoljenu velicinu layouta
	 * @param parent Container u kojem se nalazi layout
	 */
	public Dimension minimumLayoutSize(Container parent) {
		setSizes(parent);
		return new Dimension(minWidth + parent.getInsets().left + 
				parent.getInsets().right, minHeight + parent.getInsets().top +
				parent.getInsets().bottom);
	}

	@Override
	public void layoutContainer(Container parent) {
		
		setSizes(parent);
		Insets insets = parent.getInsets();
		int nOfComp = parent.getComponentCount();
		Dimension free = calculateFree(parent);

		//nova visina retka
		double rowSize = free.height / (double)ROWS;
		//nova sirina stupca
		double colSize = free.width / (double)COLUMNS;
		
		//omjeri nove velicine i preferirane velicine
		//double constX = parent.getWidth() / parent.getPreferredSize().getWidth();
		//double constY = parent.getHeight() / parent.getPreferredSize().getHeight();
		
		for(int i = 0; i < nOfComp; ++i) {
		
			//oduzimamo jedan pa imamo pozicije od 0
			int xPos = positions.get(i).getColumn() - 1;
			int yPos = positions.get(i).getRow() - 1;
			Component c = parent.getComponent(i);
			if(positions.get(i).getColumn() == 1 && positions.get(i).getRow() == 1) {
				c.setBounds((int)(gap + insets.left + xPos * (colSize + gap)),
						(int)(gap + insets.top + yPos * (rowSize + gap)),
						(int)(colSize) * 5 + 4 * gap, (int)(rowSize));
			}else {
				c.setBounds((int)(gap + insets.left + xPos * (colSize + gap)),
					(int)(gap + insets.top + yPos * (rowSize + gap)),
					(int)(colSize), (int)(rowSize));
			}
		}
	}

	/**
	 * Metoda izracunava prostor koji je slobodan za popunjavanje od strane komponenti
	 * @param parent container koji popunjavamo
	 * @return slobodan prostor
	 */
	private Dimension calculateFree(Container parent) {
		Insets insets = parent.getInsets();
		int width = parent.getWidth() - insets.left - insets.right - 
				(ROWS - 1) * gap;
		int height = parent.getHeight() - insets.bottom - insets.top - 
				(COLUMNS - 1) * gap;
		return new Dimension(width, height);
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		if(constraints instanceof RCPosition) {
			if(checkPosition(((RCPosition)constraints))) {
				this.positions.add((RCPosition)constraints);
			}else {
				throw new IllegalArgumentException("Illegal position");
			}
		}else if(constraints instanceof String){
			RCPosition position = parsePorition((String)constraints);
			if(position != null) {
				if(checkPosition(position)) {
					this.positions.add(position);
				}else {
					throw new IllegalArgumentException("Illegal position");
				}
			}else {
				throw new IllegalArgumentException("Illegal position");
			}
		}
		else {
			throw new IllegalArgumentException("Illegal position");
		}
	}
	
	/**
	 * Metoda parsira poziciju iz danog stringa, ako je to moguce.
	 * @param constrains string koji pokusavamo parsirati
	 * @return instanca klase RCPosition s zapisanom parsiranom pozicijom,
	 * ako je parsiranje uspjelo, null inace.
	 */
	private RCPosition parsePorition(String constrains) {
		String[] rowcol = constrains.split(",");
		if(rowcol.length != 2) {
			return null;
		}else {
			try {
				int row = Integer.parseInt(rowcol[0]);
				int column = Integer.parseInt(rowcol[1]);
				return new RCPosition(row, column);
			}catch(NumberFormatException e) {
				return null;
			}
		}
	}

	/**
	 * Metoda provjerava je li pozicija za ubacivanje objekta legalna.
	 * Uvjeti su zadani u zadaci.
	 * @param pos pozicija objekta
	 * @return true, ako je pozicija legalna, false inace.
	 */
	private boolean checkPosition(RCPosition pos) {
		
		if(pos.getRow() < 1 || pos.getColumn() < 1 || pos.getRow() > ROWS 
				|| pos.getColumn() > COLUMNS) {
			return false;
		}
		if(pos.getRow() == 1 && (pos.getColumn() > 1 && pos.getColumn() < 6)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Metoda vraca maksimalnu dozvoljenu velicinu layouta
	 * @param target Container u kojem se nalazi layout
	 */
	public Dimension maximumLayoutSize(Container target) {
		setSizes(target);
		return new Dimension(maxWidth + target.getInsets().left + target.getInsets().right,
				maxHeight + target.getInsets().top + target.getInsets().bottom);
	}
	
	@Override
	public float getLayoutAlignmentX(Container target) {
		return (float)0.5;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return (float)0.5;
	}

	@Override
	public void invalidateLayout(Container target) {
	}

	/**
	 * Metoda vraca layout string reprezentaciju u obliku
	 * "[gap=value_of_gap"]"
	 */
	@Override
	public String toString() {
        String str = "";
        return getClass().getName() + "[gap=" + gap + str + "]";
    }
}
