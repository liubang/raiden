package net.raiden.view;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.raiden.model.Game;
import net.raiden.model.SoundPlayer;

public class GameMain extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private SoundPlayer achievementSoundPlayer;

	public static final int VIEW_WIDTH = 784;
	public static final int VIEW_HEIGHT = 562;

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				GameMain thisClass = new GameMain();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	public GameMain() {
		super();
		initialize();
		achievementSoundPlayer.loop();
	}

	private void initialize()
	{
		this.setContentPane(getJContentPane());
		this.setTitle("´ò·É»ú");
		int l = (Toolkit.getDefaultToolkit().getScreenSize().width - 800) / 2;
		int t = (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2;
		this.setBounds(new Rectangle(l, t, 800, 600));
		try {
			achievementSoundPlayer = new SoundPlayer("src/audio/game_music.wav");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(KeyEvent e)
			{
				Game.getInstance().notifyHeroKeyreleased(e.getKeyCode());
			}

			public void keyPressed(KeyEvent e)
			{
				Game.getInstance().notifyHeroKeypressed(e.getKeyCode());
			}
		});
		Game.getInstance().init();
		new Thread() {
			public void run()
			{
				while (true) {
					repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private JPanel getJContentPane()
	{
		if (jContentPane == null) {
			jContentPane = new GamePanel();
			jContentPane.setLayout(null);
		}
		return jContentPane;
	}

}
