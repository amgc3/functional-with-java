package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

//class PassengerCountOrder implements Comparator<Car> {
//
//    @Override
//    public int compare(Car o1, Car o2) {
//        return o1.getPassengers().size() - o2.getPassengers().size();
//    }
//}


public class CarDemo {

    public static <E> void showAll(List<E> list) {
        for (E item : list) {
            System.out.println(item);
        }
        System.out.println("------------------------------");
    }

    // Iterable instead of List for best practice
    public static <E> List<E> getByCriterion(Iterable<E> in, Predicate<E> crit) {
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
        System.out.println(("By gas level >= 7:"));
        showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(7)));
        System.out.println(("By gas level >= 4:"));
        showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(4)));
//        cars.sort(new PassengerCountOrder());
        cars.sort(Car.getGasComparator());
        showAll(cars);
        System.out.println("Two passengers:");
        showAll(getByCriterion(cars, c-> c.getPassengers().size() == 2));
        System.out.println("Four passengers:");
        showAll(getByCriterion(cars, Car.getFourPassengerCriterion()));

        // just some examples
//        List<String> colours = Arrays.asList("Red", "LightBlue", "Orange", "Silver", "purple", "LightCoral");
//        showAll(getByCriterion(colours, str -> str.length() > 4));
//        showAll(getByCriterion(colours, str -> Character.isUpperCase(str.charAt(0))));
//
//        LocalDate today = LocalDate.now();
//        List<LocalDate> dates = Arrays.asList(today, today.plusDays(1), today.plusDays(7), today.minusDays(1));
//        showAll(getByCriterion(dates, ld -> ld.isAfter(today)));
        showAll(getByCriterion(cars, Car.getColourCriterion("Red", "Black")));
        Predicate<Car> level7 = Car.getGasLevelCarCriterion(7);
        showAll(getByCriterion(cars, level7));
        Predicate<Car> notLevel7 = level7.negate();
        showAll(getByCriterion(cars, notLevel7));

        Predicate<Car> isRed = Car.getColourCriterion("Red");
        Predicate<Car> fourPassengers = Car.getFourPassengerCriterion();

        Predicate<Car> redFourPassengers = isRed.and(fourPassengers);
        showAll(getByCriterion(cars, redFourPassengers));
        Predicate<Car> isBlack = Car.getColourCriterion("Black");
        Predicate<Car> blackOrFourPassengers = isBlack.or(fourPassengers);
        showAll(getByCriterion(cars, blackOrFourPassengers));





    }
}
