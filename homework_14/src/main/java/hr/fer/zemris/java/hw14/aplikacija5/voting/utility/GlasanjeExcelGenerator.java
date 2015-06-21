package hr.fer.zemris.java.hw14.aplikacija5.voting.utility;

import hr.fer.zemris.java.hw14.aplikacija5.model.PollOptionsEntry;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Razred sluzi za generiranje excel dokumenta s informacijama o glasovima
 * za opcije iz trenutne ankete.
 * nudi metodu koja stvara excel dokument s proslijedenim podatcima.
 * @author jelena
 *
 */
public class GlasanjeExcelGenerator {
	
	/**
	 * Metoda generira excel dokument s 2 stupca.
	 * U prvom stupcu nalazi se imena opcija, a u drugom
	 * pripadni broj glasova.
	 * @param votes glasovi koje je dobila neka opcija
	 * @return novokreirani dokument, tipa {@link HSSFWorkbook}
	 */
	public HSSFWorkbook generateDocument(List<PollOptionsEntry> votes) {
		try{
			HSSFWorkbook hwb=new HSSFWorkbook();
			HSSFSheet sheet =  hwb.createSheet("rezultati glasanja");
			HSSFRow rowhead=   sheet.createRow(0);
			rowhead.createCell(0).setCellValue("Naziv");
			rowhead.createCell(1).setCellValue("Broj glasova");
			for(int j = 0; j < votes.size(); ++j) {
				HSSFRow row = sheet.createRow(j + 1);
				row.createCell(0).setCellValue(votes.get(j).getOptionTitle());
				row.createCell(1).setCellValue(votes.get(j).getVotesCount());
			}
			return hwb;
		} catch ( Exception ex ) {
			return null;
		}
	}
}
