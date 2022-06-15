package ui.equationInfo;

import model.Equation;
import service.MathParser;
import ui.Location;
import ui.mainScreen.MainScreen;
import ui.model.DefaultButton;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog to put information about new equation or changing information about old one
 *
 * @see MainScreen
 * @see ui.mainScreen.ButtonsPanel
 *
 */
public class EquationInfoDialog extends JDialog {
    private static final String TITLE = "Equation Info";
    private static final String EQUATION = "Equation";
    private static final String RESULT = "Result";
    private static final String CANCEL = "Cancel";
    private static final String ADD = "Add";
    private static final String CALCULATE = "Calculate";
    private static final String OOPS = "Oops!";
    private static final String CHANGE = "Change";

    private static final String CALCULATE_TOOLTIP = "Calculates equation result";
    private static final String ADD_TOOLTIP = "Before add/change you must calculate equation";
    private static final String EQUATION_TOOLTIP = "Use numbers and symbols + - * / ( )";

    private final MainScreen mainScreen;
    private final Equation equation;

    private JTextField txt_equation, txt_result;
    private JButton btn_calculate, btn_cancel, btn_add;

    public EquationInfoDialog(MainScreen mainScreen, Equation equation){
        super(mainScreen, TITLE, true);
        this.mainScreen = mainScreen;
        this.equation = equation;

        createElements();
        build();
        setReactions();
    }

    /**
     * Creates and customizes appearance of content elements
     */
    private void createElements(){
        txt_equation = new JTextField(20);
        TitledBorder equationBorder = BorderFactory.createTitledBorder(EQUATION);
        txt_equation.setBorder(equationBorder);
        txt_equation.setToolTipText(EQUATION_TOOLTIP);

        txt_result = new JTextField(20);
        TitledBorder resultBorder = BorderFactory.createTitledBorder(RESULT);
        txt_result.setBorder(resultBorder);
        txt_result.setEnabled(false);

        btn_calculate = new DefaultButton(CALCULATE);
        btn_calculate.setToolTipText(CALCULATE_TOOLTIP);

        btn_cancel = new DefaultButton(CANCEL);

        btn_add = new DefaultButton(ADD);
        btn_add.setToolTipText(ADD_TOOLTIP);

        if (equation != null){
            txt_equation.setText(equation.getEquation());
            txt_result.setText(String.valueOf(equation.getResult()));
            btn_add.setText(CHANGE);
        }else btn_add.setEnabled(false);
    }

    /**
     * Sets up the size, location, appearance and content of dialog
     */
    private void build(){
        this.setSize(300, 250);
        this.setLocation(Location.CENTER(mainScreen, this));

        this.setContentPane(new MainPanel());
    }

    /**
     * Assign reactions to user actions to dialog and content
     */
    private void setReactions(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btn_cancel.addActionListener(clickCancel);
        btn_calculate.addActionListener(clickCalculate);
        btn_add.addActionListener(clickAdd);

        txt_equation.getDocument().addDocumentListener(changeEquation);
    }

    private final ActionListener clickCancel = e -> dispose();

    private final ActionListener clickCalculate = e -> {
        if (txt_equation.getText().length() == 0){
            String message = "Field of equation can't be empty!";
            JOptionPane.showMessageDialog(EquationInfoDialog.this,message,OOPS,JOptionPane.ERROR_MESSAGE);
        }else if (!MathParser.equationIsCorrect(txt_equation.getText())){
            String message = "Check attentively equation for incorrect symbols, missing or redundant symbols and parentheses";
            JOptionPane.showMessageDialog(EquationInfoDialog.this,message,OOPS,JOptionPane.ERROR_MESSAGE);
        }else {
            txt_result.setText(MathParser.calculate(txt_equation.getText()));
            btn_add.setEnabled(true);
        }
    };

    private final ActionListener clickAdd = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Equation newEquation = new Equation();
            newEquation.setEquation(txt_equation.getText());
            newEquation.setResult(Double.parseDouble(txt_result.getText()));
            if (equation == null) {
                mainScreen.addEquation(newEquation);
            }else {
                newEquation.setId(equation.getId());
                mainScreen.setEquation(newEquation);
            }
        }
    };

    private final DocumentListener changeEquation = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            btn_add.setEnabled(false);
            txt_result.setText("");
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            btn_add.setEnabled(false);
            txt_result.setText("");
        }

        @Override
        public void changedUpdate(DocumentEvent e) {}
    };

    /**
     * Panel of dialog content
     */
    private class MainPanel extends JPanel {
        MainPanel(){
            super(new GridBagLayout());
            this.setBackground(Color.WHITE);

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setBackground(Color.WHITE);
            buttonsPanel.add(btn_cancel);
            buttonsPanel.add(btn_add);

            this.add(txt_equation, new Cell(0));
            this.add(btn_calculate, new Cell(1));
            this.add(txt_result, new Cell(2));
            this.add(buttonsPanel, new Cell(3));
        }

        private class Cell extends GridBagConstraints {
            Cell(int y){
                this.insets = new Insets(5,5,5,5);
                this.fill = BOTH;

                this.gridx = 0;
                this.gridy = y;
            }
        }
    }
}
