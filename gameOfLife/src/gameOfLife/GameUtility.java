package gameOfLife;

public class GameUtility {
	public static int getAllNeighbors(String[][] generation, int row, int col) {
		int neighbors = 0;
		String[] allNeigh = new String[8];
		int ln = generation.length;
		allNeigh[0] = generation[Math.floorMod(row-1,ln)][Math.floorMod(col,ln)];
		allNeigh[1] = generation[Math.floorMod(row-1,ln)][Math.floorMod(col+1,ln)];
		allNeigh[2] = generation[Math.floorMod(row,ln)][Math.floorMod(col+1,ln)];
		allNeigh[3] = generation[Math.floorMod(row+1,ln)][Math.floorMod(col+1,ln)];
		allNeigh[4] = generation[Math.floorMod(row+1,ln)][Math.floorMod(col,ln)];
		allNeigh[5] = generation[Math.floorMod(row+1,ln)][Math.floorMod(col-1,ln)];
		allNeigh[6] = generation[Math.floorMod(row,ln)][Math.floorMod(col-1,ln)];
		allNeigh[7] = generation[Math.floorMod(row-1,ln)][Math.floorMod(col-1,ln)];
		for (int i = 0; i < 8; i++) {
			//System.out.println("neih " + allNeigh[i]);
			if (allNeigh[i] == "O") neighbors++;
			//System.out.print(allNeigh[i]);
		}
		
		return neighbors;
	}
	private static boolean isAlive(String val) {
		return val == "O";
	}
	public static String[][] generateNewGeneration(String[][] currentGen, int n) {
		
		String[][] newGeneration = new String[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int numOfNeighbors = getAllNeighbors(currentGen, i, j);
				if (isAlive(currentGen[i][j])) {
					if(numOfNeighbors == 2 || numOfNeighbors == 3) {
						newGeneration[i][j] = "O";
					} else {
						newGeneration[i][j] = " ";
					}
				} else {
					if (numOfNeighbors == 3) {
						newGeneration[i][j] = "O";
					} else {
						newGeneration[i][j] = " ";
					}
				}
			}
		}
		return newGeneration;
	}

}
