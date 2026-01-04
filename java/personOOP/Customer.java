package oop;

/**
 *
 * @author MaksimPC
 */
import java.time.LocalDate;

public class Customer extends person {

    private long points;
    private long membership;
    private LocalDate signup;

    public Customer(String name, Address address, String email, String phone, long points, long membership, LocalDate signup) {
        super(name, address, email, phone);
        this.points = points;
        this.membership = membership;
        this.signup = signup;
    }
    

    @Override
    public String toString() {
        return "Customer: " + super.name + " has " + Long.toString(this.points) + " points";
    }

    //main method
    public static void main(String[] args) {
        Address address = new Address("Al Reem Island", "Abu Dhabi", "UAE");
        Customer customer = new Customer("Irina Stepnova", address, "irina@email.com", "555-9999", 1500L, 12345L, LocalDate.of(2022, 5, 15));

        //test everyhting
        System.out.println("Name: " + customer.name);
        System.out.println("LoyaltyPoints: " + customer.points);
        System.out.println("Membership: " + customer.membership);
        System.out.println("Signup Date: " + customer.signup);
        System.out.println("Email: " + customer.email);
        System.out.println("Phone: " + customer.phone);
        System.out.println("Info: " + customer.toString());
    }
}
