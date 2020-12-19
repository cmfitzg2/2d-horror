package Worlds;

import Entities.Creatures.Friend1;
import Entities.Creatures.Player;
import Entities.StaticEntities.ArtFrameSmall;
import Entities.StaticEntities.Hole;
import Variables.Handler;
import Graphics.Assets;
public class World1 extends World {

    public World1(Handler handler, String path, int id) {
        super(handler, path, id, new Player(handler, 100, 100, "Player"));
    }

    @Override
    public void checkLoadZones() {
        if (entityManager.getPlayer().getX() > 1000 && entityManager.getPlayer().getY() > 1000) {
            transitionFrom(handler.getWorldManager().getWorld(2), 100, 100);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new ArtFrameSmall(handler, 72, 8, 48, 48, "Solace", null, Assets.solaceInventory));
        entityManager.addEntity(new ArtFrameSmall(handler, 136, 8, 48, 48, "Prophet", null, Assets.prophetInventory));
        entityManager.addEntity(new Hole(handler, 420, 420, 64, 64, "Hole 1", handler.getWorldManager().getWorld(3), 100, 100));
        entityManager.addEntity(new Friend1(handler, 200, 100, "Friend1"));
    }

    @Override
    protected void load() {
        handler.getFlags().setVisionLimited(true);
    }
}
