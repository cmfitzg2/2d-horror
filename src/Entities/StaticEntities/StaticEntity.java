package Entities.StaticEntities;

import Entities.Entity;
import Variables.Handler;

abstract class StaticEntity extends Entity {

	StaticEntity(Handler handler, float x, float y, int width, int height, String uniqueName) {
		super(handler, x, y, width, height, uniqueName);
	}
	boolean isInteracting;

	public void setInteracting(boolean interacting) {
		isInteracting = interacting;
	}
}
