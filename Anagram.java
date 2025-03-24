// Write a Java program to check if two input strings are anagrams of each other without using sort. 
// Two strings are considered anagrams if they contain the same characters in the same frequency 
// but in any order. Your program should:

// Input:
// String 1: race
// String 2: care

// Output: true

// Input:
// String 1: abcd
// String 2: bcdd

// Output: false

// Input:
// String 1: save
// String 2: vase

// Output: true

import java.util.HashMap;
import java.util.Scanner;

public class Anagram {

    public static boolean isAnagram(String str1, String str2) {
        // Remove spaces and convert to lowercase
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // If lengths are not the same, they cannot be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Use a hashmap to count character frequencies
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        for (char c : str1.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for (char c : str2.toCharArray()) {
            if (!charCountMap.containsKey(c) || charCountMap.get(c) == 0) {
                return false;
            }
            charCountMap.put(c, charCountMap.get(c) - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        
        // boolean result = Anagram.isPalindrom("appa");
        // System.out.println(resut);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();
        scanner.close();

        if (isAnagram(str1, str2)) {
            System.out.println("The strings are anagrams.");
        } else {
            System.out.println("The strings are not anagrams.");
        }

    }

    //Appa
    // public static boolean isPalindrom(String str1) {
    //     if (st1 != null) {
    //         int length = str1.length();
    //         for (int i = 0; i<lenght/2;i++) {
    //             if (str1.charAt(i) != str1.charAt(length - 1)) {
    //                 return false;
    //             }
    //             else {
    //                 return true;
    //             }
    //         }
    //     }
    // }

}