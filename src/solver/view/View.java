package solver.view;

import solver.model.SquareEquation;
import java.util.Optional;

public interface View {
    void setVisible(boolean visible);
    Optional<SquareEquation> parseData();
    void showSolving(String result);
}
