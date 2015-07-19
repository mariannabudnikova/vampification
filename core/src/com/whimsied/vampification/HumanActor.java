package com.whimsied.vampification;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by marianna on 6/20/15.
 */
public class HumanActor extends WalkableActor {
    Texture texture;
    boolean currentlyInteracting = false;
    BloodProgressBar progressBar;

    public HumanActor(float x, float y)
    {
        texture = new Texture(Gdx.files.internal("human.png"));
        setX(x);
        setY(y);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());

        addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        interactIfPossible();
                        break;
                }
                return true;
            }

            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        stopInteracting();
                        break;
                }
                return true;
            }
        });
    }

    public void interactIfPossible(){
        DoorActor door = ActorDistanceCalculator.getClosestDoor(this, getStage());
        if (!currentlyInteracting && ActorDistanceCalculator.closeToInteract(this, door) && door.isLocked()){
            progressBar = new BloodProgressBar(getStage());
            startInteracting();
        }
        else if (ActorDistanceCalculator.closeToInteract(this, door) && !ActorDistanceCalculator.getClosestDoor(this, getStage()).isLocked()){
            ActorFinder.getStatusLabel(getStage()).setStatus("Door is already opened!");
        }
    }

    public void stopInteracting(){
        if (currentlyInteracting) {
            currentlyInteracting = false;
            if (progressBar.bloodLevelIsAtMax()) {
                unlock(ActorDistanceCalculator.getClosestDoor(this, getStage()));
            }
            progressBar.reset();
        }
        ActorFinder.getStatusLabel(getStage()).setStatus("");
    }

    public void startInteracting(){
        currentlyInteracting = true;
        Timer.Task drinkingTask = new Timer.Task() {
            @Override
            public void run() {
                if (currentlyInteracting && !progressBar.bloodLevelIsAtMax()) {
                    progressBar.increaseBloodLevel();
                }
            }
        };
        Timer.schedule(drinkingTask, 0, 0.1f);
    }

    public boolean isUnlocking(){
        return currentlyInteracting;
    }

    public void unlock(DoorActor door){
        door.unlock();
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }


}
