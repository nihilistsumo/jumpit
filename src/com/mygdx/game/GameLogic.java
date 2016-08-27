package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class GameLogic {
	public Array<Sprite> character;
	public Sprite currentSprite;
	public int currentIndex = 0;
	public static boolean jumpingUp = false;
	public float xPos = 50f, yPos = 70f, jumpHeight = 200f;
	public enum State{
		WALK, JUMP, HURT
	}
	public static State charState = State.WALK;
	public Array<Sprite> getCharacter() {
		return character;
	}
	public void setCharacter(Array<Sprite> character) {
		this.character = character;
	}
	public Sprite getCurrentSprite() {
		return currentSprite;
	}
	public void setCurrentSprite(Sprite currentSprite) {
		this.currentSprite = currentSprite;
	}
	public int getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	public float getxPos() {
		return xPos;
	}
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}
	public float getyPos() {
		return yPos;
	}
	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
	public float getJumpHeight() {
		return jumpHeight;
	}
	public void setJumpHeight(float jumpHeight) {
		this.jumpHeight = jumpHeight;
	}

}
