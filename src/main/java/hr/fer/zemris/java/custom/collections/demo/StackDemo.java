package hr.fer.zemris.java.custom.collections.demo;

import java.util.Scanner;

import hr.fer.zemris.java.custom.collections.EmptyStackException;
import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Razred koji predstavlja program za računanje izraza u postfiksnom obliku.
 * Razred sve argumente prima kroz naredbeni redak. Razred također služi za
 * demonstraciju razreda {@link ObjectStack} i iznimke
 * {@link EmptyStackException}
 * 
 * @author Davor Češljaš
 */
public class StackDemo {

	/**
	 * Metoda koja počinje izvođenje programa
	 *
	 * @param args
	 *            u ovom programu potrebno je cijeli izraz unijeti unutar
	 *            navodnika tako da veličina polja <b>args</b> bude 1
	 */
	public static void main(String[] args) {
		if (args.length > 1) {
			System.out.println(
					"Unijeli ste prevelik broj argumenata. Molimo Vas sve argumente pošaljite unutar navodnika");
			return;
		}

		ObjectStack stack = new ObjectStack();
		try (Scanner sc = new Scanner(args[0])) {
			doTheMath(stack, sc);
			printResult(stack);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (EmptyStackException e) {
			System.out.println("Unijeli ste prevelik broj operacija, a premalo brojeva");
		}
	}

	/**
	 * Ispisuje rezultat izračunatog izraza ili ispisuje poruku da je unesen
	 * prevelik broj brojeva, a premalo operacija
	 *
	 * @param stack
	 *            primjerak stoga sa kojeg se vadi konačni rezultat.
	 */
	private static void printResult(ObjectStack stack) {
		if (stack.size() == 1) {
			System.out.println("Rezultat zadanog izraza je " + stack.pop());
		} else {
			System.out.println("Unijeli ste preveliki broj brojeva, a premalo operatora");
		}
	}

	/**
	 * Vrši operacije stavljanja brojeva na stog i računanje izraza ovisno o
	 * tome što vrati {@link Scanner#next()}
	 *
	 * @param stack
	 *            stog na koji se spremaju brojevi
	 * @param sc
	 *            primjerak razreda {@link Scanner} pomoću kojeg čitamo izraz
	 */
	private static void doTheMath(ObjectStack stack, Scanner sc) {
		while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				// autoboxing u Integer
				stack.push(sc.nextInt());
			} else {
				compute(stack, sc.next());
			}
		}

	}

	/**
	 * Uzima dva broja sa stoga i računa jednu od operacije (+,-.*,/ ili %)
	 *
	 * @param stack
	 *            stog s kojeg se uzimaju argumenti
	 * @param input
	 *            mora biti jedan od sljedećih operacija +,-.*,/ ili %
	 * 
	 * @throws IllegalArgumentException
	 *             ukoliko je <b>input</b> različit od +,-.*,/ ili %
	 */
	private static void compute(ObjectStack stack, String input) {
		// ako je na stogu manje od 2 argumenta sigurno će se throwati
		// EmtyStackException
		int result;
		// auto deboxing
		int secondArgument = (Integer) stack.pop();
		int firstArgument = (Integer) stack.pop();
		switch (input) {
		case "+":
			result = firstArgument + secondArgument;
			break;
		case "-":
			result = firstArgument - secondArgument;
			break;
		case "/":
			checkDevideByZero(secondArgument);
			result = firstArgument / secondArgument;
			break;
		case "*":
			result = firstArgument * secondArgument;
			break;
		case "%":
			checkDevideByZero(secondArgument);
			result = firstArgument % secondArgument;
			break;
		default:
			throw new IllegalArgumentException(String.format("'%s' nije valjana operacija", input));
		}
		stack.push(result);
	}

	/**
	 * Pomoćna metoda koja provjerava je li drugi argument operacije jednak 0.
	 * Ova metoda koristi se obično kod operacije dijeljenja ili ostatka pri dijeljenju  
	 *
	 * @param secondArgument
	 *            obično djelitelj
	 *            
	 * @throws IllegalArgumentException 
	 * 		ukoliko je <b>secondArgument</b> jednak nuli           
	 */
	private static void checkDevideByZero(int secondArgument) {
		if (secondArgument == 0) {
			throw new IllegalArgumentException("Djelitelj je jednak 0!");
		}
	}

}
