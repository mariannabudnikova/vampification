package com.whimsied.vampification;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by marianna on 6/21/15.
 */
public class BloodProgressBar {

    private ProgressBar bloodProgressBar;

    private float MIN_BLOOD = 0;
    private float MAX_BLOOD = 100;
    private float currBloodLevel;

    public BloodProgressBar(){
        currBloodLevel = 0;
        bloodProgressBar = createProgressBar();
    }

    public ProgressBar getBar(){
        return bloodProgressBar;
    }

    public void increaseBloodLevel()
    {
        currBloodLevel +=100;
        bloodProgressBar.setValue(currBloodLevel);
    }

    public boolean bloodLevelIsAtMax(){
        return currBloodLevel == MAX_BLOOD;
    }

    private ProgressBar createProgressBar() {

        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        Drawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("bar.png"))));
        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
        ProgressBar bar = new ProgressBar(MIN_BLOOD, MAX_BLOOD, 1, true, barStyle);
        bar.setPosition(420, 200);
        bar.setSize(290, bar.getPrefHeight());
        bar.setValue(currBloodLevel);
        bar.setAnimateDuration(2);
        return bar;
    }
}
