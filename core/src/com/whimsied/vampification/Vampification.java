package com.whimsied.vampification;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.prism.image.ViewPort;

import java.util.Iterator;
import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;

public class Vampification extends ApplicationAdapter {

	private Stage stage;
	ProgressBar progressBar;

	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		VampireActor vampireActor = new VampireActor();

		HumanActor humanActor = new HumanActor();


		progressBar = CreateProgressBar();

		stage.addActor(progressBar);
		stage.addActor(vampireActor);
		stage.addActor(humanActor);

		stage.setKeyboardFocus(vampireActor);
	}

	public ProgressBar CreateProgressBar(){

		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		Drawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("bar.png"))));
		ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
		ProgressBar bar = new ProgressBar(0, 20, 0.5f, true, barStyle);
		bar.setPosition(420, 200);
		bar.setSize(290, bar.getPrefHeight());
		bar.setValue(0);
		bar.setAnimateDuration(2);
		return bar;
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
