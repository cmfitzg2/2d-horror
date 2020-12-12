package Worlds;

import Entities.Creatures.Player;
import Entities.StaticEntities.ArtFrameSmall;
import Entities.StaticEntities.Hole;
import Variables.Handler;
import Graphics.Assets;
public class World1 extends World {

    public World1(Handler handler, String path, int id) {
        super(handler, path, id, new Player(handler, 100, 100));
    }

    @Override
    public void checkLoadZones() {
        if (entityManager.getPlayer().getX() > 1000 && entityManager.getPlayer().getY() > 1000) {
            transitionFrom(handler.getWorldManager().getWorld(2), 100, 100);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new ArtFrameSmall(handler, 72, 8, 48, 48, "Solace", null, Assets.solacePreview));
        entityManager.addEntity(new ArtFrameSmall(handler, 136, 8, 48, 48, "Prophet", null, Assets.prophetPreview));
        entityManager.addEntity(new Hole(handler, 420, 420, 64, 64, handler.getWorldManager().getWorld(3), 100, 100));
    }
}
