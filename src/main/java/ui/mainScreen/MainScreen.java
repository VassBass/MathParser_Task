package ui.mainScreen;

import model.Equation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainScreen extends JFrame {
    private static final String TITLE = "MathParser";

    private final ArrayList<Equation>list;

    public MainScreen(ArrayList<Equation>list){
        super(TITLE);
        this.list = list;

        build();
        setReactions();
    }

    private void build(){
        Dimension sizeOfScreen = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,
                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
        this.setSize(sizeOfScreen);

    }

    private void setReactions(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
