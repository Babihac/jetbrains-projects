package correcter;

import java.util.Random;

public class ErrorGenerator {
	
	public String generateError(String s, int jump) {
		StringBuilder b = new StringBuilder(s);
		for(int i = jump; i < s.length(); i += jump) {
			Random r = new Random();
			char c = (char)(r.nextInt(50) + 'a');
			while(c == b.charAt(i)) {
				c = (char)(r.nextInt(50) + 'a');
			}
			b.replace(i,i+1 , String.valueOf(c));
		}
		return b.toString();
	}

}
