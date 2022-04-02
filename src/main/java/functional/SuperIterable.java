package functional;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E>{
    private final Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

    // same this and forEach are interchangeable
//    public void forEvery(Consumer<E> consumer) {
//        // every item in self will be passed as an arg to the consumer behaviour
//        for (E e : self) {
//            consumer.accept(e);
//        }
//    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> results = new ArrayList<>();
        self.forEach(e -> results.add(op.apply(e)));
        return new SuperIterable<>(results);

    }

    public SuperIterable<E> filter(Predicate<E> pred) {
        List<E> results = new ArrayList<>();
        self.forEach(e -> {
            if (pred.test(e)) results.add(e);
        });
//        for (E element : self) {
//            if (pred.test(element)) {
//                results.add(element);
//            }
//        }
        return new SuperIterable<>(results);
    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(
                Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "LimeGreen")
        );

        strings.forEach(s -> System.out.println("> " + s));

        SuperIterable<String> upperCase =
                strings.filter(s -> Character.isUpperCase(s.charAt(0)));

        System.out.println("-------------------------------");
        upperCase.forEach(s -> System.out.println("> " + s));

        System.out.println("-------------------------------");
        strings.map(String::toUpperCase)
                .forEach(System.out::println); // x -> System.out.println(x)

        strings.forEach(s -> System.out.println("> " + s));
    }
}
