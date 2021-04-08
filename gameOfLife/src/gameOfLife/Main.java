package gameOfLife;
import java.util.Random;
import java.util.Scanner;

import javax.swing.RowFilter;

public class Main {
	interface LoopCallback {
	    
	    void onNewIteration(int iteration);
	}
	
public static void main(String[] args) {

	
	Random rand = new Random();
	GameUtility utility = new GameUtility();
	String[][] matrix = new String[10][10];
	for(int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			if (rand.nextBoolean()) {
				matrix[i][j] = "O";
			} else {
				matrix[i][j] = " ";
			}
			System.out.print(matrix[i][j]);
		}
		System.out.println();
	}
	String[][] matrix2 = new String[10][10];
	matrix2[0][1] = "O";
	matrix2[0][0] = "O";
	matrix2[1][1] = "O";
	State state = new State(matrix);
	//System.out.println("gfgf " + utility.getAllNeighbors(matrix2, 1, 0));
	state.setCurrentGen(utility.generateNewGeneration(state.getCurrentGen(), 10));
	state.setCurrentGen(utility.generateNewGeneration(state.getCurrentGen(), 10));
	state.printGen();
}
}
