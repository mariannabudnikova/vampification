package com.whimsied.vampification;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by marianna on 7/5/15.
 */
public class GuardActor extends Actor {

    Texture texture;
    boolean movingRight = true;

    public GuardActor(float x, float y){
        texture = new Texture(Gdx.files.internal("angel.png"));
        setX(x);
        setY(y);

        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
    }

    @Override
    public void act(float delta){
        move();
        attemptToDetectMischief();
    }

    public void move(){
        if (movingRight && !ActorDistanceCalculator.isOnRightEdgeOfWindow(this, getStage())) {
            setX(getX() + 1);
        }
        if (!movingRight && !ActorDistanceCalculator.isOnLeftEdgeOfWindow(this)) {
            setX(getX() - 1);
        }
        if (ActorDistanceCalculator.isOnRightEdgeOfWindow(this, getStage())){
            movingRight = false;
        }
        if (ActorDistanceCalculator.isOnLeftEdgeOfWindow(this)){
            movingRight = true;
        }
    }

    public void attemptToDetectMischief(){
        VampireActor vampire = ActorDistanceCalculator.getClosestVampire(this, getStage());
        if (ActorDistanceCalculator.closeToInteract(this, vampire) && vampire.isDrinking()){
            //vampire intercepted
            ActorFinder.getStatusLabel(getStage()).setStatus("Intercepted!");

        }
        HumanActor human = ActorDistanceCalculator.getClosestHuman(this, getStage());
        if (ActorDistanceCalculator.closeToInteract(this, human) && human.isUnlocking()){
            //human intercepted
            ActorFinder.getStatusLabel(getStage()).setStatus("Intercepted!");
        }

    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }
}
