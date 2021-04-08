package textEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicSearch implements SearchStrategy {
	public List<String> search(String file, String find) {
		List<String> res = new ArrayList<>();
		Pattern p = Pattern.compile(find);
		Matcher m = p.matcher(file);
		int index =  file.indexOf(find);
		while(index > 0) {
			System.out.println(index);
			res.add(find.length() + "	" + index + "	" + index);
			index = file.indexOf(find, index+1);
			//res.add(find.length() + "	" + index + "	" + index);
		}
//		while(m.find()) {
//			res.add(m.group() + "	" + m.start() + "	" + m.end());
//			System.out.println(m.group());
//		}
		return res;
	}
}
