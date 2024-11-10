package solver.view;

import solver.controller.Controller;
import solver.model.SquareEquation;
import java.util.Optional;

import lombok.NonNull;

public interface View {
    void subscribeToController(@NonNull Controller controller);
    void setVisible(boolean visible);
    Optional<SquareEquation> parseData();
    void showSolving(String result);
}
