package system;

import mobility.*;
import animals.*;
import Olympics.*;
import java.util.*;
/**
 * The Sys class provides functionality to create and manage various types of animals.
 */
public class Sys
{
    public void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of animals you want to create: ");
        int n = s.nextInt();
        Animal[] animals = new Animal[n];
        CreateAnimals(n,animals);
        MainMenu(n,animals);
    }
    /**
     * Creates a water animal based on user input.
     * @param size the size of the animals array
     * @param animals the array of animals
     * @param s the scanner object for user input
     * @return true if I created an animal  false otherwise
     */
    public boolean CreateWaterAnimal(int size,Animal[] animals,Scanner s) {
        boolean validInput = false;
        System.out.println("Please enter the type of water animal you want to create: ");
        System.out.println("1.Whale");
        System.out.println("2.Dolphin");
        System.out.println("3.Alligator ");
        System.out.println("4.Go Back ");
        while (!validInput)
        {
            switch (s.nextInt()) {
                case 1: {
                    CreateWhale(size, animals);
                    validInput = true;
                    break;
                }
                case 2: {
                    CreateDolphin(size, animals);
                    validInput = true;
                    break;
                }
                case 3: {
                    CreateAlligator(size, animals);
                    validInput = true;
                    break;
                }
                case 4: {
                    return false;

                }
                default:
                    System.out.println("Invalid input");

            }
        }
        return true;
    }
    /**
     * Creates an air animal based on user input.
     * @param size the size of the animals array
     * @param animals the array of animals
     * @param s the scanner object for user input
     * @return true if I created an animal  false otherwise
     */
    public boolean CreateAirAnimal(int size,Animal[] animals,Scanner s) {
        boolean validInput = false;
        System.out.println("Please enter the type of Air animal you want to create: ");
        System.out.println("1.Eagle");
        System.out.println("2.Pigeon");
        System.out.println("3.Go Back ");
        while (!validInput)
        {
            switch (s.nextInt()) {
                case 1: {
                    CreateEagle(size, animals);
                    validInput = true;
                    break;
                }
                case 2: {
                    CreatePigeon(size, animals);
                    validInput = true;
                    break;
                }
                case 3: {
                    return false;
                }
                default: {
                    System.out.println("Invalid input");
                    break;
                }

            }
        }
        return true;
    }
    /**
     * Creates a terrestrial animal based on user input.
     * @param size the size of the animals array
     * @param animals the array of animals
     * @param s the scanner object for user input
     * @return true if I created an animal  false otherwise
     */
    public boolean CreateTerrestrialAnimal(int size,Animal[] animals,Scanner s) {
        System.out.println("Please enter the type of Terrestrial animal you want to create: ");
        System.out.println("1.Dog");
        System.out.println("2.Cat");
        System.out.println("3.Snake");
        System.out.println("4.Go Back ");
        boolean validInput = false;
        while (!validInput)
        {
            switch (s.nextInt()) {
                case 1: {
                    CreateDog(size, animals);
                    validInput = true;
                    break;
                }
                case 2: {
                    CreateCat(size, animals);
                    validInput = true;
                    break;
                }
                case 3: {
                    CreateSnake(size, animals);
                    validInput = true;
                    break;
                }
                case 4: {
                    return false;
                }
                default:
                    System.out.println("Invalid input");

            }
        }
        return true;
    }
    /**
     * Creates a Whale object and adds it to the animals array.
     * @param size the size of the animals array
     * @param animals the array of animals
     */
    public void CreateWhale(int size, Animal[] animals) {
        Scanner s1 = new Scanner(System.in);
        Animal.gender gender = null;
        String name;
        double weight;
        double speed;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Whale: ");
        name = s1.nextLine();
        System.out.println("Please enter the weight of the Whale: ");
        weight = s1.nextDouble();

        System.out.println("Please enter the speed of the Whale: ");
        speed = s1.nextDouble();
        s1.nextLine();
        System.out.println("What gender is the whale");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        while (!validInput)
        {
        switch (s1.nextInt())
        {
            case 1: {
                gender = Animal.gender.Male;
                validInput = true;
                break;
            }
            case 2: {
                gender = Animal.gender.Female;
                validInput = true;
                break;
            }
            case 3: {
                gender = Animal.gender.Hermaphrodite;
                validInput = true;
                break;
            }
            default:
                System.out.println("Invalid input");
                break;
           }
        }
        double diveDepth;
        System.out.println("Please enter dive depth of the Whale: ");
        diveDepth = s1.nextDouble();

        String food_Type;
        System.out.println("Please enter food type of the Whale: ");
        Scanner s2 = new Scanner(System.in);
        food_Type = s2.nextLine();
        animals[size] = new Whale(0, gender, name, weight, speed, medals, diveDepth, food_Type);

    }
    /**
     * Creates an Alligator object and adds it to the animals array.
     * @param size the size of the animals array
     * @param animals the array of animals
     */
    public void CreateAlligator(int size, Animal[] animals) {
        Scanner s1 = new Scanner(System.in);
        Animal.gender gender = null;
        String name;
        double weight;
        double speed;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Alligator: ");
        name = s1.nextLine();
        System.out.println("Please enter the weight of the Alligator: ");
        weight = s1.nextDouble();

        System.out.println("Please enter the speed of the Alligator: ");
        speed = s1.nextDouble();
        s1.nextLine();
        System.out.println("What gender is the Alligator");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1:
                {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case 2:
                {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case 3:
                {
                    gender = Animal.gender.Hermaphrodite;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        double diveDepth;
        System.out.println("Please enter dive depth of the Alligator: ");
        diveDepth = s1.nextDouble();
        String AreaOfLiving;
        System.out.println("Please enter Area of the Alligator: ");
        AreaOfLiving = s1.nextLine();
        AreaOfLiving=s1.nextLine();
        animals[size] = new Alligator(0, gender, name, weight, speed, medals, diveDepth, AreaOfLiving);
    }
    /**
     * Creates a Dolphin object and adds it to the animals array.
     * @param size the size of the animals array
     * @param animals the array of animals
     */
    public void CreateDolphin(int size, Animal[] animals) {
        Scanner s1 = new Scanner(System.in);
        Animal.gender gender = null;
        String name;
        double weight;
        double speed;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Dolphin: ");
        name = s1.nextLine();
        System.out.println("Please enter the weight of the Dolphin: ");
        weight = s1.nextDouble();

        System.out.println("Please enter the speed of the Dolphin: ");
        speed = s1.nextDouble();
        s1.nextLine();
        System.out.println("What gender is the Dolphin");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");

        boolean validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1: {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case 2: {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case 3: {
                    gender = Animal.gender.Hermaphrodite;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        double diveDepth;
        System.out.println("Please enter dive depth of the Dolphin: ");
        diveDepth = s1.nextDouble();
        Dolphin.WaterType waterType = null;
        System.out.println("Enter dolphin water type");
        System.out.println("1. Sea");
        System.out.println("2. Sweet");
        validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1:
                {
                    waterType = Dolphin.WaterType.Sea;
                    validInput = true;
                    break;
                }
                case 2:
                {
                    waterType = Dolphin.WaterType.Sweet;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

        animals[size] = new Dolphin(0, gender, name, weight, speed, medals, diveDepth, waterType);

    }
    /**
     * Creates an Eagle object and adds it to the animals array.
     * @param size the size of the animals array
     * @param animals the array of animals
     */
    public void CreateEagle(int size, Animal[] animals) {
        Scanner s1 = new Scanner(System.in);
        Animal.gender gender = null;
        String name;
        double weight;
        double speed;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Eagle: ");
        name = s1.nextLine();
        System.out.println("Please enter the weight of the Eagle: ");
        weight = s1.nextDouble();

        System.out.println("Please enter the speed of the Eagle: ");
        speed = s1.nextDouble();
        s1.nextLine();
        System.out.println("What gender is the Eagle");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1: {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case 2: {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case 3: {
                    gender = Animal.gender.Hermaphrodite;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        double wingspan;
        System.out.println("Please enter the wingspan of the Eagle: ");
        wingspan = s1.nextDouble();
        double altitudeOfFlight;
        System.out.println("Please enter the altitude of flight of the Eagle: ");
        altitudeOfFlight = s1.nextDouble();
        animals[size] = new Eagle(0, gender, name, weight, speed, medals,wingspan, altitudeOfFlight);
    }
    /**
     * Creates a Pigeon object and adds it to the animals array.
     * @param size the size of the animals array
     * @param animals the array of animals
     */
    public void CreatePigeon(int size, Animal[] animals) {
        Scanner s1 = new Scanner(System.in);
        Animal.gender gender = null;
        String name;
        double weight;
        double speed;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Pigeon: ");
        name = s1.nextLine();
        System.out.println("Please enter the weight of the Pigeon: ");
        weight = s1.nextDouble();

        System.out.println("Please enter the speed of the Pigeon: ");
        speed = s1.nextDouble();
        s1.nextLine();
        System.out.println("What gender is the Pigeon");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1: {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case 2: {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case 3: {
                    gender = Animal.gender.Hermaphrodite;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        double wingspan;
        System.out.println("Please enter the wingspan of the Pigeon: ");
        wingspan = s1.nextDouble();
        String family;
        System.out.println("Please enter the family of the pigeon: ");
        Scanner s2 = new Scanner(System.in);
        family = s2.nextLine();

        animals[size] = new Pigeon(0, gender, name, weight, speed, medals,wingspan, family);
    }
    /**
     * Creates a Dog object and adds it to the animals array.
     * @param size the size of the animals array
     * @param animals the array of animals
     */
    public void CreateDog(int size, Animal[] animals){
        Scanner s1 = new Scanner(System.in);
        Animal.gender gender = null;
        String name;
        double weight;
        double speed;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Dog: ");
        name = s1.nextLine();
        System.out.println("Please enter the weight of the Dog: ");
        weight = s1.nextDouble();

        System.out.println("Please enter the speed of the Dog: ");
        speed = s1.nextDouble();
        s1.nextLine();
        System.out.println("What gender is the Dog");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1: {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case 2: {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case 3: {
                    gender = Animal.gender.Hermaphrodite;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        int noLegs;
        System.out.println("Please enter the number of legs of the Dog: ");
        noLegs = s1.nextInt();
        String breed;
        System.out.println("Please enter the breed of the Dog: ");
        Scanner s2 = new Scanner(System.in);
        breed = s2.nextLine();

        animals[size] = new Dog(0, gender, name, weight, speed, medals,noLegs, breed);
    }
    /**
     * Creates a Cat object and adds it to the animals array.
     * @param size the size of the animals array
     * @param animals the array of animals
     */
    public void CreateCat(int size, Animal[] animals){
        Scanner s1 = new Scanner(System.in);
        Animal.gender gender = null;
        String name;
        double weight;
        double speed;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Cat: ");
        name = s1.nextLine();
        System.out.println("Please enter the weight of the Cat: ");
        weight = s1.nextDouble();

        System.out.println("Please enter the speed of the Cat: ");
        speed = s1.nextDouble();
        s1.nextLine();
        System.out.println("What gender is the Cat");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1: {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case 2: {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case 3: {
                    gender = Animal.gender.Hermaphrodite;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        int noLegs;
        System.out.println("Please enter the number of legs of the Cat: ");
        noLegs = s1.nextInt();
        boolean Castrated = false;
        System.out.println("Please enter if the cat is Castrated: ");
        System.out.println("1. Castrated");
        System.out.println("2.not Castrated");
        validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1:
                {
                    Castrated = true;
                    validInput = true;
                    break;
                }
                case 2:
                {
                    Castrated = false;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        animals[size] = new Cat(0, gender, name, weight, speed, medals,noLegs,Castrated);
    }
    /**
     * Creates a Snake object and adds it to the animals array.
     * @param size the size of the animals array
     * @param animals the array of animals
     */
    public void CreateSnake(int size, Animal[] animals){
        Scanner s1 = new Scanner(System.in);
        Animal.gender gender = null;
        String name;
        double weight;
        double speed;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Snake: ");
        name = s1.nextLine();
        System.out.println("Please enter the weight of the Snake: ");
        weight = s1.nextDouble();

        System.out.println("Please enter the speed of the Snake: ");
        speed = s1.nextDouble();
        s1.nextLine();
        System.out.println("What gender is the Snake");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1: {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case 2: {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case 3: {
                    gender = Animal.gender.Hermaphrodite;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        int noLegs;
        System.out.println("Please enter the number of legs of the Snake: ");
        noLegs = s1.nextInt();
        double length;
        System.out.println("Please enter the length of the Snake: ");
        length = s1.nextDouble();
        Snake.Poisonous poisonous = null;
        System.out.println("Enter if the snake is poisonous or not");
        System.out.println("1. Yes");
        System.out.println("2. Not");
        validInput = false;
        while (!validInput)
        {
            switch (s1.nextInt())
            {
                case 1:
                {
                    poisonous = Snake.Poisonous.POISONOUS;
                    validInput = true;
                    break;
                }
                case 2:
                {
                    poisonous = Snake.Poisonous.NON_POISONOUS;
                    validInput = true;
                    break;
                }
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        animals[size] = new Snake(0, gender, name, weight, speed, medals,noLegs, poisonous,length);
    }
    /**
     * Prints the information of all animals in the zoo.
     *
     * @param size the number of animals in the zoo
     * @param animals the array of animals
     */
    public void printAnimalInfo(int size, Animal[] animals){
        for (int i = 0; i < size; i++) {
            System.out.println(animals[i]);
        }
    }
    /**
     * Prints the sounds made by all animals in the zoo.
     *
     * @param size the number of animals in the zoo
     * @param animals the array of animals
     */
    public void printAnimalVoices(int size, Animal[] animals){
        for (int i = 0; i < size; i++) {
            animals[i].makeSound();
        }
    }
    /**
     * Creates animals and adds them to the animals array.
     * The type of animal to create is determined by user input.
     *
     * @param size the number of animals to create
     * @param animals the array of animals
     */
    public void CreateAnimals(int size, Animal[] animals){
        Scanner s = new Scanner(System.in);
        boolean continueLoop = true;
        for (int i = 0; i < size;)
        {
            boolean validInput = false;
            while (!validInput)
            {
                System.out.println("Please enter the type of animal you want to create:");
                System.out.println("1. WaterAnimal");
                System.out.println("2. AirAnimal");
                System.out.println("3. TerrestrialAnimal");

                switch (s.nextInt()) {
                    case 1:
                        continueLoop=CreateWaterAnimal(i, animals, s);
                        validInput = true;
                        break;
                    case 2:
                        continueLoop=CreateAirAnimal(i, animals, s);
                        validInput = true;
                        break;
                    case 3:
                        continueLoop=CreateTerrestrialAnimal(i, animals, s);
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
            if (continueLoop)
            {
                i++;
            }
        }
    }
    /**
     * Displays the main menu and handles user input to print animal information,
     * print animal voices, or exit the program.
     *
     * @param size the number of animals in the zoo
     * @param animals the array of animals
     */
    public void MainMenu(int size, Animal[] animals){
        Scanner s = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput)
        {
            System.out.println("Please enter your choice:");
            System.out.println("1.Watch info about all the animals");
            System.out.println("2.Sound of all the animals");
            System.out.println("3.Exit");

            switch (s.nextInt()) {
                case 1:
                {
                    printAnimalInfo(size,animals);
                    break;
                }
                case 2:
                {
                    printAnimalVoices(size,animals);
                    break;
                }
                case 3:
                {
                    validInput = true;
                    break;
                }

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

}





