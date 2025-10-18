public class Person {
    private String name; 

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
