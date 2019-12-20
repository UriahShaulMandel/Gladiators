package structs;

public class Stack<T> {
	private Node<T> pointer;

	public Stack() {
		// TODO Auto-generated constructor stub
	}

	public T pop() {
		if (pointer == null)
			return null;
		T tmp = pointer.value();
		pointer = pointer.next();
		return tmp;
	}

	public T peek() {
		return pointer == null ? null : pointer.value();
	}

	public void push(T tmp) {
		if (pointer == null) {
			pointer = new Node<T>(tmp);
		} else {
			pointer = new Node<T>(tmp, pointer);
		}

	}

	public boolean isEmpty() {
		return pointer == null;
	}

	public int size() {
		return pointer.size();
	}

}
