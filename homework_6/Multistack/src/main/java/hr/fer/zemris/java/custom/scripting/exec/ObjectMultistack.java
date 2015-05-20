package hr.fer.zemris.java.custom.scripting.exec;

import java.util.HashMap;
import java.util.Map;

/**
 * Razred predstavlja implementaciju kolekcije ObjectMultistack.
 * Kolekcija je mapa, uz ponesto izmjenjeno pridruzivanje vrijednosti kljucu.
 * U ovoj implementaciji moguće je jednom kljucu pridruziti vise vrijednosti.
 * Pohranjuju se parovi (String, lista objekata tipa ValueWrapper).
 * Moguce je pristupiti zadnje upisanoj vrijednosti.
 * Objekti tipa {@link ValueWrapper}
 * @author Jelena Drzaic
 *
 */
public class ObjectMultistack {
	
	/**
	 * Mapa u koju pohranjujemo parove vrijednosti tipa String i 
	 * MultistackEntry.
	 */
	private Map<String, ObjectMultistack.MultistackEntry> map;
	
	/**
	 * Konstruktor razreda ObjectMultistack.
	 * Stvara praznu mapu - clansku varijablu razreda.
	 */
	public ObjectMultistack() {
		map = new HashMap<String, ObjectMultistack.MultistackEntry>();
	}
	
	/**
	 * Metoda dodaje vrijednost varijable valueWrapper kljucu name.
	 * @param name vrijednost kljuca.
	 * @param valueWrapper vrijednost pridruzena kljucu.
	 */
	public void push(String name, ValueWrapper valueWrapper) {
		MultistackEntry temporary = map.get(name);
		map.put(name, new MultistackEntry(valueWrapper));
		//System.out.println(temporary);

		if(temporary != null){
			map.get(name).next = temporary;
		}
	}
	
	/**
	 * Metoda izbacuje zadnju vrijednost dodanu kljucu name.
	 * @param name kljuc ciju vrijednost izbacujemo.
	 * @return izbaceni element.
	 */
	public ValueWrapper pop(String name) {
		ValueWrapper poped = map.get(name).value;
		MultistackEntry temporary = map.get(name).next;
		map.put(name, temporary);
		return poped;
	}
	
	/**
	 * Metoda dohvaca zadnju vrijednost pridruzenu kljucu name.
	 * ako takva ne postoji, vraca null;
	 * @param name kljuc ciju vrijednost vracamo.
	 * @return vrijednost, ako postoji, null inace.
	 */
	public ValueWrapper peek(String name) {
		if(!map.containsKey(name)) {
			return null;
		}
		return map.get(name).value;
	}
	
	/**
	 * Metoda provjerava postoji li za kljuc name pridruzena vrijednost.
	 * @param name kljuc kojeg provjeravamo.
	 * @return true ako nema pridruzene vrijednosti, 
	 */
	public boolean isEmpty(String name) {
		if(!map.containsKey(name)) {
			return true;
		}
		if(map.get(name) == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Razred koristen u implementaciji ObjectMultistack.
	 * Sadrzi jednu clansku varijablu tipa ValueWrapper te referencu na
	 * objekt tog istog tipa.
	 * Razred sluzi za implementaciju vezane liste-stacka.
	 * @author Jelena Drzaic
	 */
	private static class MultistackEntry {
		private ValueWrapper value;
		private MultistackEntry next;
		
		/**
		 * Konstruktor razreda.
		 * Pohranjuje proslijedenu vrijednost, sljedbenik joj postavlja na null.
		 * @param value vrijednost koju pohranjujemo.
		 */
		public MultistackEntry(ValueWrapper value) {
			this.value = value;
			this.next = null;
		}
	}
}
