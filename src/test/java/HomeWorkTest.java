import lesson6.HomeWork;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class HomeWorkTest {
    private HomeWork hw;

    @BeforeEach
    void init() {
        hw = new HomeWork();
    }

    @MethodSource("dataForTestIsArrTrowsRuntimeException")
    @ParameterizedTest
    @DisplayName("Тестирование массива на выбрасывание RuntimeException в методе deleteElemBeforeLast4FromArr")
    void testIsArrTrowsRuntimeException(int[] arr) {
        Assertions.assertThrows(RuntimeException.class, () -> hw.deleteElemBeforeLast4FromArr(arr),"Должна вылетить RuntimeException");
    }

    public static Stream<Arguments> dataForTestIsArrTrowsRuntimeException() {
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random(10);
        for (int i = 0; i < 20; i++) {
            int[] arr = new int[random.nextInt(10)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = random.nextInt(10);
            }
            arguments.add(Arguments.arguments(arr));
        }
        return arguments.stream();
    }

    @MethodSource("dataForDeleteElemBeforeLast4FromArr")
    @ParameterizedTest
    @DisplayName("Проверка результата метода deleteElemBeforeLast4FromArr")
    void testDeleteElemBeforeLast4FromArr(int[] arrInput, int[] arrOutput) {
        Assertions.assertArrayEquals(arrOutput, hw.deleteElemBeforeLast4FromArr(arrInput));
    }

    public static Stream<Arguments> dataForDeleteElemBeforeLast4FromArr() {
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int[] arrInput = new int[(random.nextInt(10) + 1)];
            int last4 = -1;
            for (int j = 0; j < arrInput.length; j++) {
                arrInput[j] = random.nextInt(10);
                last4 = arrInput[j] == 4 ? j : last4;
            }
            if (last4 == -1) {
                last4 = random.nextInt(arrInput.length);
                arrInput[last4] = 4;
            }
            int[] arrOutput = last4 + 1 == arrInput.length ? new int[0] : Arrays.copyOfRange(arrInput, last4 + 1, arrInput.length);
            arguments.add(Arguments.arguments(arrInput, arrOutput));
        }
        return arguments.stream();
    }

    @MethodSource("dataForIsArrOf1and4")
    @ParameterizedTest
    void testIsArrOf1and4(int[] arr, boolean result) {
        Assertions.assertEquals(result, hw.isArrOf1and4(arr));
    }

    public static Stream<Arguments> dataForIsArrOf1and4() {
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.arguments(new int[] {1, 1, 1, 4, 4, 1, 4, 4}, true));
        arguments.add(Arguments.arguments(new int[] {1, 1, 1, 1, 1, 1}, false));
        arguments.add(Arguments.arguments(new int[] {4, 4, 4, 4}, false));
        arguments.add(Arguments.arguments(new int[] {1, 4, 4, 1, 1, 4, 3}, false));
        return arguments.stream();
    }

}
