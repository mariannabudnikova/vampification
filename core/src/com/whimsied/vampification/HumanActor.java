package com.whimsied.vampification;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by marianna on 6/20/15.
 */
public class HumanActor extends WalkableActor {
    Texture texture;

    public HumanActor(float x, float y)
    {
        texture = new Texture(Gdx.files.internal("human.png"));
        setX(x);
        setY(y);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());

    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }


}
