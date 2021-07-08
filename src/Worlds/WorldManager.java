package Worlds;

import Entities.Entity;
import Entities.StaticEntities.Fireplace;
import Entities.StaticEntities.Furnace;
import Entities.StaticEntities.TableLamp;
import Items.Item;
import Items.Lighter;
import Variables.Flags;
import Variables.Handler;
import Worlds.MCHouse.*;
import Worlds.MansionInterior.L1.*;
import Worlds.MansionInterior.L2.*;
import Worlds.MansionInterior.L3.*;
import Worlds.Overworld.*;
import Worlds.School.*;

import java.awt.*;
import java.util.HashMap;

public class WorldManager {

    private Handler handler;
    private HashMap<Integer, World> worlds;
    private World activeWorld;
    public static final int MC_HOUSE_1_ID = 1, MC_HOUSE_2_ID = 2, OVERWORLD_1_ID = 3, SCHOOL_1_ID = 4, CLASSROOM_1_ID = 5,
            BATHROOM_1_ID = 6, BATHROOM_2_ID = 7, MANSION_EXTERIOR_ID = 8, MANSION_L3_ROOM_1_ID = 10,
            MANSION_L2_ROOM_1_ID = 9, MANSION_L2_ROOM_2_ID = 11, MANSION_L2_ROOM_3_ID = 12, MANSION_L2_ROOM_4_ID = 13,
            MANSION_L1_ROOM_1_ID = 14, MANSION_L1_ROOM_2_ID = 15, MANSION_L1_ROOM_3_ID = 16, MANSION_L1_ROOM_4_ID = 17;

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
        worlds.putIfAbsent(MANSION_EXTERIOR_ID, new MansionExterior(handler, MANSION_EXTERIOR_ID, null));
        worlds.putIfAbsent(MANSION_L2_ROOM_1_ID, new MansionL2Room1(handler, MANSION_L2_ROOM_1_ID, null));
        worlds.putIfAbsent(MANSION_L3_ROOM_1_ID, new MansionL3Room1(handler, MANSION_L3_ROOM_1_ID, null));
        worlds.putIfAbsent(MANSION_L2_ROOM_2_ID, new MansionL2Room2(handler, MANSION_L2_ROOM_2_ID, null));
        worlds.putIfAbsent(MANSION_L2_ROOM_3_ID, new MansionL2Room3(handler, MANSION_L2_ROOM_3_ID, null));
        worlds.putIfAbsent(MANSION_L2_ROOM_4_ID, new MansionL2Room4(handler, MANSION_L2_ROOM_4_ID, null));
        worlds.putIfAbsent(MANSION_L1_ROOM_1_ID, new MansionL1Room1(handler, MANSION_L1_ROOM_1_ID, null));
        worlds.putIfAbsent(MANSION_L1_ROOM_2_ID, new MansionL1Room2(handler, MANSION_L1_ROOM_2_ID, null));
        worlds.putIfAbsent(MANSION_L1_ROOM_3_ID, new MansionL1Room3(handler, MANSION_L1_ROOM_3_ID, null));
        worlds.putIfAbsent(MANSION_L1_ROOM_4_ID, new MansionL1Room4(handler, MANSION_L1_ROOM_4_ID, null));
        setActiveWorld(firstWorld);
    }

    public void tick() {
        activeWorld.tick();
        checkLightSources();
    }

    private void checkLightSources() {
        int ambientLight = handler.getPlayer().getAmbientLight();
        int timeOfDay = handler.getFlags().getTimeOfDay();
        boolean found = false;
        Lighter lighter = (Lighter) handler.getPlayer().getInventory().getItemByUniqueName(Item.LIGHTER_UID);
        for (Entity e : activeWorld.getEntityManager().getEntities()) {
            //check for light sources, but break once we find one (they don't stack)
            if (e instanceof TableLamp && !handler.getFlags().isPowerOut()) {
                if (((TableLamp) e).isLit()) {
                    if (lighter != null && lighter.isActive()) {
                        lighter.setActive(false);
                    }
                    if (handler.getFlags().isVisionLimited()) {
                        handler.getFlags().setVisionLimited(false);
                    }
                    if (ambientLight > 0 && ambientLight == timeOfDay) {
                        handler.getPlayer().setAmbientLight(ambientLight - 1);
                    }
                    found = true;
                    break;
                }
            } else if (e instanceof Fireplace) {
                if (((Fireplace) e).isLit()) {
                    if (lighter != null && lighter.isActive()) {
                        lighter.setActive(false);
                    }
                    if (handler.getFlags().isVisionLimited()) {
                        handler.getFlags().setVisionLimited(false);
                    }
                    if (ambientLight > 0 && ambientLight == timeOfDay) {
                        handler.getPlayer().setAmbientLight(ambientLight - 1);
                    }
                    found = true;
                    break;
                }
            } else if (e instanceof Furnace) {
                if (((Furnace) e).isLit()) {
                    if (lighter != null && lighter.isActive()) {
                        lighter.setActive(false);
                    }
                    if (handler.getFlags().isVisionLimited()) {
                        handler.getFlags().setVisionLimited(false);
                    }
                    if (ambientLight > 0 && ambientLight == timeOfDay) {
                        handler.getPlayer().setAmbientLight(ambientLight - 1);
                    }
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            if (null == lighter || !lighter.isActive()) {
                handler.getPlayer().setAmbientLight(timeOfDay);
            } else {
                handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_VERY_DARK);
                handler.getFlags().setVisionLimited(true);
            }
        }
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
        handler.getPlayer().setAmbientLight(handler.getFlags().getTimeOfDay());
        activeWorld.load();
        checkLightSources();
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
