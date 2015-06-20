package com.whimsied.vampification;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by marianna on 6/20/15.
 */
public class VampireActor extends WalkableActor {

    Texture texture;

    public VampireActor()
    {
        texture = new Texture(Gdx.files.internal("vampire.png"));
        setX(500);
        setY(50);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());

    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }
}
