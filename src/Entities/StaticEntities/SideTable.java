package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SideTable extends StaticEntity {

    private int style, accent;
    public static final int STYLE_VERTICAL = 0, STYLE_HORIZONTAL = 1;
    public static final int NONE = 0, ACCENT_EMPTY_VASE = 1, ACCENT_SINGLE_FLOWER = 2, ACCENT_BOUQUET = 3;
    private float xScale, yScale;

    public SideTable(Handler handler, float x, float y, int width, int height, String uniqueName, int style, int accent) {
        super(handler, x, y, width, height, uniqueName);
        this.style = style;
        this.accent = accent;
        bounds.x = 0;
        bounds.width = width;
        if (style == STYLE_VERTICAL) {
            xScale = (float) width / Assets.sideTableVertical.getWidth();
            yScale = (float) height / Assets.sideTableVertical.getHeight();
            bounds.y = height / 6;
            bounds.height = height * 5 / 6;
        } else if (style == STYLE_HORIZONTAL) {
            xScale = (float) width / Assets.sideTableHorizontal.getWidth();
            yScale = (float) height / Assets.sideTableHorizontal.getHeight();
            bounds.y = height / 4;
            bounds.height = height * 3 / 4;
        }
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
            case STYLE_VERTICAL:
                image = Assets.sideTableVertical;
                break;
            case STYLE_HORIZONTAL:
                image = Assets.sideTableHorizontal;
                break;
        }
        g.drawImage(image, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        if (accent != NONE) {
            int xPos = 0, yPos = 0;
            int accentWidth = 0, accentHeight = 0;
            BufferedImage accentImage = null;
            switch (accent) {
                case ACCENT_EMPTY_VASE:
                    accentImage = Assets.flowerVaseEmpty;
                    accentWidth = (int) (Assets.flowerVaseEmpty.getWidth() * xScale);
                    accentHeight = (int) (Assets.flowerVaseEmpty.getHeight() * yScale);
                    if (style == STYLE_VERTICAL) {
                        xPos = (int) (2 * xScale);
                        yPos = (int) (-4 * yScale);
                    } else if (style == STYLE_HORIZONTAL) {
                        xPos = (int) (18 * xScale);
                        yPos = (int) (-15 * yScale);
                    }
                    break;
                case ACCENT_SINGLE_FLOWER:
                    accentImage = Assets.flowerVaseSingle;
                    accentWidth = (int) (Assets.flowerVaseSingle.getWidth() * xScale);
                    accentHeight = (int) (Assets.flowerVaseSingle.getHeight() * yScale);
                    if (style == STYLE_VERTICAL) {
                        xPos = (int) (4 * xScale);
                        yPos = (int) (-5 * yScale);
                    } else if (style == STYLE_HORIZONTAL) {
                        xPos = (int) (21 * xScale);
                        yPos = (int) (-14 * yScale);
                    }
                    break;
                case ACCENT_BOUQUET:
                    accentImage = Assets.flowerVaseBouquet;
                    accentWidth = (int) (Assets.flowerVaseBouquet.getWidth() * xScale);
                    accentHeight = (int) (Assets.flowerVaseBouquet.getHeight() * yScale);
                    if (style == STYLE_VERTICAL) {
                        xPos = (int) (-1 * xScale);
                        yPos = (int) (-11 * yScale);
                    } else if (style == STYLE_HORIZONTAL) {
                        xPos = (int) (15 * xScale);
                        yPos = (int) (-22 * yScale);
                    }
                    break;
            }
            g.drawImage(accentImage, (int) (xPos + x - handler.getGameCamera().getxOffset()),
                    (int) (yPos + y - handler.getGameCamera().getyOffset()), accentWidth, accentHeight, null);
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
}
