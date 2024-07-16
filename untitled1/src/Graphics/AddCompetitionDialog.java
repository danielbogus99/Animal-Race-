package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCompetitionDialog extends JDialog {
    private static final String[] names = {"Air", "Water", "Terrestrial"};
    private final JComboBox<String> combo;
    private final JButton confirm = new JButton("Confirm");
    private final JPanel comboBoxPanel = new JPanel();
    private String selectedCompetitionType;

    public AddCompetitionDialog(JFrame parent) {
        super(parent, "Add Competition", true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.PAGE_AXIS));
        combo = new JComboBox<>();
        for (String name : names) {
            combo.addItem(name);
        }
        comboBoxPanel.add(combo);
        comboBoxPanel.add(confirm, BorderLayout.CENTER);

        // Add comboBoxPanel to the dialog
        this.add(comboBoxPanel);

        // Add action listener to confirm button
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the desired action based on the selected item
                selectedCompetitionType = (String) combo.getSelectedItem();


                // Close this dialog and return to the main frame
                dispose();
            }
        });

        this.pack();
        this.setLocationRelativeTo(parent);
    }



    public String getSelectedCompetitionType() {
        return selectedCompetitionType;
    }
}
