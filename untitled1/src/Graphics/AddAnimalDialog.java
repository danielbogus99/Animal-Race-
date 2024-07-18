package Graphics;
import Olympics.Medal;
import animals.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class AddAnimalDialog extends JDialog
{
    private String selectedAnimal = null;
    private Animal selectedAnimalObject = null;
    public AddAnimalDialog(JFrame parent, String raceType) {
        super(parent, "Animal Selection", true); // Make the dialog modal
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        JPanel animalPanel = new JPanel();
        animalPanel.setLayout(new GridLayout(4, 4));
        JRadioButton dogButton = new JRadioButton("Dog");
        JRadioButton catButton = new JRadioButton("Cat");
        JRadioButton alligatorButton = new JRadioButton("Alligator");
        JRadioButton dolphinButton = new JRadioButton("Dolphin");
        JRadioButton eagleButton = new JRadioButton("Eagle");
        JRadioButton pigeonButton = new JRadioButton("Pigeon");
        JRadioButton snakeButton = new JRadioButton("Snake");
        JRadioButton whaleButton = new JRadioButton("Whale");

        ButtonGroup animalGroup = new ButtonGroup();

        animalGroup.add(dogButton);
        animalGroup.add(catButton);
        animalGroup.add(alligatorButton);
        animalGroup.add(dolphinButton);
        animalGroup.add(eagleButton);
        animalGroup.add(pigeonButton);
        animalGroup.add(whaleButton);
        animalGroup.add(snakeButton);

        animalPanel.add(dogButton);
        animalPanel.add(catButton);
        animalPanel.add(alligatorButton);
        animalPanel.add(dolphinButton);
        animalPanel.add(eagleButton);
        animalPanel.add(pigeonButton);
        animalPanel.add(whaleButton);
        animalPanel.add(snakeButton);

        add(animalPanel, BorderLayout.CENTER);
        JButton goBackButton = new JButton("Go Back");
        JButton confirmButton = new JButton("Confirm Animal Selection");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(goBackButton);
        buttonPanel.add(confirmButton);
        buttonPanel.setLayout(new FlowLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        goBackButton.addActionListener(e -> dispose());
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (dogButton.isSelected())
                    {
                        if (!raceType.equals("Terrestrial"))
                        {
                            throw new IllegalArgumentException("You can't select a dog for a " + raceType + " race");
                        }
                        selectedAnimal = "Dog";
                        selectedAnimalObject = CreateDog();

                    }
                    else if (catButton.isSelected())
                    {
                        if (!raceType.equals("Terrestrial"))
                        {
                            throw new IllegalArgumentException("You can't select a cat for a " + raceType + " race");
                        }
                        selectedAnimal = "Cat";
                        selectedAnimalObject = CreateCat();
                    } 
                    else if (alligatorButton.isSelected())
                    {
                        if (!raceType.equals("Water"))
                        {
                            throw new IllegalArgumentException("You can't select an alligator for an " + raceType + " race");
                        }
                        selectedAnimal = "Alligator";
                        selectedAnimalObject = CreateAlligator();
                    } 
                    else if (dolphinButton.isSelected())
                    {
                        if (!raceType.equals("Water"))
                        {
                            throw new IllegalArgumentException("You can't select a dolphin for a " + raceType + " race");
                        }
                        selectedAnimal = "Dolphin";
                        selectedAnimalObject = CreateDolphin();
                    }
                    else if (whaleButton.isSelected())
                    {
                        if (!raceType.equals("Water"))
                        {
                            throw new IllegalArgumentException("You can't select a whale for a " + raceType + " race");
                        }
                        selectedAnimal = "Whale";
                        selectedAnimalObject = CreateWhale();
                    } else if (eagleButton.isSelected())
                    {
                        if (!raceType.equals("Air"))
                        {
                            throw new IllegalArgumentException("You can't select an eagle for an " + raceType + " race");
                        }
                        selectedAnimal = "Eagle";
                        selectedAnimalObject = CreateEagle();
                    } else if (pigeonButton.isSelected())
                    {
                        if (!raceType.equals("Air"))
                        {
                            throw new IllegalArgumentException("You can't select a pigeon for a " + raceType + " race");
                        }
                        selectedAnimal = "Pigeon";
                        
                        selectedAnimalObject = CreatePigeon();
                    } else if (snakeButton.isSelected())
                    {
                        if (!raceType.equals("Terrestrial"))
                        {
                            throw new IllegalArgumentException("You can't select a snake for a " + raceType + " race");
                        }
                        selectedAnimal = "Snake";
                        selectedAnimalObject = CreateSnake();
                    }
                    dispose();
                } catch (IllegalArgumentException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
    public String getSelectedAnimal() {
        return selectedAnimal;
    }
    public Animal getSelectedAnimalObject() {
        return selectedAnimalObject;
    }
    public  Animal CreateDog() {
        JDialog dogDialog = new JDialog(this, "Dog Input Panel", true);
        dogDialog.setSize(500, 300);
        dogDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 20));
        JTextField nameField = new JTextField(20);
        JTextField speedField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"," Hermaphrodite"});
        JTextField noLegsField = new JTextField(20);
        JTextField breedField = new JTextField(20);
        JTextField MaxEnergyField = new JTextField(20);
        JTextField EnergyPerMeter = new JTextField(20);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Speed:"));
        panel.add(speedField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);
        panel.add(new JLabel("Max Energy:"));
        panel.add(MaxEnergyField);
        panel.add(new JLabel("Energy Per Meter:"));
        panel.add(EnergyPerMeter);
        panel.add(new JLabel("Number of Legs:"));
        panel.add(noLegsField);
        panel.add(new JLabel("Breed:"));
        panel.add(breedField);
        JButton submitButton = new JButton("Submit");
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> dispose());
        panel.add(submitButton);
        dogDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    speed = Integer.parseInt(speedField.getText());
                    if (speed < 0)
                    {
                        throw new Exception("Speed cannot be negative.");
                    }
                    double weight = Double.parseDouble(weightField.getText());
                    int MaxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int Energy = Integer.parseInt(EnergyPerMeter.getText());
                    Animal.gender gender;
                    String genderString = (String) genderComboBox.getSelectedItem();
                    if (genderString.equals("Male"))
                    {
                        gender = Animal.gender.Male;
                    } else if(genderString.equals("Female"))
                    {
                        gender = Animal.gender.Female;
                    }
                    else
                        gender = Animal.gender.Hermaphrodite;
                    int noLegs = Integer.parseInt(noLegsField.getText());
                    String breed = breedField.getText();
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new Dog(0,0,0,gender,name,weight,speed,medals, Animal.Orientation.EAST,MaxEnergy,Energy,noLegs,breed);
                    dogDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dogDialog, ex.getMessage());
                }
            }
        });
        dogDialog.setVisible(true);
        return selectedAnimalObject;
    }
    public  Animal CreateCat() {
        JDialog CatDialog = new JDialog(this, "Cat Input Panel", true);
         CatDialog.setSize(500, 400);
         CatDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30)); // r
        JTextField nameField = new JTextField(20);
        JTextField speedField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"," Hermaphrodite"});
        JComboBox<String> CastratedComboBox = new JComboBox<>(new String[]{"Yes","No"});
        JTextField noLegsField = new JTextField(20);
        JTextField MaxEnergyField = new JTextField(20);
        JTextField EnergyPerMeter = new JTextField(20);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Speed:"));
        panel.add(speedField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);
        panel.add(new JLabel("Max Energy:"));
        panel.add(MaxEnergyField);
        panel.add(new JLabel("Energy Per Meter:"));
        panel.add(EnergyPerMeter);
        panel.add(new JLabel("Number of Legs:"));
        panel.add(noLegsField);
        panel.add(new JLabel("Castrated:"));
        panel.add(CastratedComboBox);
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> dispose());
         CatDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    int MaxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int Energy = Integer.parseInt(EnergyPerMeter.getText());
                    Animal.gender gender;
                    boolean Castrated = false;
                    String genderString = (String) genderComboBox.getSelectedItem();
                    if (genderString.equals("Male"))
                    {
                        gender = Animal.gender.Male;
                    } else if(genderString.equals("Female"))
                    {
                        gender = Animal.gender.Female;
                    }
                    else
                        gender = Animal.gender.Hermaphrodite;
                    String Castrateds = (String) CastratedComboBox.getSelectedItem();
                    if (Castrateds.equals("Yes"))
                    {
                        Castrated = true;
                    }
                    int noLegs = Integer.parseInt(noLegsField.getText());
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new  Cat(0,0,0,gender,name,weight,speed,medals, Animal.Orientation.EAST,MaxEnergy,Energy,noLegs,Castrated);
                     CatDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog( CatDialog, "Invalid input. Please check your entries.");
                }
            }
        });
         CatDialog.setVisible(true);
        return selectedAnimalObject;
    }
    public  Animal CreateAlligator() {
        JDialog AlligatorDialog = new JDialog(this, "Alligator Input Panel", true);
        AlligatorDialog.setSize(500, 400);
        AlligatorDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30)); // r
        JTextField nameField = new JTextField(20);
        JTextField speedField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"," Hermaphrodite"});
        JComboBox<Integer> WaterPath = new JComboBox<>(new Integer[]{1,2,3,4});
        JTextField noLegsField = new JTextField(20);
        JTextField MaxEnergyField = new JTextField(20);
        JTextField DiveDepthField = new JTextField(20);
        JTextField EnergyPerMeter = new JTextField(20);
        JTextField AreaOfLivingField = new JTextField(20);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Speed:"));
        panel.add(speedField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);
        panel.add(new JLabel("Max Energy"));
        panel.add(MaxEnergyField);
        panel.add(new JLabel("Energy Per Meter:"));
        panel.add(EnergyPerMeter);
        panel.add(new JLabel("Number of Legs:"));
        panel.add(noLegsField);
        panel.add(new JLabel("Dive Depth:"));
        panel.add(DiveDepthField);
        panel.add(new JLabel("Area of Living:"));
        panel.add(AreaOfLivingField);
        panel.add(new JLabel("Water Path:"));
        panel.add(WaterPath);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> dispose());
        AlligatorDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    int MaxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int Energy = Integer.parseInt(EnergyPerMeter.getText());
                    double DiveDepth = Double.parseDouble(DiveDepthField.getText());
                    Animal.gender gender;
                    String AreaOfLiving = AreaOfLivingField.getText();
                    String genderString = (String) genderComboBox.getSelectedItem();
                    if (genderString.equals("Male"))
                    {
                        gender = Animal.gender.Male;
                    } else if(genderString.equals("Female"))
                    {
                        gender = Animal.gender.Female;
                    }
                    else
                        gender = Animal.gender.Hermaphrodite;
                    int Waterpath = WaterPath.getSelectedIndex()+1;
                    int noLegs = Integer.parseInt(noLegsField.getText());
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new  Alligator(Waterpath,0,0,gender,name,weight,speed,medals, Animal.Orientation.EAST,MaxEnergy,Energy,DiveDepth,noLegs,AreaOfLiving);
                    AlligatorDialog.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( AlligatorDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        AlligatorDialog.setVisible(true);
        return selectedAnimalObject;
    }
    public  Animal CreateDolphin() {
        JDialog DolphinDialog = new JDialog(this, "Dolphin Input Panel", true);
        DolphinDialog.setSize(500, 400);
        DolphinDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30)); // r
        JTextField nameField = new JTextField(20);
        JTextField speedField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"," Hermaphrodite"});
        JComboBox<Integer> WaterPath = new JComboBox<>(new Integer[]{1,2,3,4});
        JComboBox<String> WaterType = new JComboBox<>(new String[]{"Sea","Sweet"});
        JTextField MaxEnergyField = new JTextField(20);
        JTextField DiveDepthField = new JTextField(20);
        JTextField EnergyPerMeter = new JTextField(20);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Speed:"));
        panel.add(speedField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);
        panel.add(new JLabel("Max Energy:"));
        panel.add(MaxEnergyField);
        panel.add(new JLabel("Energy Per Meter:"));
        panel.add(EnergyPerMeter);
        panel.add(new JLabel("Dive Depth:"));
        panel.add(DiveDepthField);
        panel.add(new JLabel("Water Type:"));
        panel.add(WaterType);
        panel.add(new JLabel("Water Path:"));
        panel.add(WaterPath);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> dispose());
        DolphinDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    int MaxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int Energy = Integer.parseInt(EnergyPerMeter.getText());
                    double DiveDepth = Double.parseDouble(DiveDepthField.getText());
                    Animal.gender gender;
                    String genderString = (String) genderComboBox.getSelectedItem();
                    if (genderString.equals("Male"))
                    {
                        gender = Animal.gender.Male;
                    } else if(genderString.equals("Female"))
                    {
                        gender = Animal.gender.Female;
                    }
                    else
                        gender = Animal.gender.Hermaphrodite;
                    Dolphin.WaterType waterType = null;
                    String WaterTypeString = (String) WaterType.getSelectedItem();
                    if (WaterTypeString.equals("Sea"))
                    {
                        waterType = Dolphin.WaterType.Sea;
                    }
                    else if (WaterTypeString.equals("Sweet"))
                    {
                        waterType = Dolphin.WaterType.Sweet;
                    }
                    int Waterpath = WaterPath.getSelectedIndex()+1;
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new  Dolphin(Waterpath,0,0,gender,name,weight,speed,medals, Animal.Orientation.EAST,MaxEnergy,Energy,DiveDepth,waterType);
                    DolphinDialog.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( DolphinDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        DolphinDialog.setVisible(true);
        return selectedAnimalObject;
    }
    public  Animal CreateWhale() {
        JDialog WhaleDialog = new JDialog(this, "Whale Input Panel", true);
        WhaleDialog.setSize(500, 400);
        WhaleDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30)); // r
        JTextField nameField = new JTextField(20);
        JTextField speedField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"," Hermaphrodite"});
        JComboBox<Integer> WaterPath = new JComboBox<>(new Integer[]{1,2,3,4});
        JTextField Foodtype = new JTextField(20);
        JTextField MaxEnergyField = new JTextField(20);
        JTextField DiveDepthField = new JTextField(20);
        JTextField EnergyPerMeter = new JTextField(20);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Speed:"));
        panel.add(speedField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);
        panel.add(new JLabel("Max Energy:"));
        panel.add(MaxEnergyField);
        panel.add(new JLabel("Energy Per Meter:"));
        panel.add(EnergyPerMeter);
        panel.add(new JLabel("Dive Depth:"));
        panel.add(DiveDepthField);
        panel.add(new JLabel("Food type:"));
        panel.add(Foodtype);
        panel.add(new JLabel("Water Path:"));
        panel.add(WaterPath);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> dispose());
        WhaleDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    int MaxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int Energy = Integer.parseInt(EnergyPerMeter.getText());
                    double DiveDepth = Double.parseDouble(DiveDepthField.getText());
                    String foodType = Foodtype.getText();
                    Animal.gender gender;
                    String genderString = (String) genderComboBox.getSelectedItem();
                    if (genderString.equals("Male"))
                    {
                        gender = Animal.gender.Male;
                    } else if(genderString.equals("Female"))
                    {
                        gender = Animal.gender.Female;
                    }
                    else
                        gender = Animal.gender.Hermaphrodite;

                    int Waterpath = WaterPath.getSelectedIndex()+1;
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new  Whale(Waterpath,0,0,gender,name,weight,speed,medals, Animal.Orientation.EAST,MaxEnergy,Energy,DiveDepth,foodType);
                    WhaleDialog.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( WhaleDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        WhaleDialog.setVisible(true);
        return selectedAnimalObject;
    }
    public  Animal CreateEagle() {
        JDialog EagleDialog = new JDialog(this, "Eagle Input Panel", true);
        EagleDialog.setSize(500, 400);
        EagleDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30)); 
        JTextField nameField = new JTextField(20);
        JTextField speedField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"," Hermaphrodite"});
        JComboBox<Integer> AirPath = new JComboBox<>(new Integer[]{1,2,3,4,5});
        
        JTextField MaxEnergyField = new JTextField(20);
        JTextField EnergyPerMeter = new JTextField(20);
        JTextField WingspanField = new JTextField(20);
        JTextField altitudeOfFlight = new JTextField(20);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Speed:"));
        panel.add(speedField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);
        panel.add(new JLabel("Max Energy:"));
        panel.add(MaxEnergyField);
        panel.add(new JLabel("Energy Per Meter:"));
        panel.add(EnergyPerMeter);
        panel.add(new JLabel("Wing span:"));
        panel.add(WingspanField);
        panel.add(new JLabel("Altitude of Flight:"));
        panel.add(altitudeOfFlight);
        panel.add(new JLabel("Air Path:"));
        panel.add(AirPath);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> dispose());
        EagleDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    int MaxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int Energy = Integer.parseInt(EnergyPerMeter.getText());
                    double Wingspan = Double.parseDouble(WingspanField.getText());
                    double AltitudeOfFlight = Double.parseDouble(altitudeOfFlight.getText());
                    Animal.gender gender;
                    String genderString = (String) genderComboBox.getSelectedItem();
                    if (genderString.equals("Male"))
                    {
                        gender = Animal.gender.Male;
                    } else if(genderString.equals("Female"))
                    {
                        gender = Animal.gender.Female;
                    }
                    else
                        gender = Animal.gender.Hermaphrodite;
                    int Airpath = AirPath.getSelectedIndex()+1;
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new  Eagle(Airpath,0,0,gender,name,weight,speed,medals, Animal.Orientation.EAST,MaxEnergy,Energy,Wingspan,AltitudeOfFlight);
                    EagleDialog.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( EagleDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        EagleDialog.setVisible(true);
        return selectedAnimalObject;
    }
    public  Animal CreatePigeon() {
        JDialog PigeonDialog = new JDialog(this, "Pigeon Input Panel", true);
        PigeonDialog.setSize(500, 400);
        PigeonDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30));
        JTextField nameField = new JTextField(20);
        JTextField speedField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"," Hermaphrodite"});
        JComboBox<Integer> AirPath = new JComboBox<>(new Integer[]{1,2,3,4,5});
        JTextField FamilyField = new JTextField(20);
        JTextField MaxEnergyField = new JTextField(20);
        JTextField EnergyPerMeter = new JTextField(20);
        JTextField WingspanField = new JTextField(20);
        

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Speed:"));
        panel.add(speedField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);
        panel.add(new JLabel("Max Energy:"));
        panel.add(MaxEnergyField);
        panel.add(new JLabel("Energy Per Meter:"));
        panel.add(EnergyPerMeter);
        panel.add(new JLabel("Wing span:"));
        panel.add(WingspanField);
        panel.add(new JLabel("Family:"));
        panel.add(FamilyField);
        panel.add(new JLabel("Air Path:"));
        panel.add(AirPath);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> dispose());
        PigeonDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    int MaxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int Energy = Integer.parseInt(EnergyPerMeter.getText());
                    double Wingspan = Double.parseDouble(WingspanField.getText());
                    String Family = FamilyField.getText();
                    Animal.gender gender;
                    String genderString = (String) genderComboBox.getSelectedItem();
                    if (genderString.equals("Male"))
                    {
                        gender = Animal.gender.Male;
                    } else if(genderString.equals("Female"))
                    {
                        gender = Animal.gender.Female;
                    }
                    else
                        gender = Animal.gender.Hermaphrodite;
                    int Airpath = AirPath.getSelectedIndex()+1;
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new  Pigeon(Airpath,0,0,gender,name,weight,speed,medals, Animal.Orientation.EAST,MaxEnergy,Energy,Wingspan,Family);
                    PigeonDialog.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( PigeonDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        PigeonDialog.setVisible(true);
        return selectedAnimalObject;
    }
    public  Animal CreateSnake() {
        JDialog SnakeDialog = new JDialog(this, "Snake Input Panel", true);
        SnakeDialog.setSize(500, 400);
        SnakeDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30)); // r
        JTextField nameField = new JTextField(20);
        JTextField speedField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"," Hermaphrodite"});
        JTextField noLegsField = new JTextField(20);
        JComboBox<String> PoisonousComboBox = new JComboBox<>(new String[]{"Low", "Mid"," High"});
        JTextField LengthField = new JTextField(20);
        JTextField MaxEnergyField = new JTextField(20);
        JTextField EnergyPerMeter = new JTextField(20);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Speed:"));
        panel.add(speedField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);
        panel.add(new JLabel("Max Energy"));
        panel.add(MaxEnergyField);
        panel.add(new JLabel("Energy Per Meter"));
        panel.add(EnergyPerMeter);
        panel.add(new JLabel("Number of Legs:"));
        panel.add(noLegsField);
        panel.add(new JLabel("Length:"));
        panel.add(LengthField);
        panel.add(new JLabel("Poisonous:"));
        panel.add(PoisonousComboBox);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> dispose());
        SnakeDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    int MaxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int Energy = Integer.parseInt(EnergyPerMeter.getText());
                    Animal.gender gender;
                    String genderString = (String) genderComboBox.getSelectedItem();
                    if (genderString.equals("Male"))
                    {
                        gender = Animal.gender.Male;
                    } else if(genderString.equals("Female"))
                    {
                        gender = Animal.gender.Female;
                    }
                    else
                        gender = Animal.gender.Hermaphrodite;
                    int noLegs = Integer.parseInt(noLegsField.getText());
                    double length = Double.parseDouble(LengthField.getText());
                    Snake.Poisonous poisonous = null;
                    String Poisonous = (String) PoisonousComboBox.getSelectedItem();
                    if (Poisonous.equals("Low"))
                    {
                        poisonous = Snake.Poisonous.LOW;
                    }
                    else if (Poisonous.equals("Mid"))
                    {
                        poisonous = Snake.Poisonous.MEDIUM;
                    }
                    else if (Poisonous.equals("High"))
                    {
                        poisonous = Snake.Poisonous.HIGH;
                    }
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new Snake(0,0,0,gender,name,weight,speed,medals, Animal.Orientation.EAST,MaxEnergy,Energy,noLegs,poisonous,length);
                    SnakeDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SnakeDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        SnakeDialog.setVisible(true);
        return selectedAnimalObject;
    }
}
