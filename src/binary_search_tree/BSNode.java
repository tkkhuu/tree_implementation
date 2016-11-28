package binary_search_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BSNode<T extends Comparable<T>>{
	
	private T value;
	private BSNode<T> left_node, right_node;
	private GenericComparator<T> gc;
	
	// ======================= Constructors =======================
	
	/**
	 * Constructor to create a node of type T with given value and children.
	 * @param value The value of the Node.
	 * @param first_child The first child of this node.
	 * @param second_child The second child of this node.
	 */
	public BSNode (T value, BSNode<T> first_child, BSNode<T> second_child){
		
		gc = new GenericComparator<T>();
		
		this.value = value;
		
		if (first_child == null && second_child == null){
			return;
		}
		
		else if (first_child == null) {
			if (gc.compare(second_child.get_value(), value) >= 0){
				this.right_node = second_child;
			} else {
				this.left_node = second_child;
			}
		}
		
		else if (second_child == null) {
			if (gc.compare(first_child.get_value(), value) >= 0){
				this.right_node = first_child;
			} else {
				this.left_node = first_child;
			}
		}
		
		else if (gc.compare(first_child.get_value(), value) >= 0 && gc.compare(second_child.get_value(), value) < 0){
			this.left_node = second_child;
			this.right_node = first_child;
		}
		
		else if (gc.compare(first_child.get_value(), value) < 0 && gc.compare(second_child.get_value(), value) >= 0) {
			this.left_node = first_child;
			this.right_node = second_child;
		}
		
		else {
			throw new IllegalArgumentException("Invalid Argument for Node, make sure one child is less and one child is greater than value");
		}
		
	}
	
	/**
	 * Constructor to create a node of type T with given value, children will be assigned null.
	 * @param value The value of this node.
	 */
	public BSNode (T value) {
		this.gc = new GenericComparator<T>();
		this.value = value;
		this.left_node = null;
		this.right_node = null;
	}
	
	public BSNode (BSNode<T> new_node){
		this.value = new_node.get_value();
		this.left_node = new_node.get_left_child();
		this.right_node = new_node.get_right_child();
		this.gc = new GenericComparator<T>();
	}
	// ======================= Public Functions =======================
	
	/**
	 * Getter to get the right child of this node.
	 * @return The node that represents the right child of this node.
	 */
	public BSNode<T> get_right_child(){
		return right_node;
	}
	
	/**
	 * Getter to get the left child of this node.
	 * @return The node that represents the left child of this node.
	 */
	public BSNode<T> get_left_child(){
		return left_node;
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
	public BSNode<T> find_node(T target){
		
		BSNode<T> current_node = this;
		
		while(current_node != null){
			if(current_node.get_value().equals(target)) {
				return current_node;
			}
			else if (gc.compare(current_node.get_value(), target) < 0){
				current_node = current_node.get_right_child();
			} else {
				current_node = current_node.get_left_child();
			}
		}
		
		return null;
		
	}

	/**
	 * Determine whether the tree contains a certain node.
	 * @param target Target to be determined.
	 * @return True if the target is in the tree.
	 */
	public boolean contains_node(T target){
		return (find_node(target) != null);
	}
	
	/**
	 * Display the tree in Pre-Order: value, left, right.
	 */
	public void show_tree(){
		System.out.println(value);
		if (left_node != null){
			left_node.show_tree();
		}
		if (right_node != null) {
			right_node.show_tree();
		}
	}
	
	/**
	 * Add a new node to the tree
	 * @param new_value The value of the new node to be added
	 */
	public void add_node(T new_value){
		
		BSNode<T> new_node = new BSNode<T>(new_value);
		
		if (gc.compare(new_value, value) >= 0 && right_node == null){
			right_node = new_node;
		}
		
		else if (gc.compare(new_value, value) < 0 && left_node == null) {
			left_node = new_node;
		}
		
		else if (gc.compare(new_value, value) >= 0){
			right_node.add_node(new_value);
		}
		
		else if (gc.compare(new_value, value) < 0){
			left_node.add_node(new_value);
		}
		
	}
	
	public void add_tree(BSNode<T> which_tree){
		
		if (gc.compare(which_tree.get_value(), value) >= 0 && right_node == null){
			right_node = which_tree;
		}
		
		else if (gc.compare(which_tree.get_value(), value) < 0 && left_node == null) {
			left_node = which_tree;
		}
		
		else if (gc.compare(which_tree.get_value(), value) >= 0){
			right_node.add_tree(which_tree);
		}
		
		else if (gc.compare(which_tree.get_value(), value) < 0){
			left_node.add_tree(which_tree);
		}
		
		BSNode<T> balanced = balance_tree();
		value = balanced.get_value();
		left_node = balanced.get_left_child();
		right_node = balanced.get_right_child();
	}
	
	public void remove_node(T which_node) {
		if (!contains_node(which_node)) {
			try {
				throw new BinarySearchTreeException("The node to be removed does not exist in this tree");
			} catch (BinarySearchTreeException e){
				e.printStackTrace();
				return;
			}
		}

		BSNode<T> rm_node = find_node(which_node);
		
		
		switch (rm_node.number_of_children()) {

		case 0:
			BSNode<T> current_node = this;
			while(current_node != null){

				if (current_node.get_left_child() == rm_node){
					current_node.set_left_child(null);
				}

				else if (current_node.get_right_child() == rm_node) {
					current_node.set_right_child(null);
				}

				else if (gc.compare(current_node.get_value(), which_node) < 0){
					current_node = current_node.right_node;
				} 

				else {
					current_node = current_node.left_node;
				}
			}
			
			break;
			
		case 1:
			if (rm_node.get_left_child() != null){
				rm_node.set_value(rm_node.get_left_child().get_value());
				rm_node.set_left_child(null);
			} 
			
			else if (rm_node.get_right_child() != null){
				rm_node.set_value(rm_node.get_right_child().get_value());
				rm_node.set_right_child(null);
			}
			/**
			while(current_node != null){

				if (current_node.get_left_child() == rm_node){
					BSNode<T> current_new_left = (rm_node.get_left_child() != null) ? rm_node.get_left_child() : rm_node.get_right_child();
					current_node.set_left_child(current_new_left);
				}

				else if (current_node.get_right_child() == rm_node) {
					BSNode<T> current_new_right = (rm_node.get_left_child() != null) ? rm_node.get_left_child() : rm_node.get_right_child();
					current_node.set_right_child(current_new_right);
				}

				else if (gc.compare(current_node.get_value(), which_node) < 0){
					current_node = current_node.right_node;
				} 

				else {
					current_node = current_node.left_node;
				}
			}
			*/
			break;
		default:
			
			BSNode<T> new_root = new BSNode<T>(rm_node.get_left_child());
			
			while(new_root.get_right_child() != null){
				new_root = new_root.get_right_child();
			}
			
			rm_node.set_value(new_root.get_value());
			new_root = new_root.get_left_child();
			rm_node.set_left_child(new_root);
			
			break;
		}
		
		
		



	}
	
	/**
	 * Represent the BSNode to String to be printed out.
	 */
	@Override
	public String toString(){
		String node = "Node(Val: " + value.toString() + ", Left: ";
	    node += (left_node == null) ? "Null, Right: " : left_node.get_value().toString() + ", Right: ";
		node += (right_node == null) ? "Null" : right_node.get_value().toString() + ")";
		
		return node;
	}
	
	/**
	 * Re-balance the tree
	 * @return The balanced tree
	 */
	public BSNode<T> balance_tree(){
		
		// Get the list of all the nodes from this root
		List<T> lop = get_list_of_node();
		
		// Sort the list
				Collections.sort(lop, gc);
		
		if (lop.size() == 1) {
			return new BSNode<T>(lop.get(0));
		}
		
		if(lop.size() == 2) {
			BSNode<T> new_root = new BSNode<T>(lop.get(1));
			BSNode<T> child = new BSNode<T>(lop.get(0));
			new_root.set_left_child(child);
			return new_root;
		}
		
		
		
		// The new root would be the middle node of the list
		int root_index = lop.size() / 2;
		BSNode<T> new_root = new BSNode<T>(lop.get(root_index));
		
		// Get the remaining lists to the left and right of the new root
		List<T> left_list = lop.subList(0, root_index);
		List<T> right_list = lop.subList(root_index + 1, lop.size());
		
		// The left and right child of the new root would be the nodes next to it in the sorted list
		BSNode<T> new_left_node = new BSNode<T>(lop.get(root_index - 1));
		BSNode<T> new_right_node = new BSNode<T>(lop.get(root_index + 1));
		
		// Link all the nodes in the left and right sublist
		BSNode<T> current_left = new_left_node;
		for(T t : left_list){
			if (!t.equals(new_left_node.get_value())){
				BSNode<T> new_left_child = new BSNode<T>(t);
				current_left.set_left_child(new_left_child);
				current_left = new_left_child;
			}
		}
		
		BSNode<T> current_right = new_right_node;
		for(T t : right_list){
			if (!t.equals(new_right_node.get_value())){
			BSNode<T> new_right_child = new BSNode<T>(t);
			current_right.set_right_child(new_right_child);
			current_right = new_right_child;
			}
		}
		
		new_root.set_left_child(new_left_node.balance_tree());
		new_root.set_right_child(new_right_node.balance_tree());
		
		return new_root;
	}
	
	// ======================= Private Functions =======================

	private void set_value(T which_value){
		if (which_value == null) {
			this.value = null;
		}
		else if (	(gc.compare(which_value, left_node.get_value()) < 0) && 
				(gc.compare(which_value, right_node.get_value()) > 0) ){
			try{
				throw new BinarySearchTreeException("Invalid new value, new value mu be in the range: (left_node, right_node]");
			} catch (BinarySearchTreeException e){
				e.printStackTrace();
				return;
			}
			
		}
		this.value = which_value;
	}
	
	/**
	 * Setter to assign the left child to this node
	 * @param left_child The new value of the left child
	 */
	private void set_left_child(BSNode<T> left_child){
		this.left_node = left_child;
	}
	
	/**
	 * Setter to assign the right child of this node.
	 * @param right_child The new value of the right child.
	 */
	private void set_right_child(BSNode<T> right_child){
		this.right_node = right_child;
	}
	
	private int number_of_children(){
		
		int n_children = 0;
		
		if (left_node == null && right_node == null){
			return 0;
		}
		else if (left_node == null && right_node != null){
			return 1 + right_node.number_of_children();
		}
		else if (left_node != null && right_node == null) {
			return 1 + left_node.number_of_children();
		}
		
		else {
			n_children += 2;
		}
		
		return n_children + left_node.number_of_children() + right_node.number_of_children();
	}
	
	/**
	 * Get the list of nodes from this tree.
	 * @return The list of nodes from this tree.
	 */
	private List<T> get_list_of_node(){
		
		List<T> lop = new ArrayList<T>();
		
		lop.add(value);
		
		if (left_node != null){
			lop.addAll(left_node.get_list_of_node());
		}
		
		if(right_node != null){
			lop.addAll(right_node.get_list_of_node());
		}
		
		return lop;
		
	}
	
	
	
}
