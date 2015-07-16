package com.prototype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    private final PrototypeGame game;
    private final OrthographicCamera camera;
    private final TiledMapStage stage;
    private final TiledMap map;
    private final TiledMapRenderer mapRenderer;
    private final TiledMapCameraMover cameraMover;
    private final TiledMapUnitMover unitMover;

    public GameScreen(PrototypeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        map = new TmxMapLoader().load("example.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, game.SpriteBatch);
        stage = new TiledMapStage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera), game.SpriteBatch, map, new TileClickedCallback());
        stage.addActor(new ArcherUnitActor(new UnitClickedCallback()));
        stage.addListener(new ActorGestureListener() {
            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                cameraMover.panCamera(deltaX, deltaY);
            }
        });
        cameraMover = new TiledMapCameraMover(camera, map);
        unitMover = new TiledMapUnitMover();
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

    private class UnitClickedCallback implements IUnitClickedCallback {
        @Override
        public void onClick(UnitActor unitActor) {
            unitMover.selectUnit(unitActor);
        }
    }

    private class TileClickedCallback implements ITileClickedCallback {
        @Override
        public void onClick(Actor tileActor) {
            unitMover.moveAnySelectedUnitToTile(tileActor);
        }
    }
}