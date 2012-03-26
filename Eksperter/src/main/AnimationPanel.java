package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPanel extends JPanel implements ActionListener, MouseMotionListener{

    /**
	 * Class that animates on the intersection - Prinsenkrysset. 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image;
	private Image image2;
	private Timer t;
	private Person person;
	private boolean animate = false;
	
    public AnimationPanel() {                
    	image = new ImageIcon(this.getClass().getResource("/prinsenkryssetmedium.png"));
    	image2 = image.getImage();
    	person = new Person();
		t = new Timer(50, this);
		t.start();
		addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g); // Clean up
        g.drawImage(image2, 0, 0, null); 
        person.draw(g);
    }
    public void startAnimation() {
    	animate = true;
    }
	public void stopAnimation() {
		animate = false;
	}
	public void resetAnimation() {
		animate = false;
		person.reset();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (animate == true) {
			if (person.getY() >= 300 ) {
				stopAnimation();
			}
	        person.move();   
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("Location: " + e.getX()+ " "+ e.getY());
	}

}