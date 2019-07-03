package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.GrayFilter;
import javax.swing.JFrame;

public class Jogo extends Canvas implements Runnable {

	private Thread thread;
	private boolean isRunning;
	public static JFrame frame;
	private final int WIDITH = 160, HEIGHT = 120, SCALE = 4;
	private BufferedImage background;

	public Jogo() {
		setPreferredSize(new Dimension(WIDITH * SCALE, HEIGHT * SCALE));
		iniciarFrame();
		background=new BufferedImage(WIDITH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		jogo.iniciar();
	}

	public void iniciarFrame() {
		frame = new JFrame("Projeto Jogo");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void atualizar() {
		// System.out.println("Tick");
	}

	public void renderizar() {
		BufferStrategy bs= this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=background.getGraphics();
		g.setColor(new Color(255,40,40));
		g.fillRect(0, 0, WIDITH ,HEIGHT);
		g=bs.getDrawGraphics();
		g.drawImage(background, 0, 0, WIDITH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();// ultima vez que foi executada a atualiação
		double amountOfTicks = 60.0;// quantidade de atualizações por segundo
		double ns = 1000000000 / amountOfTicks;// "constante" do momento certo do update do jogo para ficar na
												// quantidade de fps descritas no amountOfTicks
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();// tempo atual
			delta += (now - lastTime) / ns;
			lastTime = now;// substitui a ultima execução do while pelo tempo atual

			if (delta >= 1) {
				atualizar();
				renderizar();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS=" + frames);
				frames = 0;
				timer = System.currentTimeMillis();// atualiza o tempo para o tempo atual
				// ou timer+=1000; para dizer que se passaram 1000 milesegundos desde o valor
				// inicial do timer

			}

		}

	}

	public synchronized void iniciar() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;

	}

	public synchronized void parar() {

	}

}
