package Textboxes;

import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import Graphics.Assets;
import Utils.FontUtils;

public class TextboxHandler {
	private int textboxWidth = 1600, textboxHeight = 675, currentLine = 1, index, textboxNum, optionsIndex, sizeConstraint;
	private int xStart, yStart, xEnd, yEnd, xOffsetText = 25, yOffsetText = 25, minimumLineLength = 45, minimumOptionsLength;
	private int xStartOptions, yStartOptions, optionsIncrement;
	private float xScale, yScale;
	private String currentText = "";
	private Rectangle textboxRect, topRect, midRect, botRect;
	boolean textFinished = false, viewingOptions = false, finished = false, active = false, scaledUp = false;
	private String message, optionSelected = "";
	private String[] options, words;
	private Font font;
	private FontMetrics metrics;
	private Color fontColor;
	private Handler handler;
	private boolean firstTime = true;
	private HashMap<Rectangle, String> textboxLines;
	private java.util.List<Textbox> textboxes;
	private Textbox currentTextbox;
	private int frameCount = 1, textSpeed;
	private BufferedImage portrait;
	private int portraitWidth, portraitHeight, portraitXStart, portraitYStart;
	private boolean allowFastText;

	/**
	 * object for dealing with the generic functionality of a textbox
	 * @param handler game handler
	 * @param font the font to use in the textbox
	 * @param message the text to parse & show
	 * @param options a list of player-selectable options shown in a menu after text finishes
	 * @param textSpeed number of frames to wait per letter scrolled
	 * @param fontColor the font color
	 * @param portrait optional portrait to show inside of textbox
	 * @param sizeConstraint [1, 100] - divided by 100 and multiplied to font size. can only be used to constrain (e.g., reduce how much the font is shrunk or grown by default).
	 */
	public TextboxHandler(Handler handler, Font font, String message, String[] options, int textSpeed, Color fontColor,
						  BufferedImage portrait, int sizeConstraint, boolean allowFastText) {
		this.handler = handler;
		this.message = message;
		this.options = options;
		this.font = font;
		this.textSpeed = textSpeed;
		if (null != fontColor) {
			this.fontColor = fontColor;
		} else {
			this.fontColor = Color.WHITE;
		}
		this.portrait = portrait;
		this.sizeConstraint = sizeConstraint;
		if (this.sizeConstraint < 1) {
			this.sizeConstraint = 1;
		} else if (this.sizeConstraint > 100) {
			this.sizeConstraint = 100;
		}
		this.allowFastText = allowFastText;
		initParams();
		words = message.split(" ");
		textboxLines = new HashMap<>();
		textboxLines.put(topRect, "");
		textboxLines.put(midRect, "");
		textboxLines.put(botRect, "");
		textboxes = new ArrayList<>();
	}

	private void initParams() {
		xStart = handler.getWidth() / 12;
		xEnd = handler.getWidth() * 11 / 12;
		yStart = handler.getHeight() * 5 / 8;
		yEnd = handler.getHeight();
		xScale = (float) (xEnd - xStart) / textboxWidth;
		yScale = (float) (yEnd - yStart) / textboxHeight;
		textboxRect = new Rectangle(xStart + (int) (xScale * xOffsetText), yStart + (int) (yScale * yOffsetText),
				xEnd - xStart - (int) (2 * xScale * xOffsetText), yEnd - yStart - (int) (2 * yScale * yOffsetText));
		topRect = new Rectangle(textboxRect.x, textboxRect.y, textboxRect.width, textboxRect.height / 3);
		midRect = new Rectangle(textboxRect.x, textboxRect.y + textboxRect.height / 3, textboxRect.width, textboxRect.height / 3);
		botRect = new Rectangle(textboxRect.x, textboxRect.y + textboxRect.height * 2 / 3, textboxRect.width, textboxRect.height / 3);
		if (null != portrait) {
			portraitWidth = textboxRect.width / 4;
			portraitHeight = topRect.height + midRect.height + botRect.height;
			portraitXStart = textboxRect.x;
			portraitYStart = textboxRect.y;
		}
		if (handler.getWidth() < 1000) {
			minimumLineLength = 20;
		}
	}

	public void tick() {
		if (firstTime) {
			return;
		}
		if (!textFinished) {
			//need to set the hold on this even when we're not using it
			if (handler.getKeyManager().z) {
				handler.getKeyManager().setStillHoldingZ(true);
			}
			if (handler.getKeyManager().x && !handler.getKeyManager().isStillHoldingX() && allowFastText) {
				handler.getKeyManager().setStillHoldingX(true);
				textFinished = true;
				while (!currentTextbox.getCurrentText(currentLine + 1).isEmpty()) {
					currentLine++;
				}
			}
			String lineText = currentTextbox.getCurrentText(currentLine);
			if (index > lineText.length()) {
				if (!currentTextbox.getCurrentText(currentLine + 1).isEmpty()) {
					currentLine++;
					index = 0;
					currentText = "";
				} else {
					textFinished = true;
				}
			} else {
				currentText = lineText.substring(0, index);
			}
			if (frameCount % textSpeed == 0) {
				index++;
				frameCount = 0;
			}
			frameCount++;
		} else {
			//need to set the hold on this even when we're not using it
			if (handler.getKeyManager().x && allowFastText) {
				handler.getKeyManager().setStillHoldingX(true);
			}
			if (handler.getKeyManager().z && !handler.getKeyManager().isStillHoldingZ()) {
				handler.getKeyManager().setStillHoldingZ(true);
				if (textboxes.size() > textboxNum + 1) {
					textboxNum++;
					currentTextbox = textboxes.get(textboxNum);
					index = 0;
					currentText = "";
					currentLine = 0;
					textFinished = false;
				} else {
					if (options != null) {
						if (!viewingOptions) {
							viewingOptions = true;
						} else {
							optionSelected = options[optionsIndex];
							finished = true;
							handler.setPlayerFrozen(false);
						}
					} else {
						finished = true;
						handler.setPlayerFrozen(false);
					}
				}
			} else if (handler.getKeyManager().down && !handler.getKeyManager().isStillHoldingDown()) {
				handler.getKeyManager().setStillHoldingDown(true);
				if (viewingOptions) {
					if (optionsIndex == options.length - 1) {
						optionsIndex = 0;
					} else {
						optionsIndex++;
					}
				}
			} else if (handler.getKeyManager().up && !handler.getKeyManager().isStillHoldingUp()) {
				handler.getKeyManager().setStillHoldingUp(true);
				if (viewingOptions) {
					if (optionsIndex == 0) {
						optionsIndex = options.length - 1;
					} else {
						optionsIndex--;
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		if (firstTime) {
			initGraphics(g);
			firstTime = false;
		}
		g.drawImage(Assets.textbox, xStart, yStart, xEnd - xStart, yEnd - yStart, null);
		if (portrait != null) {
			g.drawImage(portrait, portraitXStart, portraitYStart, portraitWidth, portraitHeight, null);
		}
		g.setFont(font);
		g.setColor(fontColor);
		if (textFinished) {
			g.drawString(currentTextbox.getLineOne(), topRect.x, topRect.y);
			g.drawString(currentTextbox.getLineTwo(), midRect.x, midRect.y);
			g.drawString(currentTextbox.getLineThree(), botRect.x, botRect.y);
		} else {
			if (currentLine == 1) {
				g.drawString(currentText, topRect.x, topRect.y);
			} else if (currentLine == 2) {
				g.drawString(currentTextbox.getLineOne(), topRect.x, topRect.y);
				g.drawString(currentText, midRect.x, midRect.y);
			} else if (currentLine == 3) {
				g.drawString(currentTextbox.getLineOne(), topRect.x, topRect.y);
				g.drawString(currentTextbox.getLineTwo(), midRect.x, midRect.y);
				g.drawString(currentText, botRect.x, botRect.y);
			}
		}
		if (viewingOptions) {
			g.drawImage(Assets.textboxOptions, xStartOptions, yStartOptions, minimumOptionsLength * metrics.stringWidth(" "), optionsIncrement * options.length + metrics.getAscent() / 2, null);
			for (int i = 0; i < options.length; i++) {
				g.drawString(options[i], xStartOptions + (int) (metrics.stringWidth(">") * 1.5), yStartOptions + (i + 1) * optionsIncrement);
			}
			//this stupid font pack doesn't have the ">" character and i don't want to swap it or use a sprite
			g.setFont(Assets.philosopher.deriveFont((float) font.getSize()));
			g.drawString(">", xStartOptions + (int) (metrics.stringWidth(">") * 0.25), yStartOptions + (optionsIndex + 1) * optionsIncrement);
		}
	}

	private void initGraphics(Graphics g) {
		metrics = g.getFontMetrics(font);
		boolean scaleUp = metrics.getHeight() < textboxRect.height / 3;
		int initialSize = font.getSize();
		if (scaleUp) {
			font = FontUtils.scaleFontUpVertically(new Rectangle(textboxRect.x, textboxRect.y, textboxRect.width, textboxRect.height / 3), g, font);
		} else {
			font = FontUtils.scaleFontDownVertically(new Rectangle(textboxRect.x, textboxRect.y, textboxRect.width, textboxRect.height / 3), g, font);
		}
		//only scale the font up or down as a restriction
		font = font.deriveFont(initialSize + (Math.abs(font.getSize() - initialSize) * sizeConstraint / 100f));

		//scale to char minimum
		metrics = g.getFontMetrics(font);
		if (metrics.stringWidth(" ") * minimumLineLength > textboxRect.getWidth()) {
			while (metrics.stringWidth(" ") * minimumLineLength > textboxRect.getWidth()) {
				font = font.deriveFont(font.getSize() - 1.0f);
				metrics = g.getFontMetrics(font);
			}
		}
		topRect.y += topRect.height / 2 + metrics.getAscent() / 2;
		midRect.y += midRect.height / 2 + metrics.getAscent() / 2;
		botRect.y += botRect.height / 2 + metrics.getAscent() / 2;
		if (null != portrait) {
			topRect.width = topRect.width - portraitWidth - (int) (xScale * xOffsetText);
			topRect.x += portraitWidth + xScale * xOffsetText;
			midRect.width = midRect.width - portraitWidth - (int) (xScale * xOffsetText);
			midRect.x += portraitWidth + xScale * xOffsetText;
			botRect.width = botRect.width - portraitWidth - (int) (xScale * xOffsetText);
			botRect.x += portraitWidth + xScale * xOffsetText;
		}
		createTextboxes(g);

		if (options != null) {
			optionsIncrement = metrics.getHeight();
			xStartOptions = handler.getWidth() / 12;
			yStartOptions = handler.getHeight() / 4;
			minimumOptionsLength = getLongestStringLength(options) + 3;
		}
	}

	private void createTextboxes(Graphics g) {
		//build all of our textboxes using the text we were given
		//create a new currentTextbox object at the end of fitting 3 lines of text (or equivalent newlines) or at end of message
		//btw if anyone ever reads this, i know my methods tend to be verbose and probably inefficient
		//but when you're as dumb as me you just want to get the thing working.
		StringBuilder line = new StringBuilder();
		int lineNumber = 0;
		for (String word : words) {
			if (!wrapNextWord(line + word, topRect, g, font)) {
				if (word.equals("\n")) {
					if (lineNumber == 0) {
						textboxLines.put(topRect, line.toString());
					} else if (lineNumber == 1) {
						textboxLines.put(midRect, line.toString());
					} else if (lineNumber == 2) {
						textboxLines.put(botRect, line.toString());
					} else {
						//this is an error handler obviously
						textboxLines.put(topRect, "AAAAAAAAAAAAAAAAAAAA");
					}
					lineNumber++;
					line = new StringBuilder(word);
					if (lineNumber >= 3) {
						lineNumber = 0;
						textboxes.add(new Textbox(textboxLines.get(topRect), textboxLines.get(midRect), textboxLines.get(botRect), portrait));
					}
				} else if (word.equals("\r")) {
					if (lineNumber == 0) {
						textboxLines.put(topRect, line.toString());
						textboxes.add(new Textbox(textboxLines.get(topRect), "", "", portrait));
					} else if (lineNumber == 1) {
						textboxLines.put(midRect, line.toString());
						textboxes.add(new Textbox(textboxLines.get(topRect), textboxLines.get(midRect), "", portrait));
					} else if (lineNumber == 2) {
						textboxLines.put(botRect, line.toString());
						textboxes.add(new Textbox(textboxLines.get(topRect), textboxLines.get(midRect), textboxLines.get(botRect), portrait));
					}
					lineNumber = 0;
					line = new StringBuilder(word);
				} else {
					line.append(word).append(" ");
				}
			} else {
				if (lineNumber == 0) {
					textboxLines.put(topRect, line.toString());
				} else if (lineNumber == 1) {
					textboxLines.put(midRect, line.toString());
				} else if (lineNumber == 2) {
					textboxLines.put(botRect, line.toString());
				} else {
					//this is an error handler obviously
					textboxLines.put(topRect, "AAAAAAAAAAAAAAAAAAAA");
				}
				lineNumber++;
				line = new StringBuilder(word + " ");
				if (lineNumber >= 3) {
					lineNumber = 0;
					textboxes.add(new Textbox(textboxLines.get(topRect), textboxLines.get(midRect), textboxLines.get(botRect), portrait));
				}
			}
		}
		//once more for the fans
		if (!line.toString().isEmpty()) {
			if (lineNumber == 0) {
				textboxLines.put(topRect, line.toString());
				textboxes.add(new Textbox(textboxLines.get(topRect), "", "", portrait));
			} else if (lineNumber == 1) {
				textboxLines.put(midRect, line.toString());
				textboxes.add(new Textbox(textboxLines.get(topRect), textboxLines.get(midRect), "", portrait));
			} else if (lineNumber == 2) {
				textboxLines.put(botRect, line.toString());
				textboxes.add(new Textbox(textboxLines.get(topRect), textboxLines.get(midRect), textboxLines.get(botRect), portrait));
			}
		}
		currentTextbox = textboxes.get(0);
	}

	private boolean wrapNextWord(String text, Rectangle rect, Graphics g, Font font) {
		metrics = g.getFontMetrics(font);
		int textWidth = metrics.stringWidth(text);
		return textWidth >= rect.width;
	}

	private int getLongestStringLength(String[] array) {
		int index = 0;
		int elementLength = array[0].length();
		for (int i = 1; i < array.length; i++) {
			if (array[i].length() > elementLength) {
				index = i; elementLength = array[i].length();
			}
		}
		return array[index].length();
	}

	public String getOptionSelected() {
		if (!optionSelected.equals("")) {
			String tmp = optionSelected;
			optionSelected = "";
			return tmp;
		} else {
			return "";
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		handler.setPlayerFrozen(true);
	}
}

