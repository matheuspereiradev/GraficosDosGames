package Game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Jogo extends Canvas implements Runnable {

	private Thread thread;
	private boolean isRunning;
	public static JFrame frame;
	private final int WIDITH = 160, HEIGHT = 120, SCALE = 3;

	public Jogo() {
		setPreferredSize(new Dimension(WIDITH * SCALE, HEIGHT * SCALE));
		iniciarFrame();
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
		//System.out.println("Tick");
	}

	public void renderizar() {
		//System.out.println("Render");
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();//ultima vez que foi executada a atualiação
		double amountOfTicks = 60.0;//quantidade de atualizações por segundo
		double ns = 1000000000 / amountOfTicks;//"constante" do momento certo do update do jogo para ficar na quantidade de fps descritas no amountOfTicks
		double delta = 0;
		int frames = 0;
		double timer=System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();//tempo atual
			delta += (now - lastTime) / ns;
			lastTime=now;//substitui a ultima execução do while pelo tempo atual
			
			if (delta >= 1) {
				atualizar();
				renderizar();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis()-timer>=1000) {
				System.out.println("FPS="+frames);
				frames=0;
				timer=System.currentTimeMillis();//atualiza o tempo para o tempo atual
				//ou timer+=1000; para dizer que se passaram 1000 milesegundos desde o valor inicial do timer
				
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
