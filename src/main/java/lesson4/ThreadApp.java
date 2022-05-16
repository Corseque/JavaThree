package lesson4;

public class ThreadApp {
    public static final Object obj = new Object();
    public static char letter = 'A';

    public static void main(String[] args) {
        ThreadApp pl = new ThreadApp();
        new Thread(() -> {
            pl.printLetter('A');
        }).start();
        new Thread(() -> {
            pl.printLetter('B');
        }).start();
        new Thread(() -> {
            pl.printLetter('C');
        }).start();
    }

    public static void printAndSwitchLetter() {
        switch (letter) {
            case 'A': {
                System.out.print('A');
                letter = 'B';
                break;
            }
            case 'B': {
                System.out.print('B');
                letter = 'C';
                break;
            }
            case 'C': {
                System.out.print('C');
                letter = 'A';
                break;
            }
        }
    }

    public void printLetter(char printLetter) {
        synchronized (obj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != printLetter) {
                        obj.wait();
                    }
                    printAndSwitchLetter();
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
