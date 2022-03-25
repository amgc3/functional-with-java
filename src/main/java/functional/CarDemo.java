package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//class PassengerCountOrder implements Comparator<Car> {
//
//    @Override
//    public int compare(Car o1, Car o2) {
//        return o1.getPassengers().size() - o2.getPassengers().size();
//    }
//}

@FunctionalInterface
interface Criterion<E> {
    boolean test(E c);
}

public class CarDemo {
    public static <E> void showAll(List<E> list) {
        for (E item : list) {
            System.out.println(list);
        }
        System.out.println("------------------------------");
    }

    // Iterable instead of List for best practice
    public static <E> List<E> getByCriterion(Iterable<E> in, Criterion<E> crit) {
        List<E> output = new ArrayList<>();
        for (E item : in) {
            if (crit.test(item)) {
                output.add(item);
            }
        }
        return output;
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
        System.out.println("Red cars:");
        showAll(getByCriterion(cars, Car.getRedCarCriterion()));
        showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(6)));
//        cars.sort(new PassengerCountOrder());
        cars.sort(Car.getGasComparator());
        showAll(cars);
        System.out.println("Two passengers:");
        showAll(getByCriterion(cars, c-> c.getPassengers().size() == 2));
        System.out.println("Four passengers:");
        showAll(getByCriterion(cars, Car.getFourPassengerCriterion()));

    }
}
