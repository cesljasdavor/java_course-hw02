package hr.fer.zemris.java.custom.collections;

/**
 * Razred koji nasljeđuje razred {@link Collection}. Razred predstavlja
 * promijenjivu kolekciju poduprtu sa dvostruko ulančanom listom čvorova
 * {@link ListNode}. Važno: kolekcija <b>može spremati</b> duplikate, ali <b>ne
 * može spremati</b> vrijednost <code><b>null</b></code>. Metode dodane u ovaj
 * razred:
 * <ul>
 * <li><code>{@link #get(int)}</code></li>
 * <li><code>{@link #insert(Object, int)} </code></li>
 * <li><code>{@link #indexOf(Object)} </code></li>
 * <li><code>{@link #remove(int)}</code></li>
 * </ul>
 * 
 * Moguće implementacije konstruktora:
 * <ul>
 * <li><code>{@link #LinkedListIndexedCollection(Collection)}</code></li>
 * <li><code>{@link #LinkedListIndexedCollection()}</code></li>
 * </ul>
 * 
 *  @author Davor Češljaš
 */

public class LinkedListIndexedCollection extends Collection {

	/**
	 * Razred koji služi kao implementacija jednog čvora liste unutar kolekcije
	 * {@link LinkedListIndexedCollection}. Svaki primjerak razreda sastoji se
	 * od vrijednosti ({@link Object}) te referenci na slijedeći i predhodni
	 * čvora {@link ListNode}
	 */
	private static class ListNode {

		/** Vrijednost koja je spremjena u čvoru */
		private Object value;

		/** Referenca na sljedeći čvor liste */
		private ListNode next;

		/** Referenca na predhodni čvor liste */
		private ListNode previous;

		/**
		 * Konstruktor razreda {@link ListNode} koji prima samo vrijednost koju
		 * je potrebno pospremiti. Reference na predhodni i sljedeći čvor
		 * postavljaju se na <b>null</b>
		 *
		 * @param value
		 *            vrijednost koju je potrebno pospremiti u čvor
		 */
		private ListNode(Object value) {
			this.value = value;
		}
	}

	/**
	 * Status koji se koristi kod metode {@link #indexOf(Object)} ukoliko index
	 * ne postoji
	 */
	private static final int DOESNT_CONTAIN = -1;

	/** Trenutna veličina kolekcije. */
	private int size;

	/** Referenca na prvi čvor u listi */
	private ListNode first;

	/** Referenca na posljednji čvor u listi */
	private ListNode last;

	/**
	 * Konstruktor koji prima referencu na primjerak razreda {@link Collection}
	 * čiji elementi moraju biti kopirani u novi primjerak razreda
	 * {@link LinkedListIndexedCollection}.
	 * 
	 * @param other
	 *            primjerak razreda {@link Collection} čiji se elementi moraju
	 *            kopirati u ovaj.
	 * 
	 * 
	 */
	public LinkedListIndexedCollection(Collection other) {
		if (other != null) {
			this.addAll(other);
		}
	}

	/**
	 * Konstruktor koji ne prima niti jedan argument. Vrijednosti prvog i
	 * posljednjeg čvora liste postavljene su na <b>null</b>
	 */
	public LinkedListIndexedCollection() {
		this(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#size()
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Miče sve elemente iz ove kolekcije. Kolekcija "zaboravlja" na trenutnu
	 * dvostruko ulačanu listu
	 */
	@Override
	public void clear() {
		// zaboravi na početni i završni čvor. Time će garbage collector
		// uništiti cijeli ulančanu listu
		this.first = this.last = null;
		this.size = 0;
	}

	@Override
	public void forEach(Processor processor) {
		for (ListNode node = first; node != null; node = node.next) {
			processor.process(node.value);
		}
	}

	/**
	 * Dodaje predani objekt u kolekciju na posljednje mjesto; novododani čvor
	 * postaje čvor na največoj poziciji. Metoda neće dodati vrijednost
	 * <code><b>null</b></code>. Kompleksnost metoda je O(1)
	 * 
	 * @param value
	 *            objekt koji je potrebno dodati
	 * @throws IllegalArgumentException
	 *             ukoliko se preda vrijednost null
	 */
	@Override
	public void add(Object value) {
		insert(value, size);
	}

	@Override
	public boolean contains(Object value) {
		return indexOf(value) != DOESNT_CONTAIN;
	}

	@Override
	public boolean remove(Object value) {
		int index = indexOf(value);
		if (index == DOESNT_CONTAIN) {
			return false;
		}

		remove(index);
		return true;
	}

	@Override
	public Object[] toArray() {
		Object[] elements = new Object[this.size];

		ListNode node;
		int i;
		for (i = 0, node = first; i < size; i++, node = node.next) {
			elements[i] = node.value;
		}

		return elements;
	}

	/**
	 * Ubacuje vrijednost <b>value</b> na predanu poziciju <b>position</b>. Ova
	 * metoda neće prebrisati element na toj poziciji već će sve elemente na
	 * pozicijima većim od predane poziciji pomaknuti za jedno mjesto u desno.
	 * Važeće pozicjie su iz intervala <b>[0, size]</b>.
	 *
	 * @param value
	 *            vrijednost koju je potrebno ubaciti na predanu poziciju
	 * @param position
	 *            pozicija na koju je potrebno ubaciti predanu vrijednost
	 * 
	 * @throws IllegalArgumentException
	 *             ukoliko je vrijednost <b>null</b> ili pozicija nije u važećem
	 *             rasponu
	 */
	public void insert(Object value, int position) {
		// jesu li argumenti ispravni
		if (position < 0 || position > size || value == null) {
			throw new IllegalArgumentException();
		}

		ListNode newNode = new ListNode(value);
		// dodavanje u praznu listu
		if (size == 0) {
			this.first = this.last = newNode;
		} else {
			if (position == 0) {
				// dodavanje na početak
				newNode.next = this.first;
				this.first.previous = newNode;
				this.first = newNode;
			} else if (position == size) {
				// dodavanje na kraj
				newNode.previous = this.last;
				this.last.next = newNode;
				this.last = newNode;
			} else {
				// dodaje se na indexe različite od size ili 0 te lista nije
				// prazna
				ListNode node = find(position);

				newNode.next = node;
				newNode.previous = node.previous;
				node.previous = newNode;
				newNode.previous.next = newNode;
			}
		}
		this.size++;
	}

	/**
	 * Vraća objekt koji je spremljen u ulančanoj listi ove kolekcije na
	 * poziciji <b>index</b>. Važeće pozicjie su iz intervala <b>[0, size
	 * -1]</b>
	 *
	 * @param index
	 *            pozicija tražene vrijednosti u ulančanoj listi
	 * @return traženi element iz ulančane liste
	 * 
	 * @throws IndexOutOfBoundsException
	 *             ukoliko <b>index</b> nije unutar granica
	 */
	public Object get(int index) {
		return find(index).value;
	}

	/**
	 * Metoda koja se koristi za pronalačenje elemenata u dvostruko ulančanoj
	 * listi. U najgorem slučaju pronalaženje je kompleksnosti <i>n/2 + 1</i> .
	 * Važeće pozicjie su iz intervala <b>[0, size -1]</b>
	 *
	 * @param index
	 *            pozicija traženog elementa u ulančanoj listi
	 * @return čvor liste koji se nalazi na toj poziciji
	 * @throws IndexOutOfBoundsException
	 *             ukoliko <b>index</b> nije unutar granica
	 */
	private ListNode find(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		ListNode node;
		int i;
		// razmatramo je li bolje ići od prvog čvora ili od zadnjeg do nekog
		// indexa
		if (index + 1 < size - index) {
			for (i = 0, node = first; i < index; i++, node = node.next);
		} else {
			for (i = size - 1, node = last; i > index; i--, node = node.previous);
		}
		return node;
	}

	/**
	 * Pretražuje kolekciju i vraća prvu poziciju na kojoj je našla predanu
	 * vrijednost ili -1 ukoliko predana vrijednosti ne postoji u ovoj kolekciji
	 *
	 * @param value
	 * 	vrijednost koja se pretražuje
	 * @return pozicija na kojoj je prvi puta pronađena vrijednost ili -1 ukoliko vrijednost nije pronađena
	 */
	public int indexOf(Object value) {
		ListNode node;
		int i;
		for (i = 0, node = first; node != null; i++, node = node.next) {
			if (node.value.equals(value)) {
				return i;
			}
		}
		return DOESNT_CONTAIN;
	}

	/**
	 * Miče element sa predane pozicije <b>index</b>.  sve elemente desno od predane pozicije pomiče za 
	 * jedno mjesto u lijevo unutar ulančane liste.
	 *  Važeće pozicjie su iz intervala <b>[0, size-1]</b>.
	 *
	 * @param index
	 *            pozicija sa koje je potrebno maknuti element
	 * 
	 * @throws IndexOutOfBoundsException
	 *             ukoliko <b>index</b> nije unutar granica
	 */
	public void remove(int index) {
		// prvo će se provjeriti je li index izvan granica
		ListNode node = find(index);
		if (size == 1) {
			clear();
			return;
		}

		if (node == first) {
			node.next.previous = null;
			this.first = node.next;
		} else if (node == last) {
			node.previous.next = null;
			this.last = node.previous;
		} else {
			node.next.previous = node.previous;
			node.previous.next = node.next;
		}
		size--;
	}

}
