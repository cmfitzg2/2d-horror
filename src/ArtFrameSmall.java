import java.awt.*;
import java.awt.image.BufferedImage;

public class ArtFrameSmall extends StaticEntity {
    private BufferedImage artwork;

    public ArtFrameSmall(Handler handler, float x, float y, int width, int height, BufferedImage artwork) {
        super(handler, x, y, width, height);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 48;
        bounds.height = 48;
        this.artwork = artwork;
    }

    @Override
    public void tick() {
        if (handler.getFlags().isViewingArt()) {
            if (handler.getKeyManager().z) {
                if (!handler.getKeyManager().isStillHoldingZ()) {
                    handler.getKeyManager().setStillHoldingZ(true);
                    handler.getFlags().setViewingArt(false);
                    handler.setPlayerFrozen(false);
                    isInteracting = false;
                }
            }
            if (handler.getKeyManager().esc) {
                if (!handler.getKeyManager().isStillHoldingEsc()) {
                    handler.getKeyManager().setStillHoldingEsc(true);

                    if (handler.getFlags().isViewingArt()) {
                        handler.getFlags().setViewingArt(false);
                        handler.setPlayerFrozen(false);
                        isInteracting = false;
                    }
                }
            }
        }
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void render(Graphics g) {
        if (!handler.getFlags().isViewingArt()) {
            g.drawImage(Assets.artFrameSmall, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
        g.setColor(Color.RED);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public void postRender(Graphics g) {
        if (handler.getFlags().isViewingArt()) {
            handler.getScreenOverlay().drawArt(g, artwork);
        }
    }

    @Override
    public void die() {

    }

    @Override
    public void interactedWith() {
        handler.getFlags().setViewingArt(true);
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
