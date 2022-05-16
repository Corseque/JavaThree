package lesson4;


public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        PrintLetter printLetter = demo.new PrintLetter();
        new Thread(demo.new PrintThread(printLetter, 'B')).start();
        new Thread(demo.new PrintThread(printLetter, 'A')).start();
        new Thread(demo.new PrintThread(printLetter, 'C')).start();
    }

    private class PrintLetter {
        private char letter = 'A';

        public char getLetter() {
            return letter;
        }

        public void print() {
            System.out.print(letter);

        }

        public void nextLetter() {
            switch (letter) {
                case 'A': {
                    letter = 'B';
                    break;
                }
                case 'B': {
                    letter = 'C';
                    break;
                }
                case 'C': {
                    letter = 'A';
                    break;
                }
            }
        }
    }

    private class PrintThread implements Runnable {
        private PrintLetter printLetter;
        private char letter;

        public PrintThread(PrintLetter printLetter, char letter) {
            this.printLetter = printLetter;
            this.letter = letter;
        }

        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                synchronized (printLetter) {
                    while(letter != printLetter.getLetter()) {
                        try {
                            printLetter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    printLetter.print();
                    printLetter.nextLetter();
                    printLetter.notifyAll();
                }
            }
        }
    }
}
