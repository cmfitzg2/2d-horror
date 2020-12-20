package Worlds;

import Entities.StaticEntities.Door1;
import Entities.StaticEntities.Hole;
import Variables.Handler;

public class World3 extends World {

    public World3(Handler handler, String path, int id) {
        super(handler, path, id, null);
    }

    @Override
    public void checkLoadZones() {

    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Hole(handler, 420, 420, 64, 64, "Hole 3", handler.getWorldManager().getWorld(2), 100, 100));
        entityManager.addEntity(new Door1(handler, 960, 576, 64, 96, "Door1", handler.getWorldManager().getWorld(2), 900, 900, true, true));
    }

    @Override
    protected void load() {
        handler.getFlags().setVisionLimited(false);
    }
}
