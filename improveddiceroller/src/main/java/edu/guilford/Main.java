package edu.guilford;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Define the size of the array
        int arraySize = 10; // You can change this value to any size you want

        // Create an array to hold the random integers
        int[] randomNumbers = new int[arraySize];
        
        // Create an instance of the Random class
        Random random = new Random();
        
        // Fill the array with random integers
        for (int i = 0; i < arraySize; i++) {
            randomNumbers[i] = random.nextInt(100); // Generates random numbers between 0 and 99
        }

        // Print the unsorted array
        System.out.println("Unsorted array of random integers:");
        for (int number : randomNumbers) {
            System.out.print(number + " ");
        }

        System.out.println("");
        
        // Sort the array using the Arrays.sort() method
        Arrays.sort(randomNumbers);
        
        // Print the sorted array
        System.out.println("Sorted array of random integers:");
        for (int number : randomNumbers) {
            System.out.print(number + " ");
        }
    }
}