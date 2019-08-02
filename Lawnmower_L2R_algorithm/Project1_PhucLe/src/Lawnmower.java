/* 	Programming Assignment #1: 
 * 	Student Name: Phuc Le
 * 	Course: CPSC 335, Section: 01
 * 	Alternating disks problem, lawnmower algorithm 
 * 	Given 2n alternating disks disks (light­dark), starting with light  
	the program reads the number of single color disks (light or dark), 
	arranges the disks in the correct order, and outputs the arranged disks and the number of swaps 
		INPUT: 	an positive integer n and a list of 2n disks of alternating   
				colors light­dark, starting with light.
		OUTPUT:	a list of 2n disks, the first n disks are dark and the next n
				disks are light, and an integer swapNumber representing the number of moves necessary
				to move the dark ones before the light ones
 */

import java.util.Scanner;

public class Lawnmower {
	public static void main(String[] args) {
		// LAWNMOWER ALGORITHM
		Scanner scan = new Scanner(System.in);
		System.out.println("The alternating disks problem: LAWNMOWER algorithm");
		System.out.print("Enter an even number of single color disks (light or dark):");
		
		
		int n = scan.nextInt();
		// initial		
		System.out.println("Initial configuration ...");
		char[] disks = new char[n * 2];

		for (int i = 0; i < n; i++) {
			disks[2 * i] = 'l';
			disks[2 * i + 1] = 'd';
		}
		System.out.println("List of disks");
		System.out.println(disks);
		System.out.println("After moving darker ones to the left\nList of disks");
		
		Lawnmower lawnmower = new Lawnmower();
		lawnmower.move_disks(n, disks);
	
		scan.close();
	}

	public void move_disks(int n, char[] disks) {
		int swapNumber = 0;
		for (int i = 0; i < n; i++) {
			for (int k = n * 2 - i - 1; k > i; k -= 2) {
				// swapping values
				char temp = disks[k];
				disks[k] = disks[k - 1];
				disks[k - 1] = temp;
				// increase number of swap
				swapNumber++;
			}
		}
		System.out.println(disks);
		System.out.println("Number of swaps is " + swapNumber);
	}

}
