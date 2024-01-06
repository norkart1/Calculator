import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField display;

    private double num1, num2, result;
    private String operator;

    public Main() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
 
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onButtonClick(btn.getText());
                }
            });
            buttonPanel.add(btn);
        }

        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void onButtonClick(String buttonText) {
        switch (buttonText) {
            case "C":
                display.setText("");
                break;
            case "=":
                num2 = Double.parseDouble(display.getText());
                calculateResult();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                num1 = Double.parseDouble(display.getText());
                operator = buttonText;
                display.setText("");
                break;
            default:
                display.setText(display.getText() + buttonText);
        }
    }

    private void calculateResult() {
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error: Division by zero");
                    return;
                }
                break;
        }
        display.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
