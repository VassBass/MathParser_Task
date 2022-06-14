package ui.mainScreen;

import model.Equation;
import service.EquationService;
import service.EquationService_impl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainScreen extends JFrame {
    private static final String TITLE = "MathParser";
    private static final String OOPS = "Oops!";

    private final EquationService service = new EquationService_impl();

    private final ArrayList<Equation>list;
    private final MainTable mainTable;
    public final JPanel buttonsPanel;

    public MainScreen(){
        super(TITLE);

        this.list = service.getAll();
        mainTable = new MainTable(this.list, this);
        buttonsPanel = new ButtonsPanel(this);

        build();
        setReactions();
    }

    private void build(){
        Dimension sizeOfScreen = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,
                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
        this.setSize(sizeOfScreen);
        this.setContentPane(new MainPanel());
    }

    private void setReactions(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Equation getSelectedEquation(){
        int selected = mainTable.getSelectedRow();
        return selected >= 0 && selected < list.size() ? list.get(selected) : null;
    }

    public void addEquation(Equation equation){
        if (equation != null) {
            int id = service.add(equation);
            if (id >= 0) {
                equation.setId(id);
                list.add(equation);
                mainTable.setList(list);
            }else {
                String message = "Error with DB, please try again";
                JOptionPane.showMessageDialog(this,message,OOPS, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void removeEquation(int id){
        if (id >= 0) {
            service.remove(id);
            for (int index = 0; index < list.size(); index++) {
                Equation e = list.get(index);
                if (e.getId() == id) {
                    list.remove(index);
                    break;
                }
            }
            mainTable.setList(list);
        }
    }

    public void setEquation(Equation equation){
        if (equation != null) {
            service.set(equation);
            for (Equation e : list) {
                if (e.getId() == equation.getId()) {
                    e.setEquation(equation.getEquation());
                    e.setResult(equation.getResult());
                    break;
                }
            }
        }
        mainTable.setList(list);
    }

    private class MainPanel extends JPanel {
        MainPanel(){
            super(new GridBagLayout());

            this.add(buttonsPanel, new Cell(0,0.05));
            this.add(new JScrollPane(mainTable), new Cell(1,0.95));
        }

        private class Cell extends GridBagConstraints {
            Cell(int y, double weightY){
                super();

                this.fill = BOTH;
                this.weightx = 1D;
                this.gridx = 0;
                this.insets = new Insets(20,0,10,0);

                this.gridy = y;
                this.weighty = weightY;
            }
        }
    }
}
