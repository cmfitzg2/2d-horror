package Cutscenes;

import java.awt.*;

public interface Cutscene {

    int PROLOGUE = 1, ACCEPTANCE_ENCOUNTER_1 = 2, SCHOOL_CUTSCENE_1 = 3, CLASSROOM_CUTSCENE_1 = 4,
            MC_HOUSE_NIGHT_CUTSCENE_1 = 5, MC_HOUSE_NIGHT_CUTSCENE_2 = 6, MC_HOUSE_NIGHT_CUTSCENE_3 = 7,
            MC_HOUSE_NIGHT_CUTSCENE_4 = 8, MC_HOUSE_NIGHT_CUTSCENE_5 = 9;

    void tick();

    void render(Graphics g);

    void exit();
}
