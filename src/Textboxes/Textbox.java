package Textboxes;

import java.awt.image.BufferedImage;

public class Textbox {

    private String lineOne, lineTwo, lineThree;
    private BufferedImage portrait;

    public Textbox(String lineOne, String lineTwo, String lineThree, BufferedImage portrait) {
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
        this.lineThree = lineThree;
        this.portrait = portrait;
    }

    public String getLineOne() {
        return lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public String getLineThree() {
        return lineThree;
    }

    public BufferedImage getPortrait() {
        return portrait;
    }

    public String getCurrentText(int currentLine) {
        switch (currentLine) {
            case 1:
                return lineOne;
            case 2:
                return lineTwo;
            case 3:
                return lineThree;
        }
        return "";
    }
}
