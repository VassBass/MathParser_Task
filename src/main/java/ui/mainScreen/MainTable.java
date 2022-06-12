package ui.mainScreen;

import model.Equation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MainTable extends JTable {
    private static final String EQUATION = "Equation";
    private static final String RESULT = "Result";

    private ArrayList<Equation>list;

    public MainTable(ArrayList<Equation>list){
        super(tableModel(list));
        this.list = list;
    }

    public void addEquation(Equation equation){
        if (equation != null) {
            list.add(equation);
            setList(list);
        }
    }

    public void removeEquation(int id){
        if (id >= 0) {
            for (int index = 0; index < list.size(); index++) {
                Equation e = list.get(index);
                if (e.getId() == id) {
                    list.remove(index);
                    break;
                }
            }
            setList(list);
        }
    }

    public void setEquation(Equation oldEquation, Equation newEquation){

    }

    public void setList(ArrayList<Equation>list){
        if (list != null) this.setModel(tableModel(list));
    }

    public Equation getSelectedEquation(){
        int selected = this.getSelectedRow();
        return selected >= 0 && selected < list.size() ? list.get(selected) : null;
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
