package Cutscenes;

import java.awt.*;

public interface Cutscene {

    int PROLOGUE = 0, ACCEPTANCE_ENCOUNTER_1 = 1, SCHOOL_CUTSCENE_1 = 2, CLASSROOM_CUTSCENE_1 = 3,
            MC_HOUSE_NIGHT_CUTSCENE_1 = 4, MC_HOUSE_NIGHT_CUTSCENE_2 = 5, MC_HOUSE_NIGHT_CUTSCENE_3 = 6,
            MC_HOUSE_NIGHT_CUTSCENE_4 = 7, MC_HOUSE_NIGHT_CUTSCENE_5 = 8, OVERWORLD_CUTSCENE_1 = 9;

    void tick();

    void render(Graphics g);

    void exit();
}
