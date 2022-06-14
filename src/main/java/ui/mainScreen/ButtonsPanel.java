package ui.mainScreen;

import ui.equationInfo.EquationInfoDialog;
import ui.model.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel {
    private static final String ADD = "Add";
    private static final String CHANGE = "Change";
    private static final String REMOVE = "Remove";
    private static final String SEARCH = "Search";

    private final MainScreen owner;

    private final JButton btn_add, btn_change, btn_remove, btn_search;

    public ButtonsPanel(MainScreen owner){
        super(new GridBagLayout());

        this.owner = owner;

        btn_add = new DefaultButton(ADD);
        btn_remove = new DefaultButton(REMOVE);
        btn_change = new DefaultButton(CHANGE);
        btn_search = new DefaultButton(SEARCH);

        build();
        setReactions();
    }

    private void build(){
        this.add(btn_search, new Cell(0,0));
        this.add(btn_add, new Cell(1,0));
        this.add(btn_remove, new Cell(0,1));
        this.add(btn_change, new Cell(1,1));
    }

    private void setReactions(){
        btn_change.addActionListener(clickChange);
        btn_search.addActionListener(clickSearch);
        btn_remove.addActionListener(clickRemove);
        btn_add.addActionListener(clickAdd);
    }

    @Override
    public void setEnabled(boolean enabled) {
        btn_change.setEnabled(enabled);
        btn_remove.setEnabled(enabled);
    }

    private final ActionListener clickAdd = e -> EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            new EquationInfoDialog(owner, null).setVisible(true);
        }
    });

    private final ActionListener clickRemove = e -> {

    };

    private final ActionListener clickSearch = e -> {

    };

    private final ActionListener clickChange = e -> {

    };

    private static class Cell extends GridBagConstraints {
        Cell(int x, int y){
            super();

            this.fill = BOTH;
            this.insets = new Insets(5,5,5,5);

            this.gridx = x;
            this.gridy = y;
        }
    }
}
