package binary_tree;

import tree.ITree;

public interface IBinaryTree<T extends Comparable<T>> extends ITree<T>{

	public IBinaryTree<T> balance_tree();
	
	public IBinaryTree<T> get_left_child();
	
	public IBinaryTree<T> get_right_child();
	
	public void add_leaf(T new_value);
	
	public void display_tree_pre_order();
	
	public void display_tree_in_order();
	
	public void display_tree_post_order();
	
	
}
