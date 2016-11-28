package bst_common;


import java.util.List;

import binary_tree.IBinaryTree;

public interface IBSTree<T extends Comparable<T>> extends IBinaryTree<T>{

	public List<? extends T> get_descendants();
	
	public int number_of_descendants();

}
