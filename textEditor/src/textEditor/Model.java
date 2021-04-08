package textEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

public class Model {
	private boolean isForward;
	private boolean isBackward;
	private Deque<String> searchHistory;
	private List<String> searchRes;
	private StringBuilder content = new StringBuilder("");
	private ListIterator<String> search;
	public Model() {
		isBackward = false;
		isForward = false;
		searchHistory = new ArrayDeque<>();
		searchRes = new ArrayList<>();
	}
	
	public boolean isBackward() {
		return isBackward;
	}
	
	public boolean isForward() {
		return isForward;
	}
	
	public void setBackward(boolean isBackward) {
		this.isBackward = isBackward;
	}
	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}
	
	public void setContent(String content) {
		//this.content.setLength(0);
		//this.content.append(content);
		File file = new File(content);
		try(Scanner sc = new Scanner(file)) {
			while(sc.hasNext()) {
				this.content.append(sc.nextLine() + "\n");
			}
		} catch(FileNotFoundException e) {
			System.out.println("file not find");
		}
	}
	
	public void setContent(File file) {
		this.content.setLength(0);
		this.content.append(content);
		
		try(Scanner sc = new Scanner(file)) {
			while(sc.hasNext()) {
				this.content.append(sc.nextLine() + "\n");
			}
		} catch(FileNotFoundException e) {
			System.out.println("file not find");
		}
	}
	
	public StringBuilder getContent() {
		return content;
	}
	public List<String> getSearchRes() {
		return searchRes;
	}
	public void setSearchRes(List<String> searchRes) {
		this.searchRes = searchRes;
	}
	
	public Deque<String> getSearchHistory() {
		return searchHistory;
	}
	
	public void setSearch(ListIterator<String> search) {
		isForward = false;
		isBackward = false;
		this.search = search;
	}
	public ListIterator<String> getSearch() {
		return search;
	}
	
	
}
