package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlantVase extends StaticEntity {

    private int style;
    public static final int STYLE_PLANT_1 = 0, STYLE_FLOWERS = 1, STYLE_PLANT_2 = 2;
    private float xScale, yScale;

    public PlantVase(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        if (style == STYLE_PLANT_1) {
            xScale = (float) width / Assets.plantVasePlant1.getWidth();
            yScale = (float) height / Assets.plantVasePlant1.getHeight();
        } else if (style == STYLE_FLOWERS) {
            xScale = (float) width / Assets.plantVaseFlowers.getWidth();
            yScale = (float) height / Assets.plantVaseFlowers.getHeight();
        } else if (style == STYLE_PLANT_2) {
            xScale = (float) width / Assets.plantVasePlant2.getWidth();
            yScale = (float) height / Assets.plantVasePlant2.getHeight();
        }
        bounds.x = 0;
        bounds.width = width;
        bounds.y = height - (int) (23 * yScale);
        bounds.height = (int) (23 * yScale);
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
            case STYLE_PLANT_1:
                image = Assets.plantVasePlant1;
                break;
            case STYLE_FLOWERS:
                image = Assets.plantVaseFlowers;
                break;
            case STYLE_PLANT_2:
                image = Assets.plantVasePlant2;
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
