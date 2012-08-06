package chapter9;

public class Heap {

	private Comparable[] elements;
	private int lastIndex; // index of last element in priority queue
	private int maxIndex; // index of last position in array
	private int initialNumber;

	public Heap(int maxSize) {
		elements = new Comparable[maxSize];
		lastIndex = -1;
		maxIndex = maxSize - 1;
		initialNumber = maxSize;
	}

	public boolean hasLeft(int i) {
		return (elements[i * 2 + 1] != null);
	}

	public boolean hasRight(int i) {
		return (elements[i * 2 + 2] != null);
	}

	public Comparable root() {
		return elements[0];
	}

	public Comparable elementAt(int i) {
		return elements[i];
	}

	public int size() {
		return lastIndex + 1;
	}

	public void makeEmpty() {
		// Initialize the heap
		elements = new Comparable[initialNumber];
		lastIndex = -1;
		maxIndex = initialNumber - 1;
		System.out
				.println("------------------new Tree------------------------");

	}

	public boolean isEmpty() {
		// Returns true if this priority queue is empty, false otherwise.
		return lastIndex == -1;
	}

	public boolean isFull() {
		// Returns true if this priority queue is full, false otherwise.
		return lastIndex == maxIndex;
	}

	public void enqueue(Comparable element) throws PriQOverflowException {
		// Throws PriQOverflowException if this priority queue is full;
		// otherwise, adds element to this priority queue.
		if (isFull()) {
			throw new PriQOverflowException("priority queue is full ");
		} else {
			lastIndex++;
			reheapUp(element);
		}

	}
	//  ______________________________________구현하세요__________________________________________
	private void reheapUp(Comparable element) {
		int hole= lastIndex;
		elements[hole]=element;
		Comparable input;
		while(hole>0&&elements[(hole-1)/2].compareTo(element)<0){
			input=elements[hole];
			elements[hole]=elements[(hole-1)/2];
			elements[(hole-1)/2]=input;
			hole=(hole-1)/2;
		}
		// Inserts element into the tree and ensures shape and order properties.
		// hole is not root && element > hole's parent
		// move hole up
		// place element into final hole
	}

	public Comparable dequeue() throws PriQUnderflowException {
		// Throws PriQUnderflowException if this priority queue is empty;
		// otherwise, removes element with highest priority from this
		// priority queue and returns it.
		// element to be dequeued and returned
		// element to move down heap
		// remember element to be returned
		// element to reheap down
		// decrease priority queue size
		// restore heap properties
		// return largest element
		Comparable dequeueValue;
		Comparable element;
		if (isEmpty()) {
			throw new PriQOverflowException("priority queue is empty");
		} else {
			dequeueValue = root();//elements[0]
			elements[0] = null;
			element = elementAt(lastIndex);//element[lastIndex]
			elements[lastIndex] = null;
			lastIndex--;
			reheapDown(element);

		}
		return dequeueValue;
	}
	//  ______________________________________구현하세요__________________________________________
	private void reheapDown(Comparable element) {
		// Inserts element into the tree and ensures shape and order properties.
		// current index of hole
		// index where hole should move to
		// find next hole
		// move element up
		// move hole down
		// find next hole
		// fill in the final hole
		int hole = 0;
		int check = newHole(hole, element);
		while(hole!=check){		
			elements[hole]=elements[check];
			hole=newHole(hole, element);
			check=newHole(hole, element);
		}
		elements[hole]=element;
	}

	private int newHole(int hole, Comparable element) {
		// If either child of hole is larger than element return the index
		// of the larger child; otherwise return the index of hole.
		int left = (hole * 2) + 1;
		int right = (hole * 2) + 2;
		if (left > lastIndex) // hole has no children
			return hole;
		else if (left == lastIndex) // hole has left child only
			if (element.compareTo(elements[left]) < 0)// element < left child
				return left;
			else
				// element >= left child
				return hole;
		else // hole has two children
		if (elements[left].compareTo(elements[right]) < 0)// left child < right
															// child
			if (elements[right].compareTo(element) <= 0)// right child <=
														// element
				return hole;
			else
				// element < right child
				return right;
		else // left child >= right child
		if (elements[left].compareTo(element) <= 0) // left child <= element
			return hole;
		else
			// element < left child
			return left;
	}

	public void show() {
		int howManyT = 0;
		int nowLevel;
		int maxLevel;
		String t = "";
		maxLevel = level(lastIndex + 1, 0);
		for (int i = 0; i <= lastIndex; i++) {
			nowLevel = level(i + 1, 0);
			howManyT = howManyTab(maxLevel, nowLevel, 1);
			if (i == 0) {
				for (int j = 0; j < howManyT; j++) {
					System.out.print(" ");
				}
			}

			if (twoMul(i + 1)) {
				System.out.println();
				for (int j = 0; j < howManyT; j++) {
					System.out.print(" ");
				}
			}
			t = "";
			howManyT = howManyTab(maxLevel + 1, nowLevel, 1);
			for (int j = 0; j < howManyT; j++) {
				t = t + " ";
			}
			System.out.print(elements[i] + t);

		}
		System.out.println();
	}

	private int howManyTab(int maxLevel, int nowLevel, int val) {
		// TODO Auto-generated method stub
		if (maxLevel == nowLevel)
			return val - 1;
		val = val * 2;
		return howManyTab(maxLevel, nowLevel + 1, val);
	}

	public int level(int i, int count) {
		// TODO Auto-generated method stub
		if (i == 1) {
			return count;
		}
		if (i == 2) {
			return count + 1;
		} else if (i % 2 == 0) {
			count++;
			return level(i / 2, count);
		} else {
			return level(i - 1, count);
		}
	}

	private boolean twoMul(int i) {
		// TODO Auto-generated method stub
		if (i == 2) {
			return true;
		} else if (i % 2 == 0) {
			return twoMul(i / 2);
		}
		return false;
	}
}
