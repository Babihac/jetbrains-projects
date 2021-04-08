
import java.util.Scanner;

import exceptions.WrongFormatException;
import matrix_processor.Matrix;
import matrix_processor.Processor;

public class Main {
	
	public static void main(String[] args) {
		//Controller c = new Controller();
		//c.start();
		Matrix  a = new Matrix(3,3);
		a.fill(new double[][] {{2,-1,0},{0,1, 2}, {1,1,0}});
		Util util = new Util();
		try {
		Matrix d = util.inverseMatrix(a);
		d.printMatrix(); 
		} catch(WrongFormatException e) {
			
		}
		}
}
