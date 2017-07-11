package hr.fer.zemris.java.custom.collections.demo;

import java.util.Arrays;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;
import hr.fer.zemris.java.custom.collections.Collection;
import hr.fer.zemris.java.custom.collections.LinkedListIndexedCollection;
import hr.fer.zemris.java.custom.collections.Processor;
/**
 * Razred koji služi kao demonstracija rada razreda {@link Collection} , {@link ArrayIndexedCollection} i
 * {@link LinkedListIndexedCollection}
 * 
 * @author Davor Češljaš
 *
 */
public class CollectionDemo {
	
	/**
	 * Metoda koja započinje izvođenje programa
	 * 
	 * @param args ne koristi se u ovom programu
	 */
	public static void main(String[] args) {
		//isti primjeri ali sa obrnutim kolekcijama()
		example1();
		System.out.println("------------------------------------------------------------------------------");
		example2();
	}


	private static void example1() {
		LinkedListIndexedCollection col = new LinkedListIndexedCollection();
		col.add(new Integer(20));
		col.add("New York");
		col.add("San Francisco"); // here the internal array is reallocated to 4
		
		System.out.println(col.contains("New York")); // writes: true
		
		col.remove(1); // removes "New York"; shifts "San Francisco" to position
						// 1
		System.out.println(col.get(1)); // writes: "San Francisco"
		System.out.println(col.size()); // writes: 2
		
		col.add("Los Angeles");
		
		ArrayIndexedCollection col2 = new ArrayIndexedCollection(col);
		
		class P extends Processor {
			public void process(Object o) {
				System.out.println(o);
			}
		}

		System.out.println("col elements:");
		col.forEach(new P());
		System.out.println("col elements again:");
		System.out.println(Arrays.toString(col.toArray()));
		
		System.out.println("col2 elements:");
		col2.forEach(new P());
		System.out.println("col2 elements again:");
		System.out.println(Arrays.toString(col2.toArray()));
		
		System.out.println(col.contains(col2.get(1))); // true
		System.out.println(col2.contains(col.get(1))); // true
		col.remove(new Integer(20)); // removes 20 from collection (at position
										// 0)
	}

	private static void example2() {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(new Integer(20));
		col.add("New York");
		col.add("San Francisco"); // here the internal array is reallocated to 4
		
		System.out.println(col.contains("New York")); // writes: true
		
		col.remove(1); // removes "New York"; shifts "San Francisco" to position
						// 1
		System.out.println(col.get(1)); // writes: "San Francisco"
		System.out.println(col.size()); // writes: 2
		
		col.add("Los Angeles");
		
		LinkedListIndexedCollection col2 = new LinkedListIndexedCollection(col);
		
		class P extends Processor {
			public void process(Object o) {
				System.out.println(o);
			}
		}

		System.out.println("col elements:");
		col.forEach(new P());
		System.out.println("col elements again:");
		System.out.println(Arrays.toString(col.toArray()));
		
		System.out.println("col2 elements:");
		col2.forEach(new P());
		System.out.println("col2 elements again:");
		System.out.println(Arrays.toString(col2.toArray()));
		
		System.out.println(col.contains(col2.get(1))); // true
		System.out.println(col2.contains(col.get(1))); // true
		col.remove(new Integer(20)); // removes 20 from collection (at position
										// 0)
		
	}
}
