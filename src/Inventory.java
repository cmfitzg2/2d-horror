import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private Handler handler;
    public static String REGULAR_ITEM = "regular", KEY_ITEM = "key";
    private boolean isOpen, ignoreInputs, firstTime = true;
    private List<String> regularItems, keyItems;
    private int xIndex, yIndex;
    private int xSpace = 268, ySpace = 64, xStart = 64, yStart = 112, inventoryHeaderY = 96;
    private Rectangle headersRect, itemsRect, pictureRect, descriptionRect;
    private KeyManager keyManager;
    private Font headerFont, itemFont;
    private FontMetrics headerFontMetrics, itemFontMetrics;
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
        headerFont = Assets.serif;
        itemFont = Assets.serif;

        //create our constants corresponding to the different inventory sections as rectangles (see inventory-info.png)
        //if inventory asset changes *these must change*
        xScale = (float) handler.getWidth() / 1600;
        yScale = (float) handler.getHeight() / 900;
        headersRect = new Rectangle(0, 0, (int) Math.floor(1109 * xScale), (int) Math.floor(127 * yScale));
        itemsRect = new Rectangle(0, (int) Math.floor(127 * yScale) + 1, (int) Math.floor(1109 * xScale), (int) Math.floor(772 * yScale));
        pictureRect = new Rectangle((int) Math.floor(1109 * xScale) + 1, 0, (int) Math.floor(xScale * 490), (int) Math.floor(yScale * 302));
        descriptionRect = new Rectangle((int) Math.floor(1109 * xScale) + 1, (int) Math.floor(yScale * 302) + 1, (int) Math.floor(xScale * 490), (int) Math.floor(yScale * 597));
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


    private void moveFocus() {

    }

    private void selectItem(String selectedItem) {

    }

    private String getItemNameAtIndex() {
        return null;
    }

    public void render(Graphics graphics) {
        if (firstTime) {
            firstTime = false;
            initFonts(graphics);
        }
        if (isOpen) {

            //debug
            graphics.drawImage(Assets.inventory, 0, 0, handler.getWidth(), handler.getHeight(), null);
            graphics.setColor(Color.CYAN);
            graphics.drawRect(headersRect.x, headersRect.y, headersRect.width, headersRect.height);
            graphics.setColor(Color.GREEN);
            graphics.drawRect(itemsRect.x, itemsRect.y, itemsRect.width, itemsRect.height);
            graphics.setColor(Color.MAGENTA);
            graphics.drawRect(pictureRect.x, pictureRect.y, pictureRect.width, pictureRect.height);
            graphics.setColor(Color.yellow);
            graphics.drawRect(descriptionRect.x, descriptionRect.y, descriptionRect.width, descriptionRect.height);

            drawText(graphics);
        }
    }

    private void drawText(Graphics g) {
        //build the sections of the inventory
        //section headers
        Rectangle itemsHeaderRect = new Rectangle(headersRect);
        itemsHeaderRect.width = itemsHeaderRect.width / 2;
        g.setColor(Color.WHITE);
        GeneralUtils.drawCenteredString(g, "Items", itemsHeaderRect, headerFont);
        Rectangle keyItemsHeaderRect = new Rectangle(headersRect);
        keyItemsHeaderRect.width = keyItemsHeaderRect.width / 2;
        keyItemsHeaderRect.x = keyItemsHeaderRect.width;
        GeneralUtils.drawCenteredString(g, "Key Items", keyItemsHeaderRect, headerFont);

        //main items
        Rectangle regularItemsCol1 = new Rectangle(itemsRect);
        regularItemsCol1.width /= 4;
        Rectangle regularItemsCol2 = new Rectangle(itemsRect);
        regularItemsCol2.width /= 4;
        regularItemsCol2.x += regularItemsCol2.width;
        Rectangle keyItemsCol1 = new Rectangle(itemsRect);
        keyItemsCol1.width /= 4;
        keyItemsCol1.x += keyItemsCol1.width * 2;
        Rectangle keyItemsCol2 = new Rectangle(itemsRect);
        keyItemsCol2.width /= 4;
        keyItemsCol2.x += keyItemsCol2.width * 3;

        for (int i = 0; i < regularItems.size(); i++) {
            //i is a 0-based index, so odd indices are even numbers
            Rectangle itemName;
            if (i % 2 == 0) {
                itemName = new Rectangle(regularItemsCol1);
            } else {
                itemName = new Rectangle(regularItemsCol2);
            }
            itemName.height /= 8;
            itemName.y += i / 2 * (itemFontMetrics.getHeight() * 1.5);
            GeneralUtils.drawCenteredString(g, regularItems.get(i), itemName, itemFont);
        }

    }

    private void initFonts(Graphics g) {
        headerFontMetrics = g.getFontMetrics(headerFont);
        if (handler.getHeight() < handler.getWidth()) {
            while (headerFontMetrics.getHeight() < headersRect.height / 2) {
                headerFontMetrics = g.getFontMetrics(headerFont);
                headerFont = headerFont.deriveFont(headerFont.getSize() + 1.0f);
            }
        } else {
            //TODO: there's more work to be done here, but i'm not going to worry about optimizing for stupid aspect ratios yet
            while (headerFontMetrics.stringWidth("Key items") < headersRect.width / 4) {
                headerFontMetrics = g.getFontMetrics(headerFont);
                headerFont = headerFont.deriveFont(headerFont.getSize() + 1.0f);
            }
        }
        itemFont = itemFont.deriveFont(headerFont.getSize() / 1.5f);
        itemFontMetrics = g.getFontMetrics(itemFont);
        System.out.println(headerFont.getSize());
        System.out.println(headerFontMetrics.getHeight());
        System.out.println(itemFont.getSize());
        System.out.println(itemFontMetrics.getHeight());
    }

    private void drawInventoryHeaders(Graphics g) {

    }
}
