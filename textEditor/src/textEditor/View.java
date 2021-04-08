package textEditor;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View extends JPanel {
	private JTextArea textArea;
	public View() {
		setSize(270,270);
		textArea = new JTextArea();
		var spane = new JScrollPane(textArea);
		spane.setSize(100,200);
		textArea.setSize(100, 200);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		add(spane);
	}
	

}
 