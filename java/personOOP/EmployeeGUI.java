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

public class EmployeeGUI extends JFrame implements ActionListener
{
    private JPanel panel;

    private JLabel salary;
    private JTextField salaryField;
    
    private JLabel accountNumber;
    private JTextField accountNumberField;
    
    private JLabel supervisor;
    private JTextField supervisorField;

    private JButton submitButton;
    private JButton resetButton;

    public EmployeeGUI()
    {
        super("Employee Information");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(350, 300);

        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Salary field
        JPanel salaryRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.salary = new JLabel("Salary:");
        this.salaryField = new JTextField(20);
        salaryRow.add(salary);
        salaryRow.add(salaryField);
        this.panel.add(salaryRow);

        //Account Number field
        JPanel accountRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.accountNumber = new JLabel("Account Number:");
        this.accountNumberField = new JTextField(20);
        accountRow.add(accountNumber);
        accountRow.add(accountNumberField);
        this.panel.add(accountRow);

        //Supervisor field
        JPanel supervisorRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.supervisor = new JLabel("Supervisor:");
        this.supervisorField = new JTextField(20);
        supervisorRow.add(supervisor);
        supervisorRow.add(supervisorField);
        this.panel.add(supervisorRow);

        //Submit and reset buttons
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
        this.salaryField.setText("");
        this.accountNumberField.setText("");
        this.supervisorField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        if (command.equals("Submit"))
        {
            System.out.println("Salary: " + this.salaryField.getText());
            System.out.println("Account Number: " + this.accountNumberField.getText());
            System.out.println("Supervisor: " + this.supervisorField.getText());
        }
        else if (command.equals("Reset"))
        {
            this.resetFields();
        }
    }

    public static void main(String args[])
    {
        new EmployeeGUI();
    }
}