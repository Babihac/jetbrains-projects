package textEditor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileSystemView;


public class TextEditor extends JFrame {
	private IOController ioController;
	private SearchController searchController;
	private Model model;
	private JButton prev;
	private JButton next;
	private JButton search;
	private JTextField file;
	private JTextArea textArea;
	private JMenuItem exitMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem loadMenuItem;
	private JCheckBox regex;
    public TextEditor() {
    	model = new Model();
    	ioController = new IOController(model);
        initUI();
        addControllers();
    }
 
    private void initUI() {
    	JMenuBar menuBar = new JMenuBar();
    	setJMenuBar(menuBar);
    	JMenu fileMenu = new JMenu("File");
    	fileMenu.setMnemonic(java.awt.event.KeyEvent.VK_F);
    	menuBar.add(fileMenu);
    	
    	saveMenuItem = new JMenuItem("Save");
    	loadMenuItem = new JMenuItem("Load");
    	exitMenuItem = new JMenuItem("Exit");
    	 
    	fileMenu.add(saveMenuItem);
    	fileMenu.add(loadMenuItem);
    	fileMenu.addSeparator();
    	fileMenu.add(exitMenuItem);
 
    	JPanel top = new JPanel();
        textArea = new JTextArea();
        file = new JTextField("");
        file.setColumns(10);
        file.setBounds(60, 60, 177, 40);
        file.setMinimumSize(new Dimension(100,30));
        JScrollPane spane = new JScrollPane(textArea);
        prev = new JButton("Prev");
        prev.setSize(20, 20);
        next = new JButton("Next");
        prev.setSize(20, 20);
        next.setSize(20, 20);
        prev.setEnabled(false);
        next.setEnabled(false);
        search = new JButton("Search");
        search.setSize(20, 20);
        regex = new JCheckBox("Use regex");
        top.add(file);
        top.add(search);
        top.add(prev);
        top.add(next);
        top.add(regex);
 
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
 
        //createLayout(spane);
 
        setTitle("JTextArea");
        setName("textArea");
        setSize(new Dimension(500, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(spane, BorderLayout.CENTER);
    }
    public Iterator<Long> ret() {
    	int i = 0;
    	return new Iterator<Long>() {

    		private long index = i;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index < 5;
			}

			@Override
			public Long next() {
				// TODO Auto-generated method stub
				long l= index;
				index++;
				return l;
			}
		};
    }
    private void addControllers() {
     	prev.addActionListener(e -> {
     		System.out.println(model.isBackward());
     		if(model.getSearch().hasPrevious()) {
     			next.setEnabled(true);
     			if(!model.isBackward()) model.getSearch().previous();
     			String s =  model.getSearch().previous();
     			model.setBackward(true);
     			model.setForward(false);
    			//String s = model.getSearchHistory().pop();
    			String[] arr = s.split("	");
    			textArea.setCaretPosition(Integer.parseInt(arr[1]) + Integer.parseInt(arr[0]));
	        	textArea.select(Integer.parseInt(arr[1]), Integer.parseInt(arr[1]) + Integer.parseInt(arr[0]));
	        	textArea.grabFocus();
	        	if(!model.getSearch().hasPrevious()) prev.setEnabled(false);
    				
    		}
    	});
     	
     	saveMenuItem.addActionListener(e -> {
     		ioController.save(file.getText(), textArea.getText());
    		System.out.println("DONE");
     	});
    	
    	next.addActionListener(e -> {
    		if(model.getSearch().hasNext()) {
    			prev.setEnabled(true);
    			if(!model.isForward()) model.getSearch().next();
     			model.setBackward(false);
     			model.setForward(true);
    			String s = model.getSearch().next();
    			model.getSearchHistory().push(s);
    			String[] arr = s.split("	");
    			textArea.setCaretPosition(Integer.parseInt(arr[1]) + Integer.parseInt(arr[0]));
	        	textArea.select(Integer.parseInt(arr[1]), Integer.parseInt(arr[1]) + Integer.parseInt(arr[0]));
	        	textArea.grabFocus();
	        	if(!model.getSearch().hasNext()) next.setEnabled(false);
    		}
    	});
    	
    	search.addActionListener(e -> {
    		System.out.println(regex.isSelected());
    		new SwingWorker<List<String>, String>() {

				@Override
				protected List<String> doInBackground() throws Exception {
					// TODO Auto-generated method stub
					if(regex.isSelected()) searchController = new SearchController(new RegexStrategy());
					else searchController = new SearchController(new BasicSearch());
					return searchController.search(textArea.getText(), file.getText());
				}
				
			    @Override
			    protected void done() {
			        try {
			        	//prev.setEnabled(true);
			        	if(get().size() > 1) next.setEnabled(true);
			        	else next.setEnabled(false);
			        	model.setSearchRes(get());
			        	model.setSearch(get().listIterator());
			        	if(model.getSearch().hasNext()) {
				        	String[] arr = model.getSearch().next().split("	");
				        	model.setForward(true);
				        	model.getSearchHistory().push(get().get(0));
				        	textArea.setCaretPosition(Integer.parseInt(arr[1]) + Integer.parseInt(arr[0]));
				        	textArea.select(Integer.parseInt(arr[1]), Integer.parseInt(arr[1]) + Integer.parseInt(arr[0]));
				        	textArea.grabFocus();
			        	}
//			            for(String s : get()) {
//			            	System.out.println(s);
//			            }
			        } catch (Exception ignoreForNow) {
			        }
			    }
			}.execute();
    	});
    	
    	loadMenuItem.addActionListener(e -> {
    		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    		int returnValue = jfc.showOpenDialog(this);
    		if (returnValue == JFileChooser.APPROVE_OPTION) {
    			File selectedFile = jfc.getSelectedFile();
    			System.out.println(selectedFile.getAbsolutePath());
    			ioController.load(selectedFile);
        		textArea.setText(model.getContent().toString());
    		}
    	});
    	
    	exitMenuItem.addActionListener(event -> System.exit(0));
    	
    }
 
    private void createLayout(JComponent... arg) {
 
        java.awt.Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
 
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
 
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
 
        );
 
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
 
        pack();
    }
}
