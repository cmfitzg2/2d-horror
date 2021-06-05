package Items;

import Variables.Flags;
import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighter extends Item {

    private boolean active;

    public Lighter(Handler handler, String itemName, String itemType, String description, String uniqueName, BufferedImage previewImage) {
        super(handler, itemName, itemType, description, uniqueName, previewImage);
    }

    @Override
    public boolean useItem() {
        if (handler.getFlags().getTimeOfDay() == Flags.TIME_OF_DAY_PITCH_BLACK) {
            if (active) {
                handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_PITCH_BLACK);
                handler.getFlags().setVisionLimited(false);
                active = false;
            } else {
                if (handler.getPlayer().getAmbientLight() < Flags.TIME_OF_DAY_PITCH_BLACK) {
                    return false;
                }
                handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_VERY_DARK);
                handler.getFlags().setVisionLimited(true);
                active = true;
            }
            handler.setPlayerFrozen(false);
            return true;
        }
        return false;
    }

    @Override
    protected void tick() {

    }

    @Override
    protected void render(Graphics g) {

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
