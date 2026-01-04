/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homework_test;

/**
 *
 * @author MaksimPC
 */
public class letter {
    public static void main (String[] args){
        String replace = "A";
        String word = "Apple";

        String noun = word;
        word = word.replace(replace , "@");
        
        System.out.println("Word: Apple" + " , A = @");
        System.out.println("-------------------------");
        System.out.println(word);
        
    }
    
}
