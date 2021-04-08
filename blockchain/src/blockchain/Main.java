package blockchain;

public class Main {
	

	   public static void main(String[] args) {
		   //SerializationUtils utils = new SerializationUtils();
		   MiningTaskPool pool = new MiningTaskPool(5);
		   pool.startMining();
	}

}
