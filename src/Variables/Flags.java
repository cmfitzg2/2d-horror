package Variables;

import Items.Item;
import Items.Lighter;
import Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Flags {
    private boolean visionLimited, viewingArt, inPuzzle, hideEffects, prologue, cutsceneActive, powerOut, cameraOverride;
    private Handler handler;
    private List<Boolean> flags;
    private List<Integer> constants;
    private final int prologueFlag = 0;
    private final int acceptanceEncounter1Flag = 1;
    private final int schoolCutscene1Flag = 2;
    private final int classroomCutscene1Flag = 3;
    private final int mcHouseNightCutscene1Flag = 4;
    private final int mcHouseNightCutscene2Flag = 5;
    private final int mcHouseNightCutscene3Flag = 6;
    private final int mcHouseNightCutscene4Flag = 7;
    private final int mcHouseNightCutscene5Flag = 8;
    private final int overworldCutscene1Flag = 9;
    private final int mansionExteriorCutscene1Flag = 10;
    private final int denialMansionCutscene1Flag = 11;
    private final int denialMansionCutscene2Flag = 12;
    private final int timeOfDayIndex = 0;
    public static final int TIME_OF_DAY_BRIGHT = 0, TIME_OF_DAY_SOME_DARK = 1, TIME_OF_DAY_DARK = 2, TIME_OF_DAY_VERY_DARK = 3, TIME_OF_DAY_PITCH_BLACK = 4;

    public Flags(Handler handler) {
        this.handler = handler;
        flags = new ArrayList<>();
        constants = new ArrayList<>();
        loadBooleanFlagsFile();
        loadConstantsFile();
    }

    private void loadBooleanFlagsFile() {
        String file = Utils.loadFileAsString("res/save/boolean-flags.sav");
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

    private void loadConstantsFile() {
        String file = Utils.loadFileAsString("res/save/constants.sav");
        String[] tokens = file.split("\\s+");
        while (constants.size() < tokens.length) {
            constants.add(null);
        }
        for (int i = 0; i < tokens.length; i++) {
            try {
                constants.add(i, Integer.parseInt(tokens[i]));
            } catch (NumberFormatException e) {
                constants.add(i, null);
            }
        }
    }

    public boolean isVisionLimited() {
        return visionLimited;
    }

    public void setVisionLimited(boolean visionLimited) {
        this.visionLimited = visionLimited;
        if (!visionLimited) {
            try {
                Lighter lighter = (Lighter) handler.getPlayer().getInventory().getItemByUniqueName(Item.LIGHTER_UID);
                if (lighter.isActive()) {
                    lighter.setActive(false);
                }
            } catch (NullPointerException e) {

            }
        }
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

    public boolean isAcceptanceEncounter1() {
        return flags.get(acceptanceEncounter1Flag);
    }

    public void setAcceptanceEncounter1(boolean acceptanceEncounter1) {
        flags.set(acceptanceEncounter1Flag, acceptanceEncounter1);
    }

    public boolean isSchoolCutscene1() {
        return flags.get(schoolCutscene1Flag);
    }

    public void setSchoolCutscene1(boolean schoolCutscene1) {
        flags.set(schoolCutscene1Flag, schoolCutscene1);
    }

    public boolean isClassroomCutscene1() {
        return flags.get(classroomCutscene1Flag);
    }

    public void setClassroomCutscene1(boolean classroomCutscene1) {
        flags.set(classroomCutscene1Flag, classroomCutscene1);
    }

    public boolean isMCHouseNightCutscene1() {
        return flags.get(mcHouseNightCutscene1Flag);
    }

    public void setMcHouseNightCutscene1(boolean mcHouseNightCutscene1) {
        flags.set(mcHouseNightCutscene1Flag, mcHouseNightCutscene1);
    }

    public boolean isMCHouseNightCutscene2() {
        return flags.get(mcHouseNightCutscene2Flag);
    }

    public void setMcHouseNightCutscene2(boolean mcHouseNightCutscene2) {
        flags.set(mcHouseNightCutscene2Flag, mcHouseNightCutscene2);
    }

    public boolean isMCHouseNightCutscene3() {
        return flags.get(mcHouseNightCutscene3Flag);
    }

    public void setMcHouseNightCutscene3(boolean mcHouseNightCutscene3) {
        flags.set(mcHouseNightCutscene3Flag, mcHouseNightCutscene3);
    }

    public boolean isMCHouseNightCutscene4() {
        return flags.get(mcHouseNightCutscene4Flag);
    }

    public void setMcHouseNightCutscene4(boolean mcHouseNightCutscene4) {
        flags.set(mcHouseNightCutscene4Flag, mcHouseNightCutscene4);
    }

    public boolean isMCHouseNightCutscene5() {
        return flags.get(mcHouseNightCutscene5Flag);
    }

    public void setMcHouseNightCutscene5(boolean mcHouseNightCutscene5) {
        flags.set(mcHouseNightCutscene5Flag, mcHouseNightCutscene5);
    }

    public boolean isOverworldCutscene1() {
        return flags.get(overworldCutscene1Flag);
    }

    public void setOverworldCutscene1(boolean overworldCutscene1) {
        flags.set(overworldCutscene1Flag, overworldCutscene1);
    }

    public boolean isMansionExteriorCutscene1() {
        return flags.get(mansionExteriorCutscene1Flag);
    }

    public void setMansionExteriorCutscene1(boolean mansionExteriorCutscene1) {
        flags.set(mansionExteriorCutscene1Flag, mansionExteriorCutscene1);
    }

    public boolean isDenialMansionCutscene1() {
        return flags.get(denialMansionCutscene1Flag);
    }

    public void setDenialMansionCutscene1(boolean denialMansionCutscene1) {
        flags.set(denialMansionCutscene1Flag, denialMansionCutscene1);
    }

    public boolean isDenialMansionCutscene2() {
        return flags.get(denialMansionCutscene2Flag);
    }

    public void setDenialMansionCutscene2(boolean denialMansionCutscene2) {
        flags.set(denialMansionCutscene2Flag, denialMansionCutscene2);
    }

    public boolean isCutsceneActive() {
        return cutsceneActive;
    }

    public void setCutsceneActive(boolean cutsceneActive) {
        this.cutsceneActive = cutsceneActive;
    }

    public int getTimeOfDay() {
        return constants.get(timeOfDayIndex);
    }

    public void setTimeOfDay(int timeOfDay) {
        constants.set(timeOfDayIndex, timeOfDay);
    }

    public boolean isPowerOut() {
        return powerOut;
    }

    public void setPowerOut(boolean powerOut) {
        this.powerOut = powerOut;
    }

    public boolean isHideEffects() {
        return hideEffects;
    }

    public void setHideEffects(boolean hideEffects) {
        this.hideEffects = hideEffects;
    }

    public boolean isCameraOverride() {
        return cameraOverride;
    }

    public void setCameraOverride(boolean cameraOverride) {
        this.cameraOverride = cameraOverride;
    }
}
