import java.util.*;

public class CampusNavigator {
    private Map<String, String> buildingToCanteen;
    private Map<String, List<String>> canteenStores;

    public CampusNavigator() {
        buildingToCanteen = new HashMap<>();
        canteenStores = new HashMap<>();

        // Building → Nearest Canteen mapping
        buildingToCanteen.put("PLaza de Corazon Building", "Yellow Food Court");
        buildingToCanteen.put("St.Martha Hall Building", "Yellow Food Court");
        buildingToCanteen.put("San Francisco De Javier Building", "Yellow Food Court");
        buildingToCanteen.put("St.Therese of Liseux Building", "PGN");
        buildingToCanteen.put("St.Gabriel Halll Building", "Yellow Food Court");
        buildingToCanteen.put("St.Raphael Hall Building", "Yellow Food Court");
        buildingToCanteen.put("St.Michael Hall Building", "PGN");
        buildingToCanteen.put("Geronimo G. Nepomuceno Building", "GGN");
        buildingToCanteen.put("Peter G. Nepomuceno Building", "PGN");
        buildingToCanteen.put("Don Juan D. Nepomuceno Building", "PGN");
        buildingToCanteen.put("Archbishop Pedro Santos Building", "APS");
        buildingToCanteen.put("Mamerto G. Nepomuceno Building", "PGN");
        buildingToCanteen.put("Chapel of the Holy Guardian Angel", "APS");
        buildingToCanteen.put("Sister Josefina Nepomuceno Formation Center", "APS");
        buildingToCanteen.put("St.Joseph Hall Building", "APS");
        buildingToCanteen.put("Sacred Heart Building", "APS");
        buildingToCanteen.put("Covered Court", "APS");
        buildingToCanteen.put("Immaculate Heart Gymnasium/Annex", "APS");

        // PGN Food Stores
        canteenStores.put("PGN", Arrays.asList(
            "Basti's Food Court/KIK's Buko",
            "KRISTENJOY",
            "COOP Canteen",
            "Ice Cream House",
            "Belgian Waffles",
            "Khaleb",
            "Zagu",
            "JJJ-C EATERY",
            "HAU MAIN FOOD CANTEEN/SCHOOL SUPPLIES",
            "Fruit Mania",
            "Mister Donut"
        ));

        // APS Food Stores
        canteenStores.put("APS", Arrays.asList(
            "SOY'S BURGER HOUSE",
            "Yum-Yum's ALL-DAY EATS",
            "BIFROST",
            "Jovzzz Eatery",
            "Master Siomai",
            "GRANNY JING'S FOOD STATION",
            "FOOD TO GO HAU APS CANTEEN",
            "HAY COOP CANTEEN",
            "Rina's Food Station",
            "TITA NORM's",
            "WE LUV SNACK",
            "J. CAMILOS"
        ));

        // GGN Food Stores
        canteenStores.put("GGN", Arrays.asList(
            "dalicious",
            "happy-haus DONUTS",
            "Kai-Kai",
            "CHICKEN STAR",
            "NETH EATERY",
            "YET'S SNACK BAR",
            "Figo's",
            "Daily Lunch",
            "G-Meal's CORNER",
            "JULIANROSE"
        ));

        // Yellow Food Court Stores
        canteenStores.put("Yellow Food Court", Arrays.asList(
            "TRES MARIAS",
            "MILKTEA EXPRESS",
            "MAMAI'S KITCHENETTE",
            "SARAH AND KHAYE",
            "Mr. Yogurt",
            "SANTINO's",
            "MR. MACHIATO",
            "MIT'Z SNACK BAR"
        ));
    }

    public Canteen findCanteen(String building) {
        String canteenName = buildingToCanteen.get(building);
        if (canteenName == null) return null;
        List<String> stores = canteenStores.getOrDefault(canteenName, new ArrayList<>());
        return new Canteen(canteenName, stores);
    }

    public Set<String> getValidBuildings() {
        return buildingToCanteen.keySet();
    }
}
