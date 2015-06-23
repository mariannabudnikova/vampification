package com.whimsied.vampification;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by marianna on 6/20/15.
 */
public class VampireActor extends WalkableActor {

    Texture texture;
    boolean currentlyDrinking = false;
    BloodProgressBar bloodProgressBar;

    public VampireActor()
    {
        texture = new Texture(Gdx.files.internal("vampire.png"));
        setX(500);
        setY(50);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());

        addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        HumanActor human = getClosestHuman();

                        if (!currentlyDrinking && canStartDrinkingBloodFromHuman(human)){
                            bloodProgressBar = new BloodProgressBar();
                            getStage().addActor(bloodProgressBar.getBar());

                            startDrinking();
                        }
                        break;
                }
                return true;
            }

            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        if (currentlyDrinking) {
                            currentlyDrinking = false;
                            bloodProgressBar.getBar().remove();
                        }
                        break;
                }
                return true;
            }
        });
    }

    public boolean canStartDrinkingBloodFromHuman (Actor human){
        if (human == null) return false;
        float distance = getDistanceFromHuman(human);
        float vampireWidth = getWidth();
        float humanWidth = human.getWidth();
        boolean vampireIsToTheLeft = getX() < human.getX();
        return vampireIsToTheLeft ? distance < vampireWidth : distance < humanWidth ;
    }

    public void startDrinking(){
        currentlyDrinking = true;
        Timer.schedule(new Timer.Task() {
                   @Override
                   public void run() {
                       if (!bloodProgressBar.bloodLevelIsAtMax()) {
                           bloodProgressBar.increaseBloodLevel();
                       }
                   }
               },
            0,
            1
        );
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }

    public HumanActor getClosestHuman(){
        float minDistance = Float.MAX_VALUE;
        HumanActor closestHuman = null;
        Array<Actor> actors = getStage().getActors();

        for (Actor actor : actors){
            if (actor.getClass().equals(HumanActor.class)){
                float distance = getDistanceFromHuman(actor);
                if (distance < minDistance){
                    minDistance = distance;
                    closestHuman = (HumanActor)actor;
                }
            }
        }
        return closestHuman;
    }

    public float getDistanceFromHuman(Actor human){
        Vector vampireLocation = new Vector2(getX(), getY());
        Vector humanLocation = new Vector2(human.getX(), human.getY());
        return vampireLocation.dst(humanLocation);
    }
}
