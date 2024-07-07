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

        int n;
        while (true) {
            try {
                System.out.println("Enter the number of animals you want to create: ");
                n = s.nextInt();

                if (n <= 0) {
                    System.out.println("The number must be a positive integer. Please try again.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                s.next(); // clear the invalid input
            }
        }
        Animal[] animals = new Animal[n];
        CreateAnimals(n,animals);
        MainMenu(n,animals);
        s.close();
    }
    /**
     * Creates a water animal based on user input.
     * @param size the size of the animals array
     * @param animals the array of animals
     * @return true if I created an animal  false otherwise
     */
    public boolean CreateWaterAnimal(int size,Animal[] animals) {
        boolean validInput = false;
        System.out.println("Please enter the type of water animal you want to create: ");
        System.out.println("1.Whale");
        System.out.println("2.Dolphin");
        System.out.println("3.Alligator ");
        System.out.println("4.Go Back ");
        Scanner in = new Scanner(System.in);

        while (!validInput)
        {

            switch (in.nextLine())
            {
                case "1": {
                    CreateWhale(size, animals);
                    validInput = true;
                    break;
                }
                case "2": {
                    CreateDolphin(size, animals);
                    validInput = true;
                    break;
                }
                case "3": {
                    CreateAlligator(size, animals);
                    validInput = true;
                    break;
                }
                case "4": {
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
     * @return true if I created an animal  false otherwise
     */
    public boolean CreateAirAnimal(int size,Animal[] animals) {
        boolean validInput = false;
        System.out.println("Please enter the type of Air animal you want to create: ");
        System.out.println("1.Eagle");
        System.out.println("2.Pigeon");
        System.out.println("3.Go Back ");
        Scanner in = new Scanner(System.in);
        while (!validInput)
        {
            switch (in.nextLine()) {
                case "1": {
                    CreateEagle(size, animals);
                    validInput = true;
                    break;
                }
                case "2": {
                    CreatePigeon(size, animals);
                    validInput = true;
                    break;
                }
                case "3": {
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
     * @return true if I created an animal  false otherwise
     */
    public boolean CreateTerrestrialAnimal(int size,Animal[] animals) {
        System.out.println("Please enter the type of Terrestrial animal you want to create: ");
        System.out.println("1.Dog");
        System.out.println("2.Cat");
        System.out.println("3.Snake");
        System.out.println("4.Go Back ");
        boolean validInput = false;
        Scanner in = new Scanner(System.in);

        while (!validInput)
        {
            switch (in.nextLine()) {
                case "1": {
                    CreateDog(size, animals);
                    validInput = true;
                    break;
                }
                case "2": {
                    CreateCat(size, animals);
                    validInput = true;
                    break;
                }
                case "3": {
                    CreateSnake(size, animals);
                    validInput = true;
                    break;
                }
                case "4": {
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
        double weight = 0;
        double speed = 0;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the whale: ");
        name = s1.nextLine();
        validDouble(speed,"speed","whale");
        validDouble(weight,"weight","whale");
        System.out.println("What gender is the whale");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        while (!validInput)
        {
            switch (in.nextLine())
            {
                case "1":
                {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case "2":
                {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case "3":
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

        double diveDepth = 0;
        validDouble(diveDepth,"diveDepth","whale");

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
        double weight = 0;
        double speed = 0;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the alligator: ");
        name = s1.nextLine();
        validDouble(speed,"speed","alligator");
        validDouble(weight,"weight","alligator");
        System.out.println("What gender is the alligator");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        while (!validInput)
        {
            switch (in.nextLine())
            {
                case "1":
                {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case "2":
                {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case "3":
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

        double diveDepth = 0;
        validDouble(diveDepth,"diveDepth","alligator");
        String AreaOfLiving;
        System.out.println("Please enter Area of the Alligator: ");
        Scanner s2 = new Scanner(System.in);
        AreaOfLiving = s2.nextLine();
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
        double weight = 0;
        double speed = 0;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the dolphin: ");
        name = s1.nextLine();
        validDouble(speed,"speed","dolphin");
        validDouble(weight,"weight","dolphin");
        System.out.println("What gender is the dolphin");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        while (!validInput)
        {
            switch (in.nextLine())
            {
                case "1":
                {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case "2":
                {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case "3":
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

        double diveDepth = 0;
        validDouble(diveDepth,"diveDepth","dolphin");
        Dolphin.WaterType waterType = null;
        System.out.println("Enter dolphin water type");
        System.out.println("1. Sea");
        System.out.println("2. Sweet");
        validInput = false;
        Scanner in2 = new Scanner(System.in);
        while (!validInput)
        {
            switch (in2.nextLine())
            {
                case "1":
                {
                    waterType = Dolphin.WaterType.Sea;
                    validInput = true;
                    break;
                }
                case "2":
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
        double weight = 0;
        double speed = 0;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the eagle: ");
        name = s1.nextLine();
        validDouble(speed,"speed","eagle");
        validDouble(weight,"weight","eagle");
        System.out.println("What gender is the eagle");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        while (!validInput)
        {
            switch (in.nextLine())
            {
                case "1":
                {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case "2":
                {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case "3":
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
        double wingspan = 0;
        validDouble(wingspan,"wingspan","eagle");
        double altitudeOfFlight = 0;
        validDouble(altitudeOfFlight,"altitudeOfFlight","eagle");
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
        double weight = 0;
        double speed = 0;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Snake: ");
        name = s1.nextLine();
            validDouble(speed,"speed","pigeon");
            validDouble(weight,"weight","pigeon");
        System.out.println("What gender is the pigeon");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        while (!validInput)
        {
            switch (in.nextLine())
            {
                case "1":
                {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case "2":
                {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case "3":
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
        double wingspan = 0;
        validDouble(wingspan,"wingspan","pigeon");
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
        double weight = 0;
        double speed = 0;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Snake: ");
        name = s1.nextLine();
            validDouble(speed,"speed","dog");
            validDouble(weight,"weight","dog");
        System.out.println("What gender is the dog");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        while (!validInput)
        {
            switch (in.nextLine())
            {
                case "1":
                {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case "2":
                {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case "3":
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

        int noLegs = 0;
        validInt(noLegs,"noLegs","dog");
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
        double weight = 0;
        double speed = 0;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the cat: ");
        name = s1.nextLine();

        while(true)
        {
            validDouble(speed,"speed","cat");
            break;
        }
        while (true)
        {
            validDouble(weight,"weight","cat");
            break;
        }



        System.out.println("What gender is the cat");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        while (!validInput)
        {
            switch (in.nextLine())
            {
                case "1":
                {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case "2":
                {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case "3":
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

        int noLegs = 0;
        validInt(noLegs,"noLegs","cat");
        boolean Castrated = false;
        System.out.println("Please enter if the cat is Castrated: ");
        System.out.println("1. Castrated");
        System.out.println("2.not Castrated");
        validInput = false;
        Scanner in2 = new Scanner(System.in);
        while (!validInput)
        {
            switch (in2.nextLine())
            {
                case "1":
                {
                    Castrated = true;
                    validInput = true;
                    break;
                }
                case "2":
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
        double weight = 0;
        double speed = 0;
        Medal[] medals = new Medal[size];
        System.out.println("Please enter the name of the Snake: ");
        name = s1.nextLine();
            validDouble(speed,"speed","snake");
            validDouble(weight,"weight","snake");
        System.out.println("What gender is the Snake");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Hermaphrodite");
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        while (!validInput)
        {
            switch (in.nextLine())
            {
                case "1":
                {
                    gender = Animal.gender.Male;
                    validInput = true;
                    break;
                }
                case "2":
                {
                    gender = Animal.gender.Female;
                    validInput = true;
                    break;
                }
                case "3":
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

        int noLegs = 0;
        validInt(noLegs,"noLegs","snake");
        double length = 0;
            validDouble(length,"length","Snake");
        Snake.Poisonous poisonous = null;
        System.out.println("Enter if the snake is poisonous or not");
        System.out.println("1. Yes");
        System.out.println("2. Not");
        validInput = false;
        Scanner in2 = new Scanner(System.in);
        while (!validInput)
        {
            switch (in2.nextLine())
            {
                case "1":
                {
                    poisonous = Snake.Poisonous.POISONOUS;
                    validInput = true;
                    break;
                }
                case "2":
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

                switch (s.nextLine()) {
                    case "1":
                        continueLoop=CreateWaterAnimal(i, animals);
                        validInput = true;
                        break;
                    case "2":
                        continueLoop=CreateAirAnimal(i, animals);
                        validInput = true;
                        break;
                    case "3":
                        continueLoop=CreateTerrestrialAnimal(i, animals);
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
        s.nextLine();
        boolean validInput = false;
        while (!validInput)
        {
            System.out.println("Please enter your choice:");
            System.out.println("1.Watch info about all the animals");
            System.out.println("2.Sound of all the animals");
            System.out.println("3.Exit");

            switch (s.nextLine()) {
                case "1":
                {
                    printAnimalInfo(size,animals);
                    break;
                }
                case "2":
                {
                    printAnimalVoices(size,animals);
                    break;
                }
                case "3":
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
    public double validDouble(double value,String str,String str2)
    {
        boolean flag = false;
        Scanner s1 = new Scanner(System.in);
        while (!flag)
        {
            System.out.println("Please enter the "  + str + " of the " + str2 + ": ");
            try {
                value = s1.nextDouble();
                flag = true; // Exit the loop if a valid double is entered
            } catch (Exception e) {
                System.err.println("Invalid input. Please enter a valid number.");
                s1.next(); // Clear the invalid input
            }
        }
        return value;
    }
    public double validInt(int value,String str,String str2)
    {
        boolean flag = false;
        Scanner s1 = new Scanner(System.in);
        while (!flag)
        {
            System.out.println("Please enter the " + str  +" of the " + str2+": ");
            try {
                value = s1.nextInt();
                flag = true; // Exit the loop if a valid double is entered
            } catch (Exception e) {
                System.err.println("Invalid input. Please enter a valid number.");
                s1.next(); // Clear the invalid input
            }
        }
        return value;
    }
}





