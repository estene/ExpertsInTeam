package main;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ListModel;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * MainView - contains frames,panels etc. 
 * @author Even
 */

public class MainView {

	private JFrame mainFrame;
	private JPanel mainPanel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private AnimationPanel animationPanel;
	private ClassLoader classLoader;
	private String scenario;
	Image image;
	private JList messageList;
	private DefaultListModel listMessages;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("G6 Animation");
		mainFrame.setBounds(100, 100, 1117, 545);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		classLoader = Thread.currentThread().getContextClassLoader();
		
		mainPanel = new JPanel();
		mainPanel.setBounds(10, 0, 1081, 479);
		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		
		animationPanel = new AnimationPanel();
		animationPanel.setScenario("scen1");
		animationPanel.setBounds(481, 11, 590, 425);
		mainPanel.add(animationPanel);
		animationPanel.setLayout(null);
		scenario = "";
		
		listMessages = new DefaultListModel();
		
		messageList = new JList(listMessages);
		
		messageList.setBounds(10, 11, 461, 333);
		mainPanel.add(messageList);
				
		JButton playButton = new JButton("");
		
		playButton.setIcon(new ImageIcon(getImage("play2.png")));
		buttonGroup.add(playButton);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				animationPanel.startAnimation();
				System.out.println("play");
			}
		});
		playButton.setBounds(22, 355, 113, 81);
		mainPanel.add(playButton);
		
		JButton stopButton = new JButton("");
		stopButton.setIcon(new ImageIcon(getImage("stop2.png")));
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				animationPanel.stopAnimation();
				System.out.println("stop");
			}
		});
		stopButton.setBounds(145, 355, 113, 81);
		mainPanel.add(stopButton);
		
		JButton resetButton = new JButton("RESET");
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				animationPanel.removeAll();
				mainPanel.remove(animationPanel);
				animationPanel = new AnimationPanel();
				animationPanel.setScenario(getSelectedScenario());
				mainPanel.add(animationPanel);
				System.out.println("reset");
			}
		});
	
		
		resetButton.setBounds(268, 355, 104, 81);
		mainPanel.add(resetButton);
		final JComboBox scenarioBox = new JComboBox();
		String[] scenarioString = {"Scenario 1", "Scenario 2", "Scenario 3"};
		scenarioBox.setModel(new DefaultComboBoxModel(scenarioString));
		scenarioBox.setSelectedIndex(0);
		setSelectedScenario("scen1");
		
		listMessages.clear();
		
		String[] sc1 = {"Scenario 1:", 
				" ", 
				"Buss A = 30 personer, 3 min forsinket, fra sør til vest", 
				"Buss B = 31 personer, i rute, fra vest til nord", 
				"Buss C = 20 personer, i rute, fra nord til vest",
				" ", 
				"Fotgjengere:", 
				"4 fra Sør-Øst til Sør-Vest", 
				"2 fra Sør-Vest til Sør-Øst"};
		
		for(String s : sc1) listMessages.addElement(s);
		
		scenarioBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (scenarioBox.getSelectedIndex() == 0) {
					animationPanel.stopAnimation();
					
					animationPanel.setScenario("scen1");
					listMessages.clear();
					
					String[] sc1 = {"Scenario 1:", 
							" ", 
							"Buss A = 30 personer, 3 min forsinket, fra sør til vest", 
							"Buss B = 31 personer, i rute, fra vest til nord", 
							"Buss C = 20 personer, i rute, fra nord til vest",
							" ", 
							"Fotgjengere:", 
							"4 fra Sør-Øst til Sør-Vest", 
							"2 fra Sør-Vest til Sør-Øst"};
					
					for(String s : sc1) listMessages.addElement(s);
					setSelectedScenario("scen1");
										
				}
				else if (scenarioBox.getSelectedIndex() == 1) {
					animationPanel.stopAnimation();
					animationPanel.setScenario("scen2");
					
					listMessages.clear();
					String[] sc1 = {"Scenario 2:", 
							" ", 
							"Busser:",
							"Buss A = 10 personer, i rute, fra vest til nord",
							"Buss B = 10 personer, 3 min forsinket, fra sør til vest", 
							"Buss C = 10 personer, i rute, fra vest til sør", 
							" ", 
							"Fotgjengere:", 
							"-"};
					
					for(String s : sc1) listMessages.addElement(s);
					
					setSelectedScenario("scen2");
				}
				else if (scenarioBox.getSelectedIndex() == 2) {
					animationPanel.stopAnimation();
					animationPanel.setScenario("scen3");
					
					listMessages.clear();
					String[] sc1 = {"Scenario 3:", 
							" ", 
							"Buss A = 15 personer, 10 min forsinket, fra sør til vest", 
							"Buss B = 10 personer, i rute, fra vest til sør", 
							"Buss C = 20 personer, i rute, fra nord til vest",
							" ", 
							"Fotgjengere:", 
							"5 stk fra nord til sør", 
							"10 stk fra sør til nord"};
					
					for(String s : sc1) listMessages.addElement(s);
					
					setSelectedScenario("scen3");
				}
			}
		});		
		
		scenarioBox.setToolTipText("Select scenario");
		scenarioBox.setBounds(22, 448, 236, 20);
		mainPanel.add(scenarioBox);
		
		JMenuBar menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmOmOss = new JMenuItem("Om oss");
		mnAbout.add(mntmOmOss);
		
		JMenu mnFint = new JMenu("Fint");
		menuBar.add(mnFint);
	}
	
	public String getSelectedScenario() {
		return scenario;
	}
	
	public void setSelectedScenario(String scen) {
		scenario = scen;
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
			e.printStackTrace();
		}
		return inputImage;
	}
}
