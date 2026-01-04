package oop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerGUI extends JFrame implements ActionListener
{
    private JPanel panel;

    private JLabel points;
    private JTextField pointsField;
    
    private JLabel membership;
    private JTextField membershipField;
    
    private JLabel signupDate;
    private JTextField signupDateField;

    private JButton submitButton;
    private JButton resetButton;

    public CustomerGUI()
    {
        super("Customer Information");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(350, 300);

        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Points field
        JPanel pointsRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.points = new JLabel("Loyalty Points:");
        this.pointsField = new JTextField(20);
        pointsRow.add(points);
        pointsRow.add(pointsField);
        this.panel.add(pointsRow);

        //Membership field
        JPanel membershipRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.membership = new JLabel("Membership ID:");
        this.membershipField = new JTextField(20);
        membershipRow.add(membership);
        membershipRow.add(membershipField);
        this.panel.add(membershipRow);

        //Signup field
        JPanel signupRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.signupDate = new JLabel("Signup Date:");
        this.signupDateField = new JTextField(20);
        signupRow.add(signupDate);
        signupRow.add(signupDateField);
        this.panel.add(signupRow);

        //Submit buttons
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.submitButton = new JButton("Submit");
        this.resetButton = new JButton("Reset");
        this.submitButton.addActionListener(this);
        this.resetButton.addActionListener(this);
        buttonRow.add(this.submitButton);
        buttonRow.add(this.resetButton);
        this.panel.add(buttonRow);

        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void resetFields()
    {
        this.pointsField.setText("");
        this.membershipField.setText("");
        this.signupDateField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        if (command.equals("Submit"))
        {
            System.out.println("Loyalty Points: " + this.pointsField.getText());
            System.out.println("Membership ID: " + this.membershipField.getText());
            System.out.println("Signup Date: " + this.signupDateField.getText());
        }
        else if (command.equals("Reset"))
        {
            this.resetFields();
        }
    }

    public static void main(String args[])
    {
        new CustomerGUI();
    }
}