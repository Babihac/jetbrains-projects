package exceptions;

import java.util.Scanner;

import matrix_processor.Matrix;
import matrix_processor.Processor;

public class Main {
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 Processor p = new Processor(); 
	       int r1 = sc.nextInt();
	       int c1 = sc.nextInt();
	       Matrix m1 = new Matrix(r1,c1);
	       m1.fill();
	       int r2 = sc.nextInt();
	       int c2 = sc.nextInt();
	       Matrix m2 = new Matrix(r2,c2);
	       m2.fill();
	       try {
	    	   Matrix m3 = p.add(m1, m2);   
	    	   m3.printMatrix();
	       } catch(WrongFormatException e) {
	    	   System.out.println("Error");
	       }
	}

}
