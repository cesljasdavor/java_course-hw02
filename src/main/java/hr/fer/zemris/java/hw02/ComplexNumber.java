package hr.fer.zemris.java.hw02;

/**
 * Razred koji predstavlja jedan kompleksni broj. Razred nudi klasične operacije
 * za rad sa kompleksnim brojevima. Bitno je napomenuti da sve metode koje
 * vraćaju {@link ComplexNumber} ne mijenjaju objekte iz kojih je novi primjerak
 * nastao.Metode za rad sa kompleksnim brojevima su sljedeće :
 * <ul>
 * <li><code>{@link #getReal()}</code></li>
 * <li><code>{@link #getImaginary()} </code></li>
 * <li><code>{@link #getMagnitude()} </code></li>
 * <li><code>{@link #getAngle()}</code></li>
 * <li><code>{@link #add(ComplexNumber)} </code></li>
 * <li><code>{@link #sub(ComplexNumber)}</code></li>
 * <li><code>{@link #mul(ComplexNumber)} </code></li>
 * <li><code>{@link #div(ComplexNumber)}</code></li>
 * <li><code>{@link #power(int)} </code></li>
 * <li><code>{@link #root(int)} </code></li>
 * </ul>
 * 
 * Razred nudi nekoliko metoda tvornica:
 * <ul>
 * <li><code>{@link #fromReal(double)}</code></li>
 * <li><code>{@link #fromImaginary(double)} </code></li>
 * <li><code>{@link #fromMagnitudeAndAngle(double, double)} </code></li>
 * <li><code>{@link #parse(String)}</code></li>
 * </ul>
 * 
 * Razred također nudi i jedan konstruktor:
 * <code>{@link #ComplexNumber(double, double)}</code>
 * 
 * @author Davor Češljaš
 */
public class ComplexNumber {

	/**
	 * Predstavlja znak "+" koji se koristi prilikom parsiranja. Vidi
	 * {@link #recursiveParse(String[], int)} i
	 * {@link #parseImaginary(StringBuilder)}
	 */
	private static final String PLUS_CHAR = "+";

	/**
	 * Predstavlja znak "-" koji se koristi prilikom parsiranja. Vidi
	 * {@link #recursiveParse(String[], int)} i
	 * {@link #parseImaginary(StringBuilder)}
	 */
	private static final String MINUS_CHAR = "-";

	/**
	 * Predstavlja znak "i" koji se koristi prilikom parsiranja. Vidi
	 * {@link #recursiveParse(String[], int)}
	 */
	private static final String IMAGINARY_UNIT = "i";

	/**
	 * Predstavlja kompleksni broj 0 koji je neutralni element u metodi
	 * {@link #recursiveParse(String[], int)}
	 */
	private static final ComplexNumber ZERO = new ComplexNumber(0, 0);

	/** Realna komponenta kompleksnog broja */
	private double real;

	/** Imaginarna komponenta kompleksong broja */
	private double imaginary;

	/**
	 * Konstruktor koji inicijalizira realnu i imaginarnu komponentu kompleksnog
	 * broja.
	 *
	 * @param real
	 *            realna komponenta kompleksnog broja
	 * @param imaginary
	 *            imaginarna komponenta kompleksnog broja
	 */
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	/**
	 * Dohvaća realnu komponentu kompleksnog broja.
	 *
	 * @return realnu komponentu kompleksnog broja.
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Dohvaća imaginarnu komponentu kompleksnog broja.
	 *
	 * @return imaginarnu komponentu kompleksnog broja.
	 */
	public double getImaginary() {
		return imaginary;
	}

	/**
	 * Računa i dohvaća magnitudu kompleksnog broja iz pretvorbe u polarni
	 * oblika.
	 *
	 * @return magnitudu kompleksnog broja
	 */
	public double getMagnitude() {
		return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
	}

	/**
	 * Računa i dohvaća kut kompleksnog broja iz pretvorbe u polarni oblika.
	 *
	 * @return kut kompleksnog broja
	 */
	public double getAngle() {
		double angle = Math.atan2(imaginary, real);
		return angle < 0 ? (angle + 2 * Math.PI) : angle;
	}

	/**
	 * Zbraja dva kompleksna broja i rezultat vraća u novom primjerku razreda
	 * {@link ComplexNumber}
	 *
	 * @param c
	 *            kompleksni broj koji se zbraja s trenutnim
	 * 
	 * @return novi primjerak razreda {@link ComplexNumber} koji predstavlja
	 *         zbroj
	 */
	public ComplexNumber add(ComplexNumber c) {
		return new ComplexNumber(this.real + c.real, this.imaginary + c.imaginary);
	}

	/**
	 * Oduzima dva kompleksna broja i rezultat vraća u novom primjerku razreda
	 * {@link ComplexNumber}
	 *
	 * @param c
	 *            kompleksni broj koji predstavlja umanjitelj
	 * 
	 * @return novi primjerak razreda {@link ComplexNumber} koji predstavlja
	 *         razliku
	 */
	public ComplexNumber sub(ComplexNumber c) {
		return new ComplexNumber(this.real - c.real, this.imaginary - c.imaginary);
	}

	/**
	 * Množi dva kompleksna broja i rezultat vraća u novom primjerku razreda
	 * {@link ComplexNumber}
	 *
	 * @param c
	 *            kompleksni broj koji predstavlja množitelj
	 * 
	 * @return novi primjerak razreda {@link ComplexNumber} koji predstavlja
	 *         umnožak
	 */
	public ComplexNumber mul(ComplexNumber c) {
		return new ComplexNumber(real * c.real - imaginary * c.imaginary, real * c.imaginary + imaginary * c.real);
	}

	/**
	 * Dijeli dva kompleksna broja i rezultat vraća u novom primjerku razreda
	 * {@link ComplexNumber}
	 *
	 * @param c
	 *            kompleksni broj koji predstavlja djelitelj
	 * 
	 * @return novi primjerak razreda {@link ComplexNumber} koji predstavlja
	 *         količnik
	 */
	public ComplexNumber div(ComplexNumber c) {
		if (c.equals(ZERO)) {
			throw new IllegalArgumentException("Dijeljenje s 0!");
		}
		double numeratorReal = real * c.real + imaginary * c.imaginary;
		double numeratorImaginary = imaginary * c.real - real * c.imaginary;
		double denominator = Math.pow(c.real, 2) + Math.pow(c.imaginary, 2);

		return new ComplexNumber(numeratorReal / denominator, numeratorImaginary / denominator);
	}

	/**
	 * Potencira trenutni kompleksni broj na <b>n</b> i rezultat vraća u novom
	 * primjerku razreda {@link ComplexNumber}. Potencija <b>n >= 0</b>
	 *
	 * @param n
	 *            potencija kompleksnog broja
	 * 
	 * @return novi primjerak razreda {@link ComplexNumber} koji predstavlja
	 *         rezultat potenciranja
	 * 
	 * @throws IllegalArgumentException
	 *             ukoliko je potencija <b>n < 0</b>
	 */
	public ComplexNumber power(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Eksponent ne može biti mani od nule");
		}
		double powMagnitude = Math.pow(getMagnitude(), n);
		double angle = getAngle();
		double realPart = powMagnitude * Math.cos(n * angle);
		double imaginaryPart = powMagnitude * Math.sin(n * angle);
		return new ComplexNumber(realPart, imaginaryPart);
	}

	/**
	 * Korjenuje trenutni kompleksni broj sa <b>n</b> i rezultat vraća u novom
	 * primjerku razreda {@link ComplexNumber}. Korijen <b>n > 0</b>
	 *
	 * @param n
	 *            korijen kompleksnog broja
	 * 
	 * @return polje svih <b>n</b> korijena ovog kompleksnog broja
	 * 
	 * @throws IllegalArgumentException
	 *             ukoliko je korijen <b>n <= 0</b>
	 */
	public ComplexNumber[] root(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Korijen ne može biti " + n);
		}
		ComplexNumber[] roots = new ComplexNumber[n];
		double rootMagnitude = Math.pow(getMagnitude(), 1.0 / n);
		for (int k = 0; k < n; k++) {
			roots[k] = new ComplexNumber(rootMagnitude * getRootAngle(true, k, n),
					rootMagnitude * getRootAngle(false, k, n));
		}
		return roots;
	}

	/**
	 * Pomoćna metoda za računanje kuta korijena. Koristi je {@link #root(int)}.
	 * Moguće je računati realni ili imaginarni dio
	 *
	 * @param real
	 *            <code><b>true</b></code> ako tražimo realan dio korijena,
	 *            <code><b>false</b></code>inače
	 * @param rootNumber
	 *            broj korijena
	 * @param root
	 *            koji se korijen računa (n u metodi {@link #root(int)})
	 * @return kut korijena
	 */
	private double getRootAngle(boolean real, int rootNumber, int root) {
		double argument = (getAngle() + 2 * rootNumber * Math.PI) / root;
		return real ? Math.cos(argument) : Math.sin(argument);
	}

	@Override
	public String toString() {
		return String.format("z = %f%s %fi", real, imaginary >= 0 ? " +" : "", imaginary);
	}

	/**
	 * Stvara novi primjerak razreda {@link ComplexNumber} samo iz realnog
	 * dijela.
	 *
	 * @param real
	 *            realni dio kompleksnog broja
	 * @return primjerak razreda {@link ComplexNumber} koji ima samo realan dio
	 */
	public static ComplexNumber fromReal(double real) {
		return new ComplexNumber(real, 0);
	}

	/**
	 * Stvara novi primjerak razreda {@link ComplexNumber} samo iz imaginarnog
	 * dijela.
	 *
	 * @param imaginary
	 *            imaginarni dio kompleksnog broja
	 * @return primjerak razreda {@link ComplexNumber} koji ima samo imaginarni
	 *         dio
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		return new ComplexNumber(0, imaginary);
	}

	/**
	 * Stvara novi primjerak razreda {@link ComplexNumber} iz magnitude i kuta.
	 * Broj će se pretvoriti u pravokutne koordinate
	 *
	 * @param magnitude
	 *            magnituda kompleksnog broja
	 * @param angle
	 *            kut kompleksnog broja
	 * @return primjerak razreda {@link ComplexNumber} nastao iz parametara
	 *         metode.
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		return new ComplexNumber(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
	}

	/**
	 * Parsira predani argument <b>s</b> u primjerak razreda
	 * {@link ComplexNumber}. Ukoliko nije moguće parsirati predani argument,
	 * metoda baca {@link IllegalArgumentException} ili
	 * {@link NumberFormatException}. Primjeri ulaza:
	 * 
	 * <pre>
	 * "3.51", "-3.17", "-2.71i", "i", "1", "-2.71-3.15i"
	 * </pre>
	 * 
	 * @param s
	 *            {@link String} koji se pokušava parsirati
	 * @return primjerak razreda {@link ComplexNumber} koji je isparsiran
	 *
	 * @throws IllegalArgumentException
	 *             ako metoda {@link String#isEmpty()} nad <b>s</b> vrati
	 *             <code><b>true</b></code>
	 * @throws NumberFormatException
	 *             ukoliko se iz <b>s</b> ne mogu dobiti isključivo brojevi
	 */
	public static ComplexNumber parse(String s) {
		if (s.trim().isEmpty()) {
			throw new IllegalArgumentException("Unijeli ste prazni niz znakova");
		}
		String[] splitted = s.split("");
		return recursiveParse(splitted, 0);
	}

	/**
	 * Pomoćna metoda koja rekurzivno parsira string predan metodi {@link #parse(String)}
	 *
	 * @param splitted
	 *            polje stringova koje treba isparsirati
	 * @param index
	 *            trenutni index do kojeg je parsiranje uspjelo
	 * @return konačno rješenje kao primjerak razreda {@link ComplexNumber}
	 * @throws NumberFormatException
	 *             ukoliko se iz <b>splitted</b> ne mogu dobiti isključivo brojevi
	 */
	private static ComplexNumber recursiveParse(String[] splitted, int index) {
		// kako bi se krenuo vračati rekurzivno potrebno je pročitati sve i
		// vratiti neutralan element
		if (splitted.length <= index) {
			return ZERO;
		}

		StringBuilder sb = new StringBuilder();
		String currentChar = splitted[index];
		// ovdje će biti spremljen broj koji se u ovom pozivu isprasira
		ComplexNumber number = null;
		// idem znak po znak i spajam string
		while ((!currentChar.equals(PLUS_CHAR) && !currentChar.equals(MINUS_CHAR) || sb.toString().isEmpty())) {
			// ako je trenutni znak jednak "i" tada bi ono prije njega trebalo
			// biti imaginarni dio broja
			if (currentChar.equals(IMAGINARY_UNIT)) {
				// preskoči slovo i
				index++;
				// postoji nekoliko slučajeva pa sam to izolirao u metodu
				number = parseImaginary(sb);
				break;
			}
			// ako je string prazan ili je jednak razmaku preskoči (parser
			// javlja grešku za razmak između npr + i broja)
			if (!currentChar.trim().isEmpty()) {
				sb.append(currentChar);
			}

			index++;
			// provjeri jesmo li došli do kraja
			if (index == splitted.length) {
				break;
			}
			currentChar = splitted[index];
		}

		// broj nije imaginaran dakle broj je realan, ako nije realan znači da
		// NIJE broj pa ide NumberFormatException
		if (number == null) {
			number = fromReal(Double.parseDouble(sb.toString()));
		}
		return number.add(recursiveParse(splitted, index));
	}

	/**
	 * Metoda koja parsira imaginarni dio broja
	 *
	 * @param sb
	 *            primjerak razreda {@link StringBuilder} koji se koristi pri parsiranju
	 * @return imaginarni dio kompleksnog broja koji je sadržan u <b>sb</b>.
	 * 
	 * @throws NumberFormatException   ukoliko se u <b>sb</b> ne nalazi broj 
	 */
	private static ComplexNumber parseImaginary(StringBuilder sb) {
		String possibleNumber = sb.toString();
		int lastIndex = sb.toString().length() - 1;
		if (possibleNumber.lastIndexOf(PLUS_CHAR) == lastIndex || possibleNumber.lastIndexOf(MINUS_CHAR) == lastIndex) {
			return fromImaginary(Double.parseDouble(sb.append(1).toString()));
		} else if (!sb.toString().isEmpty()) {
			return fromImaginary(Double.parseDouble(possibleNumber));
		} else {
			return fromImaginary(1);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(imaginary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(real);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplexNumber other = (ComplexNumber) obj;
		if (Math.abs(imaginary - other.imaginary) > 0.01)
			return false;
		if (Math.abs(real - other.real) > 0.01)
			return false;
		return true;
	}
}
