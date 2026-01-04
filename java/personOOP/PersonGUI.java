package oop;

/**
 *
 * @author MaksimPC
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PersonGUI extends JFrame implements ActionListener
{
    private JPanel panel;

    // Person fields
    private JLabel name;
    private JTextField nameField;
    
    private JLabel email;
    private JTextField+ emailField;
    
    private JLabel phone;
    private JTextField phoneField;
    
    private JLabel street;
    private JTextField streetField;
    
    private JLabel city;
    private JTextField cityField;
    
    private JLabel country;
    private JTextField countryField;

    private JRadioButton customerSelect;
    private JRadioButton employeeSelect;

    private JButton submitButton;
    private JButton resetButton;

    public PersonGUI()
    {
        super("Person Information");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(350, 400); // Slightly larger to fit all fields

        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Row 1: Name field
        JPanel nameRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.name = new JLabel("Name:");
        this.nameField = new JTextField(20);
        nameRow.add(name);
        nameRow.add(nameField);
        this.panel.add(nameRow);

        // Row 2: Email field
        JPanel emailRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.email = new JLabel("Email:");
        this.emailField = new JTextField(20);
        emailRow.add(email);
        emailRow.add(emailField);
        this.panel.add(emailRow);

        // Row 3: Phone field
        JPanel phoneRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.phone = new JLabel("Phone:");
        this.phoneField = new JTextField(20);
        phoneRow.add(phone);
        phoneRow.add(phoneField);
        this.panel.add(phoneRow);

        // Row 4: Street field
        JPanel streetRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.street = new JLabel("Street:");
        this.streetField = new JTextField(20);
        streetRow.add(street);
        streetRow.add(streetField);
        this.panel.add(streetRow);

        // Row 5: City field
        JPanel cityRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.city = new JLabel("City:");
        this.cityField = new JTextField(20);
        cityRow.add(city);
        cityRow.add(cityField);
        this.panel.add(cityRow);

        // Row 6: Country field
        JPanel countryRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.country = new JLabel("Country:");
        this.countryField = new JTextField(20);
        countryRow.add(country);
        countryRow.add(countryField);
        this.panel.add(countryRow);

        // Row 7: Radio buttons
        JPanel radioRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.customerSelect = new JRadioButton("Customer");
        this.employeeSelect = new JRadioButton("Employee");
        ButtonGroup group = new ButtonGroup();
        group.add(customerSelect);
        group.add(employeeSelect);
        radioRow.add(customerSelect);
        radioRow.add(employeeSelect);
        this.panel.add(radioRow);

        // Row 8: Submit & Reset Buttons
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
        this.nameField.setText("");
        this.emailField.setText("");
        this.phoneField.setText("");
        this.streetField.setText("");
        this.cityField.setText("");
        this.countryField.setText("");
        this.customerSelect.setSelected(false);
        this.employeeSelect.setSelected(false);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        if (command.equals("Submit"))
        {
            System.out.println("Name: " + this.nameField.getText());
            System.out.println("Email: " + this.emailField.getText());
            System.out.println("Phone: " + this.phoneField.getText());
            System.out.println("Street: " + this.streetField.getText());
            System.out.println("City: " + this.cityField.getText());
            System.out.println("Country: " + this.countryField.getText());

            if (customerSelect.isSelected())
            {
                System.out.println("Type: Customer");
            }
            else if (employeeSelect.isSelected())
            {
                System.out.println("Type: Employee");
            }
        }
        else if (command.equals("Reset"))
        {
            this.resetFields();
        }
    }

    public static void main(String args[])
    {
        new PersonGUI();
    }
}