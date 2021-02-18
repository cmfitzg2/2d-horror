package Items;

import Variables.Flags;
import Variables.Handler;

import java.awt.image.BufferedImage;

public class Lighter extends Item {
    public Lighter(Handler handler, String itemName, String itemType, String description, String uniqueName, BufferedImage previewImage) {
        super(handler, itemName, itemType, description, uniqueName, previewImage);
    }

    @Override
    public boolean useItem() {
        if (handler.getFlags().getTimeOfDay() == Flags.TIME_OF_DAY_PITCH_BLACK) {
            handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_VERY_DARK);
            handler.getFlags().setVisionLimited(true);
            return true;
        }
        return false;
    }
}
