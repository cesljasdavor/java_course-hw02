package hr.fer.zemris.java.custom.collections;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import hr.fer.zemris.java.custom.collections.LinkedListIndexedCollection;

public class LinkedListIndexedCollectionTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void dodajNull() {
		exception.expect(IllegalArgumentException.class);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(null);
	}
	
	@Test
	public void dodajElement() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add("Test");
		assertEquals(1, collection.size());
	}
	
	@Test
	public void dohvacanjeIspod0() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add("Test");
		
		exception.expect(IndexOutOfBoundsException.class);
		collection.get(-1);
	}
	
	@Test
	public void dohvacanjeIznadNajvecegIndexa() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add("Test");
		
		exception.expect(IndexOutOfBoundsException.class);
		collection.get(2);
	}
	
	@Test
	public void dohvacanjeIspravno() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add("Test");
		assertEquals("Test", collection.get(0));
	}
	
	@Test
	public void ispravnoOciscenaKolekcija() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.clear();
		assertEquals(0, collection.size());
	}
	
	@Test
	public void umetanjeUspjelo() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.insert(6, 2);
		assertEquals(6, collection.get(2));
	}
	
	@Test
	public void umetanjeNijeUspjelo() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		exception.expect(IllegalArgumentException.class);
		collection.insert(6, 7);
	}
	
	@Test
	public void indexPronaden() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			for(int i = 0; i < 5; i++) {
				collection.add(i);
			}
			assertEquals(3, collection.indexOf(3));
	}
	
	@Test
	public void indexNijePronaden() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			for(int i = 0; i < 5; i++) {
				collection.add(i);
			}
			assertEquals(-1, collection.indexOf(8));
	}
	
	@Test
	public void umetanjeRadiDodavanjeNaKraj() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.insert(6, 5);
		assertEquals(6, collection.get(5));
	}
		
	@Test
	public void brisanjeUspjelo() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.remove(4);
		assertEquals(4 ,collection.size());
	}
	
	@Test
	public void brisanjeIzKolekcijeSJednimElementom() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(1);
		collection.remove(0);
		assertEquals(0, collection.size());
	}
	
	@Test
	public void brisanjePrvogCvora() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.remove(0);
		assertEquals(4 ,collection.size());
	}
	
	@Test
	public void brisanjeZadnjegCvora() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		collection.remove(4);
		assertEquals(4 ,collection.size());
	}
	
	@Test
	public void brisanjeIzPrazneKolekcije() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		exception.expect(IndexOutOfBoundsException.class);
		collection.remove(0);
	}
	
	@Test
	public void brisanjeNijeUspjelo() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		for(int i = 0; i < 5; i++) {
			collection.add(i);
		}
		exception.expect(IndexOutOfBoundsException.class);
		collection.remove(10);
	}
}
