package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	private HashMap<Character, Character> replaceForXml = new HashMap<>();
	public Parser() {
		replaceForXml.put('@', ' ');
		replaceForXml.put(':', '=');
		replaceForXml.put('#', ' ');
		replaceForXml.put('"', ' ');
		//replaceForXml.put('{', '<');
		//replaceForXml.put('}', '>');
		replaceForXml.put(',', ' ');
		
	}
	public String toJson(String xml) {
		StringBuilder b = new StringBuilder();
		b.append("{");
		if (xml.matches(".*>[\\s\\w]+.*")) { 
			xml.replaceAll("\\s+", "");
			String val = "\"" + xml.substring(xml.indexOf('>')+1, xml.lastIndexOf('<')) + "\"";
			System.out.println(val);
			xml = xml.substring(0,xml.indexOf(">")+1);
			xml = xml.replaceAll("[<>]", "\"");
			b.append(xml);
			b.append(":").append(val);
		} else {
			xml.replaceAll("\\s+", "");
			xml = xml.replaceAll("[<>]", "\"");
			xml = xml.replace("/", "") + ":" + "null";
			b.append(xml);
		}
		//b.append(xml);
		b.append("}");
		System.out.println(b);
		return "";
	}
	
	public String toJson(String xml, ArrayList<String> attributes) {
		StringBuilder b = new StringBuilder();
		b.append("{");
		if (xml.matches(".*>[\\s\\w]+.*")) { 
			String val = "\"" + xml.substring(xml.indexOf('>')+1, xml.lastIndexOf('<')) + "\"";
			xml.replaceAll("\\s+", "");
			xml = xml.substring(0,xml.indexOf(">")+1);
			xml = xml.replaceAll("[<>]", "\"");
			StringBuilder name = new StringBuilder();
			name.append(xml);
			name.insert(1, "#");
			b.append(xml).append(":{");
			for(String s: attributes) {
				b.append(s).append(",");
			}
			b.append(name);
			b.append(":").append(val);
			b.append("}");
		} else {
			xml = xml.replaceAll("\\s+", "");
			xml = xml.replaceAll("[<>]", "\"");
			xml = xml.replace("/", "") + ":" + "null";
			StringBuilder name = new StringBuilder();
			name.append(xml);
			name.insert(1, "#");
			b.append(xml.split(":")[0].replace(" ", "")).append(":{");
			for(String s: attributes) {
				b.append(s).append(",");
			}
			b.append(name);
			b.append("}");
		}
		b.append("}");
		System.out.println(b);
		return "";
	}
	
	public void parseAttributeToJson(String s) {
		Pattern p = Pattern.compile("\\s*[a-z]+\\s*=\\s*\"\\w*\"");
		Matcher m = p.matcher(s);
		ArrayList<String> list = new ArrayList<>();
		StringBuilder b = new StringBuilder();
		while(m.find()) {
			s = s.replace(m.group(), "");
			b.append(m.group().replace("=", ":").replaceAll("\\s+", ""));
			b.insert(0, "\"@");
			b.insert(b.indexOf(":"), "\"");
			list.add(b.toString());
			b.delete(0, b.length());
			
		}
		//s = s.replaceAll("\\s+", "");
		toJson(s, list);
	}
	
	public String replaceInGroupForXml(String s, HashMap<Character, Character> map) {
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			if(replaceForXml.get(s.charAt(i)) != null) {
				b.append(replaceForXml.get(s.charAt(i)));
			} else b.append(s.charAt(i));
		}
		b.delete(0,1);
		b.deleteCharAt(b.indexOf("=")-1);
		return b.toString();
	}
	
	public String parseJsonToAttributeXml(String s) {
		//s = s.replaceAll("\\s+", "");
		ArrayList<String> list = new ArrayList<>();
		StringBuilder b = new StringBuilder();
		Pattern p = Pattern.compile("\"@\\w+\"\\s*:\\s*\"?[\\w\\s]+\"?");
		Matcher m = p.matcher(s);
		while(m.find()) {
			//System.out.println(m.group());
			list.add(replaceInGroupForXml(m.group(), replaceForXml));
			s = s.replace(m.group(), "");
		}
		s = replaceInGroupForXml(s, replaceForXml);
		//System.out.println(s);
		s = s.substring(s.indexOf("{"),s.indexOf("}")+1 );
		toXml(s, list);
		return b.toString();
	}
	
	public void toXml(String json) {
		StringBuilder b = new StringBuilder();
		b.append("<");
		json = json.replaceAll("[\\{\\}]", "");
		String[] arr = json.split(":");
		json = json.replaceAll("\\s+", "");
		json = json.replaceAll("\"", "");
		//System.out.println(arr[1]);
		if (arr[1].equals("null")) {
			b.append(arr[0]);
			b.append("/>");
		} else {
			b.append(arr[0]).append(">");
			StringBuilder end = new StringBuilder(b).insert(1, "/");
			b.append(arr[1]).append(end);
			
		}
	    //System.out.println(b);
		//return b.toString();
	}
	
	public String toXml(String json, ArrayList<String> attr) {
		StringBuilder b = new StringBuilder();
		b.append("<");
		json = json.replaceAll("[\\{\\}]", "");
		String[] arr = json.split(":|=");
		json = json.replaceAll("\\s+", "");
		json = json.replaceAll("\"", "");
		//System.out.println(arr[1]);
		if (arr[1].trim().equals("null")) {
			b.append(arr[0].replaceAll("\\s+", ""));
			for(String s : attr) {
				String[] tmp = s.split("=");
				b.append(tmp[0]).append("=").append("\"").append(tmp[1].trim()).append("\"");
			}
			b.append("/>");
		} else {
			json = json.replaceAll("\\s+", "");
			b.append(arr[0].replaceAll("\\s+", ""));
			StringBuilder end = new StringBuilder(b).insert(1, "/");
			for(String s : attr) {
				String[] tmp = s.split("=");
				b.append(tmp[0]).append("=").append("\"").append(tmp[1].trim()).append("\"");
			}
			b.append(">");
			b.append(arr[1]).append(end).append(">");
			
		}
		System.out.println(b);
		return b.toString();
	}

}
