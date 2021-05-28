package Entities.StaticEntities;

import Cutscenes.CutsceneManager;
import Graphics.Assets;
import Textboxes.TextboxHandler;
import Variables.Handler;

import java.awt.*;

public class Barrel extends StaticEntity {

    private int messageNum = 0;

    public Barrel(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = height / 4;
        bounds.width = width;
        bounds.height = height - height / 4;
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
        g.drawImage(Assets.barrel, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public boolean interactedWith() {
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
