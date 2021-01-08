package Worlds.WorldGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TilesWindow implements Runnable, MouseListener, MouseMotionListener {

    private BufferStrategy bs;
    private Graphics g;
    private HashMap<BufferedImage, File> tileSheets;
    private Display display;
    private int width = 0, height = 0;
    private WorldView worldView;
    private int mouseX, mouseY;
    private int[][] tiles;
    private File[] infoFileArray;
    private  BufferedImage[] imageArray;
    public static BufferedImage currentImage;
    public static int currentId = -1;

    public TilesWindow(HashMap<BufferedImage, File> images) throws Exception {
        this.tileSheets = images;
        infoFileArray = new File[images.size()];
        imageArray = new BufferedImage[images.size()];
        int index = 0;
        for (BufferedImage image : images.keySet()) {
            width += image.getWidth();
            height = Math.max(image.getHeight(), height);
            //need to keep these in the same order sadly
            infoFileArray[index] = images.get(image);
            imageArray[index] = image;
            index++;
        }
        display = new Display("Tiles", width, height);
        Canvas canvas = display.getCanvas();
        g = canvas.getGraphics();
        display.getFrame().addMouseListener(this);
        display.getFrame().addMouseMotionListener(this);
        display.getCanvas().addMouseListener(this);
        display.getCanvas().addMouseMotionListener(this);
        Thread t = new Thread(this);
        t.start();
        tiles = initArray();
        setIds();
        worldView = new WorldView();
    }

    private void drawTiles() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);
        int xStart = 0;
        for (BufferedImage image : tileSheets.keySet()) {
            g.drawImage(image, xStart, 0, null);
            xStart += image.getWidth();
        }
        g.setColor(Color.GREEN);
        g.drawRect(32 * (mouseX / 32), 32 * (mouseY / 32), 32, 32);
        if (currentImage != null) {
            g.drawImage(currentImage, 0, 0, 64, 64, null);
        }
        bs.show();
        g.dispose();
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
                drawTiles();
                delta = 0;
            }

        }
    }

    public void setTileSheets(HashMap<BufferedImage, File> tileSheets) {
        this.tileSheets = tileSheets;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (tiles[mouseX / 32][mouseY / 32] == -1) {
            return;
        }
        int i = 0, currentWidth = 0;
        while (imageArray[i].getWidth() + currentWidth < mouseX) {
            currentWidth += imageArray[i].getWidth();
            i++;
        }
        worldView.setCurrentImage(imageArray[i].getSubimage(32 * ((mouseX - currentWidth) / 32), 32 * (mouseY / 32), 32, 32));
        currentId = tiles[mouseX / 32][mouseY / 32];
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
        mouseX = e.getX();
        mouseY = e.getY();
    }

    private int[][] initArray() {
        int[][] tiles = new int[width / 32][height / 32];
        for (int[] tile : tiles) {
            Arrays.fill(tile, -1);
        }
        return tiles;
    }

    private void setIds() throws Exception {
        int xIndex = 0;
        for (int i = 0; i < infoFileArray.length; i++) {
            File file = infoFileArray[i];
            BufferedImage image = imageArray[i];
            Scanner scanner = new Scanner(file);
            int lineIndex = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] ids = line.split("\\s+");
                int column = xIndex;
                for (String id : ids) {
                    tiles[column][lineIndex] = Integer.parseInt(id);
                    column++;
                }
                lineIndex++;
            }
            xIndex += image.getWidth() / 32;
        }
    }
}
