package blockchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Blockchain implements Serializable {
	private static final long serialVersionUID = 666L;
	private int trackingZeros;
	private int size = 0;
	private List<Message> recievedMessages;
	private List<Message> test = new ArrayList<>();
	private ArrayList<Block> blocks;
	
	private static class BlockchainWrapper {
		static Blockchain INSTANCE = new Blockchain(5);
	}
	
	private Blockchain(int zeros) {
		trackingZeros = zeros;
		blocks = new ArrayList<>();
		recievedMessages = new ArrayList<>();
	}
	public void setRecievedMessages(List<Message> recievedMessages) {
		this.recievedMessages = recievedMessages;
	}
	public static Blockchain getInstance() {
        return BlockchainWrapper.INSTANCE;
    }
	
	public void setTrackingZeros(int trackingZeros) {
		this.trackingZeros = trackingZeros;
	}
	public int getSize() {
		return blocks.size();
	}
	
	public int getTrackingZeros() {
		return trackingZeros;
	}
	
	public String getPrevHash() {
		return size > 0? blocks.get(size-1).getHash() : "0";
	}
	
	
//	public void addBLock() {
//		String prevHash = "";
//		if(blocks.size() == 0) prevHash = "0";
//		else prevHash = blocks.get(size-1).getHash();
//		Block block = new Block(prevHash, trackingZeros);
//		blocks.add(block);
//		size++;
//	}
	
	public synchronized void addBlock(Block block) {
		blocks.add(block);
		size++;
	}
	
	public synchronized List<Message> getRecievedMessages() {
//		for(String s : recievedMessages) {
//			System.out.println(s);
//		}
//		System.out.println();
		return recievedMessages;
	}
	
	public synchronized void addMess(Message m) {
		recievedMessages.add(m);
	}
	

	public void printBlockChain() {
		for(Block b : blocks) {
			System.out.println(b);
			System.out.println();
		}
	}

}
