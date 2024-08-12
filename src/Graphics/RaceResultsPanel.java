package Graphics;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RaceResultsPanel extends JPanel {

    // Update the format to include both date and time
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public RaceResultsPanel(String raceName, Map<String, Date> raceResults) {
        setLayout(new BorderLayout());

        // Create a label to display the race name
        JLabel raceNameLabel = new JLabel("Race Name: " + raceName, JLabel.CENTER);
        raceNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(raceNameLabel, BorderLayout.NORTH);

        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);

        StringBuilder resultsText = new StringBuilder();
        for (Map.Entry<String, Date> entry : raceResults.entrySet()) {
            String groupName = entry.getKey();
            Date finishTime = entry.getValue();
            resultsText.append(groupName)
                    .append(" finished at: ")
                    .append(dateFormat.format(finishTime)) // Format with both date and time
                    .append("\n");
        }

        resultsArea.setText(resultsText.toString());

        JScrollPane scrollPane = new JScrollPane(resultsArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });
        add(closeButton, BorderLayout.SOUTH);
    }
}
