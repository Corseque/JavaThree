package lesson1.box;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<Fruit> box;

    public Box() {
       this.box = new ArrayList<>();
    }

    public List<Fruit> getBox() {
        return box;
    }

    public <T> void addFruitToBox(Fruit fruit) {
        if (box.isEmpty() || fruit.getClass() == box.get(0).getClass()) {
            box.add(fruit);
        } else {
            System.out.println("Кладешь фрукт не в ту коробку.");
        }
    }

    public <T extends Fruit> double getWeight() {
        if (box.isEmpty()) return 0;
        return box.size() * box.get(0).getWeight();
    }

    public boolean compare(Box<? extends Fruit> box) {
        return this.getWeight() == box.getWeight();
    }
    
    public void pourOverFruitFrom (Box<? extends Fruit> boxFrom) {
        if (boxFrom.getBox().isEmpty()) {
            System.out.println("Коробка, из которой вы хотите пересыпать фрукты - пустая.");
            return;
        }
        if (box.isEmpty() || box.get(0).getClass() == boxFrom.getBox().get(0).getClass()) {
            box.addAll(boxFrom.getBox());
            boxFrom.getBox().clear();
            System.out.println("Фрукты пересыпали.");
        } else {
            System.out.println("В эту коробку нельзя пересыпать фрукты.");
        }
    }
}

