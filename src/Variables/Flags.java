package Variables;

import Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Flags {
    private boolean visionLimited, viewingArt, inPuzzle, prologue, cutsceneActive;
    private Handler handler;
    private List<Boolean> flags;

    public Flags(Handler handler) {
        this.handler = handler;
        flags = new ArrayList<>();
        loadFlagsFile();
    }

    private void loadFlagsFile() {
        String file = Utils.loadFileAsString("res/save/flags.sav");
        String[] tokens = file.split("\\s+");
        while (flags.size() < tokens.length) {
            flags.add(null);
        }
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("0")) {
                flags.set(i, false);
            } else if (tokens[i].equals("1")) {
                flags.set(i, true);
            } else {
                flags.set(i, null);
            }
        }
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
        handler.setPlayerFrozen(viewingArt);
    }

    public boolean isInPuzzle() {
        return inPuzzle;
    }

    public void setInPuzzle(boolean inPuzzle) {
        this.inPuzzle = inPuzzle;
        handler.setPlayerFrozen(inPuzzle);
    }

    public boolean isPrologue() {
        return flags.get(0);
    }

    public void setPrologue(boolean prologue) {
        flags.set(0, prologue);
    }

    public boolean isCutsceneActive() {
        return cutsceneActive;
    }

    public void setCutsceneActive(boolean cutsceneActive) {
        this.cutsceneActive = cutsceneActive;
    }
}
