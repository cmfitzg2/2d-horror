package Worlds;

import Entities.StaticEntities.ArtFrameSmall;
import Entities.StaticEntities.Hole;
import Game.Handler;
import Graphics.Assets;

import java.awt.*;

public class World2 extends World {

    public World2(Handler handler, String path, int id) {
        super(handler, path, id, null);
        entityManager.addEntity(new ArtFrameSmall(handler, 72, 8, 48, 48, "Solace", null, Assets.solacePreview));
        entityManager.addEntity(new ArtFrameSmall(handler, 136, 8, 48, 48, "Prophet", null, Assets.prophetPreview));
        entityManager.addEntity(new Hole(handler, 420, 420, 64, 64));
    }

    @Override
    public void tick() {
        entityManager.tick();
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
