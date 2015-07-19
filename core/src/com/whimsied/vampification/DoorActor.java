package com.whimsied.vampification;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
* Created by marianna on 7/3/15.
*/
public class DoorActor extends Actor{
    Texture texture;
    boolean locked = true;

    public DoorActor(float x, float y)
    {
        texture = new Texture(Gdx.files.internal("door_closed.png"));
        setX(x);
        setY(y);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());

    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }

    public void unlock() {
        texture = new Texture(Gdx.files.internal("door_open.png"));
        locked = false;
    }

    public boolean isLocked(){
        return locked;
    }
}
