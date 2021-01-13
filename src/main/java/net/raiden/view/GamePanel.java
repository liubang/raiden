package net.raiden.view;

import net.raiden.model.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public void paint(Graphics g) {
        Game.getInstance().paint(g);
    }
}
