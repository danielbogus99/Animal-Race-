package Graphics;

import mobility.Point;

public class Animal implements  IAnimal,IClonable{

    public boolean eat(int energy) {
        return false;
    }


    public String getAnimaleName() {
        return "";
    }


    public int getSpeed() {
        return 0;
    }


    public boolean move(Point p) {
        return false;
    }
}
