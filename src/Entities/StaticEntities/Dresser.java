package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class Dresser extends StaticEntity {

    private int style;
    public static final int STYLE_SHELF_EMPTY = 0, STYLE_SHELF_BOOKS_FULL = 1, STYLE_SHELF_BOOKS_PARTIAL = 2, STYLE_SHELF_DISHES = 3,
            STYLE_CABINET_DRAWERS = 4, STYLE_CABINET_CUPBOARD_1 = 5, STYLE_CABINET_CUPBOARD_2 = 6,  STYLE_CABINET_CUPBOARD_3 = 7,
            STYLE_CABINET_CUPBOARD_4 = 8;

    public Dresser(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = height / 2;
        bounds.width = width;
        bounds.height = height / 2;
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

    }

    @Override
    public void tick() {

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
