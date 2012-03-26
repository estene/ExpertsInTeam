package main;

import java.awt.EventQueue;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;

/*
 * MainView - containts frames,panels etc. 
 */

public class MainView {

	private JFrame mainFrame;
	private JPanel mainPanel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private AnimationPanel animationPanel;

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
		mainFrame.setBounds(100, 100, 1117, 517);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(10, 0, 1081, 447);
		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		animationPanel = new AnimationPanel();
		animationPanel.setBounds(481, 11, 590, 425);
		mainPanel.add(animationPanel);
		animationPanel.setLayout(null);
		
		JList messageList = new JList();
		messageList.setBounds(10, 11, 461, 333);
		mainPanel.add(messageList);
		
		JButton playButton = new JButton("");
		playButton.setIcon(new ImageIcon(MainView.class.getResource("/play2.png")));
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
		stopButton.setIcon(new ImageIcon(MainView.class.getResource("/stop2.png")));
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
				animationPanel.resetAnimation();
				System.out.println("reset");
			}
		});
		resetButton.setBounds(268, 355, 104, 81);
		mainPanel.add(resetButton);
		
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
}
