package correcter;

public class Decoder {
	public String decode(String enc) {
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < enc.length(); i+=3) {
			b.append(getDuplicate(enc.substring(i, i+3)));
		}
		return b.toString();
	}
	
	
	public String  getDuplicate(String s) {
		char c = s.charAt(0);
		for(int i = 1; i < s.length(); i++) {
			if(s.charAt(i) == c) break;
			c = s.charAt(i);
		}
		return String.valueOf(c);
	}

}
