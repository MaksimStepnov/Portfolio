/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp_sci;

/**
 *
 * @author MaksimPC
 */
import java.util.Scanner;

public class arrays {

    public static void main(String[] args) {
        bubblesort();
    }

    public static void linear() {
        Scanner scanner = new Scanner(System.in);
        int[] linear = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 6};

        boolean found = false;
        int count = 0;
        System.out.println("What to search for?");
        int key = scanner.nextInt();

        for (int i = 0; i < linear.length; i++) {
            if (linear[i] == key) {
                System.out.println(key + " found at position: " + i);
                count = count + 1;
                found = true;
            }
        }
        if (found = false) {
            System.out.println("Key not found in array");
        }
        if (found = true) {
            System.out.println("Key found in array total of " + count + " times.");
        }
    }

    public static void binary() {
        Scanner scanner = new Scanner(System.in);

        int[] binary = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boolean found = false;
        int count = 0;

        System.out.println("What to search for?");
        int key = scanner.nextInt();

        int middle = (binary.length / 2);
        if (key > middle) {
            for (int i = middle; i < binary.length; i++) {
                if (binary[i] == key) {
                    System.out.println(key + " found at position: " + i);
                    found = true;
                    count = count + 1;
                }
            }
        } else if (key < middle) {
            for (int i = middle; i < binary.length; i--) {
                if (binary[i] == key) {
                    System.out.println(key + " found at position: " + i);
                    found = true;
                    count = count + 1;
                }
            }
        }

        if (found = false) {
            System.out.println("Key not found in array.");
        }
        if (found = true) {
            System.out.println(" Key found in array total of " + count + " times");
        }

    }

    public static void bubblesort() {
        int[] bubble = {5, 3, 6, 1, 9, 2};
        System.out.println("unsorted array: 5, 3, 6, 1, 9, 2");
        System.out.println("");

        for (int j = 0; j < bubble.length - 1; j++) {
            for (int i = 0; i < bubble.length - 1; i++) {
                if (bubble[i + 1] > bubble[i]) {
                    int temp = bubble[i];
                    bubble[i] = bubble[i + 1];
                    bubble[i + 1] = temp;
                }
            }
        }
        System.out.println("");
        System.out.print("Sorted array: ");
        for (int i = 0; i < bubble.length; i++) {
            System.out.print(bubble[i]);
        }
    }
}
