package Variables;

import Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Flags {
    private boolean visionLimited, viewingArt, inPuzzle, prologue, cutsceneActive;
    private Handler handler;
    private List<Boolean> flags;
    private final int prologueFlag = 0;
    private final int friendEncounter1Flag = 1;

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
        return flags.get(prologueFlag);
    }

    public void setPrologue(boolean prologue) {
        flags.set(prologueFlag, prologue);
    }

    public boolean isFriendEncounter1() {
        return flags.get(friendEncounter1Flag);
    }

    public void setFriendEncounter1(boolean friendEncounter1) {
        flags.set(friendEncounter1Flag, friendEncounter1);
    }

    public boolean isCutsceneActive() {
        return cutsceneActive;
    }

    public void setCutsceneActive(boolean cutsceneActive) {
        this.cutsceneActive = cutsceneActive;
    }
}
