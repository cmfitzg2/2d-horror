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
        cutscenes.put(1, new Prologue(handler));
        cutscenes.put(2, new AcceptanceEncounter1(handler));
        cutscenes.put(3, new SchoolCutscene1(handler));
        setActiveCutscene(cutscenes.get(1));
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
