package Entities.StaticEntities;

import Graphics.Assets;
import Textboxes.TextboxHandler;
import Variables.Handler;

import java.awt.*;

public class TeacherDesk extends StaticEntity {

    TextboxHandler textboxHandler;

    private final String denialText = "Sit down, MC", angerText = "You're too stupid to teach. Sit down, dumbass.",
            bargainingText = "Uhh, MC, I think the teacher will be here soon... \r You should probably go to your seat.",
            depressionText = "Oh, you're teaching today? Cool, maybe I'll pay attention then.",
            acceptanceText = "placeholder";
    private String message = denialText;
    public TeacherDesk(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = height / 4;
        bounds.width = width;
        bounds.height = height - height / 3;
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {

    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.teacherDesk, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {

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
