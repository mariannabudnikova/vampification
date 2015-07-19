package com.whimsied.vampification;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by marianna on 6/21/15.
 */
public class BloodProgressBar {

    //private ProgressBar progressBar;
    private Label bloodLevelLabel;

    private float MIN_BLOOD = 0;
    private float MAX_BLOOD = 100;
    private float currBloodLevel;

    public BloodProgressBar(Stage stage){
        currBloodLevel = MIN_BLOOD;
        //progressBar = createProgressBar();
        bloodLevelLabel = ActorFinder.getStatusLabel(stage);
        bloodLevelLabel.setText(getBloodLabel());
    }

    public CharSequence getBloodLabel(){
        return currBloodLevel + "/" + MAX_BLOOD;
    }


//    public ProgressBar getBar(){
//        return progressBar;
//    }

    public void increaseBloodLevel()
    {
        currBloodLevel +=10;
        bloodLevelLabel.setText(getBloodLabel());
        //progressBar.setValue(currBloodLevel);
    }

    public boolean bloodLevelIsAtMax(){
        return currBloodLevel == MAX_BLOOD;
    }

    public void reset() {
        currBloodLevel = MIN_BLOOD;
        bloodLevelLabel.setText("");
    }

//    private ProgressBar createProgressBar() {
//
//        Skin skin = new Skin();
//        Pixmap pixmap = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
//        pixmap.setColor(Color.WHITE);
//        pixmap.fill();
//        skin.add("white", new Texture(pixmap));
//
//        Drawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("bar.png"))));
//        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
//        ProgressBar bar = new ProgressBar(MIN_BLOOD, MAX_BLOOD, 1, true, barStyle);
//        bar.setPosition(420, 200);
//        bar.setSize(290, MAX_BLOOD);
//        bar.setValue(currBloodLevel);
//        bar.setAnimateDuration(2);
//        return bar;
//    }
}
