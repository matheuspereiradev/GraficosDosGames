package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Jogo extends Canvas implements Runnable {

	private Thread thread;
	private boolean isRunning;
	public static JFrame frame;
	private final int WIDITH = 240, HEIGHT = 160, SCALE = 2;
	private BufferedImage background;
	
	private Spritesheet sheet;
	private BufferedImage jogador;
	
	private int x=0;

	public Jogo() {
		sheet=new Spritesheet("/Spritesheet.png");
		jogador=sheet.getSprite(0, 0, 16, 16);
		setPreferredSize(new Dimension(WIDITH * SCALE, HEIGHT * SCALE));//tamanho da janela
		iniciarFrame();
		background=new BufferedImage(WIDITH, HEIGHT, BufferedImage.TYPE_INT_RGB);//imagem do fundo
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
		x++;
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
		
		g.setColor(Color.CYAN);
		g.fillRect(20, 20,80, 80);
		
		g.setColor(Color.GREEN);
		g.fillOval(35, 25, 40, 40);
		
		g.setFont(new Font("Arial", Font.BOLD,20 ));
		g.setColor(Color.BLACK);
		g.drawString("Hello word", 40, 50);
		
		g.drawImage(jogador, x, 25, null);
		
		
		g.dispose();//limparr dados da imagem que nao foram usados
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
		parar();

	}

	public synchronized void iniciar() {
		thread = new Thread(this);
		thread.start();//chama o run da thread
		isRunning = true;

	}

	public synchronized void parar() {
		isRunning =false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
