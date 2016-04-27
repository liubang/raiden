package net.raiden.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import net.raiden.model.Game;

public class GamePanel extends JPanel
{

	private static final long serialVersionUID = 1L;

	public void paint(Graphics g)
	{
		Game.getInstance().paint(g);
	}
}
