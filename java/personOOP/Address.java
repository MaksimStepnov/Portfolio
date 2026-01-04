/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;

/**
 *
 * @author MaksimPC
 */
public class Address {
    private String street;
    private String city;
    private String country;

    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public String toString() {
        return street + ", " + city + ", " + country;
    }
    public static void main(String[] args) {
        Address address = new Address("Gate Tower 3", "Al Reem Island", "Abu Dhabi");
        System.out.println("Address: " + address.toString());
    }
}
