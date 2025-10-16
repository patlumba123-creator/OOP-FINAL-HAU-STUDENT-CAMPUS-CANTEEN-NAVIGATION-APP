import java.util.List;

// Encapsulation: private fields with getters
public class Canteen {
    private String name;
    private List<String> stores;

    public Canteen(String name, List<String> stores) {
        this.name = name;
        this.stores = stores;
    }

    public String getName() { return name; }
    public List<String> getStores() { return stores; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + " Canteen\nStores:");
        for (String store : stores) {
            sb.append("\n - ").append(store);
        }
        return sb.toString();
    }
}
