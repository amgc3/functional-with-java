package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//class PassengerCountOrder implements Comparator<Car> {
//
//    @Override
//    public int compare(Car o1, Car o2) {
//        return o1.getPassengers().size() - o2.getPassengers().size();
//    }
//}

interface CarCriterion {
    boolean test(Car c);  // do you like this one?
}

public class CarDemo {
    public static void showAll(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
        System.out.println("------------------------------");
    }

    // Iterable instead of List for best practice
    public static List<Car> getCarsByCriterion(Iterable<Car> cars, CarCriterion crit) {
        List<Car> carsByColour = new ArrayList<>();
        for (Car car : cars) {
            if (crit.test(car)) {
                carsByColour.add(car);
            }
        }
        return carsByColour;
    }

    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        );
        System.out.println("This is printed by ShowAll(cars)");
        showAll(cars);
        showAll(getCarsByCriterion(cars, Car.getRedCarCriterion()));
        showAll(getCarsByCriterion(cars, Car.getGasLevelCarCriterion(6)));
//        cars.sort(new PassengerCountOrder());
        cars.sort(Car.getGasComparator());
        showAll(cars);

    }
}
