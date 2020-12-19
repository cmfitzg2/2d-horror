package Worlds;

import Variables.Handler;

import java.awt.*;
import java.util.HashMap;

public class WorldManager {

    private Handler handler;
    private HashMap<Integer, World> worlds;
    private World activeWorld;

    public WorldManager(Handler handler, World1 world1) {
        this.handler = handler;
        worlds = new HashMap<>();
        worlds.put(1, world1);
        worlds.put(2, new World2(handler, "res/worlds/world2.txt", 2));
        worlds.put(3, new World3(handler, "res/worlds/world3.txt", 3));
        setActiveWorld(world1);
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
