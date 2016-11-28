package bst;


import java.util.Collections;
import java.util.List;

import bst_common.AbsBSTree;
import bst_common.IBSTree;
import tree.ITree;

public class BSTree<T extends Comparable<T>> extends AbsBSTree<T>{
	
	//private T value;
	//private BS_Tree<T> left_node, right_node;
	//private GenericComparator<T> gc;
	
	// ======================= Constructors =======================
	
	/**
	 * Constructor to create a node of type T with given value and children.
	 * @param value The value of the Node.
	 * @param first_child The first child of this node.
	 * @param second_child The second child of this node.
	 */
	public BSTree (T value, IBSTree<T> first_child, IBSTree<T> second_child){
		
		super(value, first_child, second_child);
		
	}
	
	/**
	 * Constructor to create a node of type T with given value, children will be assigned null.
	 * @param value The value of this node.
	 */
	public BSTree (T value) {
		super(value);
	}
	
	
	public BSTree (IBSTree<T> new_tree){
		super(new_tree.get_value());
		
		IBSTree<T> new_left = (IBSTree<T>) new_tree.get_left_child();
		IBSTree<T> new_right = (IBSTree<T>) new_tree.get_right_child();
		
		this.left_child = (new_tree.get_left_child() == null) ? null : new BSTree<T> (new_left);
		this.right_child = (new_tree.get_right_child() == null) ? null : new BSTree<T> (new_right); 

		this.gc = new GenericComparator<T>();
	}
	
	// ======================= Public Functions =======================
	
	

	

	
	
	/**
	 * Display the tree in Pre-Order: value, left, right.
	 */
	@Override
	public void display_tree_pre_order(){
		System.out.println(value);
		if (left_child != null){
			left_child.display_tree_pre_order();
		}
		if (right_child != null) {
			right_child.display_tree_pre_order();
		}
	}
	
	/**
	 * Add a new node to the tree
	 * @param new_value The value of the new node to be added
	 */
	public void add_leaf(T new_value){
		
		IBSTree<T> new_node = new BSTree<T>(new_value);
		
		if (gc.compare(new_value, value) >= 0 && right_child == null){
			right_child = new_node;
		}
		
		else if (gc.compare(new_value, value) < 0 && left_child == null) {
			left_child = new_node;
		}
		
		else if (gc.compare(new_value, value) >= 0){
			((BSTree<T>) right_child).add_leaf(new_value);
		}
		
		else if (gc.compare(new_value, value) < 0){
			((BSTree<T>) left_child).add_leaf(new_value);
		}
		
	}
	
	@Override
	public void add_tree(ITree<T> new_tree){
		
		super.add_tree(new_tree);
		
		IBSTree<T> which_tree = (IBSTree<T>) new_tree;
		
		if (gc.compare(which_tree.get_value(), value) >= 0 && right_child == null){
			right_child = which_tree;
		}
		
		else if (gc.compare(which_tree.get_value(), value) < 0 && left_child == null) {
			left_child = which_tree;
		}
		
		else if (gc.compare(which_tree.get_value(), value) >= 0){
			right_child.add_tree(which_tree);
		}
		
		else if (gc.compare(which_tree.get_value(), value) < 0){
			left_child.add_tree(which_tree);
		}
		
		IBSTree<T> balanced = balance_tree();
		value = balanced.get_value();
		left_child = (IBSTree<T>) balanced.get_left_child();
		right_child = (IBSTree<T>) balanced.get_right_child();
	}
	
	@Override
	public void remove_node(T which_node) {
		
		if (!has_node(which_node)) {
			try {
				throw new BinarySearchTreeException("The node to be removed does not exist in this tree");
			} catch (BinarySearchTreeException e){
				e.printStackTrace();
				return;
			}
		}

		BSTree<T> rm_node = (BSTree<T>) search_tree(which_node);
		
		
		switch (rm_node.number_of_descendants()) {

		case 0:
			BSTree<T> current_node = this;
			while(current_node != null){

				if (current_node.get_left_child() == rm_node){
					current_node.set_left_child(null);
				}

				else if (current_node.get_right_child() == rm_node) {
					current_node.set_right_child(null);
				}

				else if (gc.compare(current_node.get_value(), which_node) < 0){
					current_node = (BSTree<T>) current_node.get_right_child();
				} 

				else {
					current_node = (BSTree<T>) current_node.get_left_child();
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
			
			break;
		default:
			
			BSTree<T> new_root = new BSTree<T>(rm_node.get_left_child());
			
			while(new_root.get_right_child() != null){
				new_root = (BSTree<T>) new_root.get_right_child();
			}
			
			rm_node.set_value(new_root.get_value());
			new_root = (BSTree<T>) new_root.get_left_child();
			rm_node.set_left_child(new_root);
			
			break;
		}
		
		
		



	}
	
	
	/**
	 * Re-balance the tree
	 * @return The balanced tree
	 */
	@Override
	public IBSTree<T> balance_tree(){
		
		// Get the list of all the nodes from this root
		List<T> lop = get_descendants();
		
		// Sort the list
				Collections.sort(lop, gc);
		
		if (lop.size() == 1) {
			return new BSTree<T>(lop.get(0));
		}
		
		if(lop.size() == 2) {
			BSTree<T> new_root = new BSTree<T>(lop.get(1));
			BSTree<T> child = new BSTree<T>(lop.get(0));
			new_root.set_left_child(child);
			return new_root;
		}
		
		
		
		// The new root would be the middle node of the list
		int root_index = lop.size() / 2;
		BSTree<T> new_root = new BSTree<T>(lop.get(root_index));
		
		// Get the remaining lists to the left and right of the new root
		List<T> left_list = lop.subList(0, root_index);
		List<T> right_list = lop.subList(root_index + 1, lop.size());
		
		// The left and right child of the new root would be the nodes next to it in the sorted list
		BSTree<T> new_left_node = new BSTree<T>(lop.get(root_index - 1));
		BSTree<T> new_right_node = new BSTree<T>(lop.get(root_index + 1));
		
		// Link all the nodes in the left and right sublist
		BSTree<T> current_left = new_left_node;
		for(T t : left_list){
			if (!t.equals(new_left_node.get_value())){
				BSTree<T> new_left_child = new BSTree<T>(t);
				current_left.set_left_child(new_left_child);
				current_left = new_left_child;
			}
		}
		
		BSTree<T> current_right = new_right_node;
		for(T t : right_list){
			if (!t.equals(new_right_node.get_value())){
			BSTree<T> new_right_child = new BSTree<T>(t);
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
		else if (	(gc.compare(which_value, left_child.get_value()) < 0) && 
				(gc.compare(which_value, right_child.get_value()) > 0) ){
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
	 * @param new_left_tree The new value of the left child
	 */
	private void set_left_child(IBSTree<T> new_left_tree){
		this.left_child = new_left_tree;
	}
	
	/**
	 * Setter to assign the right child of this node.
	 * @param right_child The new value of the right child.
	 */
	private void set_right_child(IBSTree<T> new_right_child){
		this.right_child = new_right_child;
	}
	
	public int number_of_descendants(){
		
		int n_children = 0;
		
		if (left_child == null && right_child == null){
			return 0;
		}
		else if (left_child == null && right_child != null){
			return 1 + right_child.number_of_descendants();
		}
		else if (left_child != null && right_child == null) {
			return 1 + left_child.number_of_descendants();
		}
		
		else {
			n_children += 2;
		}
		
		return n_children + left_child.number_of_descendants() + right_child.number_of_descendants();
	}

	


	@Override
	public void display_tree_in_order() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display_tree_post_order() {
		// TODO Auto-generated method stub
		
	}

	



	
	
	
	
	
}
