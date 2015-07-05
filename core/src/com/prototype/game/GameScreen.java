package com.prototype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
    private final PrototypeGame game;
    private static final int WORLD_WIDTH = 2048;
    private static final int WORLD_HEIGHT = 2048;
    private OrthographicCamera camera;
    private Sprite mapSprite;

    public GameScreen(PrototypeGame game) {
        this.game = game;
        setupMap();
        setupCamera();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ensureThatCameraIsWithinMap();
        camera.update();
        game.SpriteBatch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.SpriteBatch.begin();
        mapSprite.draw(game.SpriteBatch);
        game.SpriteBatch.end();
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
        mapSprite.getTexture().dispose();
    }

    private void setupMap() {
        mapSprite = new Sprite(new Texture(Gdx.files.internal("sc_map.png")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
    }

    private void setupCamera() {
        Gdx.input.setInputProcessor(new GestureDetector(new CameraPanGestureListener()));
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    private void ensureThatCameraIsWithinMap() {
        camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2, WORLD_WIDTH - camera.viewportWidth / 2);
        camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2, WORLD_HEIGHT - camera.viewportHeight / 2);
    }

    private class CameraPanGestureListener implements GestureDetector.GestureListener {
        private static final float PAN_SPEED_FACTOR = 0.5f;

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            camera.translate(-deltaX * PAN_SPEED_FACTOR, deltaY * PAN_SPEED_FACTOR);
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom (float originalDistance, float currentDistance){
            return false;
        }

        @Override
        public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer){
            return false;
        }
    }
}