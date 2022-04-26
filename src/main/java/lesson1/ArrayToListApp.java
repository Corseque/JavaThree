package lesson1;

//Написать метод, который преобразует массив в ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayToListApp {

    public static void main(String[] args) {
        Integer[] arr1 = new Integer[]{1,2,3,4};
        System.out.println(arrayToList(arr1));
        Double[] arr2 = new Double[]{1.0,2.0,3.0,4.0};
        System.out.println(arrayToList(arr2));
    }

    public static <T> ArrayList arrayToList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }
}
