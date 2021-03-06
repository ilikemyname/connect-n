/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import ConnectN.client.view.components.MetroStyle;
import ConnectN.utilities.SegoeFont;
import java.awt.*;
import javax.swing.JLabel;

/**
 * A component that draw a tooltip shape for field validation.
 *
 * @author Dang Hong Thanh 
 */
public class ErrorTooltip extends JLabel {

    /**
     * The padding for text inside.
     */
    private static final int TEXT_PADDING = 5;
    /**
     * The font used in this case.
     */
    private static final Font TEXT_FONT = SegoeFont.getSegoeUIFont(
            "normal", 14.0f);
    /**
     * The size of tip-pointer.
     */
    private static final int TIP_SIZE = 10;
    /**
     * The distance between the shadow and tooltip.
     */
    private static final int SHADOW_DISTANCE = 2;
    private Dimension d;

    public ErrorTooltip() {
        this("Error here :(");
    }

    public ErrorTooltip(String text) {
        super(text);
        super.setFont(TEXT_FONT);
        this.d = calculateTextSize();
    }

    @Override
    public void paint(Graphics g) {
        // Get the Graphics2D object from Graphics g
        Graphics2D g2d = (Graphics2D) g;
        // Makes the painting smooth
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(hints);

        // Fills the tooltip shape
        g2d.setColor(new Color(0, 0, 0, 128));
        int[] x2d = {
            SHADOW_DISTANCE,
            TIP_SIZE + SHADOW_DISTANCE,
            TIP_SIZE + SHADOW_DISTANCE,
            TIP_SIZE + d.width + SHADOW_DISTANCE,
            TIP_SIZE + d.width + SHADOW_DISTANCE
        };
        int[] y2d = {
            d.height + SHADOW_DISTANCE,
            d.height - TIP_SIZE + SHADOW_DISTANCE,
            SHADOW_DISTANCE,
            SHADOW_DISTANCE,
            d.height + SHADOW_DISTANCE
        };
        g2d.fillPolygon(x2d, y2d, x2d.length);

        // Fills the tooltip shape
        g2d.setColor(MetroStyle.ORANGE);
        x2d = new int[]{
            0,
            TIP_SIZE,
            TIP_SIZE,
            TIP_SIZE + d.width,
            TIP_SIZE + d.width
        };
        y2d = new int[]{
            d.height,
            d.height - TIP_SIZE,
            0,
            0,
            d.height
        };
        g2d.fillPolygon(x2d, y2d, x2d.length);

        // Draws the text inside
        g2d.setColor(Color.white);
        g2d.setFont(TEXT_FONT);
        g2d.drawString(super.getText(),
                TIP_SIZE + TEXT_PADDING,
                d.height - TEXT_PADDING - (TEXT_PADDING * 3/4));
    }

    /**
     * Calculate the Dimension based on the text.
     *
     * @param g
     * @return
     */
    private Dimension calculateTextSize() {
        // Gets the height of this font
        int height = super.getPreferredSize().height + TEXT_PADDING;
        // Gets the width of text base on the font
        int width = super.getPreferredSize().width + TEXT_PADDING * 2;

        // Returns the size of message with some padding
        return new Dimension(width, height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(d.width + TIP_SIZE + SHADOW_DISTANCE,
                d.height + SHADOW_DISTANCE);
    }
}
