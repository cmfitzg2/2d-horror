package Entities.StaticEntities;

import Entities.Entity;
import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class BasementKitchen extends StaticEntity {

    private float xScale, yScale;

    public BasementKitchen(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        xScale = (float) width / Assets.basementKitchen.getWidth();
        yScale = (float) height / Assets.basementKitchen.getHeight();
        addBoundingBox(new Rectangle((int) (325 * xScale), (int) (61 * yScale), (int) (160 * xScale), (int) (37 * yScale)));
        addBoundingBox(new Rectangle((int) (485 * xScale), (int) (61 * yScale), (int) (26 * xScale), (int) (148 * yScale)));
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
        g.drawImage(Assets.basementKitchen, (int) (x - handler.getGameCamera().getxOffset()),
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

    @Override
    public int renderVsEntity(Entity e) {
        return 1;
    }

    @Override
    public boolean customRenderVsEntity() {
        return true;
    }
}
