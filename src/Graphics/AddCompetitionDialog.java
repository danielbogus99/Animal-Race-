package Graphics;

import Olympics.Medal;
import animals.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class AddCompetitionDialog extends JDialog {
    private static final String[] animalTypes = {"Air", "Water", "Terrestrial"};
    private final JTextField competitionNameField;
    private final JRadioButton regularButton;
    private final JRadioButton courierButton;
    private final JButton confirm = new JButton("Confirm");
    private final JTable groupTable;
    private final DefaultTableModel tableModel;
    private String selectedCompetitionType;
    private String selectedRaceType;
    private String competitionName;
    private boolean addAnimalFlag;
    private final JComboBox<String> raceTypeCombo;
    private String selectedGroupKey = ""; // Track the selected group

    // Use CompetitionManager to manage groups
    private final CompetitionManager competitionManager = CompetitionManager.getInstance();

    public AddCompetitionDialog(JFrame parent) {
        super(parent, "Add Competition", true);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        competitionNameField = new JTextField(15);
        competitionNameField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Radio buttons for competition type
        regularButton = new JRadioButton("Regular Competition");
        courierButton = new JRadioButton("Courier Competition");
        ButtonGroup competitionTypeGroup = new ButtonGroup();
        competitionTypeGroup.add(regularButton);
        competitionTypeGroup.add(courierButton);

        // ComboBox for selecting race type
        raceTypeCombo = new JComboBox<>(animalTypes);

        // Table setup
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Add Animal to Group");
        groupTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };

        // Set table preferred size and layout
        groupTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
        groupTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane tableScrollPane = new JScrollPane(groupTable);
        tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Layout setup
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Competition Name:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(competitionNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Select Competition Type:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(regularButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(courierButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Select Race Type:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(raceTypeCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(confirm, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JButton goBack = new JButton("Go Back");
        mainPanel.add(goBack, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.add(tableScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;

        this.add(mainPanel);

        // Add action listener to competition type radio buttons
        ActionListener competitionTypeListener = e -> {
            checkCompetitionEligibility(); // Update eligibility whenever the competition type changes
        };
        regularButton.addActionListener(competitionTypeListener);
        courierButton.addActionListener(competitionTypeListener);

        confirm.addActionListener(e -> {
            selectedCompetitionType = regularButton.isSelected() ? "Regular" : "Courier";
            competitionName = competitionNameField.getText();
            selectedRaceType = (String) raceTypeCombo.getSelectedItem(); // Ensure race type is selected

            if (competitionName.isEmpty()) {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please enter a competition name.");
            } else if (selectedCompetitionType == null) {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please select a competition type.");
            } else if (selectedRaceType == null) {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please select a race type.");
            } else {
                if (selectedCompetitionType.equals("Regular")) {
                    processRegularCompetition();
                } else if (selectedCompetitionType.equals("Courier")) {
                    processCourierCompetition();
                } else {
                    addAnimalFlag = false;
                    dispose();
                }
            }
        });

        // Add action listener to go back button
        goBack.addActionListener(e -> dispose());

        // Initialize table header renderer to include a button
        groupTable.getTableHeader().setDefaultRenderer(new HeaderRenderer(groupTable));

        // Handle clicks on header buttons
        groupTable.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int col = groupTable.columnAtPoint(evt.getPoint());
                if (col >= 1) { // Check for valid column index
                    // Set the selected group based on the clicked column
                    selectedGroupKey = (String) groupTable.getColumnModel().getColumn(col).getHeaderValue();
                    String groupType = selectedGroupKey.substring(selectedGroupKey.indexOf("(") + 1, selectedGroupKey.indexOf(")"));
                    // Open AddAnimalDialog to add an animal of the specific group type
                    AddAnimalDialog animalDialog = new AddAnimalDialog((JFrame) SwingUtilities.getWindowAncestor(AddCompetitionDialog.this), groupType);
                    Animal newAnimal = animalDialog.getSelectedAnimalObject();
                    if (newAnimal != null) {
                        // Ensure the animal is added to the correct group
                        List<Animal> selectedGroup = competitionManager.getGroupMap().get(selectedGroupKey);
                        if (selectedGroup != null) {
                            selectedGroup.add(newAnimal);
                            updateTable();
                            checkCompetitionEligibility(); // Update eligibility after adding an animal
                        }
                    }
                } else {
                    // Create a new group
                    JComboBox<String> typeCombo = new JComboBox<>(animalTypes);
                    int result = JOptionPane.showConfirmDialog(AddCompetitionDialog.this, typeCombo, "Select Group Type", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        String groupType = (String) typeCombo.getSelectedItem();
                        String groupName = JOptionPane.showInputDialog(AddCompetitionDialog.this, "Enter Group Name:");
                        if (groupName != null && !groupName.trim().isEmpty()) {
                            addGroup(groupName, groupType);
                        }
                    }
                }
            }
        });

        this.pack();
        this.setLocationRelativeTo(parent);
        loadExistingGroups(); // Load existing groups
        checkCompetitionEligibility(); // Initial check to set confirm button state
    }

    private void loadExistingGroups() {
        // Load existing groups from the manager into the dialog's table
        Map<String, List<Animal>> existingGroups = competitionManager.getGroupMap();
        for (String groupKey : existingGroups.keySet()) {
            if (!tableModel.getDataVector().stream().anyMatch(row -> ((Vector) row).contains(groupKey))) {
                tableModel.addColumn(groupKey);
            }
        }
        updateTable();
    }

    private void processRegularCompetition() {
        if (selectedCompetitionType.equals("Regular")) {
            boolean raceCreated = false;
            RegularRace race = new RegularRace(competitionName, selectedRaceType);

            List<Animal> eligibleAnimals = new ArrayList<>();

            // Gather all eligible animals from groups of the selected race type
            for (String groupKey : competitionManager.getGroupMap().keySet()) {
                String groupType = groupKey.substring(groupKey.indexOf("(") + 1, groupKey.indexOf(")"));
                List<Animal> animals = competitionManager.getGroupMap().get(groupKey);

                // Check if the current group's type matches the selected race type
                if (groupType.equals(selectedRaceType)) {
                    eligibleAnimals.addAll(animals);
                }
            }

            // Select animals from the accumulated list
            List<Animal> chosenAnimals = selectAnimals(eligibleAnimals);

            // Add only selected animals to the race
            if (!chosenAnimals.isEmpty()) {
                race.addAnimals(chosenAnimals);
                raceCreated = true;
            }

            if (raceCreated) {
                RegularRace.addRace(race);
                JOptionPane.showMessageDialog(AddCompetitionDialog.this, "The race \"" + competitionName + "\" was created successfully!");
                addAnimalFlag = false;
                dispose();
            } else {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this,
                        "No animals of the selected type (" + selectedRaceType + ") were added to the race.");
            }
        }
    }

    private void processCourierCompetition() {
        if (selectedCompetitionType.equals("Courier")) {
            boolean raceCreated = false;
            CourierRace race = new CourierRace(competitionName, selectedRaceType);

            // Ask user for the number of animals desired in each group
            String input = JOptionPane.showInputDialog(AddCompetitionDialog.this,
                    "Enter the number of animals per group:");
            if (input == null) {
                return; // User cancelled
            }

            int animalsPerGroup;
            try {
                animalsPerGroup = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this,
                        "Invalid number. Please enter a valid integer.");
                return;
            }

            List<String> eligibleGroupNames = new ArrayList<>();

            // Gather all eligible groups from the selected race type
            for (String groupKey : competitionManager.getGroupMap().keySet()) {
                String groupType = groupKey.substring(groupKey.indexOf("(") + 1, groupKey.indexOf(")"));
                List<Animal> animals = competitionManager.getGroupMap().get(groupKey);

                // Check if the current group's type matches the selected race type and size
                if (groupType.equals(selectedRaceType) && animals.size() == animalsPerGroup) {
                    eligibleGroupNames.add(groupKey);
                }
            }

            // Let user select groups to add to the race
            if (!eligibleGroupNames.isEmpty()) {
                List<String> selectedGroupNames = selectGroups(eligibleGroupNames);

                for (String groupName : selectedGroupNames) {
                    List<Animal> animals = competitionManager.getGroupMap().get(groupName);
                    if (animals != null) {
                        race.addGroup(groupName, animals); // Add the group to the race
                    }
                }

                if (!selectedGroupNames.isEmpty()) {
                    raceCreated = true;
                }
            }

            // Notify user about the added groups
            if (raceCreated) {
                CourierRace.addRace(race);
                JOptionPane.showMessageDialog(AddCompetitionDialog.this,
                        "The courier race \"" + competitionName + "\" was created successfully!\n");
                addAnimalFlag = false;
                dispose();
            } else {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this,
                        "No groups of the selected type and size were added to the race.");
            }
        }
    }


    // Method to present a selection dialog for choosing groups
    private List<String> selectGroups(List<String> eligibleGroupNames) {
        List<String> selectedGroups = new ArrayList<>();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<JCheckBox> checkBoxes = new ArrayList<>();
        for (String groupName : eligibleGroupNames) {
            JCheckBox checkBox = new JCheckBox(groupName);
            checkBoxes.add(checkBox);
            panel.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(300, 300)); // Adjust the size as needed

        int result = JOptionPane.showConfirmDialog(this, scrollPane, "Select Groups", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    selectedGroups.add(eligibleGroupNames.get(i));
                }
            }
        }

        return selectedGroups;
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Clear existing data

        // Ensure the columns are correctly set up
        tableModel.setColumnCount(1); // Reset columns to just the "Add Animal to Group" column
        for (String groupName : competitionManager.getGroupMap().keySet()) {
            tableModel.addColumn(groupName);
        }

        // Determine the maximum number of rows needed (i.e., the largest group size)
        int maxRows = competitionManager.getGroupMap().values().stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);

        // Populate the rows with the animals
        for (int row = 0; row < maxRows; row++) {
            Object[] rowData = new Object[tableModel.getColumnCount()];
            int colIndex = 1;

            for (String groupName : competitionManager.getGroupMap().keySet()) {
                List<Animal> animals = competitionManager.getGroupMap().get(groupName);
                if (row < animals.size()) {
                    Animal animal = animals.get(row);
                    rowData[colIndex] = animal.getAnimaleName() + " (" + animal.animalType() + ")";
                } else {
                    rowData[colIndex] = ""; // Fill with empty string if no animal at this index
                }
                colIndex++;
            }
            tableModel.addRow(rowData);
        }
    }

    // Method to check if a regular race can be created
    private void checkCompetitionEligibility() {
        boolean regularEligible = false;
        boolean courierEligible = false;

        // Check eligibility for Regular Competition
        Map<String, Integer> animalTypeCountMap = new HashMap<>();
        for (List<Animal> animals : competitionManager.getGroupMap().values()) {
            if (animals.size() > 1) { // Check group size
                for (Animal animal : animals) {
                    String type = animal.animalType();
                    animalTypeCountMap.put(type, animalTypeCountMap.getOrDefault(type, 0) + 1);
                }
            }
        }

        // Check if any animal type has at least 2 animals for Regular Competition
        for (String type : animalTypeCountMap.keySet()) {
            if (animalTypeCountMap.get(type) >= 2) {
                regularEligible = true;
                break;
            }
        }

        // Check eligibility for Courier Competition
        Map<String, Integer> groupTypeCountMap = new HashMap<>();
        for (Map.Entry<String, List<Animal>> entry : competitionManager.getGroupMap().entrySet()) {
            List<Animal> animals = entry.getValue();
            if (!animals.isEmpty()) { // Check group size
                String groupType = extractGroupType(entry.getKey());
                groupTypeCountMap.put(groupType, groupTypeCountMap.getOrDefault(groupType, 0) + 1);
            }
        }

        // Ensure at least 2 groups of the same type for Courier Competition
        for (int count : groupTypeCountMap.values()) {
            if (count >= 2) {
                courierEligible = true;
                break;
            }
        }

        // Enable or disable buttons based on eligibility
        courierButton.setEnabled(courierEligible);
        confirm.setEnabled(courierEligible || regularEligible);
    }

    private void addGroup(String name, String type) {
        String groupKey = name + " (" + type + ")";
        if (!competitionManager.getGroupMap().containsKey(groupKey)) {
            competitionManager.addGroup(groupKey, new ArrayList<>());
            tableModel.addColumn(groupKey);
            checkCompetitionEligibility();
        } else {
            JOptionPane.showMessageDialog(this, "A group with the name \"" + name + "\" already exists.");
        }
    }

    private String extractGroupType(String groupKey) {
        int typeStart = groupKey.lastIndexOf('(') + 1;
        int typeEnd = groupKey.lastIndexOf(')');
        if (typeStart < typeEnd) {
            return groupKey.substring(typeStart, typeEnd);
        } else {
            return "";
        }
    }

    private List<Animal> selectAnimals(List<Animal> availableAnimals) {
        List<Animal> selectedAnimals = new ArrayList<>();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<JCheckBox> checkBoxes = new ArrayList<>();
        for (Animal animal : availableAnimals) {
            JCheckBox checkBox = new JCheckBox(animal.getAnimaleName() + " (" + animal.animalType() + ")");
            checkBoxes.add(checkBox);
            panel.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(300, 300)); // Adjust the size as needed

        int result = JOptionPane.showConfirmDialog(this, scrollPane, "Select Animals", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    selectedAnimals.add(availableAnimals.get(i));
                }
            }
        }

        return selectedAnimals;
    }

    public List<RegularRace> getAllRegularRaces() {
        return RegularRace.getAllRaces();
    }

    public List<CourierRace> getAllCourierRaces() {
        return CourierRace.getAllRaces();
    }

    public String getSelectedCompetitionType() {
        return selectedCompetitionType;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getSelectedRaceType() {
        return selectedRaceType;
    }

    public boolean shouldAddAnimal() {
        return addAnimalFlag;
    }

    class HeaderRenderer extends JPanel implements TableCellRenderer {
        private final JTableHeader header;
        private final JTable table;

        public HeaderRenderer(JTable table) {
            super(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            this.table = table;
            this.header = table.getTableHeader();
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            removeAll();
            JLabel label = new JLabel(value.toString());
            JButton addButton = new JButton("+");

            addButton.setMargin(new Insets(0, 0, 0, 0));
            addButton.setFocusPainted(false);
            addButton.setPreferredSize(new Dimension(20, header.getHeight() - 4));
            addButton.setFont(new Font("Arial", Font.BOLD, 12));

            // Store the full column name (group name) as action command
            addButton.setActionCommand(value.toString());
            addButton.addActionListener(e -> {
                String fullGroupName = e.getActionCommand();
                addAnimalToGroup(fullGroupName);
            });

            add(label);
            add(addButton);
            setBackground(header.getBackground());
            setForeground(header.getForeground());

            return this;
        }

        private void addAnimalToGroup(String fullGroupName) {
            if (!fullGroupName.equals("Add Animal to Group")) {
                // Check if the group exists
                if (competitionManager.getGroupMap().containsKey(fullGroupName)) {
                    // Extract group type
                    String groupType = extractGroupType(fullGroupName);

                    // Open dialog to add animal of the specific group type
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this.table);
                    AddAnimalDialog animalDialog = new AddAnimalDialog(frame, groupType);
                    Animal newAnimal = animalDialog.getSelectedAnimalObject();

                    // Check if the animal type matches the group type before adding
                    if (newAnimal != null && newAnimal.animalType().equals(groupType)) {
                        List<Animal> selectedGroup = competitionManager.getGroupMap().get(fullGroupName);
                        if (selectedGroup != null) {
                            selectedGroup.add(newAnimal);
                            updateTable(); // Refresh table after adding animal
                            checkCompetitionEligibility(); // Update eligibility status
                        }
                    } else if (newAnimal != null) {
                        JOptionPane.showMessageDialog(this, "The animal type does not match the group type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Group not found: " + fullGroupName);
                }
            }
        }
    }
}
