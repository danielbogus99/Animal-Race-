package Graphics;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Competition {
    private String name;
    private String type;
    private List<Animal> animals;
    private ArrayList<Integer> AirPath;
    private ArrayList<Integer> WaterPath;
    public Competition(String name, String type)
    {
        this.name = name;
        this.type = type;
        this.animals = new ArrayList<>();
        AirPath = new ArrayList<>();
        AirPath.add(1);
        AirPath.add(2);
        AirPath.add(3);
        AirPath.add(4);
        AirPath.add(5);
        WaterPath = new ArrayList<>();
        WaterPath.add(1);
        WaterPath.add(2);
        WaterPath.add(3);
        WaterPath.add(4);
    }


    public Competition getCompetition() {
        return this;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }
    public ArrayList<Integer> getAirPath() {
        return AirPath;
    }
    public ArrayList<Integer> getWaterPath() {
        return WaterPath;
    }
    public void deleteWaterPath(int path) {
        WaterPath.remove(path);
    }
    public void deleteAirPath(int path) {
        AirPath.remove(path);
    }
//    public boolean CheckAvailableWaterPath(int path)
//    {
//        if (Arrays.asList(waterPath).contains(path))
//        {
//
//        }
//        else
//        {
//
//        }
//    }
//    public boolean CheckAvailableAirPath(int path)
//    {
//
//    }

}
