package main;

import javax.swing.JFrame;

public class GameMain extends JFrame {
	public final static int WIDTH = 1000;
	public final static int HEIGHT = 600;

	public GameMain(String arg0) {
		super(arg0);
		setContentPane(new GamePanel());
		pack();
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		new GameMain("Gladiators").setVisible(true);
	}

}
