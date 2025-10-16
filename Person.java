// Base class to demonstrate Inheritance and Encapsulation
public class Person {
    private String name; // encapsulated field

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Polymorphic method
    public String getRole() {
        return "Person";
    }
}
