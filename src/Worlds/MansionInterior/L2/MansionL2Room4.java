package Worlds.MansionInterior.L2;

import Entities.Creatures.Denial;
import Entities.Creatures.Player;
import Entities.StaticEntities.*;
import Tiles.Tile;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;
import Graphics.Assets;

import java.awt.*;

public class MansionL2Room4 extends World {

    public MansionL2Room4(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-L2-room-4.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneL2Room1 = new Rectangle(11 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), 13 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL2Room1)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_1_ID), 20 * Tile.TILEWIDTH, 13.75f * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Dresser(handler, 12 * Tile.TILEWIDTH, 8.5f * Tile.TILEHEIGHT,
                Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getWidth() * 2, Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getHeight() * 2,
                "mansionL2Room4-dresser1", Dresser.STYLE_SHELF_BOOKS_FULL));
        entityManager.addEntity(new Dresser(handler, 12 * Tile.TILEWIDTH + Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getWidth() * 2, 8.5f * Tile.TILEHEIGHT,
                Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getWidth() * 2, Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getHeight() * 2,
                "mansionL2Room4-dresser2", Dresser.STYLE_SHELF_BOOKS_FULL));
        entityManager.addEntity(new Bed(handler, 21.5f * Tile.TILEWIDTH, 9.5f * Tile.TILEHEIGHT,
                Assets.redSingleBed.getWidth() * 2, Assets.redSingleBed.getHeight() * 2,
                null, Bed.STYLE_RED_SINGLE));
        entityManager.addEntity(new TableLamp(handler, 23 * Tile.TILEWIDTH, 9 * Tile.TILEHEIGHT,
                Assets.tableLampOff.getWidth() * 2, Assets.tableLampOff.getHeight() * 2,
                "mansionL2Room4-tablelamp"));
        entityManager.addEntity(new PlantVase(handler, 12 * Tile.TILEWIDTH + 2, 17 * Tile.TILEHEIGHT - Assets.plantVaseFlowers.getHeight() * 2,
                Assets.plantVaseFlowers.getWidth() * 2, Assets.plantVaseFlowers.getHeight() * 2,
                null, PlantVase.STYLE_FLOWERS));
        entityManager.addEntity(new PlantVase(handler, 24 * Tile.TILEWIDTH - Assets.plantVaseFlowers.getWidth() * 2 - 2, 17 * Tile.TILEHEIGHT - Assets.plantVaseFlowers.getHeight() * 2,
                Assets.plantVaseFlowers.getWidth() * 2, Assets.plantVaseFlowers.getHeight() * 2,
                null, PlantVase.STYLE_FLOWERS));
        entityManager.addEntity(new Couch(handler, 24 * Tile.TILEWIDTH - Assets.couchOneLeft.getWidth() * 2, 13 * Tile.TILEHEIGHT,
                Assets.couchOneLeft.getWidth() * 2, Assets.couchOneLeft.getHeight() * 2,
                null, Couch.STYLE_LEFT));
        entityManager.addEntity(new SideTable(handler, 18 * Tile.TILEWIDTH - Assets.sideTableHorizontal.getWidth(), 17 * Tile.TILEHEIGHT - Assets.sideTableHorizontal.getHeight() * 2,
                Assets.sideTableHorizontal.getWidth() * 2, Assets.sideTableHorizontal.getHeight() * 2,
                "sidetable-vase-mansionL2Room4", SideTable.STYLE_HORIZONTAL, SideTable.ACCENT_EMPTY_VASE));
        if (handler.getFlags().isDenialMansionCutscene1()) {
            entityManager.addEntity(new Denial(handler, 24 * Tile.TILEWIDTH - Assets.couchOneLeft.getWidth() * 2 - Assets.denialSitBookLeft.getWidth() / 2f,
                    13 * Tile.TILEHEIGHT + Assets.sideTableHorizontal.getHeight() - Assets.denialSitBookLeft.getHeight() / 2f,
                    "denial-mansionL2Room4"));
        }
    }

    @Override
    protected void load() {
        firstRender = true;
    }
}
