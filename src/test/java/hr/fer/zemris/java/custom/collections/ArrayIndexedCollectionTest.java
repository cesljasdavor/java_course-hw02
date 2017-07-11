package hr.fer.zemris.java.custom.collections;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;

public class ArrayIndexedCollectionTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void dodajNull() {
		exception.expect(IllegalArgumentException.class);
		ArrayIndexedCollection collection = new ArrayIndexedCollection(1);
		collection.add(null);
	}
	
	@Test
	public void dodajElement() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(1);
		collection.add("Test");
		assertEquals(1, collection.size());
	}
	
	@Test
	public void realokacijaPoljaUspjela() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(1);
		collection.add("Test");
		collection.add("Test");
		collection.add("Test");
		assertEquals(3, collection.size());
	}
	
	@Test
	public void dohvacanjeIspod0() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(1);
		collection.add("Test");
		
		exception.expect(IndexOutOfBoundsException.class);
		collection.get(-1);
	}
	
	@Test
	public void dohvacanjeIznadNajvecegIndexa() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(1);
		collection.add("Test");
		
		exception.expect(IndexOutOfBoundsException.class);
		collection.get(2);
	}
	
	@Test
	public void dohvacanjeIspravno() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(1);
		collection.add("Test");
		assertEquals("Test", collection.get(0));
	}
	
	@Test
	public void ispravnoOciscenaKolekcija() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.clear();
		assertEquals(0, collection.size());
	}
	
	@Test
	public void umetanjeUspjelo() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.insert(6, 2);
		assertEquals(6, collection.get(2));
	}
	
	@Test
	public void umetanjeNijeUspjelo() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		exception.expect(IllegalArgumentException.class);
		collection.insert(6, 7);
	}
	
	@Test
	public void indexPronaden() {
			ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
			for(int i = 0; i < 5; i++) {
				collection.add(i);
			}
			assertEquals(3, collection.indexOf(3));
	}
	
	@Test
	public void indexNijePronaden() {
			ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
			for(int i = 0; i < 5; i++) {
				collection.add(i);
			}
			assertEquals(-1, collection.indexOf(8));
	}
	
	@Test
	public void umetanjeRadiDodavanjeNaKraj() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.insert(6, 5);
		assertEquals(6, collection.get(5));
	}
		
	@Test
	public void brisanjeUspjelo() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.remove(4);
		assertEquals(4 ,collection.size());
	}
	
	@Test
	public void brisanjeNijeUspjelo() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		exception.expect(IndexOutOfBoundsException.class);
		collection.remove(10);
	}
}
