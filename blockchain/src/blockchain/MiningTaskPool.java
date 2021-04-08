package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MiningTaskPool {
	int size;
	private static int messageID = 0;
	private MessageSendingTask send;
	private Author author = new Author("Rumburak");
	private List<Message> messages = new ArrayList<>();
	 private final ScheduledExecutorService messageSender = Executors.newScheduledThreadPool(1);
	Blockchain b;
	List<MiningTask> tasks;
	public MiningTaskPool(int size) {
		this.size = size;
		b = Blockchain.getInstance();
		b.setTrackingZeros(3);
		send  = new MessageSendingTask();
		tasks = new ArrayList<MiningTask>();
		for(int i = 0; i < size; i++) {
			tasks.add(new MiningTask(b, i+1));
		}
	}
	
	
	public  void startMining() {
		startSendingMessages();
		int totalTime = 0;
		ExecutorService exServ = Executors.newCachedThreadPool();
		while(b.getSize() < 5) {
			//System.out.println(messages.size());
			try {
				System.out.println();
				Block res = exServ.invokeAny(tasks);
				
				Thread.sleep(500);
					totalTime += res.getDurOfCreation();
				b.addBlock(res);
				b.setRecievedMessages(new ArrayList<Message>(messages));
				messages.clear();
				//System.out.println(b.getRecievedMessages().size() + " " + messages.size());
			} catch(InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("Average time: " + totalTime/5.0);
		exServ.shutdown();
		messageSender.shutdown();
		b.printBlockChain();
	}
	private void startSendingMessages() {
		messageSender.scheduleAtFixedRate(() ->{ 
			messages.add((author.sendMessage("brra brraaa")));
			//System.out.println(messages.size());
			}, 0, 100, TimeUnit.MILLISECONDS);
	}

}
