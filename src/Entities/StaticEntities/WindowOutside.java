package Entities.StaticEntities;

import Cutscenes.Cutscene;
import Graphics.Assets;
import Variables.Flags;
import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WindowOutside extends StaticEntity {

    public static int LIGHT = 0, DARK = 1;
    private int style;
    private BufferedImage background;

    public WindowOutside(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height * 2;
        this.style = style;
    }

    @Override
    public void preRender(Graphics g) {
        if (style == LIGHT) {
            g.drawImage(Assets.windowLight, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.windowDark, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
    }

    @Override
    public void postRender(Graphics g) {
        if (style == LIGHT) {
            if (!handler.getFlags().isViewingArt() && !handler.getFlags().isInPuzzle() && !handler.isInMenu() && !handler.getFlags().isHideEffects()) {
                g.drawImage(Assets.yellowLight, (int) (x - handler.getGameCamera().getxOffset() - 32),
                        (int) (y - handler.getGameCamera().getyOffset() - 32), 128, 128, null);
            }
        }
    }

    @Override
    public void finalRender(Graphics g) {
        if (isInteracting) {
            if (null == background) {
                if (handler.getFlags().getTimeOfDay() >= Flags.TIME_OF_DAY_DARK) {
                    g.drawImage(Assets.windowOutsideNight, 0, 0, handler.getWidth(), handler.getHeight(), null);
                } else {
                    g.drawImage(Assets.windowOutsideDay, 0, 0, handler.getWidth(), handler.getHeight(), null);
                }
            } else {
                g.drawImage(background, 0, 0, handler.getWidth(), handler.getHeight(), null);
            }
        }
    }

    @Override
    public void tick() {
        if (isInteracting && !handler.getFlags().isCutsceneActive()) {
            if (!handler.getFlags().isMCHouseNightCutscene4() && handler.getFlags().isMCHouseNightCutscene5()) {
                handler.getFlags().setCutsceneActive(true);
                handler.getCutsceneManager().setActiveCutscene(handler.getCutsceneManager().getCutscene(Cutscene.MC_HOUSE_NIGHT_CUTSCENE_5));
                handler.setPlayerFrozen(true);
            } else if (handler.getKeyManager().z) {
                if (!handler.getKeyManager().isStillHoldingZ()) {
                    handler.getKeyManager().setStillHoldingZ(true);
                    isInteracting = false;
                    handler.setPlayerFrozen(false);
                    handler.getFlags().setHideEffects(false);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        isInteracting = true;
        handler.setPlayerFrozen(true);
        handler.getFlags().setHideEffects(true);
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public void setBackground(BufferedImage background) {
        this.background = background;
    }


}
