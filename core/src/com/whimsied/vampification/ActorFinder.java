package com.whimsied.vampification;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by marianna on 7/19/15.
 */
public class ActorFinder {

    public static StatusLabel getStatusLabel(Stage stage){
        for(Actor actor: stage.getActors()){
            if (actor.getClass().equals(StatusLabel.class)){
                return (StatusLabel) actor;
            }
        }
        return null;
    }
}
