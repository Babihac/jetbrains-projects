package textEditor;

import java.util.List;

public class SearchController {
	private SearchStrategy strategy;
	public SearchController(SearchStrategy strategy) {
		this.strategy = strategy;
	}
	
	public List<String> search(String file, String find) {
		return strategy.search(file, find);
	}

}
