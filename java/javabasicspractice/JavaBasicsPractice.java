package homework_test;

import java.util.Scanner;

public class JavaBasicsPractice {

    public static void main(String[] agrs) {
        computeSum();
        computePositiveTotalNegativeCount();
        printNaturalNumbers();
        checkPerfectNumber();
        PrintPrimeNumbers();
        ComputeEvenSum();
        factorial();
        GuessNumberGame();
    }

    public static void computeSum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Compute sum");
        System.out.println("-------------------------------------------");
        System.out.println("Please enter a number: ");
        double number = scanner.nextDouble();

        System.out.println("Please enter a second number: ");
        double number_2 = scanner.nextDouble();

        double output = number + number_2;

        System.out.println(number + "+" + number_2 + "=" + output);
        System.out.println("-------------------------------------------");
    }

    public static void computePositiveTotalNegativeCount() {
        System.out.println("TotalNegativeCount");
        System.out.println("-------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int total = 0;
        int negs = 0;

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter an integer ");
            int input = scanner.nextInt();
            if (input > 0) {
                total = total + input;
            } else if (input < 0) {
                negs = negs + 1;
            }
        }
        System.out.println("Sum of positives entered: " + total);
        System.out.println("Total amount of negatvies entered: " + negs);
        System.out.println("-------------------------------------------");
    }

    public static void printNaturalNumbers() {
        System.out.println("natural numbers");
        System.out.println("-------------------------------------------");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an number: ");
        int number = scanner.nextInt();
        int sum = 0;

        for (int i = 1; i <= number; i++) {
            sum += i;
        }
        System.out.println("Sum: " + sum);
        System.out.println("-------------------------------------------");
    }

    public static void checkPerfectNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Check perfect number");
        System.out.println("-------------------------------------------");
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        int sum = 0;

        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        if (sum == number) {
            System.out.println(number + " is a perfect number");
        } else {
            System.out.println(number + " is not a perfect number");
        }
    }

    public static void PrintPrimeNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("print prime numbers");
        System.out.println("-------------------------------------------");
        System.out.println("Enter an number: ");
        int number = scanner.nextInt();

        for (int i = 2; i <= number; i++) {
            boolean isPrime = true;

            for (int n = 2; n <= i / 2; n++) {
                if (i % n == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                System.out.print(i + " ");
                System.out.println("-------------------------------------------");
            }
        }
    }

    public static void ComputeEvenSum() {
        int sum = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("compute even sum");
        System.out.println("-------------------------------------------");

        for (int i = 0; i < 10; i++) {
            System.out.println("Enter an number: ");
            int number = scanner.nextInt();
            if (number % 2 == 0) {
                sum = sum + number;
            }
        }
        System.out.println("Sum of even numbers: " + sum);
        System.out.println("-------------------------------------------");
    }

    public static void factorial() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("find factorial");
        System.out.println("-------------------------------------------");
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        int result = 1;
        for (int i = 1; i <= number; i++) {
            result = result * i;
        }
        System.out.println("Factorial of " + number + " is: " + result);
        System.out.println("-------------------------------------------");
    }

    public static void GuessNumberGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess a number between 1 - 20, you have 3 attempts: ");
        System.out.println("-------------------------------------------");

        int answer = 7;
        int attempts = 0;

        while (attempts < 3) {
            System.out.println("Guess a number: ");
            int guess = scanner.nextInt();
            attempts = attempts + 1;

            if (guess == answer) {
                System.out.println("Correct! Guessed in: " + attempts + " attempts.");
                return;
            } else if (guess != answer) {
                System.out.println("Guess again!");
            }
        }
        System.out.println("The game is locked. Try again later!");
    }
}
