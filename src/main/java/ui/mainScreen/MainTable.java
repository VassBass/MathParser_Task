package ui.mainScreen;

import model.Equation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MainTable extends JTable {
    private static final String EQUATION = "Equation";
    private static final String RESULT = "Result";

    public MainTable(ArrayList<Equation>list){
        super(tableModel(list));
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
}
