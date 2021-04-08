package correcter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
	
	public static void main(String[] args) {
//		ErrorGenerator g = new ErrorGenerator();
//		String s = g.generateError("hee hoooou jooo", 3);
//		System.out.println(s);
		
		 File sampleFile = new File("sample.txt");
		  String content = "Streams are easy!";
		 
		  try (FileWriter writer =  new FileWriter(sampleFile)) {
		      writer.write(content);
		  } catch(IOException e) {
			  
		  }
	}

}
