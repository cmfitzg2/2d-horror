package Textboxes;

import Variables.Handler;
import Worlds.WorldManager;

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
            case "Acceptance":
                return error;
            case "denial-mansionL2Room4":
                if (messageNumber == 1) {
                    return "Oh hey. \r " +
                            "All the doors here seem to be locked, so I'm just killing time while the others look around. \r " +
                            "Seems like this place is somehow even less interesting than I expected.";
                } else if (messageNumber == 2) {
                    return "I found this book on the shelf over there. \r " +
                            "It's called \"The Two Friends\".";
                }
        }
        return error;
    }
}
