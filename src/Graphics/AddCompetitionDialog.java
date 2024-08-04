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
        raceTypeCombo.setEnabled(false); // Disabled initially

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

        this.add(mainPanel);

        // Add action listener to competition type radio buttons
        ActionListener competitionTypeListener = e -> {
            raceTypeCombo.setEnabled(regularButton.isSelected());
        };
        regularButton.addActionListener(competitionTypeListener);
        courierButton.addActionListener(competitionTypeListener);

        // Add action listener to confirm button
        confirm.addActionListener(e -> {
            selectedCompetitionType = regularButton.isSelected() ? "Regular" : "Courier";
            competitionName = competitionNameField.getText();
            selectedRaceType = (String) raceTypeCombo.getSelectedItem();

            if (competitionName.isEmpty()) {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please enter a competition name.");
            } else if (selectedCompetitionType == null) {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please select a competition type.");
            } else if (regularButton.isSelected() && selectedRaceType == null) {
                JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please select a race type.");
            } else {
                if (selectedCompetitionType.equals("Regular")) {
                    processRegularCompetition();
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
                if (col == 0) {
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
                } else {
                    String groupKey = (String) groupTable.getColumnModel().getColumn(col).getHeaderValue();
                    String groupType = groupKey.substring(groupKey.indexOf("(") + 1, groupKey.indexOf(")"));
                    // Open AddAnimalDialog to add an animal of the specific group type
                    AddAnimalDialog animalDialog = new AddAnimalDialog((JFrame) SwingUtilities.getWindowAncestor(AddCompetitionDialog.this), groupType);
                    Animal newAnimal = animalDialog.getSelectedAnimalObject();
                    if (newAnimal != null) {
                        // Ensure the animal is added to the correct group
                        List<Animal> selectedGroup = competitionManager.getGroupMap().get(groupKey);
                        if (selectedGroup != null) {
                            selectedGroup.add(newAnimal);
                            updateTable();
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

            for (String groupKey : competitionManager.getGroupMap().keySet()) {
                String groupType = groupKey.substring(groupKey.indexOf("(") + 1, groupKey.indexOf(")"));
                List<Animal> animals = competitionManager.getGroupMap().get(groupKey);

                // Check if the current group's type matches the selected race type
                if (groupType.equals(selectedRaceType)) {
                    List<Animal> chosenAnimals = selectAnimals(animals);

                    // Add only selected animals from the current group
                    if (!chosenAnimals.isEmpty()) {
                        race.addAnimals(chosenAnimals);
                        raceCreated = true;
                    }
                }
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

    private void updateTable() {
        tableModel.setRowCount(0);
        int maxRows = 0;

        for (Map.Entry<String, List<Animal>> entry : competitionManager.getGroupMap().entrySet()) {
            maxRows = Math.max(maxRows, entry.getValue().size());
        }

        for (int i = 0; i < maxRows; i++) {
            Object[] rowData = new Object[competitionManager.getGroupMap().size() + 1];
            int colIndex = 1;
            for (String groupName : competitionManager.getGroupMap().keySet()) {
                List<Animal> animals = competitionManager.getGroupMap().get(groupName);
                if (i < animals.size()) {
                    Animal animal = animals.get(i);
                    rowData[colIndex] = animal.getAnimaleName() + " (" + animal.animalType() + ")";
                } else {
                    rowData[colIndex] = ""; // Ensure empty cells for rows with no animals
                }
                colIndex++;
            }
            tableModel.addRow(rowData);
        }

        // Check if the "Courier Competition" button should be enabled or disabled
        checkCompetitionEligibility();
    }

    private void checkCompetitionEligibility() {
        int totalAnimals = 0;
        for (List<Animal> animals : competitionManager.getGroupMap().values()) {
            totalAnimals += animals.size();
        }

        boolean groupsExist = !competitionManager.getGroupMap().isEmpty();
        boolean animalsExist = totalAnimals > 0;

        confirm.setEnabled(groupsExist && animalsExist); // Enable "Confirm" button only if there is at least one animal

        if (competitionManager.getGroupMap().size() < 2) {
            courierButton.setEnabled(false);
            return;
        }

        int animalCount = -1;
        for (List<Animal> animals : competitionManager.getGroupMap().values()) {
            if (animals.isEmpty()) {
                courierButton.setEnabled(false);
                return;
            }
            if (animalCount == -1) {
                animalCount = animals.size();
            } else if (animalCount != animals.size()) {
                courierButton.setEnabled(false);
                return;
            }
        }

        // Enable the "Courier Competition" button if there are at least two groups with the same number of animals
        courierButton.setEnabled(true);
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

        public HeaderRenderer(JTable table) {
            super(new FlowLayout(FlowLayout.RIGHT, 0, 0));
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

            add(label);
            add(addButton);
            setBackground(header.getBackground());
            setForeground(header.getForeground());

            return this;
        }
    }
}
