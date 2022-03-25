package functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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


    public static Criterion<Car> getFourPassengerCriterion() {
        return c -> c.getPassengers().size() == 4;
    }
    /*
     this factory method always returns the same object no matter how many times it's called
     singleton pattern - so we have created a reusable object
     another advantage over using a constructor is being able to return an object that is
     assignment compatible with the return type of the factory method
    */
    public static Criterion<Car> getRedCarCriterion() {
        // return new RedCarCriterion();
        return RED_CAR_CRITERION;
    }
    private static final Criterion<Car> RED_CAR_CRITERION = c -> c.colour.equals("Red");


//    private static final CarCriterion RED_CAR_CRITERION = new CarCriterion() {
//
//        @Override
//        public boolean test(Car c) {
//            return c.colour.equals("Red");
//        }
//    };
//    private static final CarCriterion RED_CAR_CRITERION = new /* RedCarCriterion();
//    private static class RedCarCriterion implements */ CarCriterion() {
//
//        @Override
//        public boolean test(Car c) {
//            return c.colour.equals("Red");
//        }
//    };
    public static Criterion<Car> getGasLevelCarCriterion(int threshold) {
    return c -> c.gasLevel >= threshold;
}


//    public static Criterion<Car> getGasLevelCarCriterion(int threshold) {
//        return new Criterion<Car>() {
//            @Override
//            public boolean test(Car c) {
//                return c.gasLevel >= threshold;
//            }
//        };
//    }

//    public static Criterion<Car> getGasLevelCarCriterion(int threshold) {
//        return new GasLevelCarCriterion(threshold);
//    }
//
//    private static class GasLevelCarCriterion implements Criterion<Car> {
//        private int threshold;
//        public GasLevelCarCriterion(int threshold) {
//            this.threshold = threshold;
//        }
//
//        @Override
//        public boolean test(Car c) {
//            return c.gasLevel >= threshold;
//        }
//    }

    public static Comparator<Car> getGasComparator() {
        return gasComparator;
    }

    private static final Comparator<Car> gasComparator =
            (o1, o2) -> o1.gasLevel - o2.gasLevel;

//    private static final Comparator<Car> gasComparator = new Comparator<Car>() {
//
//        @Override
//        public int compare(Car o1, Car o2) {
//            return o1.gasLevel - o2.gasLevel;
//        }
//    };
//    private static final Comparator<Car> gasComparator = new CarGasComparator();
//    private static class CarGasComparator implements Comparator<Car> {
//
//        @Override
//        public int compare(Car o1, Car o2) {
//            return o1.gasLevel - o2.gasLevel;
//        }
//    }
}
