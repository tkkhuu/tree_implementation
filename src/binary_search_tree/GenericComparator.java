package binary_search_tree;

import java.util.Comparator;

public class GenericComparator<T> implements Comparator<T>{

	@Override
	public int compare(T o1, T o2) {
		if (o1 == null && o2 == null) {
			return 0;
		}
		
		if (o1 == null || o2 == null) {
			return (o1 == null) ? -1 : 1;
		}
		return ((Comparable<T>) o1).compareTo((T) o2);
	}

}
