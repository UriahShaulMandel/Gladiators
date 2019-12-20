package structs;

public class BinTree<T> {
	private BinTree<T> left;
	private BinTree<T> right;
	private T value;

	public BinTree() {

	}

	public BinTree(T val) {
		value = val;
	}

	public BinTree(T val, BinTree<T> right) {
		value = val;
		this.right = right;
	}

	public BinTree(BinTree<T> left, T val) {
		value = val;
		this.left = left;
	}

	public BinTree(BinTree<T> left, T val, BinTree<T> right) {
		value = val;
		this.right = right;
		this.left = left;
	}

	public BinTree<T> left() {
		return left;
	}

	public void setLeft(BinTree<T> left) {
		this.left = left;
	}

	public BinTree<T> right() {
		return right;
	}

	public void setRight(BinTree<T> right) {
		this.right = right;
	}

	public T value() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public boolean isLeaf() {
		return left == null && right == null;

	}

	public int size() {
		if (isLeaf()) {
			return 1;
		} else if (left == null) {
			return 1 + right.size();
		} else if (right == null) {
			return 1 + left.size();
		}
		return 1 + left.size() + right.size();

	}

}
