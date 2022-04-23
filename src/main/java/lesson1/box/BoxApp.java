package lesson1.box;

/*
Задача:
a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу
фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можно использовать ArrayList;
d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта
и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую
коробку с той, которую подадут в compare() в качестве параметра. true – если их массы
равны, false в противоположном случае. Можно сравнивать коробки с яблоками и
апельсинами;
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются
объекты, которые были в первой;
g. Не забываем про метод добавления фрукта в коробку.

 */

public class BoxApp {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Apple> appleBox1 = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Apple apple = new Apple();
        Orange orange = new Orange();


        appleBox.addFruitToBox(apple);
        appleBox.addFruitToBox(apple);
        appleBox.addFruitToBox(apple);
        appleBox.addFruitToBox(apple);
        appleBox.addFruitToBox(orange);
        appleBox.addFruitToBox(apple);


        System.out.println("Вес коробки с яблоками: " + appleBox.getWeight());

        orangeBox.addFruitToBox(orange);
        orangeBox.addFruitToBox(orange);
        orangeBox.addFruitToBox(orange);
        orangeBox.addFruitToBox(orange);

        System.out.println("Вес коробки с апельсинами: " +orangeBox.getWeight());

        if (appleBox.compare(orangeBox)) {
            System.out.println("Коробка с яблоками равна по весу коробке с апельсинами.");
        } else {
            System.out.println("Коробка с яблоками не равна по весу коробке с апельсинами.");
        }

        appleBox.pourOverFruitFrom(appleBox1);
        orangeBox.pourOverFruitFrom(appleBox);
        appleBox1.pourOverFruitFrom(appleBox);
        System.out.println("Вес 1 коробки с яблоками: " + appleBox.getWeight());
        System.out.println("Вес 2 коробки с яблоками: " + appleBox1.getWeight());


    }
}
