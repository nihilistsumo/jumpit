package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen{
	public SpriteBatch batch;
	public BitmapFont font;
	public SpriteAtlas ta;
	public TiledMap worldmap;
	public TmxMapLoader mapLoader;
	public OrthographicCamera camera;
	public float scale = GameConstants.SCALE;
	public OrthogonalTiledMapRenderer renderer;
	public float frameDuration = GameConstants.FRAME_DURATION;
	public float changeX = GameConstants.CHANGE_X;
	public float changeY = GameConstants.CHANGE_Y;
	public float jumpHeight = GameConstants.JUMP_HEIGHT;
	public float groundHeight = GameConstants.GROUND_HEIGHT;
	public float stateTime = 0.0f;
	public GameLogic logic;
	GdxJumpGame game;
	
	public GameScreen(GdxJumpGame g){
		game = g;
		batch = new SpriteBatch();
		font = new BitmapFont();
		ta = new SpriteAtlas("sprite_pack/Base pack/Player/p1_spritesheet.json");
		logic = new GameLogic();
		logic.setCharacter(ta.createSprites("walk"));
		mapLoader = new TmxMapLoader();
		worldmap = mapLoader.load("sprite_pack/world/worldmap.tmx");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
		renderer = new OrthogonalTiledMapRenderer(worldmap, scale);
		renderer.setView(camera);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		renderer.setView(camera);
		renderer.render();
		batch.begin();
		logic.getCurrentSprite().draw(batch);
		batch.end();
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(){
		if(GameLogic.charState == GameLogic.State.WALK && Gdx.input.isTouched()){
			GameLogic.charState = GameLogic.State.JUMP;
			GameLogic.jumpingUp = true;
			logic.setCharacter(ta.createSprites("jump"));
			logic.setCurrentIndex(0);
		}
		else{
			stateTime+= Gdx.graphics.getDeltaTime();
			if(stateTime>frameDuration){
				logic.setCurrentIndex(logic.getCurrentIndex()+1);
				if(logic.getCurrentIndex() >= logic.getCharacter().size)
					logic.setCurrentIndex(0);
				stateTime = 0.0f;
			}
		}
		if(GameLogic.charState == GameLogic.State.JUMP)
			//perform jump
			performJump();
		logic.setCurrentSprite(logic.getCharacter().get(logic.getCurrentIndex()));
		logic.setxPos(logic.getxPos()+changeX);
		camera.position.x = logic.getxPos();
		camera.update();
		logic.getCurrentSprite().setPosition(logic.getxPos(), logic.getyPos());	
	}
	public void performJump(){
		//jumping code goes here
		if(GameLogic.jumpingUp){
			if(logic.getyPos()<jumpHeight)
				logic.setyPos(logic.getyPos()+changeY);
			else
				GameLogic.jumpingUp = false;
		}
		else{
			if(logic.getyPos()>groundHeight)
				logic.setyPos(logic.getyPos()-changeY);
			else{
				logic.setyPos(groundHeight);
				GameLogic.charState = GameLogic.State.WALK;
				logic.character = ta.createSprites("walk");
				logic.currentIndex = 0;
			}
		}
	}

}
