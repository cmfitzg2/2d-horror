package Worlds.WorldGenerator;

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
    private JLabel widthLabel = new JLabel("Width: ");
    private JTextField widthField = new JTextField();
    private JLabel heightLabel = new JLabel("Height: ");
    private JTextField heightField = new JTextField();
    private JLabel tilesheetDirLabel = new JLabel("Tilesheet dir: ");
    private JTextField tilesheetDirField = new JTextField();
    private JLabel outputDirLabel = new JLabel("Output dir: ");
    private JTextField outputDirField = new JTextField();
    private JLabel filenameDirLabel = new JLabel("Filename: ");
    private JTextField filenameDirField = new JTextField();
    private JButton generateButton = new JButton("Generate");
    private JButton generateWorldButton = new JButton("Output World File");
    private JPanel configPanelMain = new JPanel();
    private HashMap<BufferedImage, File> tileSheets;
    private TilesWindow tilesWindow;
    public static int width, height;

    public ConfigWindow() {
        createWindow();
    }

    public void createWindow() {
        configWindow = new JFrame("World Generator");
        configWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateButton.addActionListener(this);
        generateButton.setMnemonic(KeyEvent.VK_ENTER);
        generateButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        configPanelMain.setLayout(new BoxLayout(configPanelMain, BoxLayout.Y_AXIS));
        configPanelMain.add(widthLabel);
        configPanelMain.add(widthField);
        configPanelMain.add(heightLabel);
        configPanelMain.add(heightField);
        configPanelMain.add(tilesheetDirLabel);
        configPanelMain.add(tilesheetDirField);
        configPanelMain.add(outputDirLabel);
        configPanelMain.add(outputDirField);
        configPanelMain.add(filenameDirLabel);
        configPanelMain.add(filenameDirField);
        configPanelMain.add(generateButton);
        configWindow.add(configPanelMain);
        configWindow.setSize(350, 250);
        configWindow.setLocationRelativeTo(null);
        configWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(generateButton)) {
            if (generateButton.getText().equals("Generate")) {
                if (tilesheetDirField.getText().isEmpty() || outputDirField.getText().isEmpty()) {
                    return;
                }
                generateButton.setText("Update");
                generateWorldButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
                configPanelMain.add(generateWorldButton);
                configWindow.setSize(350, 280);
            }
            width = Integer.parseInt(widthField.getText()) * 32;
            height = Integer.parseInt(heightField.getText()) * 32;
            File folder = new File(tilesheetDirField.getText());
            File[] files = folder.listFiles();
            tileSheets = new HashMap<>();
            if (files != null) {
                try {
                    for (File file : files) {
                        if (file.getName().endsWith(".png")) {
                            System.out.println(file.getName());
                            File info = new File(folder.getPath() + "\\" + file.getName().substring(0, file.getName().lastIndexOf(".")) + "-info.txt");
                            if (info.exists()) {
                                System.out.println(info.getName());
                                BufferedImage image = ImageIO.read(file);
                                tileSheets.put(image, info);
                            }
                        }
                    }
                    if (null == tilesWindow) {
                        tilesWindow = new TilesWindow(tileSheets);
                        configWindow.setLocationRelativeTo(null);
                        configWindow.setLocation(configWindow.getLocation().x - 200, configWindow.getLocation().y);
                    }
                    tilesWindow.setTileSheets(tileSheets);
                } catch (Exception e1) {

                }
            }
        }
    }
}
