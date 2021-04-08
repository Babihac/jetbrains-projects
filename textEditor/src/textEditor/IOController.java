package textEditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IOController {
	Model model;
	public IOController(Model model) {
		this.model = model;
	}
	public void save(String filename, String content) {
		File file = new File(filename);
		try(FileWriter writter = new FileWriter(file)) {
			writter.write(content);
			System.out.println("braa");
		} catch(IOException e) {
			System.out.println("jejeje");
			
		}
	}
	
	public void load(String filename) {
		model.setContent(filename);
	}
	
	public void load(File file) {
		model.setContent(file);
	}
}
