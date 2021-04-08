package gameOfLife;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grid extends JPanel {
	javax.swing.Timer timer;
	private int dim;
	private State state;
	private String[][] life;
	private JLabel label;
	Random r = new Random();
	GameUtility utility;
	public Grid(int dim) {
		label = new JLabel();
		life = new String[dim/10][dim/10];
		for(int i = 0; i < dim/10; i++) {
			for (int j = 0; j < dim/10; j++) {
				if (r.nextBoolean()) {
					life[i][j] = "O";
				} else {
					life[i][j] = " ";
				}
			}
		}
		state = new State(life);
		this.dim = dim;
		utility = new GameUtility();
		///setBounds(100, 100, 200, 200);
		setVisible(true);
		setSize(200, 200);
		label.setText("Generation: " + state.getAlive() );
		label.setBounds(300, 0, 100, 30);
		add(label);
		
	}
	public void braa() {
		repaint();
	}
	
	public void refreshScreen() {
		timer = new javax.swing.Timer(0, e -> {
			repaint();
			label.setText("Generation: " + state.getAlive());
			});
		timer.setRepeats(true);
		timer.setDelay(100);
		timer.start();
	}
	
	public State getState() {
		return state;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		state.setCurrentGen(utility.generateNewGeneration(state.getCurrentGen(), dim/10));
		for (int x = 10; x <= dim; x+=10) {
			for (int y = 10; y <= dim; y+=10) {
				if(state.getCurrentGen()[x/10-1][y/10-1].equals("O")) {
					g.setColor(java.awt.Color.gray);
					g.fillRect(x, y, 10, 10);
				}
				else { 
					g.setColor(java.awt.Color.white);
					g.drawRect(x, y, 10, 10);
				}
			}
		}
	}
}
