package hr.fer.zemris.java.hw13.aplikacija2.voting.utility;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Razred sluzi za generiranje excel dokumenta s informacijama o glasovima
 * za bendove.
 * nudi metodu koja stvara excel dokument s proslijedenim podatcima.
 * @author jelena
 *
 */
public class BandsExcelGenerator {
	
	/**
	 * Metoda generira excel dokument s 2 stupca.
	 * U prvom stupcu nalazi se ime benda, a u drugom
	 * pripadni broj glasova.
	 * @param votes glasovi koje je dobio neki bend
	 * @return novokreirani dokument, tipa {@link HSSFWorkbook}
	 */
	public HSSFWorkbook generateDocument(List<BandResult> votes) {
		try{
			HSSFWorkbook hwb=new HSSFWorkbook();
			HSSFSheet sheet =  hwb.createSheet("rezultati glasanja");
			HSSFRow rowhead=   sheet.createRow(0);
			rowhead.createCell(0).setCellValue("Naziv benda");
			rowhead.createCell(1).setCellValue("Broj glasova");
			for(int j = 0; j < votes.size(); ++j) {
				HSSFRow row = sheet.createRow(j + 1);
				row.createCell(0).setCellValue(votes.get(j).getName());
				row.createCell(1).setCellValue(votes.get(j).getVotes());
			}
			return hwb;
		} catch ( Exception ex ) {
			return null;
		}
	}
}
