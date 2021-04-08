package correcter;

public class Encoder {
	
	public String encode(String s) {
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			for(int j = 0; j < 3; j++) b.append(s.charAt(i));
		}
		return b.toString();
	}

}
