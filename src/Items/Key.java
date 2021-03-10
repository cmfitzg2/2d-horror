package Items;

import Variables.Handler;

import java.awt.image.BufferedImage;

public class Key extends Item {

    private boolean active;
    public static String BELLTOWER = "key-belltower";

    public Key(Handler handler, String itemName, String itemType, String description, String uniqueName, BufferedImage previewImage) {
        super(handler, itemName, itemType, description, uniqueName, previewImage);
    }

    @Override
    public boolean useItem() {
        return false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
