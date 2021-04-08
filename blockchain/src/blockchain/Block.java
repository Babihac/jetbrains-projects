package blockchain;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Block implements Serializable {
	private static final long serialVersionUID = 333L;
	private static int id_inc = 1;
	private int minerID;
	private int id;
	private long timestamp;
	private long magicNumber;
	private int trackingZeros;
	private int durOfCreation;
	private List<Message> blockData;
	String hashOfPrevious;
	String hash;
	public Block(String prevHash, int zeros, int minerId, List<Message> blockData) {
		trackingZeros = zeros;
		this.blockData = blockData;
		minerID = minerId;
		id = id_inc++;
		//id_inc = id_inc +1;
		timestamp = new Date().getTime();
		this.hashOfPrevious = prevHash;
		//computeHash();
	}
	
	public int getDurOfCreation() {
		return durOfCreation;
	}
	public boolean startsWithNZeros(String hash, int zeros) {
		for(int i = 0; i < zeros; i++) {
			char x = hash.charAt(i);
			if( x  != '0') return false; 
		}
		return true;
	}
	
	public int getId() {
		return id;
	}
	
	public void setBlockData(List<Message> blockData) {
		this.blockData = blockData;
	}
	
	public void computeHash() {
		magicNumber = generateMagicNumber();
		StringBuilder data = new StringBuilder();
		data.append(hashOfPrevious).append(id).append(timestamp);
		for(Message m : blockData) {
			data.append(m.toString());
		}
		LocalTime start = LocalTime.now();
		hash = StringUtil.applySha256(data.toString().concat(String.valueOf(magicNumber)));
		Random r = new Random(Long.MAX_VALUE);
		while(!startsWithNZeros(hash, trackingZeros)) {
			magicNumber = r.nextLong();
			hash = StringUtil.applySha256(data.toString().concat(String.valueOf(magicNumber)));
		}
		durOfCreation = LocalTime.now().minusNanos(start.toNanoOfDay()).getSecond();
	}
	public String getHash() {
		return hash;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	private long generateMagicNumber() {
		Random r = new Random(Long.MAX_VALUE);
		return r.nextLong();
	}
	
	public String getHashOfPrevious() {
		char t = 'T';
		
		return hashOfPrevious;
	}
	
	@Override
	public String toString() {
		String data = "";
		if(blockData.size() == 0) {
			data = "No data";
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < blockData.size(); i++) {
				sb.append("\n").append(blockData.get(i).toString());
			}
			data = sb.toString();
		}
		StringBuilder b = new StringBuilder();
		
		b.append("Block:\n");
		b.append("Created by miner # ").append(minerID).append("\n")
		.append("Id: ").append(id).append("\n").append("Timestamp: ").append(timestamp).append("\n")
		.append("Hash of the previous block:").append("\n").append(hashOfPrevious).append("\n")
		.append("Hash of the block:").append("\n").append(hash)
		.append("\nBlock data:").append(data)
		.append("\nBlock was generating for " + durOfCreation + " seconds");
		return b.toString();
	}
}
