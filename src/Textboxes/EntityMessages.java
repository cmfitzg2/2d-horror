package Textboxes;

import Variables.Handler;

/**
 * i can't decide if i want to do things this way or not
 * the idea of this class would be to have one central location for all entity messages
 * fetched by UID + a message index
 */
public class EntityMessages {
    private final String error = "this message should not appear";
    private Handler handler;

    public EntityMessages(Handler handler) {
        this.handler = handler;
    }

    public String getTextboxMessage(String uniqueName, int messageNumber) {
        switch (uniqueName) {
            case "Player":
                return error;
            case "Friend1":
                switch (handler.getActiveWorld().getId()) {
                    case 1:
                        if (messageNumber == 1) {
                            return "hey it's me friend 1! \r just wondering when you think we might be able to get out of here? \r hope to hear back soon! \r love, friend 1. \r P.S. ur gay";
                        } else {
                            return "leave me alone lmao \r lata bitch";
                        }
                    case 2:
                        if (messageNumber == 1) {
                            return "woah! didn't i already see you?";
                        } else {
                            return "this sucks man";
                        }
                }

        }
        return error;
    }
}
