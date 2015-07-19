package com.whimsied.vampification;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Vampification extends ApplicationAdapter {

	private Stage stage;
    private Texture background;
    private float FLOOR = 130;

	@Override
	public void create() {
		Camera camera = new OrthographicCamera();
		Viewport vp = new FitViewport(640, 300, camera);

		stage = new Stage(vp);
        background = new Texture(Gdx.files.internal("castle.png"));
		Gdx.input.setInputProcessor(stage);

        DoorActor doorActor = new DoorActor(450, FLOOR+35);
		VampireActor vampireActor = new VampireActor(stage.getWidth()/2, FLOOR);
		HumanActor humanActor = new HumanActor(50, FLOOR);
        GuardActor guardActor = new GuardActor(0, FLOOR);
        StatusLabel statusLabel = new StatusLabel("", 300, 200);

        stage.addActor(doorActor);
		stage.addActor(humanActor);
        stage.addActor(guardActor);
		stage.addActor(vampireActor);
        stage.addActor(statusLabel);

		stage.setKeyboardFocus(vampireActor);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
        drawBackground();
		stage.draw();
	}

    public void drawBackground(){
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, 2000, 480);
        stage.getBatch().end();
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
