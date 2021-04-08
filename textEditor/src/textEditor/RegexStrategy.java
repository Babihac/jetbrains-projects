package textEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStrategy implements SearchStrategy {
	public List<String> search(String file, String find) {
		List<String> res = new ArrayList<>();
		Pattern p = Pattern.compile(find);
		Matcher m = p.matcher(file);
		while(m.find()) {
			System.out.println(m.group());
			res.add(m.group().length() + "	" + m.start() + "	" + m.end());
			//System.out.println(m.group());
		}
		return res;
	}
}
