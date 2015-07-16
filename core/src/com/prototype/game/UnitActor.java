package com.prototype.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class UnitActor extends Actor {
    private final Sprite sprite;

    public UnitActor() {
        super();
        sprite = getSprite();
        setSize(sprite.getWidth(), sprite.getHeight());
    }

    @Override
     public void draw (Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public abstract int getMoveDistance();

    protected abstract Sprite getSprite();
}
