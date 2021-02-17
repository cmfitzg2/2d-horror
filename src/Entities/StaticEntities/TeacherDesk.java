package Entities.StaticEntities;

import Cutscenes.CutsceneManager;
import Graphics.Assets;
import Textboxes.TextboxHandler;
import Variables.GeneralConstants;
import Variables.Handler;

import java.awt.*;

public class TeacherDesk extends StaticEntity {

    private TextboxHandler textboxHandler;
    private final String denialText = "Sit down, MC",
            angerText = "You're too stupid to teach. \n Sit down, dumbass.",
            bargainingText = "Uhh, MC, I think the teacher will be here soon... \r You should probably go to your seat.",
            depressionText = "Oh, you're teaching today? \n Cool, guess I'll pay attention for once.",
            acceptanceText = "You're having an awful lot of fun for a guy who's been talking about how bad he feels! Lack of sleep delirium kicking in? \r I think time's up though.";
    private int messageNum = 0;

    public TeacherDesk(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = height / 4;
        bounds.width = width;
        bounds.height = height - height / 3;
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {
        if (null != textboxHandler && textboxHandler.isActive()) {
            if (!textboxHandler.isFinished()) {
                textboxHandler.render(g);
            } else if (messageNum == 4){
                if (handler.getFlags().isClassroomCutscene1() && !handler.getFlags().isCutsceneActive()) {
                    handler.getFlags().setCutsceneActive(true);
                    CutsceneManager cutsceneManager = handler.getCutsceneManager();
                    cutsceneManager.setActiveCutscene(cutsceneManager.getCutscene(4));
                }
            }
        }
    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {
        if (null != textboxHandler && textboxHandler.isActive()) {
            if (!textboxHandler.isFinished()) {
                textboxHandler.tick();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.teacherDesk, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        if (handler.getFlags().isClassroomCutscene1() && handler.getPlayer().getY() < y) {
            switch (messageNum) {
                case 0:
                    textboxHandler = new TextboxHandler(handler, Assets.denialFont, denialText, null,
                            GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial,
                            Assets.denialText, 50, true, true);
                    break;
                case 1:
                    textboxHandler = new TextboxHandler(handler, Assets.angerFont, angerText, null,
                            GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger,
                            Assets.angerText, 50, true, true);
                    break;
                case 2:
                    textboxHandler = new TextboxHandler(handler, Assets.bargainingFont, bargainingText, null,
                            GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining,
                            Assets.bargainingText, 50, true, true);
                    break;
                case 3:
                    textboxHandler = new TextboxHandler(handler, Assets.depressionFont, depressionText, null,
                            GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression,
                            Assets.depressionText, 50, true, true);
                    break;
                default:
                    textboxHandler = new TextboxHandler(handler, Assets.acceptanceFont, acceptanceText, null,
                            GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance,
                            Assets.acceptanceText, 50, true, true);
            }
            textboxHandler.setActive(true);
            messageNum++;
        }
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }
}
