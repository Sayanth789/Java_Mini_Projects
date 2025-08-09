import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Guitar> guitars;

    public Inventory()  {
        guitars = new ArrayList<>();
    }
    public void addGuitar(String serialNumber, double price, GuitarSpec spec) {
        Guitar guitar = new Guitar(serialNumber, price, spec);
        guitars.add(guitar);
    }

    public List<Guitar> search(GuitarSpec searchSpec) {
        List<Guitar> matchingGuitars = new ArrayList<>();
        for (Guitar guitar: guitars) {
            GuitarSpec spec = guitar.getSpec();
            if (!spec.getBuilder().equalsIgnoreCase(searchSpec.getBuilder())) continue;
            if (!spec.getModel().equalsIgnoreCase(searchSpec.getModel())) continue;
            if (!spec.getType().equalsIgnoreCase(searchSpec.getType())) continue;
            if (!spec.getBackWood().equalsIgnoreCase(searchSpec.getBackWood()))  continue;
            if (!spec.getTopWood().equalsIgnoreCase(searchSpec.getTopWood())) continue;

            matchingGuitars.add(guitar);

        }
        return matchingGuitars;
    }
}