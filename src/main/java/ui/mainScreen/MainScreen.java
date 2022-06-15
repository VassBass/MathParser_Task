package ui.mainScreen;

import model.Equation;
import service.EquationService;
import service.EquationService_impl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Main window of UI
 *
 * @see MainTable
 * @see ButtonsPanel
 */
public class MainScreen extends JFrame {
    private static final String TITLE = "MathParser";
    private static final String OOPS = "Oops!";
    private static final String SEARCH = "Search";
    private static final String SEARCH_OFF = "Search off";

    private final EquationService service = new EquationService_impl();

    private final ArrayList<Equation>list;

    private final MainTable mainTable;
    public final ButtonsPanel buttonsPanel;

    public boolean searchOn = false;

    public MainScreen(){
        super(TITLE);

        this.list = service.getAll();

        mainTable = new MainTable(this.list, this);
        buttonsPanel = new ButtonsPanel(this);

        build();
        setReactions();
    }

    /**
     * Sets up the size, location, appearance and content of window
     */
    private void build(){
        Dimension sizeOfScreen = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,
                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
        this.setSize(sizeOfScreen);
        this.setContentPane(new MainPanel());
    }

    /**
     * Assign reactions to user actions to window and content
     *
     * @see ButtonsPanel
     */
    private void setReactions(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Gets selected equation from {@link #mainTable}
     *
     * @return equation from {@link #list} by index equal selected row from {@link #mainTable}
     * or null if row wasn't selected
     *
     * @see MainTable
     */
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

    /**
     * Removes the equation with @param from the {@link #list} and sends the command to {@link #service}
     *
     * @param id of equation that need to remove
     *
     * @see EquationService#remove(int id)
     */
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

    /**
     * Changes equation from {@link #list} with id of equation from @param
     * and sends the command to {@link #service}
     *
     * @param equation to change
     *
     * @see EquationService#set(Equation)
     */
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

    /**
     * Makes search off and show full list of equations
     *
     * @see EquationService#getAll()
     * @see #searchEquations(String, double)
     */
    public void searchOff(){
        searchOn = false;
        buttonsPanel.btn_search.setText(SEARCH);
        list.clear();
        list.addAll(service.getAll());
        mainTable.setList(list);
    }

    /**
     * Turns on search and show list of equations that matches search condition
     *
     * @param condition of search
     *
     * @param result for search
     *
     * @see EquationService#get(String condition, double result)
     * @see #searchOff()
     */
    public void searchEquations(String condition, double result){
        searchOn = true;
        buttonsPanel.btn_search.setText(SEARCH_OFF);
        list.clear();
        list.addAll(service.get(condition, result));
        mainTable.setList(list);
    }

    /**
     * Panel of main window content
     */
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
