package webCrawler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class WebCrawler extends JFrame {
	private JTextArea textArea;
	private JTextField url;
	private JButton submit;
	private JButton save;
	private JTextField file;
	private DownloadController dc;
	private ExportController ec;
	private JLabel titleLabel;
	private JTable table;
	
	
	public WebCrawler() {
		dc = new DownloadController();
		ec = new ExportController();
		initGUI();
		addControllers();
	}
	
	private void initGUI() {
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		JLabel export = new JLabel("Export");
		url  = new JTextField();
		submit = new JButton("Go");
		save = new JButton("Save");
		file = new JTextField();
		file.setColumns(10);
		url.setColumns(10);
		top.setLayout(new GridLayout(2,2));
		top.add(url);
		top.setSize(100,200);
		top.add(submit);
		bottom.add(export);
		bottom.add(file);
		bottom.add(save);
		titleLabel = new JLabel("insert title");
		top.add(titleLabel);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		JScrollPane spane = new JScrollPane(textArea);
		centerPanel.add(spane, BorderLayout.CENTER);
		textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    setTitle("JTextArea");
        setName("textArea");
        setSize(new Dimension(500, 300));
        setLayout(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	public void addControllers() {
		submit.addActionListener(e -> {
			
			new SwingWorker<String, String>() {

				@Override
				protected String doInBackground() throws Exception {
					// TODO Auto-generated method stub
					try {
						String page = dc.download(url.getText());
						//textArea.setText(page);
						//titleLabel.setText("Title: " + dc.parseTitle(page));
						return page;
					} catch(IOException exc) {
						exc.printStackTrace();
					}
					return "";
				}
				
				@Override
				public void done() {
					try {
						textArea.setText(get());
						titleLabel.setText("Title: " + dc.parseTitle(get()));
					} catch(Exception exc) {
						exc.printStackTrace();
					}
				}
			}.execute();;
			System.out.println(666);
		});
		
		save.addActionListener(e -> {
			ec.saveLink(file.getText(), textArea.getText());
		});
	}

}
