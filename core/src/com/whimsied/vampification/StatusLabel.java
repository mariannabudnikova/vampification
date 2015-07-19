package com.whimsied.vampification;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by marianna on 7/19/15.
 */
public class StatusLabel extends Label {
    private String status;

    public StatusLabel(CharSequence text, float x, float y){
        super(text, new Label.LabelStyle(new BitmapFont(), Color.RED));
        setX(x);
        setY(y);
    }

    public void setStatus(String status){
        this.status = status;
        setText(this.status);
    }
}
