package lesson2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class OptionalApp {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.addAll(Arrays.asList(new Integer[]{4,5,6,7,8,9}));
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        //min.ifPresent(v->System.out.println(v)); // 4
        if (min.isPresent()) {
            System.out.println(min.get());
        }
    }
}
