package hr.fer.zemris.java.simplecomp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hr.fer.zemris.java.simplecomp.impl.ComputerImpl;
import hr.fer.zemris.java.simplecomp.impl.ExecutionUnitImpl;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.ExecutionUnit;
import hr.fer.zemris.java.simplecomp.models.InstructionCreator;
import hr.fer.zemris.java.simplecomp.parser.InstructionCreatorImpl;
import hr.fer.zemris.java.simplecomp.parser.ProgramParser;

/**
 * Razred implementira glavni program za rad s vrstom assemblerskog programa.
 * Program se proslijeduje iz datoteke do koje je put dan argumentom komandne linije.
 * Ako put nije proslijeden na taj nacin, moguce ga je naknadno unijeti putem konzole.
 * Program rezultira ispisom na konzolu predvidenog ispisa programa.
 * @author Jelena Drzaic
 *
 */
public class Simulator {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Kao argumente prima argumente komandne linije.
	 * @param args argumenti komandne linije.
	 * @throws IOException u slucaju neuspjesnog citanja iz datoteke.
	 */
	public static void main(String[] args) throws IOException {
		// Stvori računalo s 256 memorijskih lokacija i 16 registara
		Computer comp = new ComputerImpl(256, 16);
		// Stvori objekt koji zna stvarati primjerke instrukcija
		InstructionCreator creator = new InstructionCreatorImpl(
		 "hr.fer.zemris.java.simplecomp.impl.instructions"
		);
		String path = "";
		if(args.length == 0) {
			System.out.println("Unesite put do datoteke: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			path = br.readLine();
		}else if(args.length == 1) {
			path = args[0];
		}else {
			System.err.println("Nedozvoljeni broj argumenata");
		}
		// Napuni memoriju računala programom iz datoteke; instrukcije stvaraj
		// uporabom predanog objekta za stvaranje instrukcija
		try {
			ProgramParser.parse(path, comp, creator);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Greska kod parsiranja dokumenta");
			System.exit(-1);
		}
		// Stvori izvršnu jedinicu
		ExecutionUnit exec = new ExecutionUnitImpl();
		// Izvedi program
		exec.go(comp);
	}
}
