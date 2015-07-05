package com.prototype.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PrototypeGame extends Game {
    public SpriteBatch SpriteBatch;
	public BitmapFont Font;

	@Override
	public void create() {
		SpriteBatch = new SpriteBatch();
        Font = new BitmapFont();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

    @Override
	public void dispose() {
		super.dispose();
	}
}