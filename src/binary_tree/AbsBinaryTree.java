package binary_tree;

public abstract class AbsBinaryTree<T extends Comparable<T>> implements IBinaryTree<T>{

	protected T value;
	protected IBinaryTree<T> left_tree, right_tree;
	
	public AbsBinaryTree (T value, IBinaryTree<T> left_tree, IBinaryTree<T> right_tree) {
		this.value = value;
		this.left_tree = left_tree;
		this.right_tree = right_tree;
	}
	
	public IBinaryTree<T> get_left_child(){
		return left_tree;
	}
	
	public IBinaryTree<T> get_right_child(){
		return right_tree;
	}
	
	public T get_value(){
		return value;
	}


	/**
	 * Represent the BSNode to String to be printed out.
	 */
	@Override
	public String toString(){
		String node = "Node(Val: " + value.toString() + ", Left: ";
		node += (left_tree == null) ? "Null, Right: " : left_tree.get_value().toString() + ", Right: ";
		node += (right_tree == null) ? "Null" : right_tree.get_value().toString() + ")";

		return node;
	}

}
