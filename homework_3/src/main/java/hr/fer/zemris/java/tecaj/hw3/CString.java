package hr.fer.zemris.java.tecaj.hw3;

public class CString {
	
	/**
	 * Podaci spremljeni u instanci klase.
	 */
	private char[] data;
	
	/**
	 * Odmak od pocetka stringa iz kojeg je dobivena ova instanca 
	 * klase.
	 */
	private int offset;
	
	/**
	 * Duljina polja data.
	 */
	private int length;
	
	/**
	 * Konstruktor razreda CString.
	 * Parametri su opisani u nastavku;
	 * @param data podaci koje spremamo u instancu klase koju konstruiramo, tipa char[].
	 * @param offset, odmak od pocetka proslijedenog polja, indeks od kojeg nadalje 
	 * kopiramo podatke.
	 * @param length duljina novostvorenog stringa.
	 */
	public CString(final char[] data, final int offset, final int length) {
		copyData(data, offset, length);
	}
	
	/**
	 * Konstruktor razreda CString.
	 * Parametri su dani u nastavku;
	 * @param data, tipa char[], podaci koje pohranjujemo u instancu klase koju konstruiramo.
 	 */
	public CString(final char[] data) {
		copyData(data, 0, data.length);
	}
	
	/**
	 * Privatni konstruktor razreda CString.
	 * @param data podaci koje spremamo u string.
	 * @param offset odmak od pocetka.
	 * @param length duljina string
	 * @param a bool-uvijek true.
	 */
	private CString(final char[] data, final int offset, final int length,final boolean a) {
		this.offset = offset;
		this.data = data;
		this.length = length;
	}
	/**
	 * Konstruktor razreda CString.
	 * Parametri su opisani u nastavku;
	 * @param original instanca razreda CString od koje konstruiramo novu instancu.
	 */
	public CString(final CString original) {
		if(original.data.length > original.length) {
			copyData(original.data, original.offset, original.length);
		}
		else {
			data = original.data;
			length = original.length;
			offset = 0;
		}
	}
	
	private void copyData(final char[] data, final int offset, final int length) {
		this.offset = 0;
		this.length = length;
		this.data = new char[length];
		for (int i = 0; i <length; ++i) {
			this.data[i] = data[offset + i];
		}
	}
	/**
	 * Konstruktor razreda CString.
	 * Kao parametar prima String te njegov sadrzaj poprima novokonstruirana
	 * instanca razreda CString.
	 * @param s string na koji postavljamo novi string.
	 */
	public CString(final String s) {
		data = new char[s.length()];
		length = s.length();
		offset = 0;
		for(int i = 0; i < length; ++i) {
			data[i] = s.charAt(i);
		}
 	}
	
	/**
	 * Metoda vraca duljinu stringa pohranjenog u instanci klase
	 * na kojoj je pozvana.
	 * @return duljina stringa, tipa int.
	 */
	public final int length() {
		return length;
	}
	
	/**
	 * Metoda vraca element stringa pohranjenog u instanci klase na poziciji index.
	 * @param index pozicija zeljenog elementa.
	 * @return element stringa, tipa char.
	 */
	public final char charAt(final int index) {
		return data[index + offset];
	}
	
	/**
	 * Metoda vraca novoalocirano polje s pohranjenim sadrzajem
	 * iz instance klase CString na kojoj je pozvana.
	 * @return sadrzaj instance, tipa char[].
	 */
	public final char[] toCharArray() {
		char[] novi = new char[length];
		for(int i = 0; i < length; ++i) {
			novi[i] = data[i + offset];
		}
		return novi;
	} 
	
	/**
	 * Metoda vraća String reprezentaciju pohranjenih podataka.
	 * @return String.
	 */
	@Override
	public final String toString() {
		return new String(data, offset, length);
	}
	
	/**
	 * Metoda vraca index prvog pojavljivanja chara c.
	 * Ako taj char ne postoji, vraca -1.
	 * @param c trazeni char.
	 * @return pozicija chara ako on postoji, -1 inace.
	 */
	public final int indexOf(final char c) {
		for(int i = 0; i < length; ++i) {
			if(data[i + offset] == c) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Metoda provjerava pocinje li string na kojem je pozvana proslijedenim stringom.
	 * Ako to vrijedi, vraca true, inace vraca false.
	 * @param s string za kojeg provjeravamo je li pocetak naseg stringa.
	 * @return true ako s pocetak, false inace.
	 */
	public final boolean startsWith(final CString s) {
		if(s.length > length) {
			return false;
		}
		for(int i = 0; i < s.length; ++i) {
			if(data[offset + i] != s.data[s.offset + i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Metoda provjerava zavrsava li string na kojem je pozvana proslijedenim stringom.
	 * Ako to vrijedi, vraca true, inace vraca false.
	 * @param s string za kojeg provjeravamo je li zavrsetak naseg stringa.
	 * @return true ako s zavrsetak, false inace.
	 */
	public final boolean endsWith(final CString s) {
		if(s.length > length) {
			return false;
		}
		for(int i = 0; i < s.length; ++i) {
			if(data[offset + length - i - 1] != s.data[s.offset + s.length -i - 1]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Metoda provjerava postoji li string s u stringu na kojem je poslana
	 * kao podstring.
	 * @param s string za kojeg provjeravamo postojanje
	 * @return true, ako s podstring, false inace.
	 */
	public final boolean contains(final CString s) {
		for(int i = 0; i < length; ++i) {
			if(containsAtIndex(s, i)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metoda provjerava je li string s podstring stringa na kojem je pozvana
	 * pocevsi od indeksa index
	 * @param s string koji provjeravamo.
	 * @param index pozicija od koje gledamo
	 * @return true ako postoji, false inace.
	 */
	private boolean containsAtIndex(final CString s,final int index) {
		if(s.length > length - index) {
			return false;
		}
		for(int i = 0; i < s.length; ++i) {
			if(data[offset + index + i] != s.data[s.offset + i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Metoda vraca podstring stringa na zeljenoj poziciji, od startIndex do endIndex, iskljucivo
	 * @param startIndex pozicija,pocetan znak podstringa
	 * @param endIndex pozicija, znak iza zavsrnog znaka podstringa.
	 * @return zeljeni podstring.
	 * @throws IllegalArgumentException ako parametri nisu legalni.
	 */
	public final CString substring(final int startIndex, final int endIndex) {
		if(startIndex < 0 || endIndex < 0 || endIndex <= startIndex || startIndex > length - 1 || endIndex > length) {
			throw new IllegalArgumentException("wrong parameters for substring.");
		} 
		else {
			return new CString(this.data, this.offset + startIndex, endIndex - startIndex, true);
		}
	}
	
	/**
	 * Metoda vraca pocetni komad stringa na kojem je pozvana,
	 * duljine n
	 * @param n duljina pocetnog komada.
	 * @return CString, pocetni komad duljine n.
	 */
	public final CString left(final int n) {
		return substring(0, n);
	}
	
	/**
	 * Metoda vraca zavrsni komad stringa na kojem je pozvana,
	 * duljine n
	 * @param n duljina zavrsnog komada.
	 * @return CString, zavrsni komad duljine n.
	 */
	public final CString right(final int n) {
		return substring(length - n, length);
	}
	
	/**
	 * Metoda dodaje string s na kraj stringa na kojem je pozvana.
	 * @param s string kojeg dodajemo.
	 * @return konkatenirani string.
	 */
	public final CString add(final CString s) {
		char[] novi = new char[length + s.length];
		for(int i = 0; i < length; ++i) {
			novi[i] = data[offset + i];
		}
		for(int i = 0; i < s.length; ++i) {
			novi[length + i] = s.data[s.offset + i];
		}
		return new CString(novi);
	}
	
	/**
	 * Metoda zamijenjuje svako pojavljivanje znaka oldChar znakom newChar
	 * @param oldChar znak koji zamijenjujemo
	 * @param newChar znak kojim zamijenjujemo
	 * @return izmjenjeni string.
	 */
	public final CString replaceAll(final char oldChar, final char newChar) {
		char[] novi = new char[length];
		for(int i = 0; i < length; ++i) {
			if(data[offset + i] != oldChar) {
				novi[i] = data[offset + i];
			}
			else {
				novi[i] = newChar;
			}
		}
		return new CString(novi);
 	}
	
	/**
	 * Metoda zamjenjuje sva pojavljivanje stringa oldStr stringom newCString.
	 * @param oldStr string koji zamjenjujemo.
	 * @param newCString string kojim zamjenjujemo.
	 * @return izmjenjeni string.
	 */
	public final CString replaceAll(final CString oldStr, final CString newCString) {
		CString novi = new CString(new String(""));
		int i = 0;
		while(i < length) {
			if(containsAtIndex(oldStr, i)) {
				novi = novi.add(newCString);
				i += oldStr.length;
			}
			else {
				novi = novi.add(new CString(Character.toString(data[i])));
				++i;
			}
		}
		return novi;
	}
	
}
