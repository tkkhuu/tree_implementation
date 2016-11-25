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

	public Node<T> balance_tree(List<T> lop){
		
		if (left_node == null && right_node == null){
			return this;
		}
		
		Collections.sort(lop, gc);
		
		int root_index = lop.size() / 2;
		
		Node<T> new_root = new Node<T>(lop.get(root_index));
		
		List<T> left_list = lop.subList(0, root_index - 1);
		List<T> right_list = lop.subList(root_index + 1, lop.size());
		
		Node<T> new_left = new Node<T>(lop.get((root_index - 1)/ 2));
		Node<T> new_right = new Node<T>(lop.get((lop.size() - root_index + 1) / 2));
		
		new_root.set_left_child(new_left.balance_tree(left_list));
		new_root.set_right_child(new_right.balance_tree(right_list));
		
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
	
}
