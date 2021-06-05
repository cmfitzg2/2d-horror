package Items;

import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item {

    protected String itemName;
    protected String itemType;
    protected String description;
    protected String uniqueName;
    protected BufferedImage previewImage;
    protected Handler handler;
    public static String LIGHTER_UID = "lighter";

    public Item(Handler handler, String itemName, String itemType, String description, String uniqueName, BufferedImage previewImage) {
        this.handler = handler;
        this.itemName = itemName;
        this.itemType = itemType;
        this.description = description;
        this.uniqueName = uniqueName;
        this.previewImage = previewImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public String getDescription() {
        return description;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public BufferedImage getPreviewImage() {
        return previewImage;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public void setPreviewImage(BufferedImage previewImage) {
        this.previewImage = previewImage;
    }

    public abstract boolean useItem();

    protected abstract void tick();

    protected abstract void render(Graphics g);
}
