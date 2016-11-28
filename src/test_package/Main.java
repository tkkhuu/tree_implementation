package test_package;

import bst.BSTree;
import bst_common.IBSTree;

public class Main {

	public static void main(String argv[]){
	
		
		IBSTree<Integer> first_tree = new BSTree<Integer>(new Integer(14));
		first_tree.add_leaf(new Integer(13));
		first_tree.add_leaf(new Integer(15));
		first_tree.add_leaf(new Integer(16));
		first_tree.add_leaf(new Integer(17));
		first_tree.add_leaf(new Integer(18));
		first_tree.add_leaf(new Integer(19));
		first_tree.add_leaf(new Integer(20));
		first_tree.add_leaf(new Integer(22));
		first_tree.display_tree_pre_order();
		first_tree = (IBSTree<Integer>) first_tree.balance_tree();
		System.out.println("====================");
		first_tree.display_tree_pre_order();
		
		first_tree.remove_node(new Integer(13));
		System.out.println("====================");
		first_tree.display_tree_pre_order();
		System.out.println("====================");
		
		
	}
}
