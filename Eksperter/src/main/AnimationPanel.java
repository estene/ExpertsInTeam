package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
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
	private boolean animate = false;
	private ClassLoader classLoader;
	private TrafficLight neLight, nwLight, seLight, swLight;
	private Scenario scen;
	
    public AnimationPanel() {   
    	this.setBounds(481, 11, 590, 425);
		classLoader = Thread.currentThread().getContextClassLoader();
		image = getImage("prinsenkryssetmedium.png");
    	
		scen = new Scenario("scen1", this);
		
    	neLight = new TrafficLight(Placement.NORTHEAST);
    	nwLight = new TrafficLight(Placement.NORTHWEST);
    	seLight = new TrafficLight(Placement.SOUTHEAST);
    	swLight = new TrafficLight(Placement.SOUTHWEST);
    	//seLight.changeColour(Direction.FROMSOUTHTONORTH, LightColour.RED);
		t = new Timer(25, this);
		t.start();
		addMouseMotionListener(this);
    }
    
    public ArrayList<TrafficLight> getTrafficLights(){
    	return new ArrayList<TrafficLight>(Arrays.asList(neLight, nwLight, seLight, swLight));
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g); // Clean up
        g.drawImage(image, 0, 0, null); 
        
        scen.draw(g);
        
        neLight.draw(g);
        nwLight.draw(g);
        seLight.draw(g);
        swLight.draw(g);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (animate == true) {
			scen.move();
		}
		repaint();
	}
	
	public void startAnimation(){
		this.animate = true;
	}
	
	public void stopAnimation(){
		this.animate = false;
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
