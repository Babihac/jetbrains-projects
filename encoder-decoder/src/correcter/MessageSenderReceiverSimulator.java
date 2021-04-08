package correcter;

public class MessageSenderReceiverSimulator {
	public void simulate(String s) {
		System.out.println("Message to be send: " + s);
		Decoder d = new Decoder();
		Encoder e = new Encoder();
		ErrorGenerator errGen = new ErrorGenerator();
		String encoded = e.encode(s);
		System.out.println("encoded messsage: " + encoded);
		encoded = errGen.generateError(encoded, 3);
		System.out.println("errors generated during transmision: " + encoded);
		String decoded = d.decode(encoded);
		System.out.println("decoded message: " + decoded);
		
		
		
	}
}
