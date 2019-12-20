package structs;

public class Node<T> {
	/**
	 * the current node's value
	 */
	private T value;
	/**
	 * the next node in the chain
	 */
	private Node<T> next;

	/**
	 * this constructor creates a new node which is the last node in it's chain
	 * 
	 * @param val
	 *            - the value of the new node
	 * @see <a href="http://www.wikipedia.org">wikipedia</a>
	 */
	public Node(T val) {
		value = val;
	}

	public Node() {
	}

	public Node(Node<T> pre, T val) {
		pre.setNext(this);
		value = val;
	}

	public Node(T val, Node<T> next) {
		setNext(next);
		setValue(val);
	}

	public Node(Node<T> prev, T val, Node<T> next) {
		prev.setNext(this);
		setNext(next);
		setValue(val);
	}

	public T value() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> next() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public int size() {
		if (next == null) {
			return 1;
		}
		return next.size() + 1;
	}

	public String toString() {
		return ("" + value);
	}

}
