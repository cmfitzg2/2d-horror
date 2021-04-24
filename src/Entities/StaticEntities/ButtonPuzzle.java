package Entities.StaticEntities;

import Graphics.Assets;
import Input.MouseManager;
import Variables.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ButtonPuzzle extends StaticEntity {

    private boolean[][] buttons;
    private BufferedImage background;
    private MouseManager mouseManager;
    private int xStart, yStart, scale = 1, size = 4;

    public ButtonPuzzle(Handler handler, float x, float y, int width, int height, String uniqueName, BufferedImage background) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = 120;
        this.uniqueName = uniqueName;
        this.background = background;
        mouseManager = handler.getMouseManager();
        buttons = new boolean[size][size];
        if (handler.getWidth() < handler.getHeight()) {
            while (handler.getWidth() < Assets.buttonPuzzleOff.getWidth() * size / scale) {
                scale++;
            }
        } else {
            while (handler.getHeight() < Assets.buttonPuzzleOff.getHeight() * size / scale) {
                scale++;
            }
        }
        xStart = handler.getWidth() / 2 - (Assets.buttonPuzzleOff.getWidth() * size) / (2 * scale);
        yStart = handler.getHeight() / 2 - (Assets.buttonPuzzleOff.getHeight() * size) / (2 * scale);
    }

    @Override
    public void tick() {
        if (isInteracting) {
            if (handler.getKeyManager().esc) {
                if (!handler.getKeyManager().isStillHoldingEsc()) {
                    handler.getKeyManager().setStillHoldingEsc(true);
                    isInteracting = false;
                    handler.getFlags().setInPuzzle(isInteracting);
                    for (boolean[] button : buttons) {
                        Arrays.fill(button, false);
                    }
                }
            }
            if (handler.getKeyManager().z) {
                if (!handler.getKeyManager().isStillHoldingZ()) {
                    handler.getKeyManager().setStillHoldingZ(true);
                    isInteracting = false;
                    handler.getFlags().setInPuzzle(isInteracting);
                    for (boolean[] button : buttons) {
                        Arrays.fill(button, false);
                    }
                }
            }
            if (mouseManager.isLeftPressed() && !mouseManager.isStillHoldingLeft()) {
                mouseManager.setStillHoldingLeft(true);
                int mouseX = mouseManager.getMouseX();
                int mouseY = mouseManager.getMouseY();
                if (mouseX < xStart || mouseX > xStart + buttons.length * Assets.buttonPuzzleOn.getWidth() / scale
                        || mouseY < yStart || mouseY > yStart + buttons[0].length * Assets.buttonPuzzleOn.getHeight() / scale) {
                    return;
                } else {
                    click((mouseX - xStart) / (Assets.buttonPuzzleOn.getWidth() / scale),
                            (mouseY - yStart) / (Assets.buttonPuzzleOn.getHeight() / scale));
                }
            }
        }
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void render(Graphics g) {
        if (!isInteracting) {
            g.drawImage(Assets.buttonPuzzlePanel, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
    }

    @Override
    public void postRender(Graphics g) {
        if (isInteracting) {
            drawPuzzle(g, background);
        }
    }

    private void click(int xIndex, int yIndex) {
        buttons[xIndex][yIndex] = !buttons[xIndex][yIndex];
        if (xIndex == 0 || yIndex == 0 || xIndex == buttons.length - 1 || yIndex == buttons[0].length - 1) {
            if (xIndex == 0) {
                buttons[xIndex + 1][yIndex] = !buttons[xIndex + 1][yIndex];
            } else if (xIndex == buttons.length - 1) {
                buttons[xIndex - 1][yIndex] = !buttons[xIndex - 1][yIndex];
            } else {
                buttons[xIndex + 1][yIndex] = !buttons[xIndex + 1][yIndex];
                buttons[xIndex - 1][yIndex] = !buttons[xIndex - 1][yIndex];
            }

            if (yIndex == 0) {
                buttons[xIndex][yIndex + 1] = !buttons[xIndex][yIndex + 1];
            } else if (yIndex == buttons[0].length - 1) {
                buttons[xIndex][yIndex - 1] = !buttons[xIndex][yIndex - 1];
            } else {
                buttons[xIndex][yIndex + 1] = !buttons[xIndex][yIndex + 1];
                buttons[xIndex][yIndex - 1] = !buttons[xIndex][yIndex - 1];
            }
        } else {
            buttons[xIndex - 1][yIndex] = !buttons[xIndex - 1][yIndex];
            buttons[xIndex + 1][yIndex] = !buttons[xIndex + 1][yIndex];
            buttons[xIndex][yIndex - 1] = !buttons[xIndex][yIndex - 1];
            buttons[xIndex][yIndex + 1] = !buttons[xIndex][yIndex + 1];
        }
    }

    private void drawPuzzle(Graphics g, BufferedImage background) {
        g.drawImage(background, 0, 0, handler.getWidth(), handler.getHeight(), null);
        g.drawImage(Assets.buttonPuzzleBorder, xStart - 4 / scale, yStart - 4 / scale,
                buttons.length * Assets.buttonPuzzleOn.getWidth() / scale + 8,
                buttons[0].length * Assets.buttonPuzzleOn.getHeight() / scale + 8, null);
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j]) {
                    g.drawImage(Assets.buttonPuzzleOn, xStart + i * Assets.buttonPuzzleOn.getWidth() / scale,
                            yStart + j * Assets.buttonPuzzleOn.getHeight() / scale, Assets.buttonPuzzleOn.getWidth() / scale,
                            Assets.buttonPuzzleOn.getHeight() / scale, null);
                } else {
                    g.drawImage(Assets.buttonPuzzleOff, xStart + i * Assets.buttonPuzzleOff.getWidth() / scale,
                            yStart + j * Assets.buttonPuzzleOff.getHeight() / scale, Assets.buttonPuzzleOff.getWidth() / scale,
                            Assets.buttonPuzzleOff.getHeight() / scale, null);
                }
            }
        }    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void die() {

    }

    @Override
    public boolean interactedWith() {
        isInteracting = true;
        handler.getFlags().setInPuzzle(true);
        return true;
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
