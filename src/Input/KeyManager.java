package Input;

import Items.Key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	public boolean up, down, left, right, z, shift, x, c, esc, enter, alt, one, two, three, four, five, six;
	private boolean stillHoldingZ, stillHoldingX, stillHoldingC, stillHoldingEsc, stillholdingEnter, stillHoldingAlt,
			stillHoldingUp, stillHoldingDown, stillHoldingLeft, stillHoldingRight,
			stillHolding1, stillHolding2, stillHolding3, stillHolding4, stillHolding5, stillHolding6;

	public KeyManager()
	{
		keys = new boolean[256];
	}

	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		shift = keys[KeyEvent.VK_SHIFT];
		z = keys[KeyEvent.VK_Z];
		x = keys[KeyEvent.VK_X];
		c = keys[KeyEvent.VK_C];
		esc	= keys[KeyEvent.VK_ESCAPE];
		enter = keys[KeyEvent.VK_ENTER];
		alt = keys[KeyEvent.VK_ALT];
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		three = keys[KeyEvent.VK_3];
		four = keys[KeyEvent.VK_4];
		five = keys[KeyEvent.VK_5];
		six = keys[KeyEvent.VK_6];

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() <= 256) {
			keys[e.getKeyCode()] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() <= 256) {
			keys[e.getKeyCode()] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			stillHoldingZ = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			stillHoldingX = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			stillHoldingC = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			stillHoldingEsc = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			stillHoldingUp = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			stillHoldingDown = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			stillHoldingLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			stillHoldingRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			stillholdingEnter = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ALT) {
			stillHoldingAlt = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_1) {
			stillHolding1 = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_2) {
			stillHolding2 = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_3) {
			stillHolding3 = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_4) {
			stillHolding4 = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_5) {
			stillHolding5 = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_6) {
			stillHolding6 = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean isStillHoldingZ() {
		return stillHoldingZ;
	}

	public void setStillHoldingZ(boolean stillHoldingZ) {
		this.stillHoldingZ = stillHoldingZ;
	}

	public boolean isStillHoldingX() {
		return stillHoldingX;
	}

	public void setStillHoldingX(boolean stillHoldingX) {
		this.stillHoldingX = stillHoldingX;
	}

	public boolean isStillHoldingC() {
		return stillHoldingC;
	}

	public void setStillHoldingC(boolean stillHoldingC) {
		this.stillHoldingC = stillHoldingC;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isStillHolding1() {
		return stillHolding1;
	}

	public void setStillHolding1(boolean stillHolding1) {
		this.stillHolding1 = stillHolding1;
	}

	public boolean isStillHolding2() {
		return stillHolding2;
	}

	public void setStillHolding2(boolean stillHolding2) {
		this.stillHolding2 = stillHolding2;
	}

	public boolean isStillHolding3() {
		return stillHolding3;
	}

	public void setStillHolding3(boolean stillHolding3) {
		this.stillHolding3 = stillHolding3;
	}

	public boolean isStillHolding4() {
		return stillHolding4;
	}

	public void setStillHolding4(boolean stillHolding4) {
		this.stillHolding4 = stillHolding4;
	}

	public boolean isStillHolding5() {
		return stillHolding5;
	}

	public void setStillHolding5(boolean stillHolding5) {
		this.stillHolding5 = stillHolding5;
	}

	public boolean isStillHolding6() {
		return stillHolding6;
	}

	public void setStillHolding6(boolean stillHolding6) {
		this.stillHolding6 = stillHolding6;
	}

	public boolean isStillHoldingEsc() {
		return stillHoldingEsc;
	}

	public void setStillHoldingEsc(boolean stillHoldingEsc) {
		this.stillHoldingEsc = stillHoldingEsc;
	}

	public boolean isStillHoldingUp() {
		return stillHoldingUp;
	}

	public void setStillHoldingUp(boolean stillHoldingUp) {
		this.stillHoldingUp = stillHoldingUp;
	}

	public boolean isStillHoldingDown() {
		return stillHoldingDown;
	}

	public void setStillHoldingDown(boolean stillHoldingDown) {
		this.stillHoldingDown = stillHoldingDown;
	}

	public boolean isStillHoldingLeft() {
		return stillHoldingLeft;
	}

	public void setStillHoldingLeft(boolean stillHoldingLeft) {
		this.stillHoldingLeft = stillHoldingLeft;
	}

	public boolean isStillHoldingRight() {
		return stillHoldingRight;
	}

	public void setStillHoldingRight(boolean stillHoldingRight) {
		this.stillHoldingRight = stillHoldingRight;
	}

	public boolean isStillholdingEnter() {
		return stillholdingEnter;
	}

	public void setStillholdingEnter(boolean stillholdingEnter) {
		this.stillholdingEnter = stillholdingEnter;
	}

	public boolean isStillHoldingAlt() {
		return stillHoldingAlt;
	}

	public void setStillHoldingAlt(boolean stillHoldingAlt) {
		this.stillHoldingAlt = stillHoldingAlt;
	}
}
