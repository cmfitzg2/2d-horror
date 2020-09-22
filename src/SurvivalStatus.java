

public class SurvivalStatus {

    private int currentTemperature = 0;

    private int currentTime = 0;

    private int currentThreat = 0;

    private int currentFear = 0;

    private int currentHunger = 0;

    private int currentThirst = 0;

    public int getCurrentTemperature()
    {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature)
    {
        this.currentTemperature = currentTemperature;
    }

    public int getCurrentTime()
    {
        return currentTime;
    }

    public void setCurrentTime(int currentTime)
    {
        this.currentTime = currentTime;
    }
    public int getCurrentThreat() {
        return currentThreat;
    }

    public void setCurrentThreat(int currentThreat) {
        this.currentThreat = currentThreat;
    }

    public int getCurrentFear() {
        return currentFear;
    }

    public void setCurrentFear(int currentFear) {
        this.currentFear = currentFear;
    }

    public int getCurrentHunger() {
        return currentHunger;
    }

    public void setCurrentHunger(int currentHunger) {
        this.currentHunger = currentHunger;
    }

    public int getCurrentThirst() {
        return currentThirst;
    }

    public void setCurrentThirst(int currentThirst) {
        this.currentThirst = currentThirst;
    }
}
