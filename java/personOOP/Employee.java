package oop;

/**
 *
 * @author MaksimPC
 */
public class Employee extends person {

    private long salary;
    private BankAccount account;
    private person supervisor;

    public Employee(String name, Address address, String email, String phone, long salary, BankAccount account, person supervisor) {
        super(name, address, email, phone);
        this.salary = salary;
        this.account = account;
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "Employee: " + super.name + " earns: " + Long.toString(this.salary) + " , " + account;
    }

    //main method
    public static void main(String[] args) {
        
        //test
        Address e_adr = new Address("Gate Tower 3", "Al Reem Island", "Abu Dhabi");
        Address s_adr = new Address("Sun Tower", "Al Reem Island", "Abu Dhabi");
        person supervisor = new person("Alexey Stepnov", s_adr, "alexey@email.com", "555-1234");
        BankAccount account = new BankAccount("EMP001", 5000.0);
        Employee employee = new Employee("Maksim Stepnov", e_adr, "maksim@email.com", "555-5678", 75000, account, supervisor);

        System.out.println(employee.toString());
        System.out.println("Supervisor: " + supervisor.name);
        System.out.println("Email: " + employee.email);
        System.out.println("Phone: " + employee.phone);
    }
}
