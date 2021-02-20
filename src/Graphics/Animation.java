package Graphics;

import java.awt.image.BufferedImage;

public class Animation {
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	private boolean loop;

	public Animation(int speed, BufferedImage[] frames, boolean loop) {
		this.speed = speed;
		this.frames = frames;
		this.loop = loop;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}

	public Animation(int speed, BufferedImage[] frames) {
		this(speed, frames, true);
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if (timer > speed) {
			index++;
			timer = 0;
			if (index >= frames.length) {
				if (loop) {
					index = 0;
				} else {
					index = frames.length - 1;
				}
			}
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	public BufferedImage getDefaultFrame() {
		return frames[0];
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}
}
