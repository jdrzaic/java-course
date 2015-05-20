package hr.fer.zemris.java.custom.scripting.parser;
import hr.fer.zemris.java.custom.collections.ArrayBackedIndexedCollection;
import hr.fer.zemris.java.custom.collections.ObjectStack;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;
import hr.fer.zemris.java.custom.scripting.tokens.*;
import hr.fer.zemris.java.custom.scripting.nodes.*;

import java.lang.Character;

/**
 * Razred implementira parser dokumenta prema zadanim uvjetima
 * @author Jelena Drzaic
 *
 */
public class SmartScriptParser {
	/**cvor tipa Document node
	 * reprezentira jedan dokument
	 */
	private DocumentNode parsed;
	
	/**
	 * Tip koji sluzi za prepoznavanje 
	 * pozicije unutar taga u kojem se trenutno nalazimo
	 * @author Jelena Drzaic
	 *
	 */
	static private enum ParserState {
		TEXT,
		TAG,
		FOR_TAG,
		FOR_TAG_1,
		FOR_TAG_2,
		FOR_TAG_3,
		FOR_TAG_4,
		ECHO_TAG, 
		END_TAG
	}
	
	/**
	 * Tip sluzi za prepoznavanje tipa Tokena
	 * @author Jelena Drzaic
	 *
	 */
	static private enum TokenType {
		DOUBLE,
		INTEGER,
		FUNCTION,
		STRING,
		TEXT,
		VARIABLE,
		BLANK
	}
	/**
	 * Konstruktor razreda SmartScriptParser
	 * Kao parametar prima String u kojem je zapisan tekst dokumenta 
	 * za kojeg je zatrazeno parsiranje
	 * @param String koji sadrzi sadrzaj dokumenta
	 */ 
	public SmartScriptParser(String docBody) {
		parsed = parseDocument(docBody);
	}
	
	/**
	 * Metoda koja parsira String prema zadanim pravilima
	 * @param docBody String kojeg parsiramo
	 * @return DOcumentNode u kojem je pohranjen parsirani tekst
	 */
	private DocumentNode parseDocument(String docBody){
		ArrayBackedIndexedCollection tokens = tokenize(docBody);
		return parser(tokens);
	}
	
	/**
	 * Metoda vraca parsed instance klase SmartScriptParser
	 * @return parsed DocumentNode u kojem je pohranjen tekst
	 */
	public DocumentNode getDocumentNode() {
		if(parsed == null) {
			throw new SmartScriptParserException("parsing unsuccessful");
		}
		return parsed;
	}
	
	/**
	 * Metoda prima Tokene te od njih gradi stablo 
	 * semantickom analizom prema zadanim pravilima
	 * @param tokens polje Tokena
	 * @return Document node u kojem je pohranjen parsirani tekst
	 */
	static private DocumentNode parser(ArrayBackedIndexedCollection tokens) {
		//Stack pomocu kojeg gradimo stablo
		ObjectStack stack = new ObjectStack();
		//niz u koji spremamo djecu trenutnog taga na kojem smo
		ArrayBackedIndexedCollection tagArgs = new ArrayBackedIndexedCollection();
		//trenutni token koji obradujemo
		Token current;
		//u kojem smo tokenu trenutno-pridruzena vrijednost u prosloj iteraciji
		//na pocetku stringa smo u textu
		ParserState state = ParserState.TEXT;
		stack.push(new DocumentNode());
		//prolaz po svim tokenima
		for(int pos = 0; pos < tokens.size(); ++pos) {
			current = (Token)tokens.get(pos);
			switch(state) {
			case TEXT:
				//ako smo i dalje u tekstu
				if(current instanceof TokenText) {
					((Node)(stack.peek())).addChildNode(new TextNode(current.asText()));
				}
				//ako smo naisli na pocetak ili kraj taga
				else if (current instanceof TokenSpecial) {
					if(((TokenSpecial)current).getType() == TokenSpecial.Type.START_TAG) {
						state = ParserState.TAG;
					}
					else {
						throw new SmartScriptParserException("invalid");
					}
				}
				else {
					throw new SmartScriptParserException("invalid");
				}
				break;
			case TAG:
				//naisli smo na varijablu
				if(current instanceof TokenVariable) {
					switch(current.asText().toUpperCase()) {
					case "FOR": 
						state = ParserState.FOR_TAG;
						break;
					case "END": 
						if(stack.size() <= 1) {
							throw new SmartScriptParserException("invalid");
						}
						//dosli smo do kraja taga
						else {
							state = ParserState.END_TAG;
						}
						break;
					default:
						throw new SmartScriptParserException("invalid");
					}	
				}
				//dosli smo do taga '='
				else if(current instanceof TokenEquals) {
					state = ParserState.ECHO_TAG;
				}
				else {
					throw new SmartScriptParserException("invalid");
				}
				break;
				//ako smo usli u tag i procitali varijablu for
			case FOR_TAG:
				if(current instanceof TokenVariable) {
					state = ParserState.FOR_TAG_1;
					tagArgs.add(current);
				}
				else {
					throw new SmartScriptParserException("invalid");
				}
				break;
				//ako smo procitali k Tokena u for-u (k = 1,2,3)
			case FOR_TAG_1: 
			case FOR_TAG_2:
			case FOR_TAG_3:
				//ako smo dosli do neceg regularnog
				if(current instanceof TokenVariable || 
				   current instanceof TokenConstantInteger ||
				   current instanceof TokenConstantDouble ||
				   (current instanceof TokenString && isNumber(current.asText()))) {
					state = ParserState.values()[state.ordinal() + 1];
					tagArgs.add(current);
				}
				//ako for ima samo 3 argumenta-legalno
				else if(state == ParserState.FOR_TAG_3 && 
						current instanceof TokenSpecial && 
						((TokenSpecial)current).getType() == TokenSpecial.Type.END_TAG) {
					ForLoopNode newNode = new ForLoopNode((TokenVariable)tagArgs.get(0), 
										(Token)tagArgs.get(1), (Token)tagArgs.get(2), null);
					((Node)stack.peek()).addChildNode(newNode);
					stack.push(newNode);
					state = ParserState.TEXT;
					tagArgs.clear();
				} 
				else {
					throw new SmartScriptParserException("invalid");
				}
				break;
				//ako smo ucitali 4 vrijednosti u for-tagu, treba zavrsit
			case FOR_TAG_4: 
				if (current instanceof TokenSpecial && 
				((TokenSpecial)current).getType() == TokenSpecial.Type.END_TAG) {
					ForLoopNode newNode = new ForLoopNode((TokenVariable)tagArgs.get(0), 
								(Token)tagArgs.get(1), (Token)tagArgs.get(2), (Token)tagArgs.get(3));
					((Node)stack.peek()).addChildNode(newNode);
					stack.push(newNode);
					state = ParserState.TEXT;
					tagArgs.clear();
				}
				else {
					throw new SmartScriptParserException("invalid");
				}
				break;
				//ako smo u = tagu
			case ECHO_TAG:
				//nesto legalno
				if(current instanceof TokenVariable || 
					current instanceof TokenOperator ||
					current instanceof TokenFunction || 
					current instanceof TokenConstantInteger ||
					current instanceof TokenConstantDouble ||
					current instanceof TokenString) {
					tagArgs.add(current);
				}
				//dosli smo do kraja taga =
				else if(current instanceof TokenSpecial && 
					((TokenSpecial)current).getType() == TokenSpecial.Type.END_TAG) {
					Token[] newTokens = new Token[tagArgs.size()];
					for(int i = 0; i < newTokens.length; ++i) {
						newTokens[i] = (Token)tagArgs.get(i);
					}
					EchoNode newNode = new EchoNode(newTokens);
					((Node)stack.peek()).addChildNode(newNode);
					tagArgs.clear();
					state = ParserState.TEXT;
				}
				else {
					throw new SmartScriptParserException("invalid");
				}
				break;
				//ucitali smo END-mora slijediti specijalni token-zatvaranje taga
			case END_TAG:
				if(current instanceof TokenSpecial && 
					((TokenSpecial)current).getType() == TokenSpecial.Type.END_TAG
					&& stack.size() > 1) {
					stack.pop();
					state = ParserState.TEXT;
				}
				else {
					throw new SmartScriptParserException("invalid");
				}
				break;
			}
		}
		//na stacku ostalo nesto osim DocumentNode
		if(stack.size() > 1) {
			throw new SmartScriptParserException("invalid");
		}
		return (DocumentNode)stack.peek();
	}
	/**
	 * Metoda pomocu koje provodimo leksicku analizu Stringa
	 * @param document String kojeg obradujemo
	 * @return Niz u kojem su spremljeni Tokeni dobiveni analizom
	 */
	static private ArrayBackedIndexedCollection tokenize(String document) {
		int insideTag = 0;
		TokenType current = TokenType.TEXT;
		//sadrzaj trenutnog tokena		
		String tokenValue = "";
		ArrayBackedIndexedCollection tokens = new ArrayBackedIndexedCollection();
		for(int i = 0; i < document.length(); ++i) {
			char tmp = document.charAt(i);
			boolean end = i == document.length() - 1;
			switch(current) {
			//ako smo u tekstu
			case TEXT:
				//nema spec. znakova-ostajemo u tekstu
				if(tmp == '\\') {
					if(end || (document.charAt(i + 1) != '\\'
					   && document.charAt(i + 1) != '{')) {
						tokenValue += tmp;
					}
					//inace ubacimo u tokenValue ono sto slijedi nakon \
					else {
						tokenValue += document.charAt(i + 1);
						++i;
					}
				}
				else if(tmp == '{') {
					if(end || document.charAt(i + 1) != '$') {
						tokenValue += tmp;
					}
					else {
						if(!tokenValue.equals("")) {
							tokens.add(new TokenText(tokenValue));
						}
						tokens.add(new TokenSpecial(TokenSpecial.Type.START_TAG));
						++i;
						tokenValue = "";
						current = TokenType.BLANK;
						++insideTag;
					}
				}
				else{
					tokenValue += tmp;
				}
				break;
			case VARIABLE:
				//regularno u varijabli nakon 1.znaka
				if(Character.isDigit(tmp) || Character.isLetter(tmp) || tmp == '_') {
					tokenValue += tmp;
				}
				else {
					tokens.add(new TokenVariable(tokenValue));
					--i;
					tokenValue = "";
					current = TokenType.BLANK;
				}
				break;
			case INTEGER:
				if(Character.isDigit(tmp)) {
					tokenValue += tmp;
				}
				//dosli smo do tocke-broj je decimalni
				else if(tmp == '.') {
					tokenValue += tmp;
					current = TokenType.DOUBLE;
				}
				//pretpostavljam da ne moze slovo biti naljepljeno na broj(nije strogo definirano u zad)
				else if(Character.isLetter(tmp)) {
					throw new SmartScriptParserException("invalid document");
				}
				//ako nije nista od navedenog, vratimo se opet na taj znak i provjerimo sve slucajeve
				//navedeni su u BLANK-da se smanji duplikacija koda
				else {
					tokens.add(new TokenConstantInteger(Integer.parseInt(tokenValue)));
					--i;
					current = TokenType.BLANK;
					tokenValue = "";
				}
				break;
			case DOUBLE:
				if(tmp == '.') {
					throw new SmartScriptParserException("invalid document");
				}
				if(Character.isDigit(tmp)) {
					tokenValue += tmp;
				}
				else if(Character.isLetter(tmp)) {
					throw new SmartScriptParserException("invalid document");
				}
				else {
					tokens.add(new TokenConstantDouble(Double.parseDouble(tokenValue)));
					--i;
					current = TokenType.BLANK;
					tokenValue = "";
				}
				break;
			case STRING:
				if(tmp == '\\') {
					if(end || (document.charAt(i + 1) != '\\') && 
					  (document.charAt(i + 1) != '"') && (document.charAt(i + 1) != 'n')
					  && (document.charAt(i + 1) != 't') && (document.charAt(i + 1) != 'r')) {
						tokenValue += tmp;
					}
					else {
						char next = document.charAt(i + 1);
						switch(next) {
						case 'n': tokenValue += '\n'; break;
						case 't': tokenValue += '\t'; break;
						case 'r': tokenValue += '\r'; break;
						case '"': tokenValue += '"'; break;
						case '\\': tokenValue += '\\'; break;
						}
						++i;
					}
				}
				else if(tmp == '"') {
					tokens.add(new TokenString(tokenValue));
					current = TokenType.BLANK;
					tokenValue = "";
				}
				else {
					tokenValue += tmp;
				}
				break;
			case FUNCTION:
				if(Character.isLetter(tmp)) {
					tokenValue += tmp;
				}
				else {
					tokens.add(new TokenFunction(tokenValue));
					--i;
					current = TokenType.BLANK;
					tokenValue = "";
				}
				break;
			case BLANK:
				if(Character.isWhitespace(tmp)) {
					continue;
				}
				if (Character.isLetter(tmp)) {
					current = TokenType.VARIABLE;
					tokenValue += tmp;
				} 
				else if (Character.isDigit(tmp)) {
					current = TokenType.INTEGER;
					tokenValue += tmp;
				}
				else if (tmp == '@') {
					current = TokenType.FUNCTION;
				}
				else if(tmp == '"') {
					current = TokenType.STRING;
				}
				else if(tmp == '=') {
					tokens.add(new TokenEquals());
				}
				//ako je operator zalijepljen na broj sprijeda-to  je broj s predznakom
				else if(!end && (tmp == '+' || tmp == '-') && 
						Character.isDigit(document.charAt(i + 1))) {
					tokenValue = document.substring(i, i + 2);
					current = TokenType.INTEGER;
					++i;
				}
				//gledamo operator kao binarni operator
				else if(tmp == '+' || tmp == '-' || tmp == '/' || tmp == '*' 
						|| tmp == '%') {
					tokens.add(new TokenOperator(Character.toString(tmp)));
				}
				//izlazimo iz taga
				else if(tmp == '$' && !end && document.charAt(i + 1) == '}') {
					tokens.add(new TokenSpecial(TokenSpecial.Type.END_TAG));
					--insideTag;
					++i;
					//ako smo izasli iz svih tagova, u tekstu smo
					if(insideTag == 0) {
						current = TokenType.TEXT;
					}
				}
				else {
					throw new SmartScriptParserException("invalid document");
				}
				break;
			}
		}
		//sto je na kraju ostalo u tekstu
		switch(current) {
		case INTEGER: 
			tokens.add(new TokenConstantInteger(Integer.parseInt(tokenValue)));
			break;
		case DOUBLE:
			tokens.add(new TokenConstantDouble(Double.parseDouble(tokenValue)));
			break;
		case VARIABLE:
			tokens.add(new TokenVariable(tokenValue));
			break;
		case BLANK:
			break;
		case TEXT:
			if(!tokenValue.equals("")) {
				tokens.add(new TokenText(tokenValue));
			}
			break;
		case STRING:
			throw new SmartScriptParserException("invalid document");
		case FUNCTION:
			tokens.add(new TokenFunction(tokenValue));
			break;
		}
		return tokens;
	}
	/**
	 * Metoda provjerava je li u str zapisan broj
	 * @param str String kojeg provjeravamo
	 * @return true ako je str broj, false inace
	 */
	static private boolean isNumber(String str)  {  
		try {  
			Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe) {  
			return false;  
	    }  
		return true;  
	}
}
