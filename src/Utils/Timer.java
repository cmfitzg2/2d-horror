package Utils;

public class Timer {

    private int frameLimit;
    private int frameCount;

    public Timer(int frameLimit) {
        this.frameLimit = frameLimit;
    }

    public void tick() {
        frameCount++;
    }

    public int getFrameLimit() {
        return frameLimit;
    }

    public int getFrameCount() {
        return frameCount;
    }
}
