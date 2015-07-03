package com.whimsied.vampification;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by marianna on 6/20/15.
 */
public class VampireActor extends WalkableActor {

    Texture texture;
    boolean currentlyDrinking = false;
    BloodProgressBar bloodProgressBar;

    public VampireActor(float x, float y)
    {
        texture = new Texture(Gdx.files.internal("vampire.png"));
        setX(x);
        setY(y);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());

        addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        drinkIfPossible();
                        break;
                }
                return true;
            }

            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        stopDrinking();
                        break;
                }
                return true;
            }
        });
    }

    public void drinkIfPossible(){
        HumanActor human = ActorDistanceCalculator.getClosestHuman(this, getStage());
        if (!currentlyDrinking && ActorDistanceCalculator.closeToInteract(this, human)){
            bloodProgressBar = new BloodProgressBar();
            getStage().addActor(bloodProgressBar.getLabel());
            startDrinking();
        }
    }

    public void stopDrinking(){
        if (currentlyDrinking) {
            currentlyDrinking = false;
            if (bloodProgressBar.bloodLevelIsAtMax()) {
                setVisible(false);
                possess(ActorDistanceCalculator.getClosestHuman(this, getStage()));
            }
        }
    }

    public void startDrinking(){
        currentlyDrinking = true;
        Timer.Task drinkingTask = new Timer.Task() {
            @Override
            public void run() {
                if (currentlyDrinking && !bloodProgressBar.bloodLevelIsAtMax()) {
                    bloodProgressBar.increaseBloodLevel();
                }
            }
        };
        Timer.schedule(drinkingTask, 0, 0.1f);
    }

    public void possess(HumanActor human){
        getStage().setKeyboardFocus(human);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }

    public boolean canMove(){
        return !currentlyDrinking;
    }
}
