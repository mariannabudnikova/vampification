package com.whimsied.vampification;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * Created by marianna on 7/3/15.
 */
public class ActorDistanceCalculator {

    public static Actor getClosestActor(Actor thisActor, Stage stage, Class actorClass){
        float minDistance = Float.MAX_VALUE;
        Actor closestActor = null;

        Array<Actor> actors = stage.getActors();

        for (Actor actor : actors){
            if (actor.getClass().equals(actorClass)){
                float distance = getDistanceBetween(thisActor, actor);
                if (distance < minDistance){
                    minDistance = distance;
                    closestActor = actor;
                }
            }
        }
        return closestActor;
    }

    public static HumanActor getClosestHuman(Actor thisActor, Stage stage){
        return (HumanActor) getClosestActor(thisActor, stage, HumanActor.class);
    }

    public static DoorActor getClosestDoor(Actor thisActor, Stage stage){
        return (DoorActor) getClosestActor(thisActor, stage, DoorActor.class);
    }

    public static VampireActor getClosestVampire(Actor thisActor, Stage stage){
        return (VampireActor) getClosestActor(thisActor, stage, VampireActor.class);
    }


    public static float getDistanceBetween(Actor actor1, Actor actor2){
        Vector actor1Location = new Vector2(actor1.getX(), actor1.getY());
        Vector actor2Location = new Vector2(actor2.getX(), actor2.getY());
        return actor1Location.dst(actor2Location);
    }

    public static boolean closeToInteract(Actor actor1, Actor actor2){
        if (actor1 == null || actor2 == null) return false;
        float distance = ActorDistanceCalculator.getDistanceBetween(actor1, actor2);
        float actor1Width = actor1.getWidth();
        float actor2Width = actor2.getWidth();
        boolean actor1IsToTheLeft = actor1.getX() < actor2.getX();
        return actor1IsToTheLeft ? distance < actor1Width : distance < actor2Width ;
    }

    public static boolean isOnLeftEdgeOfWindow(Actor actor){

        float leftPosition = actor.getX();
        return leftPosition <= 0;
    }

    public static boolean isOnRightEdgeOfWindow(Actor actor, Stage stage){
        float rightPosition = actor.getX() + actor.getWidth();
        return rightPosition >= stage.getWidth();
    }
}
