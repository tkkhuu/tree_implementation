package test_package;

import binary_search_tree.Node;

public class Person implements Comparable<Person>{
	
	private String name;
	private int age;
	
	public Person (String name, int age){
		this.name = name;
		this.age = age;
	}
	
	@Override
	public int compareTo(Person other) {
		
		if (age - other.get_age() == 0) {
			return name.compareTo(other.get_name());
		}
		
		return age - other.get_age();
	}
	
	public String get_name(){
		return name;
	}
	
	public int get_age(){
		return age;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null) {
	        return false;
	    }
		
	    final Person other = (Person) obj;
	    
	    return (name.equals(other.get_name()) && age == other.get_age());
	}
	
	@Override
	public String toString(){
		return "Person(" + name + ", " + age + ")";
	}
	
	public static void main(String argv[]){
		Person tri = new Person ("Tri", 22);
		Person peter = new Person("Peter", 20);
		Person brian = new Person("Brian", 18);
		Person mary = new Person("Mary", 15);
		Person lee = new Person("Lee", 16);
		Person fran = new Person("Fran", 14);
		Person am = new Person("Am", 19);
		
		Node<Person> person_bst = new Node<Person>(brian, 	new Node<Person>(peter, new Node<Person>(tri), new Node<Person>(am)), 
															new Node<Person>(mary, new Node<Person>(fran), new Node<Person>(lee)));
		
		Node<Person> m = person_bst.find_node(new Person("Mary", 15));
		
		//System.out.println(m.get_value().get_name());
		//System.out.println(m.get_value().get_age());
		Node<Person> balanced = person_bst.balance_tree();
		balanced.show_tree();
	}

}
