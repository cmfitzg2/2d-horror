package Textboxes;

/**
 * i can't decide if i want to do things this way or not
 * the idea of this class would be to have one central location for all entity messages
 * fetched by UID + a message index
 */
public class EntityMessages {
    private final String error = "this message should not appear";

    public String getTextboxMessage(String uniqueName, int messageNumber) {
        switch (uniqueName) {
            case "Player":
                return error;
        }
        return "";
    }
}
