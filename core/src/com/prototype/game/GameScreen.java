package com.prototype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private final PrototypeGame game;
    private final OrthographicCamera camera;
    private final TiledMapStage stage;
    private final TiledMap map;
    private final TiledMapRenderer mapRenderer;
    private final List<UnitActor> unitActors;
    private UnitActor currentlySelectedUnitActor;
    private SelectionState currentSelectionState = SelectionState.NothingSelected;

    public GameScreen(PrototypeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        map = new TmxMapLoader().load("example.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, game.SpriteBatch);
        stage = new TiledMapStage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera), game.SpriteBatch, map, new TileClickedCallback());
        stage.addListener(new CameraPanActorGestureListener(map));
        unitActors = new ArrayList<UnitActor>();
        unitActors.add(new ArcherUnitActor());
        for (UnitActor unitActor : unitActors) {
            stage.addActor(unitActor);
        }
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

    private class CameraPanActorGestureListener extends ActorGestureListener {
        private static final float PAN_SPEED_FACTOR = 0.5f;
        private final int mapWidthInPixels;
        private final int mapHeightInPixels;

        public CameraPanActorGestureListener(TiledMap map) {
            mapWidthInPixels = TiledMapHelper.getWidthInPixels(map);
            mapHeightInPixels = TiledMapHelper.getHeightInPixels(map);
        }

        @Override
        public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
            camera.translate(-deltaX * PAN_SPEED_FACTOR, -deltaY * PAN_SPEED_FACTOR);
            ensureThatCameraIsWithinMap(camera);
        }

        private void ensureThatCameraIsWithinMap(Camera camera) {
            camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2, mapWidthInPixels - camera.viewportWidth / 2);
            camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2, mapHeightInPixels - camera.viewportHeight / 2);
        }
    }

    private class TileClickedCallback implements ITileClickedCallback {
        @Override
        public void onClick(Actor tileActor) {
            for (UnitActor unitActor : unitActors) {
                unitActor.addAction(Actions.moveTo(tileActor.getX(), tileActor.getY(), 5));
            }
        }
    }

    private enum SelectionState {
        NothingSelected,
        UnitActorSelected
    }
}