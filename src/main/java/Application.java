import service.EquationService;
import service.EquationService_impl;
import ui.mainScreen.MainScreen;

import java.awt.*;

public class Application {
    private static final String DB_URL = "URL";

    public static EquationService equationService = new EquationService_impl();
    public static MainScreen mainScreen;

    public static void main(String[]args){
        equationService.init(DB_URL);

        EventQueue.invokeLater(() -> {
            mainScreen = new MainScreen(equationService.getAll());
            mainScreen.setVisible(true);
        });
    }
}
