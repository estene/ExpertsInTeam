package main;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AnimationPanel extends JPanel{

    /**
	 * Class that animates on the intersection - Prinsenkrysset
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image;
	private Image image2;
	private int test;
	private Timer timer;
    public AnimationPanel() {                
    	image = new ImageIcon(this.getClass().getResource("/prinsenkryssetmedium.png"));
    	image2 = image.getImage();
    	test = 0;
    	timer = new Timer();
    	
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image2, 0, 0, null); 
        test = test + 5;
        g.drawRect(test, 20, 10, 10);
        System.out.println("drawing");
        
        //TODO : Redraw at some interval

    }

}