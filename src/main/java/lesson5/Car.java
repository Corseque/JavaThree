package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private static CountDownLatch winner = new CountDownLatch(1);
    private static boolean hasWinner = false;

    private static CyclicBarrier cyclicBarrier;

    public static CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public static void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        Car.cyclicBarrier = cyclicBarrier;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (!hasWinner) {
            try {
                System.out.println(this.name + " победил!");
                hasWinner = true;
                winner.countDown();
                winner.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            cyclicBarrier.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
