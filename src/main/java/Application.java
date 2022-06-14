import ui.mainScreen.MainScreen;

import java.awt.*;

public class Application {

    public static void main(String[]args){
        EventQueue.invokeLater(() -> new MainScreen().setVisible(true));
    }
}
