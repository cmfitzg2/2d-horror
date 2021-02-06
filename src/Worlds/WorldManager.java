package Worlds;

import Variables.Handler;

import java.awt.*;
import java.util.HashMap;

public class WorldManager {

    private Handler handler;
    private HashMap<Integer, World> worlds;
    private World activeWorld;
    public static final int MC_HOUSE_1_ID = 1, MC_HOUSE_2_ID = 2, OVERWORLD_1_ID = 3, SCHOOL_1_ID = 4, CLASSROOM_1_ID = 5, BATHROOM_1_ID = 6, BATHROOM_2_ID = 7;

    public WorldManager(Handler handler, World firstWorld) {
        this.handler = handler;
        worlds = new HashMap<>();
        //put the active world in first, then conditionally add every world (to avoid re-adding the first world)
        worlds.put(firstWorld.getId(), firstWorld);
        worlds.putIfAbsent(MC_HOUSE_1_ID, new MCHouse1(handler, MC_HOUSE_1_ID, null));
        worlds.putIfAbsent(MC_HOUSE_2_ID, new MCHouse2(handler, MC_HOUSE_2_ID, null));
        worlds.putIfAbsent(OVERWORLD_1_ID, new Overworld1(handler, OVERWORLD_1_ID, null));
        worlds.putIfAbsent(SCHOOL_1_ID, new School1(handler, SCHOOL_1_ID, null));
        worlds.putIfAbsent(CLASSROOM_1_ID, new Classroom1(handler, CLASSROOM_1_ID, null));
        worlds.putIfAbsent(BATHROOM_1_ID, new Bathroom1(handler, BATHROOM_1_ID, null));
        setActiveWorld(firstWorld);
    }

    public void tick() {
        activeWorld.tick();
    }

    public void render(Graphics g) {
        activeWorld.render(g);
    }

    public World getActiveWorld() {
        return activeWorld;
    }

    public void setActiveWorld(World activeWorld) {
        this.activeWorld = activeWorld;
        handler.setActiveWorld(activeWorld);
        activeWorld.load();
        activeWorld.resetEntityMessages();
        activeWorld.transitioningTo = true;
    }

    public World getWorld(int id) {
        if (worlds.containsKey(id)) {
            return worlds.get(id);
        }
        return worlds.get(1);
    }
}
