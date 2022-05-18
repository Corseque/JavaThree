package lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import static java.lang.Math.round;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Car.setCyclicBarrier(new CyclicBarrier(CARS_COUNT + 1));
        Tunnel.setSemaphore(new Semaphore(round(CARS_COUNT / 2)));

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        //тут ждём пока все машины подготовяться
        sleep();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        await();

        //тут ждём пока все машины финишируют
        sleep();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        await();
    }

    private static void sleep() {
        while (Car.getCyclicBarrier().getNumberWaiting() < 4) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void await() {
        try {
            Car.getCyclicBarrier().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

