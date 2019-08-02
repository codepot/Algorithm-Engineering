/*
 Assignment 2: Longest non-decreasing subsequence problem, end-to-beginning algorithm                                                                   
 STUDENT NAME: PHUC LE
 COURSE: CPSC 335-1
 Given a sequence of elements the program finds a subsequence of it in which the subsequence's
 elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible.                     	
 The program reads the number of elements in the sequence, then the elements and outputs the sorted
 sequence and the running time.          
 INPUT: a positive integer n and a list of n elements                     	
 OUTPUT: a longest non-decreasing subsequence of the initial sequence       	
 */

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class End2Begin {

    private static void print_sequence(int n, int[] A) {
        String seq = "";
        for (int i = 0; i < n; i++) {
            seq += A[i] + " ";
        }
        System.out.print(seq + "\n");
    }

    public static void main(String[] args) {
        int n, i, j, max, index;
        int[] A, R, H;
        Scanner scanner = new Scanner(System.in);
        System.out.println("CPSC 335-1 - Programming Assignment #2\nLongest non-decreasing subsequence problem, end-to-beginning algorithm\nEnter the number of elements in the sequence ");
        n = scanner.nextInt();
        A = new int[n];
        H = new int[n];
        System.out.println("Enter the elements in the sequence");

        for (i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println("Input Sequence");
        print_sequence(n, A);

        // Start the chronograph to time the execution of the algorithm
        long startTime = System.nanoTime();

        // populate the array H with 0 values                             
        Arrays.fill(H, 0);

        // loop to calculate the values of array H
        for (i = n - 2; i >= 0; i--) {
            for (j = n - 1; j > i; j--) {
                if (A[i] < A[j] && H[i] <= H[j]) {
                    H[i] = H[j] + 1;
                }
            }
        }

        // calculate the length of the longest subsequence 
        max = H[0];
        for (i = 1; i < n; i++) {
            if (H[i] > max) {
                max = H[i];
            }
        }

        System.out.println("The longest non-decreasing subsequence has length\n" + (max + 1));

        // allocate space for the subsequence R 
        R = new int[max + 1];

        // store in j the index of the element appended to R  
        index = 0;
        for (i = 0; i < n; i++) {
            if (H[i] == max) {
                R[index] = A[i];
                index++;
                max--;
            }
        }

        // End the chronograph to time the loop
        long endTime = System.nanoTime();
        
        System.out.println("The longest non-decreasing subsequence is");
        print_sequence(R.length, R);
        double elapsedTime = (endTime - startTime) / 1000000000.0;
        System.out.println("Elapse time: " + new DecimalFormat("#.######").format(elapsedTime) + " seconds ");
        scanner.close();
    }

}
