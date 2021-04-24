package Cutscenes;

import Variables.Handler;

import java.awt.*;
import java.util.HashMap;

public class CutsceneManager {

    private Handler handler;
    private HashMap<Integer, Cutscene> cutscenes;
    private Cutscene activeCutscene;

    public CutsceneManager(Handler handler) {
        this.handler = handler;
        cutscenes = new HashMap<>();
        cutscenes.put(Cutscene.PROLOGUE, new Prologue(handler));
        cutscenes.put(Cutscene.ACCEPTANCE_ENCOUNTER_1, new AcceptanceEncounter1(handler));
        cutscenes.put(Cutscene.SCHOOL_CUTSCENE_1, new SchoolCutscene1(handler));
        cutscenes.put(Cutscene.CLASSROOM_CUTSCENE_1, new ClassroomCutscene1(handler));
        cutscenes.put(Cutscene.MC_HOUSE_NIGHT_CUTSCENE_1, new MCHouseNightCutscene1(handler));
        cutscenes.put(Cutscene.MC_HOUSE_NIGHT_CUTSCENE_2, new MCHouseNightCutscene2(handler));
        cutscenes.put(Cutscene.MC_HOUSE_NIGHT_CUTSCENE_3, new MCHouseNightCutscene3(handler));
        cutscenes.put(Cutscene.MC_HOUSE_NIGHT_CUTSCENE_4, new MCHouseNightCutscene4(handler));
        cutscenes.put(Cutscene.MC_HOUSE_NIGHT_CUTSCENE_5, new MCHouseNightCutscene5(handler));
        cutscenes.put(Cutscene.OVERWORLD_CUTSCENE_1, new OverworldCutscene1(handler));
        setActiveCutscene(cutscenes.get(Cutscene.PROLOGUE));
    }

    public void tick() {
        activeCutscene.tick();
    }

    public void render(Graphics g) {
        activeCutscene.render(g);
    }

    public Cutscene getActiveCutscene() {
        return activeCutscene;
    }

    public void setActiveCutscene(Cutscene activeCutscene) {
        this.activeCutscene = activeCutscene;
    }

    public Cutscene getCutscene(int id) {
        if (cutscenes.containsKey(id)) {
            return cutscenes.get(id);
        }
        return cutscenes.get(1);
    }
}
