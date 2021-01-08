package Worlds.WorldGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.io.File;

public class WorldView extends JFrame implements Runnable, MouseListener, MouseMotionListener {

    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    ImageIcon currentWorld;
    private JLabel world;
    int width, height;

    public WorldView() {
        super("Current World");
        world = new JLabel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = ConfigWindow.width < (int) (3 / 4.0f * screenSize.width) ? ConfigWindow.width : (int) (3 / 4.0f * screenSize.width);
        height = ConfigWindow.height < (int) (3 / 4.0f * screenSize.width) ? ConfigWindow.width : (int) (3 / 4.0f * screenSize.width);
        setSize(width, height);
        setVisible(true);
        display = new Display("World", width * 2, height * 2);
        Canvas canvas = display.getCanvas();
        JScrollPane jsp = new JScrollPane(world);
        jsp.add(canvas);
        getContentPane().add(jsp);
        g = canvas.getGraphics();
        display.getFrame().addMouseListener(this);
        display.getFrame().addMouseMotionListener(this);
        display.getCanvas().addMouseListener(this);
        display.getCanvas().addMouseMotionListener(this);
        Thread t = new Thread(this);
        t.start();
    }

    public void setCurrentWorld(ImageIcon currentWorld) {
        world.setIcon(currentWorld);
        invalidate();
        revalidate();
        repaint();
    }

    private void drawWorld() throws Exception {
        g.drawImage(ImageIO.read(new File("C:\\Users\\narwhal\\Desktop\\dev\\2d-horror\\res\\textures\\tile-sheets\\houses-sheet.png")), 0, 0, width * 2, height * 2, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
}
