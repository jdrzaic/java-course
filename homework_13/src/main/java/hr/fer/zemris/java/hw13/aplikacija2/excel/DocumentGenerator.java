package hr.fer.zemris.java.hw13.aplikacija2.excel;

import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import  org.apache.poi.hssf.usermodel.HSSFRow;

/**
 * Razred sadrzi metodu koja generira excel dokument s zadanim specifikacijama,
 * dobivenim kao argumentima.
 * U dokument pohranjuje brojeve odredene proslijedenim parametrima, 
 * te njihove potencije
 * @author jelena
 *
 */
public class DocumentGenerator {
	
	/**
	 * Metoda stvara excel dokument s n stranica.
	 * Na svakoj stranici, za brojeve iz intervala [a,b],
	 * ispisuje i-tu potenciju tih brojeva, gdje i ide od 1
	 * do n.
	 * @param a pocetni broj za kojeg racunamo
	 * @param b zavrsni broj za koji racunamo
	 * @param n broj stranica dokumenta
	 * @return kreirani excel dokument, tipa {@link HSSFWorkbook}
	 */
	public HSSFWorkbook generateDocument(int a, int b, int n) {
		try{
			HSSFWorkbook hwb=new HSSFWorkbook();
			for(int i = 0; i < n; ++i) {
				HSSFSheet sheet =  hwb.createSheet("sheet" + i);
				//zaglavlje
				HSSFRow rowhead=   sheet.createRow(0);
				rowhead.createCell(0).setCellValue("Number");
				rowhead.createCell(1).setCellValue("Power");
				for(int j = a; j <= b; ++j) {
					HSSFRow row = sheet.createRow(j - a + 1);
					row.createCell(0).setCellValue(j);
					row.createCell(1).setCellValue(Math.pow(j, i + 1));
				}
			}
			return hwb;
		} catch ( Exception ex ) {
			return null;
		}
	}
}
