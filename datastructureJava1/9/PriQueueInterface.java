package chapter9;

public interface PriQueueInterface {
	boolean isEmpty();

	boolean isFull();

	void enqueue(Comparable element);

	Comparable dequeue();
}
