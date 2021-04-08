package gameOfLife;

public class State {
	private String[][] currentGen;
	public State(String[][] arr) {
		this.currentGen = arr;
	}
	
	public String[][] getCurrentGen() {
		return currentGen;
	}
	public void setCurrentGen(String[][] currentGen) {
		this.currentGen = currentGen;
	}
	
	public void printGen() {
		for (int i = 0; i < currentGen.length; i++) {
			for(int j = 0; j < currentGen.length; j++) {
				System.out.print(currentGen[i][j]);
			}
			System.out.println();
		}
	}
	
	public int getAlive() {
		int alive = 0;
		for (int i = 0; i < currentGen.length; i++) {
			for(int j = 0; j < currentGen.length; j++) {
				if(currentGen[i][j].equals("O")) alive++;
			}
		}
		return alive;
	}
	
	

}
