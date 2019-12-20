package structs;

public class Queue<T> {
	private Node<T> head;
	private Node<T> tail;

	public Queue() {
	}

	public T dequeue() {
		if (tail == null)
			return null;
		if (head == tail) {
			T tmp = tail.value();
			tail = head = null;
			return tmp;
		}
		T tmp = tail.value();
		tail = tail.next();
		return tmp;
	}

	public T peek() {
		return tail == null ? null : tail.value();
	}

	public void enqueue(T tmp) {
		if (tail == null) {
			tail = head = new Node<T>(tmp);
		} else {
			head.setNext(new Node<T>(tmp));
			head = head.next();
		}
	}

	public boolean isEmpty() {
		return tail == null;
	}

	public int size() {
		return tail.size();

	}

}
