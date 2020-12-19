package Entities.StaticEntities;

import Variables.Handler;

import java.awt.*;
import Graphics.Assets;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 30;
        bounds.y = (int) (height/1.5f);
        bounds.width = width/2;
        bounds.height = (int) (height - height/1.5f);
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
    public void render(Graphics g)
    {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die()
    {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        isInteracting = true;
        isInteracting = false;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
