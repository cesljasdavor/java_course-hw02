package hr.fer.zemris.java.hw02;

import static org.junit.Assert.*;

import org.junit.Rule;

import static hr.fer.zemris.java.hw02.ComplexNumber.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ComplexNumberTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void provjeraStvaranjaSamoRealniDio() {
		ComplexNumber c = fromReal(5.256);
		assertEquals(5.256, c.getReal(), 0.01);
	}

	@Test
	public void provjeraStvaranjaSamoImaginarniDio() {
		ComplexNumber c = fromImaginary(5.256);
		assertEquals(5.256, c.getImaginary(), 0.01);
	}

	@Test
	public void provjeraPretvorbeUPolarneKoordinate() {
		ComplexNumber c = new ComplexNumber(5, 7);
		assertEquals(8.60, c.getMagnitude(), 0.01);
		assertEquals(0.95, c.getAngle(), 0.01);
	}

	@Test
	public void provjeraPretvorbeUPravokutneKoordinate() {
		ComplexNumber c = fromMagnitudeAndAngle(8.60, 0.95);
		assertEquals(5, c.getReal(), 0.01);
		assertEquals(7, c.getImaginary(), 0.01);
	}

	@Test
	public void provjeraMetodeZaZbrajanje() {
		ComplexNumber c1 = new ComplexNumber(5.20, 3.50);
		ComplexNumber c2 = new ComplexNumber(3, 2.50);
		assertEquals(c1.add(c2), new ComplexNumber(8.20, 6));
	}

	@Test
	public void provjeraMetodeZaOduzimanje() {
		ComplexNumber c1 = new ComplexNumber(5.20, 3.50);
		ComplexNumber c2 = new ComplexNumber(3, 2.50);
		assertEquals(c1.sub(c2), new ComplexNumber(2.20, 1));
	}

	@Test
	public void provjeraMetodeZaMnozenje() {
		ComplexNumber c1 = new ComplexNumber(5.256, 3);
		ComplexNumber c2 = new ComplexNumber(2, -1);
		assertEquals(c1.mul(c2), new ComplexNumber(13.512, 0.744));
	}

	@Test
	public void provjeraMetodeZaDijelenje() {
		ComplexNumber c1 = new ComplexNumber(9.564, -7.45);
		ComplexNumber c2 = new ComplexNumber(3, -5);
		assertEquals(c1.div(c2), new ComplexNumber(1.939, 0.749));
	}

	@Test
	public void greskaMetodeZaDijelenje() {
		ComplexNumber c1 = new ComplexNumber(9.564, -7.45);
		ComplexNumber c2 = new ComplexNumber(0, 0);
		exception.expect(IllegalArgumentException.class);
		c1.div(c2);
	}

	@Test
	public void provjeraMetodeZaPotenciju() {
		ComplexNumber c1 = new ComplexNumber(1.57, -2);
		assertEquals(c1.power(5), new ComplexNumber(-19.656, 104.435));

	}

	@Test
	public void greskaMetodeZaPotenciju() {
		ComplexNumber c1 = new ComplexNumber(1.57, -2);
		exception.expect(IllegalArgumentException.class);
		c1.power(-1);
	}

	@Test
	public void provjeraMetodeZaKorijen() {
		ComplexNumber c1 = new ComplexNumber(1.57, -2);
		ComplexNumber[] roots = new ComplexNumber[] { 
				new ComplexNumber(-0.300, +1.3314),
				new ComplexNumber(-1.0029, -0.9258),
				new ComplexNumber(1.303, -0.4056)
		};
		ComplexNumber[] myRoots  = c1.root(3);
		assertArrayEquals(roots, myRoots);
 	}
	
	@Test
	public void greskaKorijenNula() {
		ComplexNumber c1 = new ComplexNumber(1.57, -2);
		exception.expect(IllegalArgumentException.class);
		c1.root(0);
 	}
	
	@Test
	public void greskaKorijenManjiOdNula() {
		ComplexNumber c1 = new ComplexNumber(1.57, -2);
		exception.expect(IllegalArgumentException.class);
		c1.root(-2);
	}
	
	@Test
	public void parsiranjePrazanString() throws Exception {
		exception.expect(IllegalArgumentException.class);
		parse("");
	}
	
	@Test
	public void parsiranjePozitivanRealanDio() throws Exception {
		ComplexNumber c = parse("3.51");
		assertEquals(3.51, c.getReal(), 0.01);
	}
	
	@Test
	public void parsiranjeNegativanRealanDio() throws Exception {
		ComplexNumber c = parse("-3.17");
		assertEquals(-3.17, c.getReal(), 0.01);
	}
	
	@Test
	public void parsiranjeNegativanImaginaranDio() throws Exception {
		ComplexNumber c = parse("-2.71i");
		assertEquals(-2.71, c.getImaginary(), 0.01);
	}
	
	@Test
	public void parsiranjeImaginarnaJedinica() throws Exception {
		ComplexNumber iNeg = parse("-i");
		assertEquals(-1, iNeg.getImaginary(), 0.01);
		ComplexNumber iPoz = parse("i");
		assertEquals(1, iPoz.getImaginary(), 0.01);
	}
	
	@Test
	public void parsiranjeKompleksanBroj() throws Exception {	
		assertEquals(parse("-2.71-3.15i"), new ComplexNumber(-2.71,-3.15));
	}
	
	@Test
	public void parsiranjeKompleksanBrojSlozenije() throws Exception {	
		assertEquals(parse("-2.71-3.15i + 10 +2.546i + 3.256-8i"), new ComplexNumber(10.546,-8.604));
	}
}
