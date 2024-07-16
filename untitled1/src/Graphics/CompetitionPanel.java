package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
class CompetitionPanel extends JPanel {


    private JButton addCompetitionButton;
    private JButton addAnimal;
    private JButton clear;
    private JButton eat;
    private JButton info;
    private JButton exit;
    private JButton SelectTypeOfCompetition;
    private JButton GoBackButton;
    private List<String> competitions = new ArrayList<>();
    private String animal;
    private  int selectedRace;
    private CompetitionFrame parentFrame;

    private void addCompetition() {

        AddCompetitionDialog addCompetitionDialog = new AddCompetitionDialog(parentFrame);
        addCompetitionDialog.setVisible(true);

        String newCompetition = addCompetitionDialog.getSelectedCompetitionType();
        if (newCompetition != null)
        {
            competitions.add(newCompetition);
            System.out.println(competitions);

        } else
        {
            System.out.println("Competition not added.");
        }
    }
    private void addAnimal() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if(competitions.isEmpty())
        {
            JOptionPane.showMessageDialog(parentFrame, "You need to select at least one competition.");
            return;
        }
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setTitle("Competition Type selection");
        frame.setSize(200, 120);

        JComboBox competitionType = new JComboBox(competitions.toArray());
        for (int i = 0; i < competitionType.getItemCount(); i++) {
            competitionType.setSelectedIndex(i);
        }
        competitionType.addActionListener(e -> selectedRace = competitionType.getSelectedIndex());

        SelectTypeOfCompetition = new JButton("select type of competition");
        GoBackButton = new JButton("Go back");
        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.add(competitionType);
        panel.add(GoBackButton, BorderLayout.SOUTH);
        panel.add(SelectTypeOfCompetition);
        frame.add(panel);
        frame.setVisible(true);
        GoBackButton.addActionListener(e -> frame.dispose());
        SelectTypeOfCompetition.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e)
            {
                AddAnimalDialog addAnimalDialog = new AddAnimalDialog(parentFrame,competitions.get(selectedRace));
                animal = addAnimalDialog.getSelectedAnimal();
                frame.dispose();
                if (animal != null) {
                    ((CompetitionFrame) parentFrame).getImagePanel().addAnimal(animal);
                }
            }
        });


    }


    public CompetitionPanel(CompetitionFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridLayout(1,0));
        addCompetitionButton = new JButton("Add Competition");
        addAnimal = new JButton("Add Animal");
        clear = new JButton("Clear");
        eat = new JButton("Eat");
        info = new JButton("Info");
        exit = new JButton("Exit");
        add(addCompetitionButton);
        add(addAnimal);
        add(clear);
        add(eat);
        add(info);
        add(exit);

        exit.addActionListener(e -> System.exit(0));
        addCompetitionButton.addActionListener(e -> addCompetition());
        addAnimal.addActionListener(e -> addAnimal());
//        clear.addActionListener(e -> clear());
//        eat.addActionListener(e -> eat());
//        info.addActionListener(e -> info());


    }

}