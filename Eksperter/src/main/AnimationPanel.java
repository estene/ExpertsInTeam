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
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPanel extends JPanel implements ActionListener, MouseMotionListener{

    /**
	 * Class that animates on the intersection - Prinsenkrysset. 
	 * @author Even
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	private Timer t;
	private Person person;
	private Bus bus;
	private boolean animate = false;
	private ClassLoader classLoader;
	
    public AnimationPanel() {   
    	this.setBounds(481, 11, 590, 425);
		classLoader = Thread.currentThread().getContextClassLoader();
		image = getImage("prinsenkryssetmedium.png");
    	person = new Person(285,85);
    	bus = new Bus(120,180);
		t = new Timer(25, this);
		t.start();
		addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g); // Clean up
        g.drawImage(image, 0, 0, null); 
        person.draw(g);
        bus.draw(g);
    }
    
    public void startAnimation() {
    	animate = true;
    }
	public void stopAnimation() {
		animate = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (animate == true) {
			if (person.getY() >= 300 ) {
				//stopAnimation();
			}
	        person.move();   
	        bus.move();
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
	
	// Retrieve image from resource
	public Image getImage(String imgName) {
		InputStream input = classLoader.getResourceAsStream("" + imgName);
		Image inputImage = null;
		try {
			inputImage = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputImage;
	}

}