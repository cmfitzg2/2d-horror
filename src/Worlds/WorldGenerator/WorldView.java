package Worlds.WorldGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class WorldView implements Runnable, MouseListener, MouseMotionListener {

    private BufferStrategy bs;
    private Graphics g;
    private Canvas canvas;
    private Display display;
    private BufferedImage[][] tiles;
    private BufferedImage currentImage;
    private int mouseX, mouseY;
    int width, height;
    private boolean leftPressed, rightPressed;

    public WorldView() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = Math.min(ConfigWindow.width, (int) (3 / 4.0f * screenSize.width));
        height = Math.min(ConfigWindow.height, (int) (3 / 4.0f * screenSize.height));
        tiles = new BufferedImage[Math.max(ConfigWindow.width, (int) (3 / 4.0f * screenSize.width)) / 32]
                [Math.max(ConfigWindow.height, (int) (3 / 4.0f * screenSize.height)) / 32];
        display = new Display("World", width, height);
        canvas = display.getCanvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        g = canvas.getGraphics();
        display.getFrame().addMouseListener(this);
        display.getFrame().addMouseMotionListener(this);
        display.getCanvas().addMouseListener(this);
        display.getCanvas().addMouseMotionListener(this);
        Thread t = new Thread(this);
        t.start();
    }

    private void drawWorld() throws Exception {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != null) {
                    g.drawImage(tiles[i][j],32 * i, 32 * j, null);
                }
            }
        }
        g.setColor(Color.GREEN);
        g.drawRect(32 * (mouseX / 32), 32 * (mouseY / 32), 32, 32);
        bs.show();
        g.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
            tiles[mouseX / 32][mouseY / 32] = currentImage;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void run() {
        int fps = 15;
        double timePerTick = (double) 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (true) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if(delta >= 1) {
                try {
                    drawWorld();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                delta = 0;
            }

        }
    }

    public void setCurrentImage(BufferedImage currentImage) {
        this.currentImage = currentImage;
    }
}
