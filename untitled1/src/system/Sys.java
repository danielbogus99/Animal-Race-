package system;

import mobility.*;
import animals.*;
import Olympics.*;
import java.util.*;
public class Sys
{
    public void main(String[] args)
    {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of animals you want to create: ");
        int n = s.nextInt();
        Animal[] animals = new Animal[n];
        for(int i = 0; i < n; i++)
        {
            System.out.println("Please enter the number of type animal you want to create:");
            System.out.println("1.WaterAnimal");
            System.out.println("2.TerrestrialAnimal");
            System.out.println("3.AirAnimal ");

            switch(s.nextInt())
            {
                case 1:
                    CreateWaterAnimal(n,animals,s);
                case 2:
                    CreateTerrestrialAnimal(n,animals,s);
                case 3:
                    CreateAirAnimal(n,animals,s);

            }
        }
    }
    public void CreateWaterAnimal(int size,Animal[] animals,Scanner s)
    {
        System.out.println("Please enter the type of water animal you want to create: ");
        System.out.println("1.Whale");
        System.out.println("2.Dolphin");
        System.out.println("3.Alligator ");
        switch(s.nextInt())
        {
            case 1:
                CreateWaterAnimal(size,animals,s);


        }
    }
    public void CreateAirAnimal(int size,Animal[] animals,Scanner s)
    {
        System.out.println("Please enter the type of Air animal you want to create: ");
        System.out.println("1.Eagle");
        System.out.println("2.Pigeon");
        switch(s.nextInt())
        {
            case 1:


        }
    }
    public void CreateTerrestrialAnimal(int size,Animal[] animals,Scanner s)
    {
        System.out.println("Please enter the type of Terrestrial animal you want to create: ");
        System.out.println("1.Dog");
        System.out.println("2.Cat");
        System.out.println("3.Snake");
        switch(s.nextInt())
        {
            case 1:


        }
    }
    public void CreateWhale(int size,Animal[] animals,Scanner s)
    {
        int a = Animal.gender.Male.ordinal();
        System.out.println(a);
        //animals[size] = new Whale(0, Animal.gender.Male)
    }
}





