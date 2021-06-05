package Items;

import Entities.Entity;
import Textboxes.TextboxHandler;
import Variables.GeneralConstants;
import Variables.Handler;
import Input.KeyManager;
import Graphics.Assets;
import Utils.FontUtils;
import Utils.GeneralUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

    private Handler handler;
    public static String REGULAR_ITEM = "regular", KEY_ITEM = "key";
    private boolean open, ignoreInputs, showItemSelectedTextbox, firstTime = true;
    private List<String> regularItems, keyItems, regularUniqueNames, keyUniqueNames;
    private Map<String, Item> items;
    private int xIndex, yIndex;
    private Rectangle headersRect, itemsRect, pictureRect, descriptionRect, pictureRectBorderless;
    private KeyManager keyManager;
    private Font headerFont, itemFont;
    private FontMetrics headerFontMetrics, itemFontMetrics;
    private float xScale, yScale;
    private final int borderWidth = 10;
    private TextboxHandler itemSelectedTextbox;

    public Inventory(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        regularItems = new ArrayList<>();
        keyItems = new ArrayList<>();
        regularUniqueNames = new ArrayList<>();
        keyUniqueNames = new ArrayList<>();
        items = new HashMap<>();
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
        pictureRectBorderless = new Rectangle(pictureRect.x + Math.round(borderWidth / 2.0f * xScale), pictureRect.y + Math.round(borderWidth * yScale), pictureRect.width - Math.round(borderWidth * xScale * 1.5f), pictureRect.height - Math.round(borderWidth * yScale * 1.5f));
    }

    public void tick() {
        if (!handler.isPlayerFrozen() || handler.isInMenu()) {
            if (keyManager.c && !keyManager.isStillHoldingC() && !handler.getActiveWorld().transitioningTo && !handler.getActiveWorld().transitioningFrom) {
                keyManager.setStillHoldingC(true);
                if (!handler.getFlags().isViewingArt() && !handler.getFlags().isInPuzzle()) {
                    if (open) {
                        //closing the menu
                        Assets.closeInventory.stop();
                        Assets.closeInventory.play();
                        handler.setPlayerFrozen(false);
                        handler.setInMenu(false);
                        handler.setGamePaused(false);
                    } else {
                        //opening the menu
                        Assets.openInventory.stop();
                        Assets.openInventory.play();
                        handler.setPlayerFrozen(true);
                        handler.setInMenu(true);
                        handler.setGamePaused(true);
                        if (regularItems.size() > 0) {
                            xIndex = 0;
                            yIndex = 0;
                        } else if (keyItems.size() > 0) {
                            xIndex = 2;
                            yIndex = 0;
                        }
                    }
                    open = !open;
                }
            }
        }
        if (open) {
            if (!ignoreInputs) {
                if (regularItems.isEmpty() && keyItems.isEmpty()) {
                    return;
                }
                if (keyManager.left && !keyManager.isStillHoldingLeft()) {
                    keyManager.setStillHoldingLeft(true);
                    moveFocus("left");
                } else if (keyManager.right && !keyManager.isStillHoldingRight()) {
                    keyManager.setStillHoldingRight(true);
                    moveFocus("right");
                } else if (keyManager.up && !keyManager.isStillHoldingUp()) {
                    keyManager.setStillHoldingUp(true);
                    moveFocus("up");
                } else if (keyManager.down && ! keyManager.isStillHoldingDown()) {
                    keyManager.setStillHoldingDown(true);
                    moveFocus("down");
                }
            }
            if (keyManager.enter && !keyManager.isStillholdingEnter()) {
                keyManager.setStillholdingEnter(true);
                String selectedItem = getUniqueItemNameAtIndex();
                if (null != selectedItem) {
                    selectItem(selectedItem);
                }
            }
            if (keyManager.z && !keyManager.isStillHoldingZ()) {
                keyManager.setStillHoldingZ(true);
                String selectedItem = getUniqueItemNameAtIndex();
                if (null != selectedItem) {
                    selectItem(selectedItem);
                }
            }
        } else {
            if (showItemSelectedTextbox) {
                if (!itemSelectedTextbox.isFinished()) {
                    itemSelectedTextbox.tick();
                }
            }
        }
        for (Item item : items.values()) {
            item.tick();
        }
    }

    public boolean containsUnique(String uniqueName) {
        return keyUniqueNames.contains(uniqueName) || regularUniqueNames.contains(uniqueName);
    }

    public boolean contains(String name) {
        return keyItems.contains(name) || regularItems.contains(name);
    }


    /**
     * this is a mess of edge cases for dealing with updating the cursor indices in a way that feels natural
     * @param dir the direction to move the cursor
     */
    private void moveFocus(String dir) {
        if (keyItems.isEmpty() && regularItems.isEmpty()) {
            return;
        }
        boolean col1Exists = !regularItems.isEmpty();
        boolean col2Exists = regularItems.size() > 1;
        boolean col3Exists = !keyItems.isEmpty();
        boolean col4Exists = keyItems.size() > 1;

        switch (dir) {
            case "left":
                if (xIndex == 0) {
                    //column 1
                    if (col4Exists) {
                        xIndex = 3;
                        if (yIndex > keyItems.size() / 2 - 1) {
                            yIndex = keyItems.size() / 2 - 1;
                        }
                    } else if (col3Exists) {
                        xIndex = 2;
                        //we know there's only one item since col4 doesn't exist
                        yIndex = 0;
                    } else if (col2Exists) {
                        xIndex = 1;
                        if (regularItems.size() % 2 == 1) {
                            //only need to check for odd edge case
                            if (yIndex == regularItems.size() / 2) {
                                yIndex--;
                            }
                        }
                    } //else do nothing
                } else if (xIndex == 1) {
                    //column 2 (column 1 is now necessarily populated)
                    xIndex = 0;
                } else if (xIndex == 2) {
                    //column 3
                    if (col2Exists) {
                        xIndex = 1;
                        if (yIndex > regularItems.size() / 2 - 1) {
                            yIndex = regularItems.size() / 2 - 1;
                        }
                    } else if (col1Exists) {
                        xIndex = 0;
                        yIndex = 0;
                    } else if (col4Exists) {
                        xIndex = 3;
                        if (keyItems.size() % 2 == 1) {
                            //only need to check for odd edge case
                            if (yIndex == keyItems.size() / 2) {
                                yIndex--;
                            }
                        }
                    }
                } else if (xIndex == 3) {
                    //column 4 (column 3 is now necessarily populated)
                    xIndex = 2;
                }
                break;
            case "right":
                if (xIndex == 0) {
                    if (col2Exists) {
                        xIndex = 1;
                        if (yIndex > regularItems.size() / 2 - 1) {
                            yIndex = regularItems.size() / 2 - 1;
                        }
                    } else if(col3Exists) {
                        xIndex = 2;
                        //we know yIndex = 0
                    } //else do nothing (col4 doesnt exist)
                } else if (xIndex == 1) {
                    if (col3Exists) {
                        xIndex = 2;
                        if (yIndex > keyItems.size() / 2) {
                            yIndex = keyItems.size() / 2;
                        }
                    } else {
                        xIndex = 0;
                    }
                } else if (xIndex == 2) {
                    if (col4Exists) {
                        xIndex = 3;
                        if (keyItems.size() % 2 == 1) {
                            //only need to check for odd edge case
                            if (yIndex == keyItems.size() / 2) {
                                yIndex--;
                            }
                        }
                    } else if (col1Exists) {
                        xIndex = 0;
                    }
                } else if (xIndex == 3) {
                    if (col1Exists) {
                        xIndex = 0;
                        if (yIndex > regularItems.size() / 2 - 1 ) {
                            yIndex = regularItems.size() / 2 - 1;
                        }
                    } else {
                        xIndex = 2;
                    }
                }
                break;
            case "up":
                if ((xIndex == 0 && regularItems.size() < 3) || (xIndex == 1 && regularItems.size() < 4)
                        || (xIndex == 2 && keyItems.size() < 3) || (xIndex == 3 && keyItems.size() < 4)) {
                    return;
                }
                if (yIndex == 0) {
                    if (xIndex == 0) {
                        if (regularItems.size() % 2 == 0) {
                            yIndex = regularItems.size() / 2 - 1;
                        } else {
                            yIndex = regularItems.size() / 2;
                        }
                    } else if (xIndex == 1) {
                        yIndex = regularItems.size() / 2 - 1;
                    } else if (xIndex == 2) {
                        if (keyItems.size() % 2 == 0) {
                            yIndex = keyItems.size() / 2 - 1;
                        } else {
                            yIndex = keyItems.size() / 2;
                        }
                    } else if (xIndex == 3) {
                        yIndex = keyItems.size() / 2 - 1;
                    }
                } else {
                    yIndex--;
                }
                break;
            case "down":
                if ((xIndex == 0 && regularItems.size() < 3) || (xIndex == 1 && regularItems.size() < 4)
                        || (xIndex == 2 && keyItems.size() < 3) || (xIndex == 3 && keyItems.size() < 4)) {
                    return;
                }
                if (xIndex == 0) {
                    if (yIndex >= Math.ceil(regularItems.size() / 2.0f) - 1) {
                        yIndex = 0;
                    } else {
                        yIndex++;
                    }
                } else if (xIndex == 1) {
                    if (yIndex == regularItems.size() / 2 - 1) {
                        yIndex = 0;
                    } else {
                        yIndex++;
                    }
                } else if (xIndex == 2) {
                    if (yIndex >= Math.ceil(keyItems.size() / 2.0f) - 1) {
                        yIndex = 0;
                    } else {
                        yIndex++;
                    }
                } else if (xIndex == 3) {
                    if (yIndex == keyItems.size() / 2 - 1) {
                        yIndex = 0;
                    } else {
                        yIndex++;
                    }
                }
                break;
        }
    }

    private void selectItem(String selectedItem) {
        Rectangle interactionRectangle = handler.getPlayer().getInteractionRectangle();
        boolean found = false;
        for (Entity e : handler.getActiveWorld().getEntityManager().getEntities()) {
            if (interactionRectangle.intersects(e.getCollisionBounds(0,0))) {
                System.out.println("Interaction with " + e);
                found = e.itemInteraction(selectedItem);
                if (found) {
                    break;
                }
            }
        }
        if (found) {
            System.out.println("Found");
        } else {
            if (!items.get(selectedItem).useItem()) {
                System.out.println("Not found");
                showItemSelectedTextbox = true;
                itemSelectedTextbox = new TextboxHandler(handler, Assets.serif, "That item can't be used here.", null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDefault, null, 50, true, true);
            }
        }
        open = false;
        handler.setInMenu(false);
        handler.setGamePaused(false);
    }

    private String getItemNameAtIndex() {
        switch (xIndex) {
            case 0:
                return regularItems.get(yIndex * 2);
            case 1:
                return regularItems.get(yIndex * 2 + 1);
            case 2:
                return keyItems.get(yIndex * 2);
            case 3:
                return keyItems.get(yIndex * 2  + 1);
        }
        return null;
    }

    private String getUniqueItemNameAtIndex() {
        switch (xIndex) {
            case 0:
                return regularUniqueNames.get(yIndex * 2);
            case 1:
                return regularUniqueNames.get(yIndex * 2 + 1);
            case 2:
                return keyUniqueNames.get(yIndex * 2);
            case 3:
                return keyUniqueNames.get(yIndex * 2  + 1);
        }
        return null;
    }

    public void render(Graphics g) {
        if (firstTime) {
            firstTime = false;
            initFonts(g);
        }
        if (open) {
            //debug
            g.drawImage(Assets.inventory, 0, 0, handler.getWidth(), handler.getHeight(), null);
            g.setColor(Color.CYAN);
            g.drawRect(headersRect.x, headersRect.y, headersRect.width, headersRect.height);
            g.setColor(Color.GREEN);
            g.drawRect(itemsRect.x, itemsRect.y, itemsRect.width, itemsRect.height);
            g.setColor(Color.MAGENTA);
            g.drawRect(pictureRectBorderless.x, pictureRectBorderless.y, pictureRectBorderless.width, pictureRectBorderless.height);
            g.setColor(Color.yellow);
            g.drawRect(descriptionRect.x, descriptionRect.y, descriptionRect.width, descriptionRect.height);

            //business logic
            drawSectionHeaders(g);
            if (!regularItems.isEmpty() || !keyItems.isEmpty()) {
                drawText(g);
                writeItemDescription(g);
                drawItemPreview(g, pictureRectBorderless);
            }
        } else {
            if (showItemSelectedTextbox) {
                if (!itemSelectedTextbox.isFinished()) {
                    itemSelectedTextbox.render(g);
                } else {
                    showItemSelectedTextbox = false;
                }
            }
        }
        for (Item item : items.values()) {
            item.render(g);
        }
    }

    private void drawSectionHeaders(Graphics g) {
        Rectangle itemsHeaderRect = new Rectangle(headersRect);
        itemsHeaderRect.width = itemsHeaderRect.width / 2;
        g.setColor(Color.WHITE);
        GeneralUtils.drawCenteredString(g, "Items", itemsHeaderRect, headerFont);
        Rectangle keyItemsHeaderRect = new Rectangle(headersRect);
        keyItemsHeaderRect.width = keyItemsHeaderRect.width / 2;
        keyItemsHeaderRect.x = keyItemsHeaderRect.width;
        GeneralUtils.drawCenteredString(g, "Key Items", keyItemsHeaderRect, headerFont);
    }

    private void drawText(Graphics g) {
        //build our main item rectangles
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
                itemName.height /= 12;
                itemName.height -= borderWidth * yScale;
                itemName.x += 1.5 * borderWidth * xScale;
                itemName.width -= 2 * (borderWidth * xScale);
                itemName.y += i / 2 * itemName.height + (borderWidth * yScale);
                if (yIndex == Math.floorDiv(i, 2)) {
                    //yIndex matches our row, and we know we're in the first or third column since i is even
                    if (xIndex == 0) {
                        g.drawImage(Assets.inventoryHighlight, itemName.x, itemName.y, itemName.width, itemName.height, null);
                    }
                }
                GeneralUtils.drawCenteredString(g, regularItems.get(i), itemName, FontUtils.scaleFontDown(regularItems.get(i), regularItemsCol1, g, itemFont));
            } else {
                itemName = new Rectangle(regularItemsCol2);
                itemName.height /= 12;
                itemName.height -= borderWidth * yScale;
                itemName.x += 1.5 * borderWidth * xScale;
                itemName.width -= 2 * (borderWidth * xScale);
                itemName.y += i / 2 * itemName.height + (borderWidth * yScale);
                if (yIndex == Math.floorDiv(i, 2)) {
                    //yIndex matches our row, and we know we're in the second or fourth column since i is odd
                    if (xIndex == 1) {
                        g.drawImage(Assets.inventoryHighlight, itemName.x, itemName.y, itemName.width, itemName.height, null);
                    }
                }
                GeneralUtils.drawCenteredString(g, regularItems.get(i), itemName, FontUtils.scaleFontDown(regularItems.get(i), regularItemsCol2, g, itemFont));
            }
        }

        for (int i = 0; i < keyItems.size(); i++) {
            Rectangle itemName;
            if (i % 2 == 0) {
                itemName = new Rectangle(keyItemsCol1);
                itemName.height /= 12;
                itemName.height -= borderWidth * yScale;
                itemName.x += 1.5 * borderWidth * xScale;
                itemName.width -= 2 * (borderWidth * xScale);
                itemName.y += i / 2 * itemName.height + (borderWidth * yScale);
                if (yIndex == Math.floorDiv(i, 2)) {
                    //yIndex matches our row, and we know we're in the first or third column since i is even
                    if (xIndex == 2) {
                        g.drawImage(Assets.inventoryHighlight, itemName.x, itemName.y, itemName.width, itemName.height, null);
                    }
                }
                GeneralUtils.drawCenteredString(g, keyItems.get(i), itemName, FontUtils.scaleFontDown(keyItems.get(i), keyItemsCol1, g, itemFont));
            } else {
                itemName = new Rectangle(keyItemsCol2);
                itemName.height /= 12;
                itemName.height -= borderWidth * yScale;
                itemName.x += 1.5 * borderWidth * xScale;
                itemName.width -= 2 * (borderWidth * xScale);
                itemName.y += i / 2 * itemName.height + (borderWidth * yScale);
                if (yIndex == Math.floorDiv(i, 2)) {
                    //yIndex matches our row, and we know we're in the second or fourth column since i is odd
                    if (xIndex == 3) {
                        g.drawImage(Assets.inventoryHighlight, itemName.x, itemName.y, itemName.width, itemName.height, null);
                    }
                }
                GeneralUtils.drawCenteredString(g, keyItems.get(i), itemName, FontUtils.scaleFontDown(keyItems.get(i), keyItemsCol2, g, itemFont));
            }
        }

    }

    private void writeItemDescription(Graphics g) {
        String uniqueName = getUniqueItemNameAtIndex();
        if (null != uniqueName) {
            Item item = items.get(uniqueName);
            if (null != item) {
                String itemDescription = item.getDescription();
                if (null != itemDescription && !itemDescription.equals("")) {
                    String[] words = item.getDescription().split(" ");
                    Rectangle r = new Rectangle(descriptionRect);
                    r.height /= 9;
                    r.x += xScale * borderWidth / 2 + 3;
                    r.width -= xScale * (3 * borderWidth / 2) + 6;
                    r.y += yScale * borderWidth / 2 + 3;
                    Font descriptionFont = FontUtils.scaleFontDown(itemDescription,
                            new Rectangle(r.x, r.y, r.width * 8, r.height), g, itemFont);
                    FontMetrics descriptionMetrics = g.getFontMetrics(descriptionFont);
                    r.y += descriptionMetrics.getHeight();
                    StringBuilder phrase = new StringBuilder();
                    HashMap<Rectangle, String> descriptionRects = new HashMap<>();
                    for (String word : words) {
                        if (wrapNextWord(phrase + word, r, g, itemFont)) {
                            descriptionRects.put(new Rectangle(r), phrase.toString());
                            phrase = new StringBuilder().append(word).append(" ");
                            r.y += descriptionMetrics.getHeight();
                        } else {
                            phrase.append(word).append(" ");
                        }
                    }
                    if (!phrase.toString().isEmpty()) {
                        descriptionRects.put(new Rectangle(r), phrase.toString());
                    }
                    for (Map.Entry<Rectangle, String> entry : descriptionRects.entrySet()) {
                        String line = entry.getValue();
                        Rectangle bounds = entry.getKey();
                        g.setFont(descriptionFont.deriveFont(Font.ITALIC));
                        g.drawString(line, bounds.x, bounds.y);
                    }
                }
            }
        }
    }

    private void drawItemPreview(Graphics g, Rectangle rectangle) {
        int xStart, yStart, pictureSize, offset = 30;
        if (rectangle.width >= rectangle.height) {
            pictureSize = (int) (rectangle.height - (xScale * offset));
            xStart = rectangle.x + (rectangle.width - pictureSize) / 2;
            yStart = rectangle.y + (int) (yScale * (offset / 2));
        } else {
            pictureSize = rectangle.width;
            yStart = (int) (((rectangle.y + offset) / 2) * yScale);
            xStart = rectangle.x + offset / 2;
        }
        String uniqueName = getUniqueItemNameAtIndex();
        if (null != uniqueName) {
            Item item = items.get(uniqueName);
            if (null != item) {
                BufferedImage image = item.getPreviewImage();
                if (null != image) {
                    g.drawImage(items.get(uniqueName).getPreviewImage(), xStart, yStart, pictureSize, pictureSize, null);
                }
            }
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
    }

    private boolean wrapNextWord(String text, Rectangle rect, Graphics g, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        return textWidth >= rect.width;
    }

    public void addItem(Item item) {
        if (item.getItemType().equals(REGULAR_ITEM)) {
            regularItems.add(item.getItemName());
            regularUniqueNames.add(item.getUniqueName());
        } else if (item.getItemType().equals(KEY_ITEM)) {
            keyItems.add(item.getItemName());
            keyUniqueNames.add(item.getUniqueName());
        }
        items.put(item.getUniqueName(), item);
    }

    public void removeItem(String uniqueName, String itemType) {
        if (itemType.equals(REGULAR_ITEM)) {
            regularItems.remove(regularUniqueNames.indexOf(uniqueName));
            regularUniqueNames.remove(uniqueName);
        } else if (itemType.equals(KEY_ITEM)) {
            keyItems.remove(keyUniqueNames.indexOf(uniqueName));
            keyUniqueNames.remove(uniqueName);
        }
        items.remove(uniqueName);
    }

    public Item getItemByUniqueName(String name) {
        return items.getOrDefault(name, null);
    }

    public Item getItemByGenericName(String name, String itemType) {
        if (itemType.equals(REGULAR_ITEM)) {
            for (int i = 0; i < regularItems.size(); i++) {
                if (regularItems.get(i).equals(name)) {
                    return items.get(regularUniqueNames.get(i));
                }
            }
        } else if (itemType.equals(KEY_ITEM)) {
            for (int i = 0; i < keyItems.size(); i++) {
                if (keyItems.get(i).equals(name)) {
                    return items.get(keyUniqueNames.get(i));
                }
            }
        }
        return null;
    }
}
