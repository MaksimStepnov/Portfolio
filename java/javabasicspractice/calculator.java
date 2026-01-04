package homework_test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator {
    public JFrame frame;
    public JTextField text;
    public double num;
    public double num_2;
    public char operator;
    
    public void main (String[] args){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        text = new JTextField();
        text.setFont(new Font("Arial", Font.BOLD, 20));
        text.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(text, BorderLayout.NORTH);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4,4,5,5));
        String[] button = {
            "1","2","3",
            "4","5","6",
            "7","8","9",
            "0","C","+",
            "-","/","*"
        };
        for (String text : button) {
            JButton button_2 = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }
        
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(300, 400);
        frame.setVisible(true);
    }
        
    }
    
}
