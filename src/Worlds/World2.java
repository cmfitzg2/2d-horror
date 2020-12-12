package Worlds;

import Entities.StaticEntities.ArtFrameSmall;
import Entities.StaticEntities.Hole;
import Variables.Handler;
import Graphics.Assets;

public class World2 extends World {

    public World2(Handler handler, String path, int id) {
        super(handler, path, id, null);
    }

    @Override
    public void checkLoadZones() {
        if (entityManager.getPlayer().getX() > 1000 && entityManager.getPlayer().getY() > 1000) {
            transitionFrom(handler.getWorldManager().getWorld(1), 400, 400);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new ArtFrameSmall(handler, 72, 8, 48, 48, "Solace", null, Assets.solaceInventory));
        entityManager.addEntity(new ArtFrameSmall(handler, 136, 8, 48, 48, "Prophet", null, Assets.prophetInventory));
        entityManager.addEntity(new Hole(handler, 420, 420, 64, 64, "Hole 2", handler.getWorldManager().getWorld(1), 100, 100));
    }
}
