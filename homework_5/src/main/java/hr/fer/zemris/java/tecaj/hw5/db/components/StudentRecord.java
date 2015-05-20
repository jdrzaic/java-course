package hr.fer.zemris.java.tecaj.hw5.db.components;

/**
 * Razred predstavlja jedan zapis o studentu.
 * Zapis se sastoji od JMBAG-a, prezimena, imena te ocjene.
 * @author Jelena Drzaic
 */
public class StudentRecord {
	
	/**
	 * JMBAG studenta
	 */
	String jmbag;
	
	/**
	 * Ime studenta
	 */
	String firstName;
	
	/**
	 * Prezime studenta
	 */
	String lastName;
	
	/**
	 * Ocjena studenta
	 */
	int finalGrade;
	
	/**
	 * KOnstruktor razreda StudentRecord.
	 * @param jmbag jmbag studenta
	 * @param lastName prezime studenta
	 * @param firstName ime studenta
	 * @param finalGrade ocjena studenta
	 */
	public StudentRecord(String jmbag, String lastName, 
			String firstName, int finalGrade) {
		this.jmbag = jmbag;
		this.firstName = firstName;
		this.lastName = lastName;
		this.finalGrade = finalGrade;
	}

	/**
	 * Metoda usporeduje dva zapisa o studentu.
	 * @return true ako su zapisi jednaki, false inace.
	 */
	@Override
	public boolean equals(Object student) {
		if(student == null) {
			return false;
		}
		if(!(student instanceof StudentRecord)) {
			return false;
		}
		
		StudentRecord other = (StudentRecord) student;
		return other.jmbag == jmbag;
	}
	
	/**
	 * Metoda vraca hash code zapisa.
	 * @return hash code zapisa.
	 */
	@Override
	public int hashCode() {
		return Integer.parseInt(jmbag);
	}
	
	/**
	 * Metoda vraca jmbag instance na kojoj je pozvana.
	 * @return jmbag
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * Metoda vraca ime studenta na kojem je pozvana.
	 * @return ime studenta
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Metoda vraca prezime studenta na kojem je pozvana.
	 * @return prezime studenta
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Metoda vraca ocjenu studenta na kojem je pozvana.
	 * @return ocjena studenta
	 */
	public int getFinalGrade() {
		return finalGrade;
	}
	
	/**
	 * String reprezentacija instance klase;
	 * Argumenti su razdvojeni razmakom(space)
	 */
	@Override
	public String toString() {
		return jmbag + " " + firstName + " " + lastName +
				" " + finalGrade;
	}
}
