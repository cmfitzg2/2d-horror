package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TimerManager {

    private Map<String, Timer> timerMap;
    private List<String> timerList;

    public TimerManager() {
        timerMap = new HashMap<>();
        timerList = new ArrayList<>();
    }

    public void tick() {
        if (!timerMap.isEmpty()) {
            for (Entry entry : timerMap.entrySet()) {
                Timer timer = (Timer) entry.getValue();
                String uid = (String) entry.getKey();
                timer.tick();
                if (timer.getFrameCount() >= timer.getFrameLimit()) {
                    timerMap.remove(uid);
                }
            }
        }
    }

    public void addTimer(String uid, int frameLimit) {
        if (timerMap.containsKey(uid)) {
            System.out.println("trying to add a duplicate timer");
            return;
        }
        timerMap.put(uid, new Timer(frameLimit));
        if (!timerList.contains(uid)) {
            //support adding the same timer uid as long as the map doesn't contain it.
            System.out.println("DEBUG -- timer list already contained a timer with uid " + uid);
            timerList.add(uid);
        }
    }

    public boolean timerExpired(String uid) {
        return !timerMap.containsKey(uid) && timerAdded(uid);
    }

    public boolean timerAdded(String uid) {
        return timerList.contains(uid);
    }
}
