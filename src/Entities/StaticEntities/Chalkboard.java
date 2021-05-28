package Entities.StaticEntities;

import Graphics.Assets;
import Textboxes.TextboxHandler;
import Variables.GeneralConstants;
import Variables.Handler;

import java.awt.*;

public class Chalkboard extends StaticEntity {

    private int style;
    private TextboxHandler textboxHandler;
    public static final int STYLE_INITIAL = 0, STYLE_DRAWN = 1;

    public Chalkboard(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        this.style = style;
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
        if (null != textboxHandler && !textboxHandler.isFinished()) {
            textboxHandler.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.chalkboard[style], (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public boolean interactedWith() {
        if (!handler.getFlags().isClassroomCutscene1()) {
            textboxHandler = new TextboxHandler(handler, Assets.playerThinkingFont,
                    "Definitely one of the worst things I've ever woken up to.", null, GeneralConstants.defaultTextSpeed,
                    Color.WHITE, null, Assets.textboxDefault, null, 50, true, true);
            textboxHandler.setActive(true);
            return true;
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

    public void setStyle(int style) {
        this.style = style;
    }
}
