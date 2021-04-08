package webCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportController {
	
	public void saveLink(String filename, String links) {
		File file = new File(filename);
		try(FileWriter writer = new FileWriter(file)) {
			writer.write(links);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
