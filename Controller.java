package GraphicTest;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements KeyListener, MouseListener, MouseMotionListener{

	public float mouseX, mouseY;
	public boolean[] characters = new boolean[255];
	public boolean hoppa = true;
	private boolean pressed = false;
	public Controller(Component parent) {
		// Sätt kontrollern till lyssnare på en komponent
		parent.addMouseListener(this);
		parent.addMouseMotionListener(this);
		parent.addKeyListener(this);
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	/*
	 * Lyssnar på musens position och sparar undan
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	/*
	 * Metoder sätter en flagga till sann eller falsk beroende pÃ¥
	 * tangenters status.
	 */
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(!pressed){
			characters[arg0.getKeyCode()] = true;
			pressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		characters[arg0.getKeyCode()] = false;
		hoppa = true;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		characters[arg0.getKeyCode()] = false;
	}
}