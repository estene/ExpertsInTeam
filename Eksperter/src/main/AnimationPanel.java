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
	 * Class that animates scenarios in the intersection - Prinsenkrysset
	 * @author Even
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	private Timer t;
	private boolean animate = false;
	private ClassLoader classLoader;
	private Scenario scen;
	private OPController overpassController;
	
	
	/**
	 * Constructor, initating the given scenario and other elements 
	 */
    public AnimationPanel() {   

		overpassController = new OPController();
    	this.setBounds(481, 11, 590, 425);
		classLoader = Thread.currentThread().getContextClassLoader();
		image = getImage("prinsenkryssetmedium.png");
		scen = new Scenario("scen1", this);
				
		t = new Timer(25, this);
		t.start();
		addMouseMotionListener(this);
		
    }
    
    public void addBusToQueue(Bus b){
    	overpassController.addBusToQueue(b);
    }
    
    public void removeBusFromQueue(Bus b){
    	overpassController.removeBusFromQueue(b);
    }
    
    public void opcAction(){
    	overpassController.calculateNextAction();
    }
    
    /**
     * 
     * @return an arrayList of the type TrafficLight , containing all trafficlights in the intersection
     */
    public ArrayList<TrafficLight> getTrafficLights(){
    	return overpassController.getTrafficLights();
    }
    /**
     * Method is called from MainView when a user selects a scenario from the dropdown menu
     * @param scenario
     */
    public void setScenario(String scenario) {
    	scen = new Scenario(scenario, this);
    }
    
    /**
     * Paintcomponent method. Does all the draw calls for the scenarios and other elements in the animationPanel
     */
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g); // Clean up
        g.drawImage(image, 0, 0, null); 
        scen.draw(g);
        
        for(TrafficLight tl : getTrafficLights()){
        	tl.draw(g);
        }
    }

    /**
     * actionPerformed method for the timer. This is called when the user presses the playbutton. 
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (animate == true) {
			overpassController.calculateNextAction();
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
	
	/**
	 * Method to create an image from the given filepath
	 * @param imgName
	 * @return inputImage , the loaded image from resource
	 */
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
