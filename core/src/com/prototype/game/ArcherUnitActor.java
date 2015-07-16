package com.prototype.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ArcherUnitActor extends UnitActor {
    public ArcherUnitActor(IUnitClickedCallback unitClickedCallback) {
        super(unitClickedCallback);
    }

    @Override
    protected Sprite getSprite() {
        return new Sprite(new Texture("Gobbe.png"));
    }

    @Override
    public int getMoveDistance() {
        return 5;
    }
}