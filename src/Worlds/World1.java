package Worlds;

import Game.Handler;

import java.awt.*;

public class World1 extends World {

    public World1(Handler handler, String path, int id) {
        super(handler, path, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        renderTiles(g);
    }
}
