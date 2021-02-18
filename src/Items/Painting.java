package Items;

import Variables.Handler;

import java.awt.image.BufferedImage;

public class Painting extends Item {
    public Painting(Handler handler, String itemName, String itemType, String description, String uniqueName, BufferedImage previewImage) {
        super(handler, itemName, itemType, description, uniqueName, previewImage);
    }

    @Override
    public boolean useItem() {
        return false;
    }
}
