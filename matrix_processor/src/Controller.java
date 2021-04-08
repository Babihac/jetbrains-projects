import java.util.Scanner;

import exceptions.WrongFormatException;
import matrix_processor.Matrix;
import matrix_processor.Processor;

public class Controller {
	private boolean finished = false; 

	public void start() {
		Scanner sc = new Scanner(System.in);
		Matrix a;
		Matrix b;
		Matrix res;
		Processor p;
		int r1,r2;
		int c1,c2;
		
		while(!finished) {
			System.out.println("1. Add matrices");
			System.out.println("2. Multiply matrix to a constant");
			System.out.println("3. Multiply matrices");
			System.out.println("4. Transpose matrix");
			System.out.println("5. Calculate determinant");
			System.out.println("0. Exit");
			System.out.println("Your choice:");
			int c = sc.nextInt();
			
			switch(c) {
			case 1 : 
				p = new Processor(); 
				System.out.println("Enter first matrix size");
				r1 = sc.nextInt();
				c1 = sc.nextInt();
		       a = new Matrix(r1,c1);
		       System.out.println("enter matrix");
		       a.fill();
		       
		       System.out.println("Enter second matrix size");
		       r2 = sc.nextInt();
		       c2 = sc.nextInt();
		       b = new Matrix(r2,c2);
		       System.out.println("Enter second matrix");
		       b.fill();
		       try {
		    	  res = p.add(a, b);
		    	  System.out.println("Result");
		    	   res.printMatrix();
		       } catch(WrongFormatException e) {
		    	   System.out.println("Error");
		       }
		       break;
			case 2:
				p = new Processor(); 
				System.out.println("Enter matrix size");
				r1 = sc.nextInt();
			    c1= sc.nextInt();
			    a = new Matrix(r1,c1);
			    System.out.println("Enter matrix");
			    a.fill();
			    System.out.println("Enter constant");
			    int val = sc.nextInt();
			    res = p.mult(a, val);
			    System.out.println("Result");
			    res.printMatrix();
			    break;
			case 3:
				 p = new Processor(); 
				 System.out.println("Enter first matrix size");
			     r1 = sc.nextInt();
			     c1 = sc.nextInt();
			     a = new Matrix(r1,c1);
			     System.out.println("Enter matrix");
			     a.fill();
			     System.out.println("Enter second  matrix size");
			     r2 = sc.nextInt();
			     c2 = sc.nextInt();
			     b = new Matrix(r2,c2);
			     System.out.println("enter matrix");
			     b.fill();
			       
			    res = p.matrixMult(a, b);
			    System.out.println("Result");
			    res.printMatrix();
			    break;
			case 4 :
				p = new Processor();
				transposeMenu(sc, p);
				break;
			case 5 :
				p = new Processor();
				System.out.println("Enter first matrix size");
			    r1 = sc.nextInt();
			    c1 = sc.nextInt();
			    a = new Matrix(r1,c1);
			    System.out.println("Enter matrix");
			    a.fill();
			    System.out.println("Result");
			    try {
			    	double d = p.computeDeterminant(a);
					System.out.println(d);
			    } catch(WrongFormatException e) {
			    	System.out.println(e.getMessage());
			    }
			    break;
			case 6 :
				p = new Processor();
				System.out.println("Enter first matrix size");
			    r1 = sc.nextInt();
			    c1 = sc.nextInt();
			    a = new Matrix(r1,c1);
			    System.out.println("Enter matrix");
			    a.fill();
			    System.out.println("Result");
			    try {
			    	res = p.inverseMatrix(a);
					res.printMatrix();
			    } catch(WrongFormatException e) {
			    	System.out.println(e.getMessage());
			    }
			    break;
				
				
			case 0: finished = true;
				
				
			}
			
		}
		
	}
	
	public void transposeMenu(Scanner sc, Processor p) {
		Matrix a;
		System.out.println("1. Main diagonal");
		System.out.println("2. Side diagonal");
		System.out.println("3. Vertical line");
		System.out.println("4. Horizontal line");
		System.out.println("Your choice:");
		int c = sc.nextInt();
		System.out.println("enter matrix size");
		int r = sc.nextInt();
		int col = sc.nextInt();
		a = new Matrix(r, col);
		System.out.println("Enter matrix");
		a.fill();
		Matrix res = p.selectTransposeAlgo(a, c);
		res.printMatrix();
	}
} 











