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

public class CarDemo {
    public static void showAll(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
        System.out.println("------------------------------");
    }

    // Iterable instead of List for best practice
    public static List<Car> getCarsByColour(Iterable<Car> cars, String colour) {
        List<Car> carsByColour = new ArrayList<>();
        for (Car car : cars) {
            if (car.getColour().equals(colour)) {
                carsByColour.add(car);
            }
        }
        return carsByColour;
    }

    public static List<Car> getCarsByGasLevel(Iterable<Car> cars, int gasLevel) {
        List<Car> carsByGasLevel = new ArrayList<>();
        for (Car car : cars) {
            if (car.getGasLevel() >= gasLevel) {
                carsByGasLevel.add(car);
            }
        }
        return carsByGasLevel;
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
        showAll(getCarsByColour(cars, "Red"));
        showAll(getCarsByColour(cars, "Black"));
        cars.sort(new PassengerCountOrder());
        showAll(cars);

    }
}
