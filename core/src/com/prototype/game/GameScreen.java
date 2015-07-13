package com.prototype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;

public class GameScreen implements Screen {
    private static final float PAN_SPEED_FACTOR = 0.5f;
    private final PrototypeGame game;
    private final OrthographicCamera camera;
    private final TiledMap map;
    private final TiledMapRenderer mapRenderer;
    private final int mapWidthInPixels;
    private final int mapHeightInPixels;
    private final Unit unit;

    public GameScreen(PrototypeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        map = new TmxMapLoader().load("example.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, game.SpriteBatch);
        mapWidthInPixels = TiledMapHelper.getWidthInPixels(map);
        mapHeightInPixels = TiledMapHelper.getHeightInPixels(map);
        unit = new Unit(new Texture("Gobbe.png"));
        Gdx.input.setInputProcessor(new GestureDetector(new GestureAdapter()));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
        game.SpriteBatch.begin();
        unit.draw(game.SpriteBatch);
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
    }

    private class GestureAdapter extends GestureDetector.GestureAdapter {
        @Override
        public boolean tap (float x, float y, int count, int button) {
            unit.setCenter(x + CameraHelper.getOffsetX(camera), camera.viewportHeight - y + CameraHelper.getOffsetY(camera));
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            camera.translate(-deltaX * PAN_SPEED_FACTOR, deltaY * PAN_SPEED_FACTOR);
            ensureThatCameraIsWithinMap();
            return false;
        }

        private void ensureThatCameraIsWithinMap() {
            camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2, mapWidthInPixels - camera.viewportWidth / 2);
            camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2, mapHeightInPixels - camera.viewportHeight / 2);
        }
    }
}