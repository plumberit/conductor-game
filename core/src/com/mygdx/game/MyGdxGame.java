package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Person bird;
	boolean gameOver;
	Texture restartTexture;
	Warrior warrior;
	boolean start;
	boolean canClick;


	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		bird = new Person();
		warrior = new Warrior();
		gameOver = false;
		restartTexture = new Texture("RestartBtn.png");
		start=true;
		canClick=false;
	}

	@Override
	public void render () {
			update();
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			bg.render(batch);
			if (!start) {
				if(!gameOver) {
					bird.render(batch);
					warrior.render(batch);
				}else{
					batch.draw(restartTexture, 400, 500);
				}
			}

			batch.end();
		}




	public void update() {
		bg.update();
		if (!canClick) {
			startGame();
		} else {
			if(start){
				if(Gdx.input.isKeyPressed(Input.Keys.E)) {
					start=false;
				}
			} else {
				bird.update();
				warrior.update();
				for (int i = 0; i < bird.dotsOfTouch().length; i++) {
					if (warrior.rect.contains(bird.dotsOfTouch()[i])) {
						gameOver = true;
					}
				}
				if (gameOver) {
					if (Gdx.input.isKeyPressed(Input.Keys.E)) {
						recreate();
					}

				}
			}

		}
	}

	private void startGame() {
		Pauses pauseFirst = new Pauses();
		canClick=pauseFirst.isPause(1000);
	}


	@Override
	public void dispose () {
		batch.dispose();
	}

	public void recreate(){
		bird.recreate();
		warrior.recreate();
		gameOver = false;
	}
}
