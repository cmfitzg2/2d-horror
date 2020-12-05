package Worlds;

import Entities.Creatures.Player;
import Entities.StaticEntities.ArtFrameSmall;
import Entities.StaticEntities.Hole;
import Game.Handler;
import Graphics.Assets;

import java.awt.*;

public class World1 extends World {

    public World1(Handler handler, String path, int id) {
        super(handler, path, id, new Player(handler, 100, 100));
        entityManager.addEntity(new ArtFrameSmall(handler, 72, 8, 48, 48, "Solace", null, Assets.solacePreview));
        entityManager.addEntity(new ArtFrameSmall(handler, 136, 8, 48, 48, "Prophet", null, Assets.prophetPreview));
        entityManager.addEntity(new Hole(handler, 420, 420, 64, 64));
    }

    @Override
    public void tick() {
        entityManager.tick();
        checkLoadZones();
        if (entityManager.getPlayer().getX() > 1000 && entityManager.getPlayer().getY() > 1000) {
            World world2 = handler.getWorldManager().getWorld(2);
            handler.getWorldManager().setActiveWorld(world2);
        }
    }

    @Override
    public void render(Graphics g) {
        renderTiles(g);
        entityManager.render(g);
    }

    @Override
    public void checkLoadZones() {

    }
}
