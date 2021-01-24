package Worlds.WorldGenerator;

import Input.KeyManager;
import Utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class WorldView implements Runnable, MouseListener, MouseMotionListener {

    private BufferStrategy bs;
    private Graphics g;
    private Canvas canvas;
    private Display display;
    private BufferedImage[][] tiles;
    private int[][] tileIds;
    private BufferedImage currentImage;
    private int currentId;
    private int mouseX, mouseY;
    int width, height;
    private KeyManager keyManager;
    private int xOffset = 0, yOffset = 0;
    private HashMap<Integer, BufferedImage> tilesMap;

    public WorldView() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = Math.min(ConfigWindow.width, (int) (3 / 4.0f * screenSize.width));
        if (ConfigWindow.width > (int) (3 / 4.0f * screenSize.width)) {
            width += 32 - width % 32;
        }
        height = Math.min(ConfigWindow.height, (int) (3 / 4.0f * screenSize.height));
        if (ConfigWindow.height > (int) (3 / 4.0f * screenSize.height)) {
            height += 32 - height % 32;
        }
        tiles = new BufferedImage[ConfigWindow.width / 32][ConfigWindow.height / 32];
        tileIds = new int[tiles.length][tiles[0].length];
        display = new Display("World", width, height);
        display.getFrame().setLocationRelativeTo(null);
        canvas = display.getCanvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        g = canvas.getGraphics();
        keyManager = new KeyManager();
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addKeyListener(keyManager);
        display.getFrame().addMouseListener(this);
        display.getFrame().addMouseMotionListener(this);
        display.getCanvas().addMouseListener(this);
        display.getCanvas().addMouseMotionListener(this);
        Thread t = new Thread(this);
        t.start();
    }

    private void drawWorld() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //gonna just do input here rather than a separate method because whatever
        keyManager.tick();
        if (keyManager.right && !keyManager.isStillHoldingRight() && canvas.getWidth() + xOffset + 32 <= ConfigWindow.width) {
            keyManager.setStillHoldingRight(true);
            xOffset += 32;
        }
        if (keyManager.left && !keyManager.isStillHoldingLeft() && xOffset > 0) {
            keyManager.setStillHoldingLeft(true);
            xOffset -= 32;
        }
        if (keyManager.down && !keyManager.isStillHoldingDown() && canvas.getHeight() + yOffset + 32 <= ConfigWindow.height) {
            keyManager.setStillHoldingDown(true);
            yOffset += 32;
        }
        if (keyManager.up && !keyManager.isStillHoldingUp() && yOffset > 0) {
            keyManager.setStillHoldingUp(true);
            yOffset -= 32;
        }

        //clear screen
        g.clearRect(0, 0, width, height);
        if (tilesMap != null) {
            for (int i = 0; i < tileIds.length; i++) {
                for (int j = 0; j < tileIds[i].length; j++) {
                    if (tileIds[i][j] >= 0) {
                        g.drawImage(tilesMap.get(tileIds[i][j]), 32 * i - xOffset, 32 * j - yOffset, null);
                    }
                }
            }
        }
        g.setColor(Color.GREEN);
        g.drawRect(32 * (mouseX / 32), 32 * (mouseY / 32), 32, 32);
        g.setColor(Color.RED);
        String currentPos = "";
        if (ConfigWindow.width > width) {
            currentPos = "Width: " + xOffset / 32 + " / " + ((ConfigWindow.width - width) / 32) + "    ";
        }
        if (ConfigWindow.height > height) {
            currentPos += "Height: " + yOffset / 32 + " / " + ((ConfigWindow.height - height) / 32);
        }
        g.drawString(currentPos, 20, 20);
        bs.show();
        g.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            tiles[mouseX / 32 + xOffset / 32][mouseY / 32 + yOffset / 32] = currentImage;
            tileIds[mouseX / 32 + xOffset / 32][mouseY / 32 + yOffset / 32] = currentId;
        }
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
        mouseX = e.getX();
        mouseY = e.getY();
        tiles[mouseX / 32 + xOffset / 32][mouseY / 32 + yOffset / 32] = currentImage;
        tileIds[mouseX / 32 + xOffset / 32][mouseY / 32 + yOffset / 32] = currentId;
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

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public void update(int width, int height) {

    }

    public void generateWorld(String outputDir) {
        //need to swap X and Y here to make writing to file easier later
        int[][] temp = new int[tileIds[0].length][tileIds.length];
        for (int i = 0; i < tileIds.length; i++) {
            for (int j = 0; j < tileIds[0].length; j++) {
                temp[j][i] = tileIds[i][j];
            }
        }

        String world = Arrays.deepToString(temp);
        world = world.replace("], ", "\n").replace("[[", "").replace("]]", "").replace(",", "").replace("[", "").replace(" ", "\t");
        world = ConfigWindow.width / 32 + "\t" + ConfigWindow.height / 32 + "\n" + world;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputDir), StandardCharsets.UTF_8))) {
            writer.write(world);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            //skip the first line with width/height
            br.readLine();
            int i, j = 0;
            while ((line = br.readLine()) != null) {
                i = 0;
                String[] ids = line.split("\t");
                for (String id : ids) {
                    tileIds[i][j] = Utils.parseInt(id);
                    i++;
                }
                j++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTilesMap(HashMap<Integer, BufferedImage> tilesMap) {
        this.tilesMap = tilesMap;
    }
}
