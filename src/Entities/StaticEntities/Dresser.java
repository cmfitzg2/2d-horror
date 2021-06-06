package Entities.StaticEntities;

import Graphics.Assets;
import Items.Book;
import Items.Inventory;
import Textboxes.TextboxHandler;
import Variables.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dresser extends StaticEntity {

    private int style;
    public static final int STYLE_SHELF_EMPTY = 0, STYLE_SHELF_BOOKS_FULL = 1, STYLE_SHELF_BOOKS_PARTIAL = 2, STYLE_SHELF_DISHES = 3,
            STYLE_CABINET_DRAWERS = 4, STYLE_CABINET_CUPBOARD_1 = 5, STYLE_CABINET_CUPBOARD_2 = 6,  STYLE_CABINET_CUPBOARD_3 = 7,
            STYLE_CABINET_CUPBOARD_4 = 8;
    private List<String> booksL2R3, booksL2R3Order;
    private TextboxHandler textboxHandler;
    private boolean firstTime = true, viewingTextbox = false;
    private Inventory inventory;

    public Dresser(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = height / 2;
        bounds.width = width;
        bounds.height = height / 2;
        this.style = style;
        booksL2R3 = new ArrayList<>();
        booksL2R3Order = new ArrayList<>();
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {

    }

    @Override
    public void finalRender(Graphics g) {
        if (null != textboxHandler && !textboxHandler.isFinished()) {
            textboxHandler.render(g);
        }
    }

    @Override
    public void tick() {
        if (firstTime) {
            inventory = handler.getPlayer().getInventory();
            booksL2R3.add("Take \"The Two Friends\"");
            booksL2R3.add("Take \"Book 1\"");
            booksL2R3.add("Take \"Book 2\"");
            booksL2R3.add("Take \"Book 3\"");
            booksL2R3.add("Exit");
            if (inventory.contains(Book.BOOK_GENERIC_NAME)) {
                booksL2R3.remove(inventory.getItemByGenericName(Book.BOOK_GENERIC_NAME, Inventory.REGULAR_ITEM).getUniqueName());
            }
            booksL2R3Order.add("Take \"The Two Friends\"");
            booksL2R3Order.add("Take \"Book 1\"");
            booksL2R3Order.add("Take \"Book 2\"");
            booksL2R3Order.add("Take \"Book 3\"");
            booksL2R3Order.add("Exit");
            firstTime = false;
        }

        if (viewingTextbox) {
            if (null != textboxHandler) {
                if (!textboxHandler.isFinished()) {
                    textboxHandler.tick();
                } else {
                    textboxCallback(textboxHandler.getOptionSelected());
                }
            }
        }
    }

    private void textboxCallback(String option) {
        if (!option.equals("Exit")) {
            if (inventory.contains(Book.BOOK_GENERIC_NAME)) {
                String bookName = inventory.getItemByGenericName(Book.BOOK_GENERIC_NAME, Inventory.REGULAR_ITEM).getUniqueName();
                if (bookName.equals(Book.BOOK_TWO_FRIENDS_NAME)) {
                    booksL2R3.add("Take \"The Two Friends\"");
                } else if (bookName.equals(Book.BOOK_1_NAME)) {
                    booksL2R3.add("Take \"Book 1\"");
                } else if (bookName.equals(Book.BOOK_2_NAME)) {
                    booksL2R3.add("Take \"Book 2\"");
                } else if (bookName.equals(Book.BOOK_3_NAME)) {
                    booksL2R3.add("Take \"Book 3\"");
                }
                inventory.removeItem(inventory.getItemByGenericName(Book.BOOK_GENERIC_NAME, Inventory.REGULAR_ITEM).getUniqueName(), Inventory.REGULAR_ITEM);
            }
            if (option.equals("Take \"The Two Friends\"")) {
                inventory.addItem(new Book(handler, Book.BOOK_GENERIC_NAME, Inventory.REGULAR_ITEM, Book.BOOK_TWO_FRIENDS_DESCRIPTION, Book.BOOK_TWO_FRIENDS_NAME, Assets.keyInventory));
                booksL2R3.remove("Take \"The Two Friends\"");
            } else if (option.equals("Take \"Book 1\"")) {
                inventory.addItem(new Book(handler, Book.BOOK_GENERIC_NAME, Inventory.REGULAR_ITEM, Book.BOOK_DESCRIPTION_GENERIC, Book.BOOK_1_NAME, Assets.keyInventory));
                booksL2R3.remove("Take \"Book 1\"");
            } else if (option.equals("Take \"Book 2\"")) {
                inventory.addItem(new Book(handler, Book.BOOK_GENERIC_NAME, Inventory.REGULAR_ITEM, Book.BOOK_DESCRIPTION_GENERIC, Book.BOOK_2_NAME, Assets.keyInventory));
                booksL2R3.remove("Take \"Book 2\"");
            } else if (option.equals("Take \"Book 3\"")) {
                inventory.addItem(new Book(handler, Book.BOOK_GENERIC_NAME, Inventory.REGULAR_ITEM, Book.BOOK_DESCRIPTION_GENERIC, Book.BOOK_3_NAME, Assets.keyInventory));
                booksL2R3.remove("Take \"Book 3\"");
            }
            booksL2R3.sort(Comparator.comparingInt(booksL2R3Order::indexOf));
        }
        viewingTextbox = false;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.dressers[style], (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public boolean interactedWith() {
        if (style == STYLE_SHELF_BOOKS_FULL && uniqueName != null) {
            if (uniqueName.equals("mansionL2Room3-1") || uniqueName.equals("mansionL2Room3-2")) {
                String message;
                if (handler.getPlayer().getInventory().contains("Book")) {
                    message = "Take a different book?";
                } else {
                    message = "Take a book?";
                }
                textboxHandler = new TextboxHandler(handler, Assets.textboxFontDefault, message, booksL2R3.toArray(new String[0]), 2, Color.WHITE, null, Assets.textboxDefault, null, 100, true, true);
                textboxHandler.setActive(true);
                viewingTextbox = true;
                return true;
            } else if (uniqueName.equals("mansionL2Room4-dresser1") || uniqueName.equals("mansionL2Room4-dresser2")) {
                textboxHandler = new TextboxHandler(handler, Assets.textboxFontDefault, "There's a variety of books here. \r There appears to be one missing.", null, 2, Color.WHITE, null, Assets.textboxDefault, null, 100, true, true);
                textboxHandler.setActive(true);
                viewingTextbox = true;
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }
}
