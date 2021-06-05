package Entities.StaticEntities;

import Entities.Entity;
import Graphics.Assets;
import Tiles.Tile;
import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Couch extends StaticEntity {

    private int style;
    public static final int STYLE_UP = 0, STYLE_DOWN = 1, STYLE_LEFT = 2, STYLE_RIGHT = 3;
    private float xScale, yScale;

    public Couch(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.width = width;
        if (style == STYLE_LEFT) {
            xScale = (float) width / Assets.couchOneLeft.getWidth();
            yScale = (float) height / Assets.couchOneLeft.getHeight();
            bounds.y = (int) (15 * yScale);
            bounds.height = (int) (height - 15 * yScale);
        } else if (style == STYLE_RIGHT) {
            xScale = (float) width / Assets.couchOneRight.getWidth();
            yScale = (float) height / Assets.couchOneRight.getHeight();
            bounds.y = (int) (15 * yScale);
            bounds.height = (int) (height - 15 * yScale);
        } else if (style == STYLE_UP) {
            xScale = (float) width / Assets.couchOneUp.getWidth();
            yScale = (float) height / Assets.couchOneUp.getHeight();
            bounds.y = (int) (4 * yScale);
            bounds.height = (int) (height - 4 * yScale);
        } else if (style == STYLE_DOWN) {
            xScale = (float) width / Assets.couchOneDown.getWidth();
            yScale = (float) height / Assets.couchOneDown.getHeight();
            bounds.y = (int) (14 * yScale);
            bounds.height = (int) (height - 14 * yScale);
        }
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
        BufferedImage couch;
        switch (style) {
            case STYLE_DOWN:
                couch = Assets.couchOneDown;
                break;
            case STYLE_UP:
                couch = Assets.couchOneUp;
                break;
            case STYLE_LEFT:
                couch = Assets.couchOneLeft;
                break;
            default:
                couch = Assets.couchOneRight;
                break;
        }
        g.drawImage(couch, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public boolean interactedWith() {
        if (uniqueName != null && uniqueName.equals("couch-mansionL2Room3") && handler.getFlags().isDenialMansionCutscene1() && !handler.getPlayer().isSitRight()) {
            handler.getPlayer().setSitRight(true);
            handler.getPlayer().setLockX(true);
            handler.getPlayer().setLockY(true);
            handler.getPlayer().setLockZ(true);
            handler.getPlayer().setX(12 * Tile.TILEWIDTH + Assets.playerSitBookRight.getWidth() / 2f);
            handler.getPlayer().setY(13 * Tile.TILEHEIGHT + Assets.sideTableHorizontal.getHeight() - Assets.playerSitBookRight.getHeight() / 2f);
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

    @Override
    public int renderVsEntity(Entity e) {
        if (e.getX() < x + width && e.getX() + e.getWidth() > x && e.getY() + e.getHeight() >= y && e.getY() <= y + height) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean customRenderVsEntity() {
        return true;
    }
}
