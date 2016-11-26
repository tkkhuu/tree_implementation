package binary_search_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node<T>{
	
	private T value;
	private Node<T> left_node;
	private Node<T> right_node;
	private GenericComparator<T> gc;
	
	public Node (T value, Node<T> first_child, Node<T> second_child){
		
		gc = new GenericComparator<T>();
		
		this.value = value;
		
		if (gc.compare(first_child.get_value(), value) >= 0 && gc.compare(second_child.get_value(), value) < 0){
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
	
	public Node (T value) {
		this.value = value;
		this.left_node = null;
		this.right_node = null;
	}
	
	public Node<T> get_right_child(){
		return right_node;
	}
	
	public Node<T> get_left_child(){
		return left_node;
	}
	
	public T get_value(){
		return value;
	}

	private void set_left_child(Node<T> left_child){
		this.left_node = left_child;
	}
	
	private void set_right_child(Node<T> right_child){
		this.right_node = right_child;
	}
	
	public Node<T> find_node(T target){
		
		Node<T> current_node = this;
		
		while(!current_node.get_value().equals(target)){
			
			if (gc.compare(current_node.get_value(), target) < 0){
				current_node = current_node.get_right_child();
			} else {
				current_node = current_node.get_left_child();
			}
		}
		
		return current_node;
		
	}

	public Node<T> balance_tree(){
		
		// Get the list of all the nodes from this root
		List<T> lop = get_list_of_node();
		
		if (lop.size() == 1) {
			return new Node<T>(lop.get(0));
		}
		
		if(lop.size() == 2) {
			Node<T> new_root = new Node<T>(lop.get(1));
			Node<T> child = new Node<T>(lop.get(0));
			new_root.set_left_child(child);
			return new_root;
		}
		
		// Sort the list
		Collections.sort(lop, gc);
		
		// The new root would be the middle node of the list
		int root_index = lop.size() / 2;
		Node<T> new_root = new Node<T>(lop.get(root_index));
		
		// Get the remaining lists to the left and right of the new root
		List<T> left_list = lop.subList(0, root_index - 1);
		List<T> right_list = lop.subList(root_index + 1, lop.size());
		
		// The left and right child of the new root would be the nodes next to it in the sorted list
		Node<T> new_left_node = new Node<T>(lop.get(root_index - 1));
		Node<T> new_right_node = new Node<T>(lop.get(root_index + 1));
		
		// Link all the nodes in the left and right sublist
		Node<T> current_left = new_left_node;
		for(T t : left_list){
			Node<T> new_left_child = new Node<T>(t);
			current_left.set_left_child(new_left_child);
			current_left = new_left_child;
		}
		
		Node<T> current_right = new_right_node;
		for(T t : right_list){
			Node<T> new_right_child = new Node<T>(t);
			current_right.set_right_child(new_right_child);
			current_right = new_right_child;
		}
		
		//Node<T> new_left = new Node<T>(lop.get((root_index - 1)/ 2));
		//Node<T> new_right = new Node<T>(lop.get((lop.size() - root_index + 1) / 2));
		
		new_root.set_left_child(new_left_node.balance_tree());
		new_root.set_right_child(new_right_node.balance_tree());
		
		return new_root;
	}
	
	public List<T> get_list_of_node(){
		
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
	
	public void show_tree(){
		System.out.println(value);
		if (left_node != null){
			left_node.show_tree();
		}
		if (right_node != null) {
			right_node.show_tree();
		}
	}
	
}
