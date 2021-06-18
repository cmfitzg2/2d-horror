package Worlds.MansionInterior.L2;

import Entities.Creatures.Player;
import Entities.EntityManager;
import Entities.StaticEntities.*;
import Graphics.Assets;
import Items.Book;
import Tiles.Tile;
import Utils.GeneralUtils;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;

public class MansionL2Room3 extends World {

    private boolean roomMatches;
    private TableLamp lamp;
    EntityManager entityManagerR4;
    SideTable bouquet;
    TableLamp lampR4;

    public MansionL2Room3(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-L2-room-3.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneL2Room1 = new Rectangle((int) (24.5f * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset()), 13 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL2Room1)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_1_ID), 15 * Tile.TILEWIDTH, 13.75f * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Dresser(handler, 23 * Tile.TILEWIDTH, 8.5f * Tile.TILEHEIGHT,
                Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getWidth() * 2, Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getHeight() * 2,
                "mansionL2Room3-dresser1", Dresser.STYLE_SHELF_BOOKS_FULL));
        entityManager.addEntity(new Dresser(handler, 23 * Tile.TILEWIDTH - Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getWidth() * 2, 8.5f * Tile.TILEHEIGHT,
                Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getWidth() * 2, Assets.dressers[Dresser.STYLE_SHELF_BOOKS_FULL].getHeight() * 2,
                "mansionL2Room3-dresser2", Dresser.STYLE_SHELF_BOOKS_FULL));
        entityManager.addEntity(new Bed(handler, 13 * Tile.TILEWIDTH, 9.5f * Tile.TILEHEIGHT,
                Assets.redSingleBed.getWidth() * 2, Assets.redSingleBed.getHeight() * 2,
                null, Bed.STYLE_RED_SINGLE));
        lamp = new TableLamp(handler, 12 * Tile.TILEWIDTH, 9 * Tile.TILEHEIGHT,
                Assets.tableLampOff.getWidth() * 2, Assets.tableLampOff.getHeight() * 2,
                null);
        entityManager.addEntity(lamp);
        entityManager.addEntity(new PlantVase(handler, 12 * Tile.TILEWIDTH + 2, 17 * Tile.TILEHEIGHT - Assets.plantVaseFlowers.getHeight() * 2,
                Assets.plantVaseFlowers.getWidth() * 2, Assets.plantVaseFlowers.getHeight() * 2,
                null, PlantVase.STYLE_FLOWERS));
        entityManager.addEntity(new PlantVase(handler, 24 * Tile.TILEWIDTH - Assets.plantVaseFlowers.getWidth() * 2 - 2, 17 * Tile.TILEHEIGHT - Assets.plantVaseFlowers.getHeight() * 2,
                Assets.plantVaseFlowers.getWidth() * 2, Assets.plantVaseFlowers.getHeight() * 2,
                null, PlantVase.STYLE_FLOWERS));
        entityManager.addEntity(new Couch(handler, 12 * Tile.TILEWIDTH, 13 * Tile.TILEHEIGHT,
                Assets.couchOneRight.getWidth() * 2, Assets.couchOneRight.getHeight() * 2,
                "couch-mansionL2Room3", Couch.STYLE_RIGHT));
        entityManager.addEntity(new SideTable(handler, 18 * Tile.TILEWIDTH - Assets.sideTableHorizontal.getWidth(), 17 * Tile.TILEHEIGHT - Assets.sideTableHorizontal.getHeight() * 2,
                Assets.sideTableHorizontal.getWidth() * 2, Assets.sideTableHorizontal.getHeight() * 2,
                null, SideTable.STYLE_HORIZONTAL, SideTable.ACCENT_BOUQUET));
        entityManager.addEntity(new ItemSparkle(handler, 22 * Tile.TILEWIDTH, 10 * Tile.TILEHEIGHT, Assets.itemSparkle[0].getWidth(), Assets.itemSparkle[0].getHeight(), "mansionL2Room3-itemsparkle1"));
    }

    @Override
    protected void tick() {
        if (firstTime) {
            if (fadeIn) {
                GeneralUtils.levelFadeIn(handler, fadeInFrames);
            }
            entityManagerR4 = handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_4_ID).getEntityManager();
            bouquet = (SideTable) entityManagerR4.getEntityByUid("sidetable-vase-mansionL2Room4");
            lampR4 = (TableLamp) entityManagerR4.getEntityByUid("mansionL2Room4-tablelamp");
            firstTime = false;
        }
        entityManager.tick();
        if (!transitioningTo) {
            checkLoadZones();
        }
        if (transitioningTo) {
            transitionTo();
        }
    }

    @Override
    protected void load() {
        firstRender = true;
    }

    public boolean isRoomMatches() {
        return bouquet.getAccent() == SideTable.ACCENT_BOUQUET && lamp.isLit() == lampR4.isLit()
                && handler.getPlayer().isSitBookRight() && handler.getPlayer().getInventory().containsUnique(Book.BOOK_TWO_FRIENDS_NAME);
    }
}
