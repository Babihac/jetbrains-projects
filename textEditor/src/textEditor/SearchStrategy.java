package textEditor;

import java.util.List;

public interface SearchStrategy {
	List<String> search(String file, String find);
}
