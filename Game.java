

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game implements Runnable {

	private Display display;
	
	private int width;
	private int height;
	private final int TILE_SIZE = 64;
	private String title;
	
	private float currentTime;
	
	private Thread thread;
	
	private BufferedImage testImage;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private boolean running;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.currentTime = 0;
	}
	
	private void init() {
		display = new Display(title, width, height);
		//broken
		testImage = ImageLoader.loadImage("test.png");
	}
	
	private void update() {
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw a boxed grid
		for (int i = 0; i < width/TILE_SIZE; i++) {
			for (int j = 0; j < height/TILE_SIZE; j++) {
				g.drawRect(TILE_SIZE * i, TILE_SIZE * j, TILE_SIZE - 1, TILE_SIZE - 1);
		}
		}
		//Set colour
		g.setColor(new Color(100, 100, 100));
		//Draw a circle in the centre of a box
		g.fillOval((width + TILE_SIZE/2)/2, (height + TILE_SIZE/2)/2, TILE_SIZE/2, TILE_SIZE/2);
		//Show all drawn shapes
		g.drawImage(testImage, 20, 20, null);
		bs.show();
		//Close graphics object correctly
		g.dispose();
	}
	
	public void run() {
		init();
		
		while(running) {
			update();
			render();
			currentTime += 0.1;
			try {
				Thread.sleep((long) 0.1);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		stop();
	}
	
	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
