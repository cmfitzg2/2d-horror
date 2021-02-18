package Cutscenes;

import Graphics.Assets;
import Input.KeyManager;
import Items.Inventory;
import Items.Lighter;
import Textboxes.TextboxHandler;
import Variables.Flags;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class MCHouseNightCutscene3 implements Cutscene {
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2;
    private KeyManager keyManager;
    private boolean showingText = false, textbox1 = true, textbox2 = false;
    private boolean firstTime = true;
    private final String messageOne = "The power is out? Geez, what a pain. I can't see a thing. \r " +
            "I'd better find a way to light this place up.",
            messageTwo = "It's too dark to move around in here, I need a light source.";
    public MCHouseNightCutscene3(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.playerThinkingFont, messageOne, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.playerThinkingFont, messageTwo, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_2_ID) {
            return;
        }
        if (firstTime) {
            //need to be able to open inventory so can't set frozen
            handler.getPlayer().setLockX(true);
            handler.getPlayer().setLockY(true);
            handler.getPlayer().setLockZ(true);
            handler.setPlayerFrozen(true);
            Assets.powerDown.start();
            handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_PITCH_BLACK);
            handler.getPlayer().getInventory().addItem(new Lighter(handler, "Lighter", Inventory.REGULAR_ITEM, "a lighter", "lighter", Assets.keyInventory));
            firstTime = false;
        }
        if (textbox1 && !Assets.powerDown.isActive()) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.tick();
            } else {
                textbox1 = false;
                showingText = true;
                handler.setPlayerFrozen(false);
            }
        }
        if (showingText && !textbox2) {
            if (handler.getKeyManager().up || handler.getKeyManager().down || handler.getKeyManager().left || handler.getKeyManager().right) {
                textboxHandler2 = new TextboxHandler(handler, Assets.playerThinkingFont, messageTwo, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
                textbox2 = true;
                handler.setPlayerFrozen(true);
            }
        }
        if (textbox2) {
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.tick();
            } else {
                textbox2 = false;
                handler.setPlayerFrozen(false);
            }
        }
        if (handler.getFlags().getTimeOfDay() != Flags.TIME_OF_DAY_PITCH_BLACK) {
            exit();
        }
    }

    @Override
    public void render(Graphics g) {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_2_ID) {
            return;
        }
        if (textbox1 && !Assets.powerDown.isActive()) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.render(g);
            }
        } else if (textbox2) {
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.render(g);
            }
        }
    }

    @Override
    public void exit() {
        handler.setPlayerFrozen(false);
        handler.getPlayer().setLockX(false);
        handler.getPlayer().setLockY(false);
        handler.getPlayer().setLockZ(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setMcHouseNightCutscene3(false);
    }
}
