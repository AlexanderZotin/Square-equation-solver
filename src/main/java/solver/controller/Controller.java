package solver.controller;

import solver.model.QuadraticEquationSolver;
import solver.model.SquareEquation;
import solver.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Controller implements ActionListener {
    private final View view;

    @Override
    public void actionPerformed(ActionEvent e) {
        Optional<SquareEquation> equation = view.parseData();
        equation.ifPresent(squareEquation -> view.showSolving(QuadraticEquationSolver.solve(squareEquation)));
    }
}
