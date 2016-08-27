package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;

public class SpriteAtlas {
	public String spriteFile;
	public String getSpriteFile() {
		return spriteFile;
	}
	public void setSpriteFile(String spriteFile) {
		this.spriteFile = spriteFile;
	}
	public HashMap<String, Object> spriteDetails;
	
	public HashMap<String, Object> getSpriteDetails() {
		return spriteDetails;
	}
	public void setSpriteDetails(HashMap<String, Object> spriteDetails) {
		this.spriteDetails = spriteDetails;
	}
	public Texture spriteTexture;
	public Texture getSpriteTexture() {
		return spriteTexture;
	}
	public void setSpriteTexture(Texture spriteTexture) {
		this.spriteTexture = spriteTexture;
	}
	@SuppressWarnings("unchecked")
	public SpriteAtlas(String filepath){
		Json json = new Json();
		this.setSpriteDetails(json.fromJson(HashMap.class, Gdx.files.internal(filepath)));
		this.setSpriteFile(this.getSpriteDetails().get("file").toString());
		this.setSpriteTexture(new Texture(Gdx.files.internal("sprite_pack/Base pack/Player/"+this.getSpriteFile())));
	}
	
	public Array<Sprite> createSprites(String spriteLabel){
		Array<Sprite> spriteArray = new Array<Sprite>();
		String[] spritePositions = new String[4];
		TextureRegion spriteRegion;
		Array<String> listOfSprites = (Array<String>) this.getSpriteDetails().get(spriteLabel);
		Iterator<String> spriteIterator = listOfSprites.iterator();
		while(spriteIterator.hasNext()){
			String spriteValue = spriteIterator.next().toString();
			spritePositions = spriteValue.split(" ");
			spriteRegion = new TextureRegion(this.getSpriteTexture(), 
					Integer.parseInt(spritePositions[0]), 
					Integer.parseInt(spritePositions[1]), 
					Integer.parseInt(spritePositions[2]), 
					Integer.parseInt(spritePositions[3]));
			spriteArray.add(new Sprite(spriteRegion));
		}
		return spriteArray;
	}
}
