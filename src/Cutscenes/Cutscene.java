package Cutscenes;

import java.awt.*;

public interface Cutscene {

    int PROLOGUE = 1, ACCEPTANCE_ENCOUNTER_1 = 2, SCHOOL_CUTSCENE_1 = 3, CLASSROOM_CUTSCENE_1 = 4, MC_HOUSE_NIGHT_CUTSCENE_1 = 5;

    void tick();

    void render(Graphics g);

    void exit();
}
