package GraphicTest;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block {
	
	private int x, y, width, height;
	

	public Block(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	
	}
	
		
	public void render(Graphics2D g){
		
		g.drawRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		
	}
		
}
