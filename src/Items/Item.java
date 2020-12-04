package Items;

import java.awt.image.BufferedImage;

public class Item {

    private String itemName;
    private String itemType;
    private String description;
    private String uniqueName;
    private BufferedImage previewImage;

    public Item(String itemName, String itemType, String description, String uniqueName, BufferedImage previewImage) {
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
}
