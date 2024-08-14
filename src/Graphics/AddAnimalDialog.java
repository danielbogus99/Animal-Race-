package Graphics;

import Olympics.Medal;
import animals.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a dialog for adding an animal to the competition.
 */
public class AddAnimalDialog extends JDialog {

    private Animal selectedAnimalObject = null;
    private final JTextField nameField = new JTextField(20);
    private final JTextField speedField = new JTextField(20);
    private final JTextField weightField = new JTextField(20);
    private final JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
    private final JTextField noLegsField = new JTextField(20);
    private final JTextField MaxEnergyField = new JTextField(20);
    private final JTextField DiveDepthField = new JTextField(20);
    private final JTextField EnergyPerMeter = new JTextField(20);
    private final JTextField AreaOfLivingField = new JTextField(20);
    private final JComboBox<String> CastratedComboBox = new JComboBox<>(new String[]{"Yes", "No"});
    private Competition competition;
    private CompetitionPanel competitionPanel;
    private ImagePanel imagePanel;
    private int width, high;
    private int x, y;

    /**
     * Constructor to initialize the AddAnimalDialog.
     *
     * @param parent   The parent frame.
     * @param raceType The type of the race.
     */
    public AddAnimalDialog(JFrame parent, String raceType) {
        super(parent, "Animal Selection", true); // Make the dialog modal
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.competition = new Competition("s",raceType); // Initialize competition instance

        // Assuming these panels and dimensions are correctly initialized elsewhere
        this.competitionPanel = ((CompetitionFrame) parent).getCompetitionPanel();
        this.imagePanel = ((CompetitionFrame) parent).getImagePanel();

        width = imagePanel.getWidth2();
        high = imagePanel.getHeight2();

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
                    if (dogButton.isSelected()) {
                        if (!raceType.equals("Terrestrial")) {
                            throw new IllegalArgumentException("You can't select a dog for a " + raceType + " race");
                        }
                        selectedAnimalObject = CreateDog();

                    } else if (catButton.isSelected()) {
                        if (!raceType.equals("Terrestrial")) {
                            throw new IllegalArgumentException("You can't select a cat for a " + raceType + " race");
                        }
                        selectedAnimalObject = CreateCat();
                    } else if (alligatorButton.isSelected()) {
                        if (raceType.equals("Air")) {
                            throw new IllegalArgumentException("You can't select an alligator for a " + raceType + " race");
                        }

                        selectedAnimalObject = CreateAlligator(raceType);
                    } else if (dolphinButton.isSelected()) {
                        if (!raceType.equals("Water")) {
                            throw new IllegalArgumentException("You can't select a dolphin for a " + raceType + " race");
                        }

                        selectedAnimalObject = CreateDolphin();
                    } else if (whaleButton.isSelected()) {
                        if (!raceType.equals("Water")) {
                            throw new IllegalArgumentException("You can't select a whale for a " + raceType + " race");
                        }
                        selectedAnimalObject = CreateWhale();
                    } else if (eagleButton.isSelected()) {
                        if (!raceType.equals("Air")) {
                            throw new IllegalArgumentException("You can't select an eagle for a " + raceType + " race");
                        }

                        selectedAnimalObject = CreateEagle();
                    } else if (pigeonButton.isSelected()) {
                        if (!raceType.equals("Air")) {
                            throw new IllegalArgumentException("You can't select a pigeon for a " + raceType + " race");
                        }

                        selectedAnimalObject = CreatePigeon();
                    } else if (snakeButton.isSelected()) {
                        if (!raceType.equals("Terrestrial")) {
                            throw new IllegalArgumentException("You can't select a snake for a " + raceType + " race");
                        }
                        selectedAnimalObject = CreateSnake();
                    }
                    dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    /**
     * Gets the selected animal object.
     *
     * @return The selected animal object.
     */
    public Animal getSelectedAnimalObject() {
        return selectedAnimalObject;
    }

    private Animal.gender getGender() {
        String genderString = (String) genderComboBox.getSelectedItem();
        switch (genderString) {
            case "Male":
                return Animal.gender.Male;
            case "Female":
                return Animal.gender.Female;
            default:
                return Animal.gender.Hermaphrodite;
        }
    }

    private void addCommonFields(JPanel panel) {
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
    }

    // Inside the AddAnimalDialog class, add the validation methods

    private void validateSpeed(int speed) throws Exception {
        if (speed < 1 || speed > 30) {
            throw new Exception("Speed must be between 1 and 30.");
        }
    }

    private void validateEnergy(int maxEnergy, int energyPerMeter) throws Exception {
        if (energyPerMeter >= maxEnergy) {
            throw new Exception("Energy per meter must be less than max energy.");
        }
    }

// Update all the animal creation methods:

    // Updated CreateDog method
    public Animal CreateDog() {
        JDialog dogDialog = new JDialog(this, "Dog Input Panel", true);
        dogDialog.setSize(500, 300);
        dogDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 20));
        addCommonFields(panel);
        JTextField breedField = new JTextField(20);
        panel.add(new JLabel("Number of Legs:"));
        panel.add(noLegsField);
        panel.add(new JLabel("Breed:"));
        panel.add(breedField);
        JButton submitButton = new JButton("Submit");
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> dogDialog.dispose());
        panel.add(submitButton);
        dogDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    validateSpeed(speed); // Check the speed

                    double weight = Double.parseDouble(weightField.getText());
                    int maxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int energy = Integer.parseInt(EnergyPerMeter.getText());
                    validateEnergy(maxEnergy, energy); // Check the energy

                    Animal.gender gender = getGender();
                    int noLegs = Integer.parseInt(noLegsField.getText());
                    String breed = breedField.getText();
                    Medal[] medals = new Medal[0];
                    selectedAnimalObject = new Dog(0, 0, 0, gender, name, weight, speed, medals, Animal.Orientation.EAST, maxEnergy, energy, noLegs, breed, competitionPanel);
                    dogDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dogDialog, ex.getMessage());
                }
            }
        });
        dogDialog.setLocationRelativeTo(null);
        dogDialog.setVisible(true);
        return selectedAnimalObject;
    }

    // Updated CreateCat method
    public Animal CreateCat() {
        JDialog CatDialog = new JDialog(this, "Cat Input Panel", true);
        CatDialog.setSize(500, 400);
        CatDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30));
        addCommonFields(panel);
        panel.add(new JLabel("Number of Legs:"));
        panel.add(noLegsField);
        panel.add(new JLabel("Castrated:"));
        panel.add(CastratedComboBox);
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> CatDialog.dispose());
        CatDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    validateSpeed(speed); // Check the speed

                    double weight = Double.parseDouble(weightField.getText());
                    int maxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int energy = Integer.parseInt(EnergyPerMeter.getText());
                    validateEnergy(maxEnergy, energy); // Check the energy

                    Animal.gender gender = getGender();
                    boolean Castrated = CastratedComboBox.getSelectedItem().equals("Yes");
                    int noLegs = Integer.parseInt(noLegsField.getText());
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new Cat(0, 0, 0, gender, name, weight, speed, medals, Animal.Orientation.EAST, maxEnergy, energy, noLegs, Castrated, competitionPanel);
                    CatDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CatDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        CatDialog.setLocationRelativeTo(null);
        CatDialog.setVisible(true);
        return selectedAnimalObject;
    }

    // Updated CreateAlligator method
    public Animal CreateAlligator(String raceType) {
        JDialog AlligatorDialog = new JDialog(this, "Alligator Input Panel", true);
        AlligatorDialog.setSize(500, 400);
        AlligatorDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30));
        addCommonFields(panel);
        panel.add(new JLabel("Number of Legs:"));
        panel.add(noLegsField);
        panel.add(new JLabel("Dive Depth:"));
        panel.add(DiveDepthField);
        panel.add(new JLabel("Area of Living:"));
        panel.add(AreaOfLivingField);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> AlligatorDialog.dispose());
        AlligatorDialog.add(panel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    validateSpeed(speed); // Check the speed

                    double weight = Double.parseDouble(weightField.getText());
                    int maxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int energy = Integer.parseInt(EnergyPerMeter.getText());
                    validateEnergy(maxEnergy, energy); // Check the energy

                    double DiveDepth = Double.parseDouble(DiveDepthField.getText());
                    Animal.gender gender = getGender();
                    String AreaOfLiving = AreaOfLivingField.getText();
                    int noLegs = Integer.parseInt(noLegsField.getText());

                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new Alligator(width/14, 0, 0, gender, name, weight, speed, medals, Animal.Orientation.EAST, maxEnergy, energy, DiveDepth, noLegs, AreaOfLiving, competitionPanel);
                    AlligatorDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AlligatorDialog, ex.getMessage());
                }
            }
        });
        AlligatorDialog.setLocationRelativeTo(null);
        AlligatorDialog.setVisible(true);
        return selectedAnimalObject;
    }

    // Updated CreateDolphin method
    public Animal CreateDolphin() {
        JDialog DolphinDialog = new JDialog(this, "Dolphin Input Panel", true);
        DolphinDialog.setSize(500, 400);
        DolphinDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30));
        JComboBox<String> WaterType = new JComboBox<>(new String[]{"Sea", "Sweet"});
        addCommonFields(panel);
        panel.add(new JLabel("Dive Depth:"));
        panel.add(DiveDepthField);
        panel.add(new JLabel("Water Type:"));
        panel.add(WaterType);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> DolphinDialog.dispose());
        DolphinDialog.add(panel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    validateSpeed(speed); // Check the speed

                    double weight = Double.parseDouble(weightField.getText());
                    int maxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int energy = Integer.parseInt(EnergyPerMeter.getText());
                    validateEnergy(maxEnergy, energy); // Check the energy

                    double DiveDepth = Double.parseDouble(DiveDepthField.getText());
                    Animal.gender gender = getGender();
                    Dolphin.WaterType waterType = WaterType.getSelectedItem().equals("Sea") ? Dolphin.WaterType.Sea : Dolphin.WaterType.Sweet;

                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new Dolphin(width/14, 0, 0, gender, name, weight, speed, medals, Animal.Orientation.EAST, maxEnergy, energy, DiveDepth, waterType, competitionPanel);
                    DolphinDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DolphinDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        DolphinDialog.setLocationRelativeTo(null);
        DolphinDialog.setVisible(true);
        return selectedAnimalObject;
    }

    // Updated CreateWhale method
    public Animal CreateWhale() {
        JDialog WhaleDialog = new JDialog(this, "Whale Input Panel", true);
        WhaleDialog.setSize(500, 400);
        WhaleDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30));
        JTextField Foodtype = new JTextField(20);
        addCommonFields(panel);
        panel.add(new JLabel("Dive Depth:"));
        panel.add(DiveDepthField);
        panel.add(new JLabel("Food type:"));
        panel.add(Foodtype);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> WhaleDialog.dispose());
        WhaleDialog.add(panel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    validateSpeed(speed); // Check the speed

                    double weight = Double.parseDouble(weightField.getText());
                    int maxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int energy = Integer.parseInt(EnergyPerMeter.getText());
                    validateEnergy(maxEnergy, energy); // Check the energy

                    double DiveDepth = Double.parseDouble(DiveDepthField.getText());
                    String foodType = Foodtype.getText();
                    Animal.gender gender = getGender();

                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new Whale(width/14, 0, 0, gender, name, weight, speed, medals, Animal.Orientation.EAST, maxEnergy, energy, DiveDepth, foodType, competitionPanel);
                    WhaleDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(WhaleDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        WhaleDialog.setLocationRelativeTo(null);
        WhaleDialog.setVisible(true);
        return selectedAnimalObject;
    }

    // Updated CreateEagle method
    public Animal CreateEagle() {
        JDialog EagleDialog = new JDialog(this, "Eagle Input Panel", true);
        EagleDialog.setSize(500, 400);
        EagleDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30));
        JTextField WingspanField = new JTextField(20);
        JTextField altitudeOfFlight = new JTextField(20);
        addCommonFields(panel);
        panel.add(new JLabel("Wing span:"));
        panel.add(WingspanField);
        panel.add(new JLabel("Altitude of Flight:"));
        panel.add(altitudeOfFlight);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> EagleDialog.dispose());
        EagleDialog.add(panel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    validateSpeed(speed); // Check the speed

                    double weight = Double.parseDouble(weightField.getText());
                    int maxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int energy = Integer.parseInt(EnergyPerMeter.getText());
                    validateEnergy(maxEnergy, energy); // Check the energy

                    double Wingspan = Double.parseDouble(WingspanField.getText());
                    double AltitudeOfFlight = Double.parseDouble(altitudeOfFlight.getText());
                    Animal.gender gender = getGender();

                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new Eagle(0, 0, 0, gender, name, weight, speed, medals, Animal.Orientation.EAST, maxEnergy, energy, Wingspan, AltitudeOfFlight, competitionPanel);
                    EagleDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(EagleDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        EagleDialog.setLocationRelativeTo(null);
        EagleDialog.setVisible(true);
        return selectedAnimalObject;
    }

    // Updated CreatePigeon method
    public Animal CreatePigeon() {
        JDialog PigeonDialog = new JDialog(this, "Pigeon Input Panel", true);
        PigeonDialog.setSize(500, 400);
        PigeonDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30));
        JTextField FamilyField = new JTextField(20);
        JTextField WingspanField = new JTextField(20);
        addCommonFields(panel);
        panel.add(new JLabel("Wing span:"));
        panel.add(WingspanField);
        panel.add(new JLabel("Family:"));
        panel.add(FamilyField);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        JButton GoBackButton = new JButton("Go back");
        panel.add(GoBackButton);
        GoBackButton.addActionListener(e -> PigeonDialog.dispose());
        PigeonDialog.add(panel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    validateSpeed(speed); // Check the speed

                    double weight = Double.parseDouble(weightField.getText());
                    int maxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int energy = Integer.parseInt(EnergyPerMeter.getText());
                    validateEnergy(maxEnergy, energy); // Check the energy

                    double Wingspan = Double.parseDouble(WingspanField.getText());
                    String Family = FamilyField.getText();
                    Animal.gender gender = getGender();

                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new Pigeon(0, 0, 0, gender, name, weight, speed, medals, Animal.Orientation.EAST, maxEnergy, energy, Wingspan, Family, competitionPanel);
                    PigeonDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PigeonDialog, "Invalid input. Please check your entries.");
                }
            }
        });
        PigeonDialog.setLocationRelativeTo(null);
        PigeonDialog.setVisible(true);
        return selectedAnimalObject;
    }

    // Updated CreateSnake method
    public Animal CreateSnake() {
        JDialog SnakeDialog = new JDialog(this, "Snake Input Panel", true);
        SnakeDialog.setSize(500, 400);
        SnakeDialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 30));
        JComboBox<String> PoisonousComboBox = new JComboBox<>(new String[]{"Low", "Mid", "High"});
        JTextField LengthField = new JTextField(20);
        addCommonFields(panel);
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
        GoBackButton.addActionListener(e -> SnakeDialog.dispose());
        SnakeDialog.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int speed = Integer.parseInt(speedField.getText());
                    validateSpeed(speed); // Check the speed

                    double weight = Double.parseDouble(weightField.getText());
                    int maxEnergy = Integer.parseInt(MaxEnergyField.getText());
                    int energy = Integer.parseInt(EnergyPerMeter.getText());
                    validateEnergy(maxEnergy, energy); // Check the energy

                    Animal.gender gender = getGender();
                    int noLegs = Integer.parseInt(noLegsField.getText());
                    double length = Double.parseDouble(LengthField.getText());
                    Snake.Poisonous poisonous = null;
                    String Poisonous = (String) PoisonousComboBox.getSelectedItem();
                    if (Poisonous.equals("Low")) {
                        poisonous = Snake.Poisonous.LOW;
                    } else if (Poisonous.equals("Mid")) {
                        poisonous = Snake.Poisonous.MEDIUM;
                    } else if (Poisonous.equals("High")) {
                        poisonous = Snake.Poisonous.HIGH;
                    }
                    Medal[] medals = new Medal[2];
                    selectedAnimalObject = new Snake(0, 0, 0, gender, name, weight, speed, medals, Animal.Orientation.EAST, maxEnergy, energy, noLegs, poisonous, length, competitionPanel);
                    SnakeDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SnakeDialog, ex.getMessage());
                }
            }
        });
        SnakeDialog.setLocationRelativeTo(null);
        SnakeDialog.setVisible(true);
        return selectedAnimalObject;
    }

}
