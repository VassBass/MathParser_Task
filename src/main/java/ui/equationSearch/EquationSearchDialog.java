package ui.equationSearch;

import service.MathParser;
import ui.Location;
import ui.mainScreen.MainScreen;
import ui.model.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog to sets up search conditions
 *
 * @see MainScreen
 * @see ui.mainScreen.ButtonsPanel
 */
public class EquationSearchDialog extends JDialog {
    private static final String SEARCH = "Search";
    private static final String EQUATION_RESULT = "Equation result";
    private static final String CANCEL = "Cancel";
    private static final String OOPS = "Oops!";

    private static final String TOOLTIP_NUMBER = "Use only decimals and natural numbers";

    private final MainScreen mainScreen;

    private JLabel lab_result;
    private JComboBox<String>comboBox_condition;
    private JTextField txt_number;
    private JButton btn_cancel, btn_search;

    public EquationSearchDialog(MainScreen mainScreen){
        super(mainScreen, SEARCH, true);
        this.mainScreen = mainScreen;

        createElements();
        build();
        setReactions();
    }

    /**
     * Creates and customizes appearance of content elements
     */
    private void createElements(){
        lab_result = new JLabel(EQUATION_RESULT);

        String[] conditions = new String[]{"<", "<=", "=", ">=", ">"};
        comboBox_condition = new JComboBox<>(conditions);
        comboBox_condition.setSelectedIndex(2);
        ((JLabel)comboBox_condition.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        txt_number = new JTextField(10);
        txt_number.setToolTipText(TOOLTIP_NUMBER);

        btn_cancel = new DefaultButton(CANCEL);
        btn_search = new DefaultButton(SEARCH);
    }

    /**
     * Sets up the size, location, appearance and content of dialog
     */
    private void build(){
        this.setSize(400, 200);
        this.setLocation(Location.CENTER(mainScreen, this));

        this.setContentPane(new MainPanel());
    }

    /**
     * Assign reactions to user actions to dialog and content
     */
    private void setReactions(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btn_cancel.addActionListener(clickCancel);
        btn_search.addActionListener(clickSearch);
    }

    private final ActionListener clickCancel = e -> dispose();

    private final ActionListener clickSearch = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (txt_number.getText().length() == 0) {
                String message = "Field of result can't be empty!";
                JOptionPane.showMessageDialog(EquationSearchDialog.this, message, OOPS, JOptionPane.ERROR_MESSAGE);
            } else {
                String number = MathParser.prepare(txt_number.getText());
                try {
                    double num = Double.parseDouble(number);
                    if (comboBox_condition.getSelectedItem() != null) {
                        dispose();
                        mainScreen.searchEquations(comboBox_condition.getSelectedItem().toString(), num);
                    }else {
                        String message = "Something go wrong, please try again";
                        JOptionPane.showMessageDialog(EquationSearchDialog.this, message, OOPS, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    String message = "Check attentively result for incorrect symbols";
                    JOptionPane.showMessageDialog(EquationSearchDialog.this, message, OOPS, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    /**
     * Panel of dialog content
     */
    private class MainPanel extends JPanel {
        MainPanel(){
            super(new GridBagLayout());

            JPanel conditionPanel = new JPanel();
            conditionPanel.add(lab_result);
            conditionPanel.add(comboBox_condition);
            conditionPanel.add(txt_number);

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.add(btn_cancel);
            buttonsPanel.add(btn_search);

            this.add(conditionPanel, new Cell(0));
            this.add(buttonsPanel, new Cell(1));
        }

        private class Cell extends GridBagConstraints {
            Cell(int y){
                super();

                this.fill = BOTH;
                this.insets = new Insets(5,5,5,5);

                this.gridx = 0;
                this.gridy = y;
            }
        }
    }

}
