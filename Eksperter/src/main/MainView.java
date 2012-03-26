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

public class MainView implements ActionListener {

	private JFrame mainFrame;
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
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("G6 Animation");
		mainFrame.setBounds(100, 100, 1117, 517);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 1081, 447);
		mainFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		animationPanel = new AnimationPanel();
		animationPanel.setBounds(481, 11, 590, 425);
		panel.add(animationPanel);
		animationPanel.setLayout(null);
		
		JList messageList = new JList();
		messageList.setBounds(10, 11, 461, 333);
		panel.add(messageList);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(MainView.class.getResource("/play2.png")));
		buttonGroup.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(22, 355, 113, 81);
		panel.add(btnNewButton);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(MainView.class.getResource("/stop2.png")));
		button.setBounds(145, 355, 113, 81);
		panel.add(button);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int)b.getX();
		int y = (int)b.getY();
		System.out.println(x+ " " +y);
		
	}
}
