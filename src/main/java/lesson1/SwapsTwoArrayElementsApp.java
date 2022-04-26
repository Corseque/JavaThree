package lesson1;

import java.util.Arrays;

//1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);

public class SwapsTwoArrayElementsApp {

    public static void main(String[] args) {
       Integer[] arr1 = new Integer[]{1,2,3,4};
       Arrays.stream(swapsTwoArrayElements(arr1, 0, 3))
               .forEach(elem -> System.out.print(elem + " "));
       System.out.println();
       Double[] arr2 = new Double[]{1.0,2.0,3.0,4.0};
       Arrays.stream(swapsTwoArrayElements(arr2, 1, 2))
               .forEach(elem -> System.out.print(elem + " "));
    }

    public static <T extends Number> T[] swapsTwoArrayElements(T[] arr, int firstElem, int secondElem) {
        T auxiliaryElem = arr[firstElem];
        arr[firstElem] = arr[secondElem];
        arr[secondElem] = auxiliaryElem;
        return arr;
    }
}
