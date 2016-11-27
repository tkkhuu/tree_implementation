package test_package;


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
	
	

}
