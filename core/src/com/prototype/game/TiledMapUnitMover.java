package com.prototype.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class TiledMapUnitMover {
    private static final float MOVE_DISTANCE_PER_SECOND = 64;
    private SelectionState currentSelectionState = SelectionState.NothingSelected;
    private UnitActor currentlySelectedUnitActor;

    public void moveAnySelectedUnitToTile(Actor tileActor) {
        if (currentSelectionState == SelectionState.UnitActorSelected) {
            currentlySelectedUnitActor.addAction(Actions.moveTo(tileActor.getX(), tileActor.getY(), getMoveDuration(currentlySelectedUnitActor, tileActor)));
            resetSelection();
        }
    }

    public void selectUnit(UnitActor unitActor) {
        currentlySelectedUnitActor = unitActor;
        currentSelectionState = SelectionState.UnitActorSelected;
    }

    public void resetSelection() {
        currentlySelectedUnitActor = null;
        currentSelectionState = SelectionState.NothingSelected;
    }

    private final float getMoveDuration(UnitActor unitActor, Actor tileActor) {
        float moveDistance = (float)Math.sqrt(Math.pow(tileActor.getX() - unitActor.getX(), 2) + Math.pow(tileActor.getY() - unitActor.getY(), 2));
        float moveDuration = moveDistance / MOVE_DISTANCE_PER_SECOND;
        return moveDuration;
    }

    private enum SelectionState {
        NothingSelected,
        UnitActorSelected
    }
}
