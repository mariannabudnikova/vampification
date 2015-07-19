package com.whimsied.vampification;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by marianna on 6/20/15.
 */
public abstract class WalkableActor extends Actor {

    private boolean movingRight = false;
    private boolean movingLeft = false;
    public WalkableActor(){
        addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.D:
                        movingRight = true;
                        break;
                    case Input.Keys.A:
                        movingLeft = true;
                        break;
                }
                return true;
            }
            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.D:
                        movingRight = false;
                        break;
                    case Input.Keys.A:
                        movingLeft = false;
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void act(float delta){
        if (canMove() && movingRight)
            moveRight();
        else if (canMove() && movingLeft)
            moveLeft();
    }

    public void moveRight(){
        getStage().getCamera().translate(5, 0, 0);
        getStage().getCamera().update();

        setX(getX() + 5);
    }

    public void moveLeft(){
        getStage().getCamera().translate(-5, 0, 0);
        getStage().getCamera().update();

        setX(getX() - 5);
    }

    public boolean canMove()
    {
        return true;
    }
}
