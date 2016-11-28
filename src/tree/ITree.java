package tree;

public interface ITree<T extends Comparable<T>> {
	
	public void add_tree(ITree<T> which_tree);
	
	public void remove_node(T which_node);
	
	public ITree<T> search_tree(T which_tree);
	
	public boolean has_node(T which_node);
	
	public String get_tree_type();
	
	public T get_value();
	
}
