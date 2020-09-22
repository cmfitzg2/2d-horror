import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private Handler handler;
    public static String REGULAR_ITEM = "regular", KEY_ITEM = "key";
    private boolean isOpen, ignoreInputs;
    private List<String> regularItems, keyItems;
    private int xIndex, yIndex;
    private int xSpace = 268, ySpace = 64, xStart = 64, yStart = 112, inventoryHeaderY = 96;
    private Rectangle headersRect, itemsRect, pictureRect, descriptionRect;
    private KeyManager keyManager;
    private Font inventoryFont;
    private ItemManager itemManager;
    private float xScale, yScale;

    public Inventory(Handler handler, Player player) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        itemManager = player.getItemManager();
        regularItems = new ArrayList<>();
        keyItems = new ArrayList<>();
        regularItems.add("regular item 1");
        regularItems.add("regular item 2");
        regularItems.add("regular item 3");
        regularItems.add("regular item 4");
        regularItems.add("regular item 5");
        keyItems.add("key item 1");
        keyItems.add("key item 2");
        keyItems.add("key item 3");
        inventoryFont = Assets.serif;

        //create our constants corresponding to the different inventory sections as rectangles (see inventory-info.png)
        //if inventory asset changes *these must change*
        xScale = (float) handler.getWidth() / 1600;
        yScale = (float) handler.getHeight() / 900;
        headersRect = new Rectangle(0, 0, (int) Math.floor(1109 * xScale), (int) Math.floor(127 * yScale));
        itemsRect = new Rectangle(0, (int) Math.floor(128 * yScale), (int) Math.floor(1109 * xScale), (int) Math.floor(772 * yScale));
        pictureRect = new Rectangle((int) Math.floor(1110 * xScale), 0, (int) Math.floor(xScale * 490), (int) Math.floor(yScale * 302));
        descriptionRect = new Rectangle((int) Math.floor(xScale * 1110), (int) Math.floor(yScale * 303), (int) Math.floor(xScale * 490), (int) Math.floor(yScale * 597));
    }

    public void tick() {
        if (keyManager.c && !keyManager.isStillHoldingC()) {
            keyManager.setStillHoldingC(true);
            if (!handler.getFlags().isViewingArt()) {
                if (isOpen) {
                    handler.setPlayerFrozen(false);
                } else {
                    handler.setPlayerFrozen(true);
                }
                isOpen = !isOpen;
            }
        }
        if (isOpen) {
            if (!ignoreInputs) {
                moveFocus();
            }
            if (keyManager.enter && !keyManager.isStillholdingEnter()) {
                keyManager.setStillholdingEnter(true);
                String selectedItem = getItemNameAtIndex();
                if (null != selectedItem) {
                    selectItem(selectedItem);
                }
            }
        }
    }

    public void render(Graphics graphics) {
        if (isOpen) {
            if (Assets.inventory != null) {
                graphics.drawImage(Assets.inventory, 0, 0, handler.getWidth(), handler.getHeight(), null);
                graphics.setColor(Color.CYAN);
                graphics.drawRect(headersRect.x, headersRect.y, headersRect.width, headersRect.height);
                graphics.setColor(Color.GREEN);
                graphics.drawRect(itemsRect.x, itemsRect.y, itemsRect.width, itemsRect.height);
                graphics.setColor(Color.MAGENTA);
                graphics.drawRect(pictureRect.x, pictureRect.y, pictureRect.width, pictureRect.height);
                graphics.setColor(Color.yellow);
                graphics.drawRect(descriptionRect.x, descriptionRect.y, descriptionRect.width, descriptionRect.height);
            }
        }
    }

    private void moveFocus() {

    }

    private void selectItem(String selectedItem) {

    }

    private String getItemNameAtIndex() {
        return null;
    }

    private void drawInventoryHeaders(Graphics g) {

    }
}
