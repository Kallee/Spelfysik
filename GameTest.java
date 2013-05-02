package GraphicTest;

import java.awt.Dimension;
import java.awt.Image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import GraphicDeom.Controller;
import GraphicDeom.GameCanvas;

public class GameTest extends Canvas implements Runnable {

	private Image background;
	private Image heroImage;
	
	public boolean occupiedCoordinates[][] = new boolean[WINDOW_WIDTH][WINDOW_HEIGHT];
	
	//Konstanter
	private static final int WINDOW_WIDTH = 800; 
	private static final int WINDOW_HEIGHT = 600;
	
	private Controller controller;
	private Hero hero;
	private Block block;
	
	private boolean running = false;
	
	public GameTest(){
		setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		setPreferredSize(getMinimumSize());
		setMaximumSize(getMinimumSize());
		
		try {
			heroImage = ImageIO.read(getClass().getResource("barbapapa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		


		heroImage.getScaledInstance(10, 20, Image.SCALE_DEFAULT);
		
		controller = new Controller(this);
		block = new Block(200, 400, 200, 200);
		for (int i = 0; i < WINDOW_WIDTH; i++) {
			for (int j = 0; j < WINDOW_HEIGHT; j++) {
				if(i >= 200 && i <= 400 && j >= 400 && j < 600){
					occupiedCoordinates[i][j] = true;
				}
			}
		}
		hero = new Hero(0, 0, 0, 0, heroImage);
		
	}
	
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_WIDTH);
		
		block.render(g);
		hero.render(g);
		
		bs.show();
	}
	
	private void update(float delta){
		
		hero.update(controller, delta, occupiedCoordinates);
		
	}
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Mitt coola demo");
		GameTest gt = new GameTest();
		window.add(gt);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gt.start();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void start() {
		if(! running) {
			createBufferStrategy(2);
			Thread t = new Thread(this);
			running = true;
			t.start();
		}
	}

	@Override
	public void run() {
		long delta = 0;
		
		while(running) {
			long now = System.currentTimeMillis();
			update(delta);
			render();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			delta = System.currentTimeMillis() - now;
		}
	}
}