package lesson6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class HomeWork {
    private static final Logger logger = LogManager.getLogger(HomeWork.class);

    public static void main(String[] args) {
        System.out.println(Arrays.toString(deleteElemBeforeLast4FromArr(new int[]{1, 2, 3, 4, 5, 5, 6, 4, 8 ,9})));
        System.out.println("верно? " + isArrOf1and4(new int[]{4, 4, 4 ,4}));
    }

    public static int[] deleteElemBeforeLast4FromArr (int[] arr) {
        int position = -1;
        for (int i = 0; i < arr.length; i++) {
            position = arr[i] == 4 ? i : position;
        }
        if (position == -1) throw new RuntimeException("Не корректный входной массив");
        return Arrays.copyOfRange(arr, position + 1,arr.length);
    }

    public static boolean isArrOf1and4(int[] arr) {
//        int count = (int) Arrays.stream(arr).filter(one -> one != 1).count();
//        if (count == 0) return false;
//        count = (int) Arrays.stream(arr).filter(one -> one != 1).filter(four -> four != 4).count();
//        if (count == 0) return true;
        return (Arrays.stream(arr).filter(one -> one != 1).filter(four -> four != 4).count() == 0
                & Arrays.stream(arr).filter(four -> four != 4).filter(one -> one != 1).count() == 0
                & Arrays.stream(arr).filter(one -> one != 1).count()!= 0 & Arrays.stream(arr).filter(four -> four != 4).count() !=0);

    }
}
