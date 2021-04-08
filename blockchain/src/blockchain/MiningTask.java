package blockchain;

import java.util.List;
import java.util.concurrent.Callable;

public class MiningTask implements Callable<Block> {

	private int minerID;
	Blockchain blockChain;
	public MiningTask(Blockchain b, int id) {
		minerID = id;
		blockChain = b;
	}

	@Override
	public Block call() throws Exception {
		// TODO Auto-generated method stub
		List<Message> t = blockChain.getRecievedMessages();
		Block b = new Block(blockChain.getPrevHash(), blockChain.getTrackingZeros(), minerID, blockChain.getRecievedMessages());
		b.setBlockData(t);
		b.computeHash();
		return b;
	}

}
