package oop;

/**
 *
 * @author MaksimPC
 */
public class person {

    protected String name;
    protected int age;
    protected Address address;
    protected String email;
    protected String phone;

    public person(String name, Address address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String toString() {
        return name + " , " + email + ", " + address + ", " + phone;
    }

    public static void main(String[] args) {
        Address address = new Address("Gate Tower 3", "Al Reem Island", "Abu Dhabi");
        person person = new person("Maksim Stepnov", address, "makism@email.com", "555-1234");
        System.out.println(person.toString());
    }
}
