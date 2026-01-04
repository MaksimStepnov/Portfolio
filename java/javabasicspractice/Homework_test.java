package homework_test;

import java.util.Scanner;

public class Homework_test {
public static void main (String[] args){
    
Scanner scanner = new Scanner(System.in);
    
System.out.println("Enter a number: ");
Double number = scanner.nextDouble();

System.out.println("Enter a number: ");
Double number_2 = scanner.nextDouble();

System.out.println("Select operation (+ , - , / , *) : ");
char operator = scanner.next().charAt(0);

Double result;

if (operator == '+'){
    result = number + number_2;
    System.out.println("Result = " + result);
}
else if (operator == '-'){
    result = number - number_2;
    System.out.println("Result = " + result);
}
else if (operator == '/'){
    result = number / number_2;
    System.out.println("Result = " + result);
}
else if (operator == '*'){
    result = number * number_2;
    System.out.println("Result = " + result);   
}

System.out.println("Thank you for using calculator!");
    
}
}
