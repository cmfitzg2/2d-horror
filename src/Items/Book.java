package Items;

import Textboxes.TextboxHandler;
import Variables.GeneralConstants;
import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.Assets;

public class Book extends Item {

    private boolean viewingTextbox = false;
    private TextboxHandler textboxHandler;
    private final String textTwoFriends = "Once there were two friends. \r " +
            "The two friends were the same age, and were next door neighbors. \r " +
            "Ever since they were very young, they had done everything together. \r " +
            "They went to the same school, they played the same games, they laughed at the same jokes. \r " +
            "Their rooms were even identical!";
    public static final String BOOK_GENERIC_NAME = "Book";
    public static final String BOOK_TWO_FRIENDS_NAME = "book-two-friends", BOOK_1_NAME = "book-1", BOOK_2_NAME = "book-2", BOOK_3_NAME = "book-3";
    public static final String BOOK_TWO_FRIENDS_DESCRIPTION = "An old book called \"The Two Friends\" taken from a book shelf in a room on the ground floor.";
    public static final String BOOK_DESCRIPTION_GENERIC = "An old book called taken from a book shelf in a room on the ground floor.";
    public Book(Handler handler, String itemName, String itemType, String description, String uniqueName, BufferedImage previewImage) {
        super(handler, itemName, itemType, description, uniqueName, previewImage);
    }

    @Override
    public boolean useItem() {
        viewingTextbox = true;
        if (uniqueName.equals(BOOK_TWO_FRIENDS_NAME) || uniqueName.equals(BOOK_1_NAME) || uniqueName.equals(BOOK_2_NAME) || uniqueName.equals(BOOK_3_NAME)) {
            if (handler.getPlayer().isSitRight()) {
                handler.getPlayer().setSitBookRight(true);
            }
            switch (uniqueName) {
                case BOOK_TWO_FRIENDS_NAME:
                    textboxHandler = new TextboxHandler(handler, Assets.textboxFontDefault, textTwoFriends, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDefault, null, 50, true, true);
                    return true;
                case BOOK_1_NAME:
                    textboxHandler = new TextboxHandler(handler, Assets.textboxFontDefault, "This is book 1.", null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDefault, null, 50, true, true);
                    return true;
                case BOOK_2_NAME:
                    textboxHandler = new TextboxHandler(handler, Assets.textboxFontDefault, "This is book 2.", null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDefault, null, 50, true, true);
                    return true;
                case BOOK_3_NAME:
                    textboxHandler = new TextboxHandler(handler, Assets.textboxFontDefault, "This is book 3.", null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDefault, null, 50, true, true);
                    return true;
            }
        }
        return false;
    }

    @Override
    protected void tick() {
        if (viewingTextbox) {
            if (null != textboxHandler) {
                if (!textboxHandler.isFinished()) {
                    textboxHandler.tick();
                }
            }
        }
    }

    @Override
    protected void render(Graphics g) {
        if (null != textboxHandler && !textboxHandler.isFinished()) {
            textboxHandler.render(g);
        } else {
            viewingTextbox = false;
            handler.getPlayer().setSitBookRight(false);
        }
    }
}
