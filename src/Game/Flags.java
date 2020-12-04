package Game;

public class Flags {
    private boolean visionLimited, viewingArt;
    private Handler handler;

    public Flags(Handler handler) {
        this.handler = handler;
    }

    public boolean isVisionLimited() {
        return visionLimited;
    }

    public void setVisionLimited(boolean visionLimited) {
        this.visionLimited = visionLimited;
    }

    public boolean isViewingArt() {
        return viewingArt;
    }

    public void setViewingArt(boolean viewingArt) {
        this.viewingArt = viewingArt;
        if (viewingArt) {
            handler.setPlayerFrozen(true);
        } else {
            handler.setPlayerFrozen(false);
        }
    }
}
