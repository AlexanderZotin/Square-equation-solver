package solver;

import solver.controller.Controller;
import solver.view.View;
import solver.view.Window;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String [] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(UnsupportedLookAndFeelException | ClassNotFoundException 
                | InstantiationException | IllegalAccessException e) {
            System.err.println("An error has occurred trying set L&F! The program will work correct but will not look same as ussually!");
        }
        SwingUtilities.invokeLater(() -> {
            View view = new Window();
            Controller сontroller = new Controller(view);
            view.subscribeToController(сontroller);
            view.setVisible(true);
        });
    }
}
