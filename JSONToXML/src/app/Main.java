package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		Parser p = new Parser();
		XmlParser parser = new XmlParser();
		File file = new File("test.txt");
		try(Scanner sc = new Scanner(file)) {
			String  s = "";
			while(sc.hasNext()) {
				s += sc.nextLine();
			}
			//s.replace("\n", "").trim();
			//System.out.println(s);
		//if(s.startsWith("<"))p.parseAttributeToJson(s);
		//else p.parseJsonToAttributeXml(s);
			//System.out.println(s);
			//s = parser.getAttributes(s);
			//s =  parser.parse(s);
			//s = s.replaceAll("<\\s+>", "");
			//System.out.println(s);
			s =  parser.parseDocument(s);
			String[] arr = s.split("\n");
			Pattern pat = Pattern.compile("<\\/\\w*");
			Matcher mat;
			Deque<String> stack = new ArrayDeque<>();
			for(String row : arr ) {
				//System.out.println(row);
				mat = pat.matcher(row);
				if(!mat.find()) { 
					stack.push(parser.getPath(row));
					StringBuilder b = new StringBuilder();
					Iterator<String> i = stack.descendingIterator();
					while(i.hasNext()) {
						b.append(i.next()).append(" ");
					}
					parser.parse(row, b.toString());
					System.out.println();
					if(row.contains("/>")) stack.pop();
				}
				else stack.pop();
				
			}
			//System.out.println(44);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
//		Scanner sc = new Scanner(System.in);
//		//String in = sc.nextLine();
//		String in = "<jdk>1.8.9</jdk>";
//		in.trim();
//		String s = in.startsWith("<")? p.toJson( in) : p.toXml(in);
//		System.out.println(s);

	}

}
