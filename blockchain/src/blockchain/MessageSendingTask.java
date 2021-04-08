package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MessageSendingTask  {
	private static int num = 0;
	private List<String> messages = new ArrayList<>();
	private  ScheduledExecutorService scheduler;
	
	public void startSendingMessages() {
		 scheduler = Executors.newScheduledThreadPool(1);
		 scheduler.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				messages.add("New message: " + num++);
			}
		}, 0, 100, TimeUnit.MILLISECONDS);
	}
	
	public void clearMess() {
		messages.clear();
	}
	
	public List<String> getMessages() {
		return messages;
	}

	

}
