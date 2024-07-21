package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCompetitionDialog extends JDialog {
    private static final String[] names = {"Air", "Water", "Terrestrial"};
    private final JComboBox<String> combo;
    private final JTextField competitionNameField;
    private final JButton confirm = new JButton("Confirm");
    private final JButton goBack = new JButton("Go Back");
    private String selectedCompetitionType;
    private String competitionName;

    public AddCompetitionDialog(JFrame parent) {
        super(parent, "Add Competition", true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel comboBoxPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        combo = new JComboBox<>(names);
        combo.setFont(new Font("Arial", Font.PLAIN, 14));

        competitionNameField = new JTextField(15);
        competitionNameField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Layout setup
        gbc.gridx = 0;
        gbc.gridy = 0;
        comboBoxPanel.add(new JLabel("Competition Name:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        comboBoxPanel.add(competitionNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        comboBoxPanel.add(new JLabel("Select Competition Type:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        comboBoxPanel.add(combo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        comboBoxPanel.add(confirm, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        comboBoxPanel.add(goBack, gbc);

        this.add(comboBoxPanel);

        // Add action listener to confirm button
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCompetitionType = (String) combo.getSelectedItem();
                competitionName = competitionNameField.getText();
                if (competitionName.isEmpty()) {
                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please enter a competition name.");
                } else {
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

        this.pack();
        this.setLocationRelativeTo(parent); // Center the dialog
    }

    public String getSelectedCompetitionType() {
        return selectedCompetitionType;
    }

    public String getCompetitionName() {
        return competitionName;
    }
}
