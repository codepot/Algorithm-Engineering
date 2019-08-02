/*
 Assignment 2: Longest non-decreasing subsequence problem, power set algorithm                                                                   
 STUDENT'S NAME: PHUC LE, CPSC 335.01
 Given a sequence of elements the program finds a subsequence of it in which the subsequence's
 elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible.                     	
 The program reads the number of elements in the sequence, then the elements and outputs the sorted
 sequence and the running time.        
 INPUT: a positive integer n and a list of n elements                     	
 OUTPUT: a longest non-decreasing subsequence of the initial sequence       
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class PowerSet {
    //function to print a sequence, given the number of elements the actual sequence stored as an array
    private static void print_sequence(int n, int[] A) {
        String seq = "";
        for (int i = 0; i < n; i++) {
            seq += A[i] + " ";
        }
        System.out.print(seq + "\n");
    }

// function to generate the power set of {1, 2, …first argument} and retrieve the best set                                                    
    private static void printPowerset(int n, IntegerObject bestSize, IntegerObject[] bestSet, int[] A) {
        // function to generate the power set of {1, .., n} and retrieve the best set
        // n represents the maximum value in the set                                	
        // bestSet represents the set                                                  
        // bestSize is the size of the bestSet
        int stack[], k;
        // allocate space for the set                                                
        stack = new int[n + 1];
        stack[0] = 0;
        /* 0 is not considered as part of the set */
        k = 0;
        while (true) {
            if (stack[k] < n) {
                stack[k + 1] = stack[k] + 1;
                k++;
            } else {
                stack[k - 1]++;
                k--;
            }
            if (k == 0) {
                break;
            }
            checkSet(stack, k, bestSet, bestSize, A);
        }
        return;
    }

    private static void checkSet(int[] stack, int k, IntegerObject[] bestSet, IntegerObject bestSize, int[] A) {
        // function to check the currently generated set stack of size k against the current
        // best set bestSet of size bestSize                               
        {
            int i = 0;
            // check that the indices in stack generate a subsequence of non-decreasing order
            if (k < 2) {
                // the set contains a single index so the subsequence is in non-decreasing order            
                if (k > bestSize.value) {
                    // we found a better set       	 
                    // WRITE CODE TO STORE stack into bestSet and UPDATE bestSize TO 
                    bestSet[0] = new IntegerObject(stack[0]);
                    bestSize.value = k;
                    return;
                }
            } else {
                // the set contains more than a single index so check that the subsequence is in order
                // decrease each index by one since the indices of array A are in     	
                // the range 0..n-1 and not 1..n 	
                // WRITE CODE (AN IF STATEMENT) TO CHECK THAT THE ELEMENTS IN ARRAY
                // A AT INDICES stack[i+1]-1 AND stack[i+2]-1 ARE IN NON-DECREASING ORDER
                // IF THE TWO ELEMENTS ARE OUT OF ORDER THEN return
                for (i = 0; i < k - 1; i++) {
                    if (A[stack[i + 1] - 1] > A[stack[i + 2] - 1]) {
                        return;
                    }
                }
            }
            // we have an non-decreasing so we compare it against the current best set                             	
            if (k > bestSize.value) {
                            
                // we found a better set        	
                // WRITE CODE TO STORE stack into bestSet and UPDATE bestSize TO k                
                for (i = 0; i < k; i++) {
                    bestSet[i] = new IntegerObject(stack[i + 1] - 1);
                    bestSize.value = k;
                }
                return;
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        IntegerObject bestSize;
        int n, i;
        IntegerObject[] bestSet;
        int[] A, R;       
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("CPSC 335-1 - Programming Assignment #2\nLongest non-decreasing subsequence problem, powerset  algorithm\nEnter the number of elements in the sequence ");
        n = scanner.nextInt();
        A = new int[n];        
        System.out.println("Enter the elements in the sequence");

        for (i = 0; i < n; i++) {
            A[i] = scanner.nextInt();            
        }
        System.out.println("Input Sequence");
        print_sequence(A.length, A);
       
        // Start the chronograph to time the execution of the algorithm
        long startTime = System.nanoTime();        
       
        bestSet = new IntegerObject[n + 1];
        bestSize = new IntegerObject(0);
        printPowerset(n, bestSize, bestSet, A);
        R = new int[bestSize.value];
        
        //ASSIGN VALUES FOR RESULT SET
        for (i = 0; i < bestSize.value; i++) {           
            R[i] = A[bestSet[i].value];
        }
        // END TIME
        System.out.println("The longest non-decreasing subsequence has length\n" + bestSize.value);
        
        //DISPLAY RESULT SET
        System.out.println("The longest non-decreasing subsequence is");
        print_sequence(bestSize.value, R);
        
        // End the chronograph to time the loop
        long endTime = System.nanoTime();        
           
        double elapsedTime = (endTime - startTime) / 1000000000.0;
        System.out.println("Elapse time: " + new DecimalFormat("#.######").format(elapsedTime) + " seconds ");
        scanner.close();
    }    
}

// in Java, primitives like integers are not passed by references
class IntegerObject {
    int value;
    IntegerObject(int val) {
        this.value = val;
    }
}
