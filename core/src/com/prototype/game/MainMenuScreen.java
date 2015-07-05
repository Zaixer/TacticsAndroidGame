package com.prototype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenuScreen implements Screen {
    private final PrototypeGame game;
    private Stage stage;

    public MainMenuScreen(PrototypeGame game) {
        this.game = game;
        setupStage();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        setBackgroundColor();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void setupStage() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        addNewGameButtonToStage();
    }

    private void setBackgroundColor() {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void addNewGameButtonToStage() {
        TextButton.TextButtonStyle buttonStyle = getTextButtonStyle();
        TextButton button = new TextButton("New Game", buttonStyle);
        button.setPosition(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2, Gdx.graphics.getHeight() / 2 - button.getHeight() / 2);
        button.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(button);
    }

    private TextButton.TextButtonStyle getTextButtonStyle() {
        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(200, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.Font;
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.LIGHT_GRAY);
        return textButtonStyle;
    }
}