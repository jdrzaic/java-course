package hr.fer.zemris.java.custom.collections.demo;
import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Razred demonstrira racunanje izraza zadanih u postfiksu
 * Izraz se prima kao jedna jedan string, dakle, unutar " "
 * Izraz se zadaje putem naredbenog retka
 * @author Jelena Drzaic
 *
 */
public class StackDemo {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa
	 * Argumenti su objasnjeni u nastavku;
	 * @param args argumenti naredbenog retka
	 */
	public static void main(String[] args) {
		
		/*rezultat racunanja izraza*/
		double result = 0;
		
		/*komponente dobivene od dobivenog postfix izraza*/
		String[] components = new String[0];
		
		if(args.length == 1) {
			/*izraz razdvajamo na mjestu jednog ili vise razmaka*/
			components = args[0].split("\\s+");
		}
		else {
			System.err.println("wrong number of arguments");
			System.exit(1);
		}
		/*stack na kojem se provodi racunanje*/
		ObjectStack evaluate = new ObjectStack();
		for(int position = 0; position < components.length; ++position) {
			if(isNumber(components[position])) {
				evaluate.push(components[position]);
			}
			else {
				double number1 = 0;
				if(isNumber((String)evaluate.peek())) { 
					number1 = Double.parseDouble((String)evaluate.peek());
				//System.out.println(number1);
				}
				else  {
					System.err.println("mistake in expression given");
					System.exit(1);
				}
				evaluate.pop();
				//double number2 = Double.parseDouble((String)evaluate.peek());
				//System.out.println(number2);
				double number2 = 0;
				if(isNumber((String)evaluate.peek())) { 
					number2 = Double.parseDouble((String)evaluate.peek());
				//System.out.printl(number1);
				}
				else  {
					System.err.println("mistake in expression given");
					System.exit(1);
				}
				evaluate.pop();
				double temporary = (operation(number2, number1, components[position]));
				evaluate.push(Double.toString(temporary));
			}
		}
		if(evaluate.size() == 1) {
			result = Double.parseDouble((String)evaluate.peek());
			evaluate.pop();
		}
		else {
			System.err.println("mistake in expression given");
			System.exit(1);
		}
		System.out.println("Expression evaluates to " + result + ".");
		
	}
	
	/**
	 * Metoda provjerava je li proslijedena varijabla tipa String neki
	 * regularan broj
	 * @param str String kojeg provjeravamo
	 * @return true ako je String str reprezentacija nekog broja,
	 * false inace 
	 */
	public static boolean isNumber(String str)  {  
		try {  
			Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe) {  
			return false;  
	    }  
		return true;  
	}
	
	/**
	 * Metoda koja primjenjuje operaciju operation na brojevima
	 * number1 i number2
	 * U slucaju dijeljenja s nulom, program se prekida uz ispis
	 * pogreske o gresci
	 * @param number1 prvi operand operacije
	 * @param number2 drugi operand operacije
	 * @param operation operacija koju provodimo - jedna od sljedecih:
	 * +, *, -, /, %(ostatak kod cijelobrojnog dijeljenja)
	 * @return rezultat provedene operacije
	 */
	private static double operation(double number1, double number2, String operation) {
		double result = 0;
		if((operation.equals("/") || operation.equals("%")) && number2 == 0) {
			System.err.println("Dividing by zero not defined");
			System.exit(1);
		}
		
		switch(operation) {
			case "+": result = number1 + number2;
				break;
			case "-": result = number1 - number2;
				break;
			case "*": result = number1 * number2;
				break;
			case "/": result = number1 / number2;
				break;
			case "%": result = number1 % number2;
				break;
		}
		return result;
	} 
}
