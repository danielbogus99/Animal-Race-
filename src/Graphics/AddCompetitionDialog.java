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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a dialog for adding a new competition.
 */
public class AddCompetitionDialog extends JDialog {
    private static final String[] animalTypes = {"Air", "Water", "Terrestrial"};
    private final JTextField competitionNameField;
    private final JRadioButton regularButton;
    private final JRadioButton courierButton;
    private final JButton confirm = new JButton("Confirm");
    private final JButton goBack = new JButton("Go Back");
    private final JTable groupTable;
    private final DefaultTableModel tableModel;
    private String selectedCompetitionType;
    private String competitionName;
    private boolean addAnimalFlag;
    private final Map<String, List<Animal>> groupMap = new HashMap<>();

    /**
     * Constructor to initialize the AddCompetitionDialog.
     *
     * @param parent The parent frame.
     */
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

        // Table setup
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Add Animal to Group");
        groupTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };

        groupTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane tableScrollPane = new JScrollPane(groupTable);

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
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(confirm, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(goBack, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.add(tableScrollPane, gbc);

        this.add(mainPanel);

        // Add action listener to confirm button
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCompetitionType = regularButton.isSelected() ? "Regular" : "Courier";
                competitionName = competitionNameField.getText();
                if (competitionName.isEmpty()) {
                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please enter a competition name.");
                } else if (selectedCompetitionType == null) {
                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please select a competition type.");
                } else {
                    addAnimalFlag = false;
                    dispose();
                }
            }
        });

        // Add action listener to go back button
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog to go back to the previous frame
            }
        });

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
                        groupMap.get(groupKey).add(newAnimal);
                        updateTable();
                    }
                }
            }
        });

        this.pack();
        this.setLocationRelativeTo(parent);
    }

    /**
     * Updates the table to display animals in all columns for all groups.
     */
    private void updateTable() {
        tableModel.setRowCount(0);
        int maxRows = 0;

        for (Map.Entry<String, List<Animal>> entry : groupMap.entrySet()) {
            maxRows = Math.max(maxRows, entry.getValue().size());
        }

        for (int i = 0; i < maxRows; i++) {
            Object[] rowData = new Object[groupMap.size() + 1];
            int colIndex = 1;
            for (String groupName : groupMap.keySet()) {
                List<Animal> animals = groupMap.get(groupName);
                if (i < animals.size()) {
                    Animal animal = animals.get(i);
                    rowData[colIndex] = animal.getAnimaleName() + " (" + animal.animalType() + ")";
                }
                colIndex++;
            }
            tableModel.addRow(rowData);
        }
    }

    /**
     * Adds a new group to the competition.
     *
     * @param name The name of the group.
     * @param type The type of the group.
     */
    private void addGroup(String name, String type) {
        String groupKey = name + " (" + type + ")";
        groupMap.put(groupKey, new ArrayList<>());
        tableModel.addColumn(groupKey);
    }

    /**
     * Gets the selected competition type.
     *
     * @return The selected competition type.
     */
    public String getSelectedCompetitionType() {
        return selectedCompetitionType;
    }

    /**
     * Gets the competition name.
     *
     * @return The competition name.
     */
    public String getCompetitionName() {
        return competitionName;
    }

    /**
     * Checks if the user wants to add an animal.
     *
     * @return True if adding an animal, false otherwise.
     */
    public boolean shouldAddAnimal() {
        return addAnimalFlag;
    }

    /**
     * Custom renderer for the table header to add buttons.
     */
    private static class HeaderRenderer extends JPanel implements TableCellRenderer {
        private final JTableHeader header;

        public HeaderRenderer(JTable table) {
            super(new FlowLayout(FlowLayout.CENTER, 0, 0));
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
