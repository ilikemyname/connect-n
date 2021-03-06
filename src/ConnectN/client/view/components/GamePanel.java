/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import ConnectN.client.model.ClientModel;
import ConnectN.component.Session;
import ConnectN.utilities.Util;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Dang Hong Thanh 
 */
public class GamePanel extends JPanel implements MouseListener {

    public static final int GRID_SIZE = 54;
    public static final int MOVE_SIZE = 50;
    public static final int BORDER_SIZE = (GRID_SIZE - MOVE_SIZE) / 2;
    private static final int[][] TEST_BOARD = {
        {00, 00, 00, 00, 00, 20, 20, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 20, 20, 20, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 10, 20, 10, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 00, 20, 20, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 20, 20, 20, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 10, 20, 10, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 11, 11, 11, 11, 11, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {20, 00, 10, 10, 10, 20, 10, 10, 20, 00, 00, 00, 00, 00, 00, 00, 00},
        {20, 00, 10, 20, 20, 10, 20, 10, 20, 20, 00, 00, 00, 00, 00, 00, 00},};
    private static final int[][] TEST_BOARD_2 = {
        {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
        {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},};
    private int[][] gameMap;
    private ClientModel model;
//    private Session room;

    public GamePanel(ClientModel model, Session room) {
        this.model = model;
//        this.room = room;
        gameMap = new int[room.getRows()][room.getCols()];

        init();
        initListener();
    }

    private void init() {
        setPreferredSize(new Dimension(GRID_SIZE * gameMap[0].length,
                GRID_SIZE * gameMap.length));
    }

    private void initListener() {
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        setSize(GRID_SIZE * gameMap.length, GRID_SIZE * gameMap[0].length);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 255, 255, 198));

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(hints);

        int startX = 0, startY = 0;

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[0].length; j++) {
                if (gameMap[i][j] == 0) {
                    // Draws the circle grids
                    drawEmptyPlace(g2d, startX, startY);
                } else {
                    drawMove(gameMap[i][j], g2d, startX, startY);
                }
                startX += GRID_SIZE;
            }
            startX = 0;
            startY += GRID_SIZE;
        }
    }

    private void drawEmptyPlace(Graphics2D g2d, int startX, int startY) {
        // The actual size of this empty place is 46x46
        startX += BORDER_SIZE + 2;
        startY += BORDER_SIZE + 2;

        g2d.drawOval(startX, startY, MOVE_SIZE - 4, MOVE_SIZE - 4);
    }

    private void drawMove(int player, Graphics2D g2d, int startX, int startY) {
        startX += BORDER_SIZE;
        startY += BORDER_SIZE;

        g2d.drawImage(Util.makeIcon("player" + player + "Btn").getImage(),
                startX, startY,
                this);
    }

    public int update(int x, int move) {
        for (int i = 0; i < gameMap.length; i++) {
            if (gameMap[i][x] > 0) {
                try {
                    gameMap[i - 1][x] = move;
                    repaint();
                } catch (ArrayIndexOutOfBoundsException indexOutOfBoundsEx) {
                }
                return i - 1;
            }
        }

        gameMap[gameMap.length - 1][x] = move;
        repaint();
        return gameMap.length - 1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if (model.getPlayer().isMyTurn()) {
                int y = update(e.getX() / GRID_SIZE, 10);
                model.createDisk(e.getX() / GRID_SIZE, y);
                model.getPlayer().setMyTurn(false);
            }
        } catch (ArrayIndexOutOfBoundsException indexOutOfBoundsException) {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
