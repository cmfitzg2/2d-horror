package Textboxes;

public class Textbox {

    private String lineOne, lineTwo, lineThree;

    public Textbox(String lineOne, String lineTwo, String lineThree) {
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
        this.lineThree = lineThree;
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
