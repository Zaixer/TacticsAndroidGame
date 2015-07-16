package com.prototype.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ArcherUnitActor extends UnitActor {
    @Override
    protected Sprite getSprite() {
        return new Sprite(new Texture("Gobbe.png"));
    }

    @Override
    public int getMoveDistance() {
        return 5;
    }
}