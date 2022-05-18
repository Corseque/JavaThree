package lesson5;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownApp {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            int w = i + 1;
            new Thread(() -> {
                try {
                    //task
                    Thread.sleep(random.nextInt(5) * 1000);
                    countDownLatch.countDown();
                    System.out.println("Поток " + w + " готов");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }

        //Ждем всех
        countDownLatch.await();


        System.out.println("Работа завершена");
    }
}
