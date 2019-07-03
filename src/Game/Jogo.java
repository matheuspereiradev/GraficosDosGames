package Game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Jogo extends Canvas implements Runnable {

	public static JFrame frame;
	private final int WIDITH = 160, HEIGHT = 120, SCALE = 3;

	public Jogo() {
		setPreferredSize(new Dimension(WIDITH * SCALE, HEIGHT * SCALE));
		frame = new JFrame("Projeto Jogo");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
	}

	@Override
	public void run() {

	}

}
