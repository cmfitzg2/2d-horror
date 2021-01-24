package Worlds.WorldGenerator;

import Utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class ConfigWindow implements ActionListener {

    private JFrame configWindow;
    private JLabel widthLabel = new JLabel("Width");
    private JTextField widthField = new JTextField();
    private JLabel heightLabel = new JLabel("Height");
    private JTextField heightField = new JTextField();
    private JLabel tilesheetDirLabel = new JLabel("Tilesheet dir");
    private JTextField tilesheetDirField = new JTextField();
    private JLabel outputDirLabel = new JLabel("Output file");
    private JTextField outputDirField = new JTextField();
    private JLabel loadFileLabel = new JLabel("File to load");
    private JTextField loadFileField = new JTextField();
    private JButton generateButton = new JButton("Generate");
    private JButton generateWorldButton = new JButton("Output World File");
    private JButton loadWorldFile = new JButton("Load From File");
    private JPanel configPanelMain = new JPanel();
    private HashMap<BufferedImage, File> tileSheets;
    private TilesWindow tilesWindow;
    private WorldView worldView;
    public static int width, height;

    public ConfigWindow() {
        createWindow();
    }

    public void createWindow() {
        configWindow = new JFrame("World Generator");
        configWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateButton.addActionListener(this);
        generateWorldButton.addActionListener(this);
        loadWorldFile.addActionListener(this);
        generateButton.setMnemonic(KeyEvent.VK_ENTER);
        generateButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        generateWorldButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        loadWorldFile.setAlignmentX(JButton.CENTER_ALIGNMENT);
        configPanelMain.setLayout(new BoxLayout(configPanelMain, BoxLayout.Y_AXIS));
        configPanelMain.add(widthLabel);
        configPanelMain.add(widthField);
        configPanelMain.add(heightLabel);
        configPanelMain.add(heightField);
        configPanelMain.add(tilesheetDirLabel);
        configPanelMain.add(tilesheetDirField);
        configPanelMain.add(outputDirLabel);
        configPanelMain.add(outputDirField);
        configPanelMain.add(loadFileLabel);
        configPanelMain.add(loadFileField);
        configPanelMain.add(generateButton);
        configPanelMain.add(loadWorldFile);
        configWindow.add(configPanelMain);
        configWindow.setSize(320, 280);
        configWindow.setLocationRelativeTo(null);
        configWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(generateButton)) {
            if (tilesheetDirField.getText().isEmpty() || widthField.getText().isEmpty() || heightField.getText().isEmpty()) {
                return;
            }
            configPanelMain.remove(loadWorldFile);
            configPanelMain.remove(generateButton);
            configPanelMain.add(generateWorldButton);
            configWindow.setSize(350, 280);
            width = Integer.parseInt(widthField.getText()) * 32;
            height = Integer.parseInt(heightField.getText()) * 32;
            tileSheets = loadTileSheets(tilesheetDirField.getText());
            if (null == tilesWindow) {
                try {
                    worldView = new WorldView();
                    tilesWindow = new TilesWindow(tileSheets, worldView);
                    configWindow.setLocationRelativeTo(null);
                    configWindow.setLocation(configWindow.getLocation().x - 200, configWindow.getLocation().y);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            tilesWindow.setTileSheets(tileSheets);
        }

        if (e.getSource().equals(generateWorldButton)) {
            if (null != worldView && !outputDirField.getText().isEmpty()) {
                worldView.generateWorld(outputDirField.getText());
            }
        }
        if (e.getSource().equals(loadWorldFile)) {
            if (tilesheetDirField.getText().isEmpty() || loadFileField.getText().isEmpty()) {
                return;
            }
            configPanelMain.remove(loadWorldFile);
            configPanelMain.remove(generateButton);
            configPanelMain.add(generateWorldButton);
            configWindow.setSize(350, 280);
            String[] tokens = Utils.loadFileAsString(loadFileField.getText()).split("\t");
            width = Integer.parseInt(tokens[0]) * 32;
            height = Integer.parseInt(tokens[1]) * 32;
            tileSheets = loadTileSheets(tilesheetDirField.getText());
            if (null == tilesWindow) {
                try {
                    worldView = new WorldView();
                    tilesWindow = new TilesWindow(tileSheets, worldView);
                    configWindow.setLocationRelativeTo(null);
                    configWindow.setLocation(configWindow.getLocation().x - 200, configWindow.getLocation().y);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            tilesWindow.setTileSheets(tileSheets);
            worldView.loadFile(loadFileField.getText());
        }
    }

    private HashMap<BufferedImage, File> loadTileSheets(String tileDir) {
        File folder = new File(tileDir);
        File[] files = folder.listFiles();
        HashMap<BufferedImage, File> map = new HashMap<>();
        if (files != null) {
            try {
                for (File file : files) {
                    if (file.getName().endsWith(".png")) {
                        System.out.println(file.getName());
                        File info = new File(folder.getPath() + "\\" + file.getName().substring(0, file.getName().lastIndexOf(".")) + "-info.txt");
                        if (info.exists()) {
                            System.out.println(info.getName());
                            BufferedImage image = ImageIO.read(file);
                            map.put(image, info);
                        }
                    }
                }
            } catch (Exception e1) {

            }

        }
        return map;
    }
}
