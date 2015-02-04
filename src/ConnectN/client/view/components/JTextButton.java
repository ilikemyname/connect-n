/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * A JButton that has text only.
 *
 * @author Dang Hong Thanh 
 */
public class JTextButton extends JButton implements MouseListener {

    public JTextButton(String text, Font font) {
        super(text);
        setFont(font);

        init();
    }

    private void init() {
        // Makes the button's text as-is
        setBorder(null);
        setContentAreaFilled(false);

        setForeground(new Color(255, 255, 255, 128));
        addMouseListener(this);

        setPressedIcon(new ImageIcon(new BufferedImage(1,
                    1, BufferedImage.TYPE_INT_ARGB_PRE)));
        setVerticalTextPosition(CENTER);
        setHorizontalTextPosition(CENTER);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setForeground(new Color(255, 255, 255, 128));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setForeground(new Color(255, 255, 255, 128));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setForeground(new Color(255, 255, 255, 255));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setForeground(new Color(255, 255, 255, 128));
    }
}
