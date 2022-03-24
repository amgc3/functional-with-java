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

    /*
     this factory method always returns the same object no matter how many times it's called
     singleton pattern - so we have created a reusable object
     another advantage over using a constructor is being able to return an object that is
     assignment compatible with the return type of the factory method
    */
    public static CarCriterion getRedCarCriterion() {
        // return new RedCarCriterion();
        return RED_CAR_CRITERION;
    }
    private static final RedCarCriterion RED_CAR_CRITERION = new RedCarCriterion();
    static class RedCarCriterion implements CarCriterion {

        @Override
        public boolean test(Car c) {
            return c.colour.equals("Red"); // yes if and only if its colour is red
        }
    }

    static class GasLevelCarCriterion implements CarCriterion {
        private int threshold;
        public GasLevelCarCriterion(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public boolean test(Car c) {
            return c.gasLevel >= threshold;
        }
    }
}
