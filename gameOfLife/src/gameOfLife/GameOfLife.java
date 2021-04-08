package gameOfLife;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOfLife extends JFrame {
    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
        setLayout(new BorderLayout());
        //JLabel nameLabel = new JLabel();
        Grid grid = new Grid(200);
        //nameLabel.setText("Generation:" + grid.getState().getAlive());
        //nameLabel.setBounds(40, 20, 100, 30);
        //add(nameLabel, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        JButton button = new JButton("New Gen");
        button.setSize(50, 30);
        button.addActionListener(e -> {
        	System.out.println(666);
        	//grid.repaint();
        	//grid.removeAll();
        	//grid.revalidate();
        	//grid.repaint();
        	grid.refreshScreen();
        });
        
        grid.add(button, BorderLayout.EAST);
    
    }
    
    public static void main(String[] args) {
		new GameOfLife();
	}

}
