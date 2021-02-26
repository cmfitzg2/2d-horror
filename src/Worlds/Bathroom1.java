package Worlds;

import Entities.Creatures.Player;
import Entities.StaticEntities.Toilet;
import Tiles.Tile;
import Variables.Handler;
import Graphics.Assets;

import java.awt.*;

public class Bathroom1 extends World {

    public Bathroom1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/bathroom1.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneSchool = new Rectangle(9 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), (int) (11.5 * Tile.TILEHEIGHT) - (int) handler.getGameCamera().getyOffset(), 130, 36);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneSchool)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID), 15 * Tile.TILEWIDTH, 4 * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Toilet(handler, 12f * Tile.TILEWIDTH, 3.5f * Tile.TILEHEIGHT,
                Assets.toilet.getWidth() * 2, Assets.toilet.getHeight() * 2, "toilet1-bathroom1"));
        entityManager.addEntity(new Toilet(handler, 14f * Tile.TILEWIDTH, 3.5f * Tile.TILEHEIGHT,
                Assets.toilet.getWidth() * 2, Assets.toilet.getHeight() * 2, "toilet2-bathroom1"));
    }

    @Override
    protected void load() {
        firstRender = true;
    }
}
