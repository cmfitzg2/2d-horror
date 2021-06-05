package Entities.StaticEntities;

import Entities.Creatures.Player;
import Entities.Entity;
import Graphics.Animation;
import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class ItemSparkle extends StaticEntity {

    private Animation twinkle;
    private float xScale, yScale;

    public ItemSparkle(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 0;
        bounds.height = 0;
        twinkle = new Animation(100, Assets.itemSparkle);
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {
        if (!handler.getFlags().isViewingArt() && !handler.getFlags().isInPuzzle() && !handler.isInMenu() && !handler.getFlags().isHideEffects()) {
            g.drawImage(twinkle.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {
        twinkle.tick();
    }

    @Override
    public void render(Graphics g) {

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
        if (e instanceof Player) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean customRenderVsEntity() {
        return true;
    }
}
