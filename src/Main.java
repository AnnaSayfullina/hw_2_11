import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Stream.*;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(
                new Person("Анна", 30),
                new Person("Олег", 40),
                new Person("Федор", 45),
                new Person("Иван", 20),
                new Person("Ксения",25)
        );
        Stream<Person> stream = personList.stream();
        Stream<Person> stream1 = Stream.empty();
        BiConsumer<Person, Person> minMaxConsumer = (min, max) -> System.out.println("Самый молодой человек " + min + ", самый взрослый -  " + max);
        findMinMax(stream,
                (o1, o2) -> (o1.getAge()>o2.getAge()) ? 1 : (o1.getAge()<o2.getAge() ? -1 : 0),
                minMaxConsumer);
        findMinMax(stream1,
                (o1, o2) -> (o1.getAge()>o2.getAge()) ? 1 : (o1.getAge()<o2.getAge() ? -1 : 0),
                minMaxConsumer);

    }

    /**
     Напишите метод public static void findMinMax, который будет находить в стриме
     минимальный и максимальный элементы в соответствии с порядком, заданным Comparator'ом.
     Данный метод принимает на вход 3 элемента:
     Stream <? extends T> stream
     Comparator <? super T> order
     BiConsumer <? super T, ? super T> minMaxConsumer

     Найденные минимальный и максимальный элементы передавайте в minMaxConsumer следующим образом:
     minMaxConsumer.accept(min, max);
     Если стрим не содержит элементов, то вызывайте:
     minMaxConsumer.accept(null, null);
     */
    public static<T> void findMinMax(Stream<? extends T> stream,
                                     Comparator<? super T> order,
                                     BiConsumer<? super T, ? super T> minMaxConsumer){
            List<T> list = stream.sorted(order).collect(Collectors.toList());
            if (list.isEmpty()){
                minMaxConsumer.accept(null, null);
            } else {
            T min = list.get(0);
            T max = list.get(list.size()-1);
            minMaxConsumer.accept(min, max);
            // пыталась найти max и min через Supplier, но бросает исключение IllegalStateException
            // В чем ошибка?
//            Supplier<Stream<? extends T>> streamSupplier = () -> stream;
//            T min = streamSupplier.get().min(order).get();
//            T max = streamSupplier.get().max(order).get();
        }
    }
}