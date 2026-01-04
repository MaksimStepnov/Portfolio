package oop;

/**
 *
 * @author MaksimPC
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayAllDataGUI extends JFrame
{
    private JPanel panel;
    private JLabel displayLabel;
    public DisplayAllDataGUI()
    {
        super("db gui");
        this.setSize(700, 150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.displayLabel = new JLabel();

        CustomerDBAccess cdb = new CustomerDBAccess();
        String[] customerColumns =
        {
            "name",
            "phone",
            "email",
            "street",
            "city",
            "country",
            "membership",
            "points",
            "signup_on"
        };

        java.util.ArrayList<java.util.ArrayList<String>> customerData =
                cdb.getData(DBManager.CUSTOMERS_TABLE_NAME, customerColumns);

        EmployeeDBAccess edb = new EmployeeDBAccess();

        String[] employeeColumns =
        {
            "name",
            "phone",
            "email",
            "street",
            "city",
            "country",
            "supervisor"
        };

        java.util.ArrayList<java.util.ArrayList<String>> employeeData =
                edb.getData(DBManager.EMPLOYEES_TABLE_NAME, employeeColumns);

        displayLabel.setText(
            "Customers: " + customerData.toString()
            + "     Employees: " + employeeData.toString()
        );

        panel.add(displayLabel, BorderLayout.CENTER);

        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new DisplayAllDataGUI();
    }
}

