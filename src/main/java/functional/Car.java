package functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Car {
    private final int gasLevel;
    private final String colour;
    private final List<String> passengers;
    private final List<String> trunkContents;

    private Car(int gasLevel, String colour, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = gasLevel;
        this.colour = colour;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }
    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        return new Car(gas, color, p, null);
    }

    public static Car withGasColorPassengersAndTrunk(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        return new Car(gas, color, p, Arrays.asList("jack", "wrench", "spare wheel"));
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColour() {
        return colour;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    @Override
    public String toString() {
        return "Car{" +
                "gasLevel=" + gasLevel +
                ", colour='" + colour + '\'' +
                ", passengers=" + passengers +
                (trunkContents != null ? ", trunkContents=" + trunkContents : " no trunk") +
                '}';
    }
}
