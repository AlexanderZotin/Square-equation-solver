package solver.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import lombok.NonNull;

public final class QuadraticEquationSolver {
    private static final MathContext mathContext = new MathContext(8, RoundingMode.HALF_UP);

    private QuadraticEquationSolver() {
        throw new AssertionError("No instances of class QuadraticEquationSolver for you!");
    }
    
    public static String solve(@NonNull SquareEquation equation) {
        StringBuilder solving = new StringBuilder("<html><h2>Решение:</h2>");
        BigDecimal discriminant = countDiscriminant(equation);
        solving.append(explainDiscriminantCount(discriminant, equation));
        RootsCount rootsCount = defineRootsCount(discriminant);
        solving.append(switch (rootsCount) {
            case NOTHING -> "Дискриминант меньше нуля, поэтому корней нет!<h2>Ответ:</h2> Нет корней";
            case TWO -> countTwoRoots(equation, discriminant);
            case ONE -> countOneRoot(equation);
            default -> throw new UnsupportedOperationException("Unexpected enum-constant: " + rootsCount);
        });
        return solving.toString();
    }

    private static BigDecimal countDiscriminant(SquareEquation equation) {
        return equation.b().pow(2)
                .subtract(BigDecimal.valueOf(4).multiply(equation.a()).multiply(equation.c()))
                .setScale(8, RoundingMode.HALF_UP)
                .stripTrailingZeros();
    }

    private static StringBuilder explainDiscriminantCount(BigDecimal foundDiscriminant, SquareEquation equation) {
        return new StringBuilder("Найдём дискриминант по формуле: b<sup>2</sup> - 4 * a * c<br>")
                .append("D = ").append('(').append(equation.b()).append(')').append("<sup>2</sup>")
                .append(" - 4 * ").append(equation.a()).append(" * ").append(equation.c()).append("<br>")
                .append("D = ").append(foundDiscriminant.toPlainString()).append("<br>");
    }

    private static RootsCount defineRootsCount(BigDecimal discriminant) {
        int result = discriminant.compareTo(BigDecimal.ZERO);
        return (result > 0)? RootsCount.TWO :
                ((result < 0)? RootsCount.NOTHING : RootsCount.ONE);
    }

    private enum RootsCount {ONE, TWO, NOTHING}

    private static StringBuilder countTwoRoots(SquareEquation equation, BigDecimal discriminant) {
        StringBuilder solving = new StringBuilder("Дискриминант больше нуля, поэтому у уравнения 2 корня.<br>");
        BigDecimal sqrtFromDiscriminant = discriminant.sqrt(mathContext);
        BigDecimal x1 = equation.b().negate()
                .add(sqrtFromDiscriminant)
                .divide(equation.a().multiply(BigDecimal.TWO), mathContext)
                .stripTrailingZeros();
        solving.append("x<sub>1</sub> = (-b + √D) : 2a<br>x<sub>1</sub> = (-")
                .append(equation.b().toPlainString()).append(" + √")
                .append(discriminant.toPlainString()).append(") : 2 * ")
                .append(equation.a().toPlainString()).append("<br>")
                .append("x<sub>1</sub> = ").append(x1.toPlainString()).append("<br>");
        BigDecimal x2 = equation.b().negate()
                .subtract(sqrtFromDiscriminant)
                .divide(equation.a().multiply(BigDecimal.TWO), mathContext)
                .stripTrailingZeros();
        solving.append("x<sub>2</sub> = (-b - √D) : 2a<br>x<sub>2</sub> = (-")
                .append(equation.b().toPlainString()).append(" + √")
                .append(discriminant.toPlainString()).append(") : 2 * ")
                .append(equation.a().toPlainString()).append("<br>")
                .append("x<sub>2</sub> = ").append(x2.toPlainString()).append("<br>");
        return solving.append("<h2>Ответ:</h2>").append(x1.toPlainString()).append("; ").append(x2.toPlainString());
    }

    private static StringBuilder countOneRoot(SquareEquation equation) {
        StringBuilder solving = new StringBuilder("Дискриминант равен нулю, поэтому у уравнения 1 корень.<br>");
        BigDecimal x = equation.b().negate().divide(BigDecimal.TWO.multiply(equation.a()), mathContext).stripTrailingZeros();
        solving.append("x = -b : 2a").append("<br>")
                .append("x = -").append(equation.b().toPlainString()).append(" : 2 * ").append(equation.a()).append("<br>")
                .append("x = ").append(x.toPlainString())
                .append("<h2>Ответ:</h2>").append(x.toPlainString());
        return solving;
    }
}
