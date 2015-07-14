package com.prototype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    private static final float PAN_SPEED_FACTOR = 0.5f;
    private final PrototypeGame game;
    private final OrthographicCamera camera;
    private final TiledMapStage stage;
    private final TiledMap map;
    private final TiledMapRenderer mapRenderer;
    private final int mapWidthInPixels;
    private final int mapHeightInPixels;
    private final UnitActor unit;

    public GameScreen(PrototypeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        map = new TmxMapLoader().load("example.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, game.SpriteBatch);
        mapWidthInPixels = TiledMapHelper.getWidthInPixels(map);
        mapHeightInPixels = TiledMapHelper.getHeightInPixels(map);
        stage = new TiledMapStage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera), game.SpriteBatch, map, new CellClickedCallback());
        unit = new UnitActor(new Sprite(new Texture("Gobbe.png")));
        stage.addActor(unit);
        stage.addListener(new ActorGestureListener() {
            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                camera.translate(-deltaX * PAN_SPEED_FACTOR, -deltaY * PAN_SPEED_FACTOR);
                ensureThatCameraIsWithinMap();
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.setView(camera);
        mapRenderer.render();
        stage.act();
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
    }

    private void ensureThatCameraIsWithinMap() {
        camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2, mapWidthInPixels - camera.viewportWidth / 2);
        camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2, mapHeightInPixels - camera.viewportHeight / 2);
    }

    private class CellClickedCallback implements ITileClickedCallback {
        @Override
        public void onClick(Actor tileActor) {
            unit.addAction(Actions.moveTo(tileActor.getX(), tileActor.getY(), 5));
        }
    }
}