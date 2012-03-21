package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class MainView {

	private JFrame mainFrame;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		mainFrame.setBounds(100, 100, 720, 445);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 684, 385);
		mainFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel animationLabel = new JLabel("");
		animationLabel.setIcon(new ImageIcon(MainView.class.getResource("/prinsenkryssetmed.png")));
		animationLabel.setBounds(305, 5, 369, 333);
		panel.add(animationLabel);
		
		JList messageList = new JList();
		messageList.setBounds(10, 5, 285, 277);
		panel.add(messageList);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(MainView.class.getResource("/play2.png")));
		buttonGroup.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(20, 293, 113, 81);
		panel.add(btnNewButton);
		
		JButton button = new JButton("");
		button.setSelectedIcon(new ImageIcon(MainView.class.getResource("/stop2.png")));
		button.setBounds(143, 293, 113, 81);
		panel.add(button);
	}
}
