package ui.mainScreen;

import model.Equation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MainTable extends JTable {
    private static final String EQUATION = "Equation";
    private static final String RESULT = "Result";

    private final MainScreen owner;

    public MainTable(ArrayList<Equation>list, MainScreen owner){
        super(tableModel(list));
        this.owner = owner;

        this.getTableHeader().setReorderingAllowed(false);
        this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getSelectionModel().addListSelectionListener(selected);
    }

    public void setList(ArrayList<Equation>list){
        if (list != null) this.setModel(tableModel(list));
    }

    private static DefaultTableModel tableModel(ArrayList<Equation>list){
        if (list == null) list = new ArrayList<>();

        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        String[]columnsHeader = new String[] {EQUATION, RESULT};
        model.setColumnIdentifiers(columnsHeader);

        for (Equation equation : list) {
            String[] data = new String[2];
            data[0] = equation.getEquation();
            data[1] = equation.getResult();

            model.addRow(data);
        }

        return model;
    }

    private final ListSelectionListener selected = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            owner.buttonsPanel.setEnabled(MainTable.this.getSelectedRow() >= 0);
        }
    };
}
