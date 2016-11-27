package test_package;

import binary_search_tree.BSNode;

public class Main {

	public static void main(String argv[]){
	
		
		BSNode<Integer> first_tree = new BSNode<Integer>(new Integer(14));
		first_tree.add_node(new Integer(13));
		first_tree.add_node(new Integer(15));
		first_tree.add_node(new Integer(16));
		first_tree.add_node(new Integer(17));
		first_tree.add_node(new Integer(18));
		first_tree.add_node(new Integer(19));
		first_tree.add_node(new Integer(20));
		first_tree.add_node(new Integer(22));
		first_tree.show_tree();
		first_tree = first_tree.balance_tree();
		System.out.println("====================");
		first_tree.show_tree();
		
		first_tree.remove_node(new Integer(13));
		System.out.println("====================");
		first_tree.show_tree();
		System.out.println("====================");
		
		
	}
}
