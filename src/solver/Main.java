package solver;

import solver.controller.ApplicationController;
import solver.controller.Controller;
import solver.view.View;
import solver.view.Window;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    private static Controller applicationController;

    @SuppressWarnings("SpellCheckingInspection")
    public static void main(String [] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(UnsupportedLookAndFeelException | ClassNotFoundException 
                | InstantiationException | IllegalAccessException e) {
            System.err.println("Не удалось установить желаемый Look&Feel! Программа будет работать корректно, но может изменить внешний вид.");
        }
        View view = new Window();
        applicationController = new ApplicationController(view);
        view.setVisible(true);
    }

    public static Controller getController() {
        return applicationController;
    }
}
