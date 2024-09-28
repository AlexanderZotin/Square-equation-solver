package solver.controller;

import solver.model.QuadraticEquationSolver;
import solver.model.SquareEquation;
import solver.view.View;
import java.util.Objects;
import java.util.Optional;

public class ApplicationController implements Controller {
    private final View view;

    public ApplicationController(View view) {
        this.view = Objects.requireNonNull(view);
    }

    @Override
    public void countRequested() {
        Optional<SquareEquation> equation = view.parseData();
        equation.ifPresent(squareEquation -> view.showSolving(QuadraticEquationSolver.solve(squareEquation)));
    }
}
