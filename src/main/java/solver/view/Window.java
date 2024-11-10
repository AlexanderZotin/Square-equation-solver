package solver.view;

import solver.controller.Controller;
import solver.model.SquareEquation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.NonNull;

public class Window extends JFrame implements View {
    private static final Character[] SIGNS = {'+', '-'};
    private final JComboBox<Character> signOfA = new JComboBox<>(SIGNS);
    private final JTextField aCoefficient = new JTextField(2);
    private final JComboBox<Character> signOfB = new JComboBox<>(SIGNS);
    private final JTextField bCoefficient = new JTextField(2);
    private final JComboBox<Character> signOfC = new JComboBox<>(SIGNS);
    private final JTextField cCoefficient = new JTextField(2);
    private final JButton button = new JButton("Найти корни!");

    public Window() {
        JPanel generalPanel = new JPanel(new BorderLayout());
        JPanel panelWithEquation = new JPanel(new FlowLayout());
        panelWithEquation.add(signOfA);
        panelWithEquation.add(aCoefficient);
        panelWithEquation.add(new JLabel("<html>x<sup>2</sup></html>"));
        panelWithEquation.add(signOfB);
        panelWithEquation.add(bCoefficient);
        panelWithEquation.add(new JLabel("x"));
        panelWithEquation.add(signOfC);
        panelWithEquation.add(cCoefficient);
        panelWithEquation.add(new JLabel("= 0"));
        generalPanel.add("South", button);
        generalPanel.add("Center", panelWithEquation);
        setContentPane(generalPanel);
        setTitle("Решалка. ©А.А. Зотин, 2024");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    @Override
    public void subscribeToController(Controller controller) {
        button.addActionListener(controller);
    }
    
    @Override
    public Optional<SquareEquation> parseData() {
        try {
            BigDecimal a, b, c;
            a = parseNumber(aCoefficient.getText(), signOfA, BigDecimal.ONE);
            if(a.compareTo(BigDecimal.ZERO) == 0) {
                throw new ArithmeticException();
            }
            b = parseNumber(bCoefficient.getText(), signOfB, BigDecimal.ONE);
            c = parseNumber(cCoefficient.getText(), signOfC, BigDecimal.ZERO);
            return Optional.of(new SquareEquation(a, b, c));
        } catch(NumberFormatException _) {
            JOptionPane.showMessageDialog(this,
                    "В одно из полей введено не число!",
                    "Некорректный ввод", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException _) {
            JOptionPane.showMessageDialog(this,
                    "<html>Коэффициент <strong>A</strong> не должен быть 0!</html>",
                    "Некорректный ввод", JOptionPane.ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    private BigDecimal parseNumber(String str, JComboBox<Character> sign, BigDecimal defaultValue) {
        BigDecimal number;
        if(str.isEmpty()) {
            number = defaultValue;
        } else {
            str = str.replace(',', '.');
            number = new BigDecimal(str);
        }
        Object selectedSign = sign.getSelectedItem();
        assert selectedSign != null;
        return selectedSign.equals('-')? number.negate() : number;
    }

    @Override
    public void showSolving(@NonNull String solving) {
        JOptionPane.showMessageDialog(this, solving, "Решение", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            setLocationRelativeTo(null);
        }
    }
}
