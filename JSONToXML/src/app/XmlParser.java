package app;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser {
	private HashMap<String, String> attributes;
	public XmlParser() {
		attributes = new HashMap<>();
	}
	
	public String getPath(String s) {
		Pattern p = Pattern.compile("\\s*<\\s*\\w+");
		Matcher m = p.matcher(s);
		m.find();
		return m.group().substring(1);
	}
	
	public String parse(String s, String path) {
		s = s.replaceAll("\\s+", " ");
		Pattern p = Pattern.compile("\\s*<\\s*\\w+");
		Pattern noVal = Pattern.compile("<.*\\/>.?"); 
		Pattern attr = Pattern.compile("\\w+\\s*=\\s*\"[\\w\\s]+\"");
		Pattern val = Pattern.compile(">.*");
		Matcher m = p.matcher(s);
		m.find();
		System.out.println("Element:");
		System.out.println("Path: " + path);
		m = attr.matcher(s);
		if(m.find()) {
			System.out.println("Attributes:");
			String[] arr = m.group().split("=");
			System.out.println(arr[0] + " = " + arr[1]);
			while(m.find()) {
				arr = m.group().split("=");
				String res = arr[0] + " = " + arr[1];
				res = res.replaceAll("\\s+", " ");
				System.out.println(res);
			}
		}
		m = noVal.matcher(s);
		if(m.find()) {
			System.out.print("Value = ");
			System.out.println("Null");
		} else if((m = val.matcher(s)).find()) {
			System.out.print("Value = ");
			String res = m.group().substring(1);
			System.out.println(res.length() > 0?  res : "\"\"");
		}
		return s;
	}
	
	public String parseDocument(String s) {
		s  =s.trim();
		s = s.replaceAll("<\\/", "\n<\\/");
		s = s.replaceAll(">\\s*<", ">\n<");
		//System.out.println(s);
		return s;
	}
	public String getAttributes(String s) {
		Pattern p = Pattern.compile("\\s*[a-z]+\\s*=\\s*\"\\w*\"");
		Matcher m = p.matcher(s);
		while(m.find()) {
			//System.out.println(m.group());
			s = s.replace(m.group(), "");
			System.out.println("Attributes: " + m.group());
		}
		return s;
	}
	
	public String getValue(String s) {
		
		Pattern p = Pattern.compile(".*>[\\s\\w]+.*");
		Matcher m = p.matcher(s);
		while(m.find()) {
			//System.out.println(m.group());
			s = s.replace(m.group(), "");
			System.out.println("Attributes: " + m.group());
		}
		return s;
		
	}
	
	
	public void printPath(String s) {
		Pattern p = Pattern.compile(".?<[\\w\\s=\"]+>|.?<[\\w\\s=\"]+\\/>");
		Matcher m = p.matcher(s);
		while(m.find()) {
			String tmp = getAttributes(m.group());
			System.out.println("Element: " + tmp);
			//String tmp = getAttributes(m.group());
			//System.out.println("Element:\n" + tmp);
		}
		
	}

}
