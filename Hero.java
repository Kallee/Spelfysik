package GraphicTest;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import GraphicDeom.Controller;

public class Hero {

	private Image image;
	private float x, y; 
	private double speedY, speedX;
	private double gravity = 30;
	
	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public Hero(float x, float y, double speedX, double speedY, Image image) {
		this.x = x;
		this.y = y;
		this.image = image;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void render(Graphics2D g) {	
		g.translate(x, y);
		
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		
		height = height/2;
		width = width/2;
		
		if(gravity < 0){
			g.rotate(Math.PI);
			g.drawImage(image, -width, -height, width, height, null);
		}
				
		else{
				
			g.drawImage(image, 0, 0, width, height, null);
		}
		g.translate(-x, -y);
	}
	
	public void update(Controller controller, double delta, boolean occupiedCoordinates[][]) {
		
		delta = delta/1000;
		setSpeedX(0);
		setSpeedY(getSpeedY()+gravity*delta);
		
		if(checkOccupied(occupiedCoordinates)){
			
		}
		
		if(y > 538){
			speedY = 0;
			y = 538;
		}
		
		if(y < 0){
			speedY = 0;
			y = 0;
		}
		
		if(x < 0){
			x = 0;
		}
		
		if(x > 755){
			x = 755;
		}
		
		
		rightButton(controller, delta);
		
		leftButton(controller, delta);
		
		downButton(controller, delta);
		
		upButton(controller, delta);
		
		spaceButton(controller, delta);
		
		x += getSpeedX();
		y += getSpeedY();
		
		//Skriver ut SpeedY och delta
		if(speedY != 0){
			
			System.out.println("Speed Y: " +speedY);
			System.out.println("Delta: " +delta);
			System.out.println("Y: " +y);
		}
	}

	private boolean checkOccupied(boolean[][] occupiedCoordinates) {
		
		if(occupiedCoordinates[(int)x][(int)y]){
			return true;
		}
		
		return false;
	}

	private void spaceButton(Controller controller, double delta) {
		
		if(controller.characters[KeyEvent.VK_SPACE] && !controller.hoppa){
			gravity = gravity*-1;
			controller.hoppa = false;
		}
		
	}

	private void upButton(Controller controller, double delta) {
		
		if(y >=538 && controller.characters[KeyEvent.VK_UP]){
			setSpeedY(-10);	
		}		
	}

	private void downButton(Controller controller, double delta) {
		
		if(controller.characters[KeyEvent.VK_DOWN]){
			
			if(getSpeedY() == 0){
				setSpeedY(10);
			}
		}
	}

	private void leftButton(Controller controller, double delta) {
		
		if(controller.characters[KeyEvent.VK_LEFT]){
			setSpeedX(-3);
		}
	}

	private void rightButton(Controller controller, double delta) {
		if(controller.characters[KeyEvent.VK_RIGHT]){
			setSpeedX(3);
		}
	}
}