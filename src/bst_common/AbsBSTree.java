package bst_common;


import java.util.ArrayList;
import java.util.List;

import bst.BinarySearchTreeException;
import bst.GenericComparator;
import tree.ITree;

public abstract class AbsBSTree<T extends Comparable<T>> implements IBSTree<T>{

	protected T value;
	protected IBSTree<T> left_child, right_child;
	protected GenericComparator<T> gc;
	
	/**
	 * Constructor to create a node of type T with given value and children.
	 * @param value The value of the Node.
	 * @param first_child The first child of this node.
	 * @param second_child The second child of this node.
	 */
	public AbsBSTree(T value, IBSTree<T> first_child, IBSTree<T> second_child) {
		gc = new GenericComparator<T>();
		
		this.value = value;
		
		if (first_child == null && second_child == null){
			return;
		}
		
		else if (first_child == null) {
			if (gc.compare(second_child.get_value(), value) >= 0){
				this.right_child = second_child;
			} else {
				this.left_child = second_child;
			}
		}
		
		else if (second_child == null) {
			if (gc.compare(first_child.get_value(), value) >= 0){
				this.right_child = first_child;
			} else {
				this.left_child = first_child;
			}
		}
		
		else if (gc.compare(first_child.get_value(), value) >= 0 && gc.compare(second_child.get_value(), value) < 0){
			this.left_child = second_child;
			this.right_child = first_child;
		}
		
		else if (gc.compare(first_child.get_value(), value) < 0 && gc.compare(second_child.get_value(), value) >= 0) {
			this.left_child = first_child;
			this.right_child = second_child;
		}
		
		else {
			throw new IllegalArgumentException("Invalid Argument for Node, make sure one child is less and one child is greater than value");
		}
	}

	/**
	 * Constructor to create a node of type T with given value, children will be assigned null.
	 * @param value The value of this node.
	 */
	public AbsBSTree (T value) {
		this.gc = new GenericComparator<T>();
		this.value = value;
		this.left_child = null;
		this.right_child = null;
	}
	
	/**
	 * Represent the BSNode to String to be printed out.
	 */
	@Override
	public String toString(){
		String node = "Node(Val: " + value.toString() + ", Left: ";
	    node += (left_child == null) ? "Null, Right: " : left_child.get_value().toString() + ", Right: ";
		node += (right_child == null) ? "Null" : right_child.get_value().toString() + ")";
		
		return node;
	}

	/**
	 * Getter to get the right child of this node.
	 * @return The node that represents the right child of this node.
	 */
	public IBSTree<T> get_right_child(){
		return right_child;
	}
	
	/**
	 * Getter to get the left child of this node.
	 * @return The node that represents the left child of this node.
	 */
	public IBSTree<T> get_left_child(){
		return left_child;
	}
	
	/**
	 * Getter to get the value of this node.
	 * @return The value of this node.
	 */
	public T get_value(){
		return value;
	}
	
	/**
	 * Find a node in this tree.
	 * @param target The target node to be found.
	 * @return The pointer to that node.
	 */
	public ITree<T> search_tree(T target){
		
		IBSTree<T> current_node = this;
		
		while(current_node != null){
			if(current_node.get_value().equals(target)) {
				return current_node;
			}
			else if (gc.compare(current_node.get_value(), target) < 0){
				current_node = (IBSTree<T>) current_node.get_right_child();
			} else {
				current_node = (IBSTree<T>) current_node.get_left_child();
			}
		}
		
		return null;
		
	}

	/**
	 * Determine whether the tree contains a certain node.
	 * @param target Target to be determined.
	 * @return True if the target is in the tree.
	 */
	public boolean has_node(T which_node){
		
		return (search_tree(which_node) != null);
	}
	
	/**
	 * Get the list of nodes from this tree.
	 * @return The list of nodes from this tree.
	 */
	public List<T> get_descendants(){
		
		List<T> lop = new ArrayList<T>();
		
		lop.add(value);
		
		if (left_child != null){
			lop.addAll(((IBSTree<T>) left_child).get_descendants());
		}
		
		if(right_child != null){
			lop.addAll(right_child.get_descendants());
		}
		
		return lop;
		
	}

	public String get_tree_type(){
		return "BST";
	}
	
	@Override
	public void add_tree(ITree<T> new_tree){
		if (!new_tree.get_tree_type().equals("BST")) {
			try {
				throw new BinarySearchTreeException("The tree to be added must be a IBSTree<T>");
			} catch (BinarySearchTreeException e){
				e.printStackTrace();
			}
		}
	}

}
