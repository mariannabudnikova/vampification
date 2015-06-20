package com.whimsied.vampification;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.prism.image.ViewPort;

import java.util.Iterator;
import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;

public class Vampification extends ApplicationAdapter {

	private Stage stage;

	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		VampireActor vampireActor = new VampireActor();

		HumanActor humanActor = new HumanActor();

		ProgressBar progressBar = new ProgressBar(0, 100, 20, true, new ProgressBar.ProgressBarStyle());

		stage.addActor(vampireActor);
		stage.addActor(humanActor);
		stage.addActor(progressBar);
		stage.setKeyboardFocus(vampireActor);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
