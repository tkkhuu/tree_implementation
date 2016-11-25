package test_package;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		if (o1.get_age() - o2.get_age() == 0) {
			return o1.get_name().compareTo(o2.get_name());
		}
		
		return o1.get_age() - o2.get_age();
	}

}
