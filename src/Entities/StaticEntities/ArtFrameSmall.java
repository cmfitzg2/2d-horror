package Entities.StaticEntities;

import Utils.GeneralUtils;
import Variables.Handler;
import Items.Inventory;
import Items.Item;
import Textboxes.TextboxHandler;
import Graphics.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ArtFrameSmall extends StaticEntity {
    private String message;
    private Font textboxFont;
    String[] options = {"Yes", "No"};
    String description = "A painting taken from an art gallery";
    BufferedImage previewImage;
    TextboxHandler textboxHandler;
    Inventory inventory;
    private boolean viewingTextbox;
    private boolean empty, open;
    private boolean firstTime = true;

    public ArtFrameSmall(Handler handler, float x, float y, int width, int height, String uniqueName, String description, BufferedImage previewImage) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 48;
        bounds.height = 48;
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
        this.previewImage = previewImage;
        textboxFont = Assets.textboxDefault.deriveFont(Font.ITALIC, 28.0f);
    }

    @Override
    public void tick() {
        if (firstTime) {
            inventory = handler.getPlayer().getInventory();
            firstTime = false;
        }
        if (open) {
            if (viewingTextbox) {
                textboxHandler.tick();
                if (textboxHandler.isFinished()) {
                    textboxCallback(textboxHandler.getOptionSelected());
                }
            }
            if (handler.getKeyManager().z) {
                if (!handler.getKeyManager().isStillHoldingZ()) {
                    handler.getKeyManager().setStillHoldingZ(true);
                    if (!viewingTextbox) {
                        viewingTextbox = true;
                        if (handler.getPlayer().getInventory().contains("Painting")) {
                            if (!empty) {
                                message = "Replace the painting in your inventory with this painting?";
                            } else {
                                message = "Place the painting in your inventory here?";
                            }
                        } else {
                            message = "Take the painting?";
                        }
                        textboxHandler = new TextboxHandler(handler, textboxFont, message, options, 2, Color.WHITE, Assets.portrait);
                    }
                }
            }
            if (handler.getKeyManager().esc) {
                if (!handler.getKeyManager().isStillHoldingEsc()) {
                    handler.getKeyManager().setStillHoldingEsc(true);
                    handler.getFlags().setViewingArt(false);
                    handler.setPlayerFrozen(false);
                    isInteracting = false;
                    viewingTextbox = false;
                    open = false;
                }
            }
        }
    }

    public void textboxCallback(String option) {
        if (option.equals("Yes")) {
            if (!inventory.contains("Painting")) {
                //we are definitely taking the painting from the wall
                inventory.addItem(new Item("Painting", Inventory.REGULAR_ITEM, description, uniqueName, previewImage));
                empty = true;
            } else {
                Item inventoryPainting = inventory.getItemByGenericName("Painting", Inventory.REGULAR_ITEM);
                if (null != inventoryPainting) {
                    String inventoryPaintingName = inventoryPainting.getUniqueName();
                    String inventoryPaintingDescription = inventoryPainting.getDescription();
                    BufferedImage inventoryPreviewImage = inventoryPainting.getPreviewImage();
                    if (empty) {
                        //putting the painting in our inventory onto this empty frame
                        description = inventoryPaintingDescription;
                        inventory.removeItem(uniqueName, Inventory.REGULAR_ITEM);
                        empty = false;
                    } else {
                        //swapping the one in our inventory with this one
                        inventoryPainting.setUniqueName(uniqueName);
                        inventoryPainting.setDescription(description);
                        inventoryPainting.setPreviewImage(previewImage);
                        description = inventoryPaintingDescription;
                        previewImage = inventoryPreviewImage;
                    }
                    uniqueName = inventoryPaintingName;
                }
            }
        }
        handler.getFlags().setViewingArt(false);
        handler.setPlayerFrozen(false);
        open = false;
        isInteracting = false;
        viewingTextbox = false;
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void render(Graphics g) {
        if (!open) {
            if (!empty) {
                g.drawImage(Assets.artFrameSmall, (int) (x - handler.getGameCamera().getxOffset()),
                        (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            } else {
                g.drawImage(Assets.artFrameSmallEmpty, (int) (x - handler.getGameCamera().getxOffset()),
                        (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            }
        }
    }

    @Override
    public void postRender(Graphics g) {
        if (open) {
            if (!empty) {
                handler.getScreenOverlay().drawArt(g, GeneralUtils.getArtworkByName(uniqueName));
            } else {
                handler.getScreenOverlay().drawArt(g, Assets.artFrame);
            }
            if (viewingTextbox) {
                textboxHandler.render(g);
            }
        }
    }

    @Override
    public void die() {

    }

    @Override
    public void interactedWith() {
        handler.getFlags().setViewingArt(true);
        open = true;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
