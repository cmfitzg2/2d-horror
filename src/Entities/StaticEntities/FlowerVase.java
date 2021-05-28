package Entities.StaticEntities;

import Graphics.Assets;
import Textboxes.TextboxHandler;
import Variables.GeneralConstants;
import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FlowerVase extends StaticEntity {

    private int style;
    public static final int STYLE_EMPTY = 0, STYLE_SINGLE = 1, STYLE_BOUQUET = 2;
    private float xScale, yScale;

    public FlowerVase(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        if (style == STYLE_EMPTY) {
            xScale = (float) width / Assets.flowerVaseEmpty.getWidth();
            yScale = (float) height / Assets.flowerVaseEmpty.getHeight();
        } else if (style == STYLE_SINGLE) {
            xScale = (float) width / Assets.flowerVaseSingle.getWidth();
            yScale = (float) height / Assets.flowerVaseSingle.getHeight();
        } else if (style == STYLE_BOUQUET) {
            xScale = (float) width / Assets.flowerVaseBouquet.getWidth();
            yScale = (float) height / Assets.flowerVaseBouquet.getHeight();
        }
        if (style == STYLE_BOUQUET) {
            bounds.x = (int) (3 * xScale);
            bounds.width = (int) (width - 6 * xScale);
        } else {
            bounds.x = 0;
            bounds.width = width;
        }
        bounds.y = height / 2;
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
        BufferedImage image = null;
        switch (style) {
            case STYLE_EMPTY:
                image = Assets.flowerVaseEmpty;
                break;
            case STYLE_SINGLE:
                image = Assets.flowerVaseSingle;
                break;
            case STYLE_BOUQUET:
                image = Assets.flowerVaseBouquet;
                break;
        }
        if (null != image) {
            g.drawImage(image, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
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

    public void setStyle(int style) {
        this.style = style;
    }
}
