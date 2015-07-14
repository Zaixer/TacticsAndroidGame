package com.prototype.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TiledMapStage extends Stage {
    public TiledMapStage(Viewport viewport, Batch batch, TiledMap tiledMap, final ITileClickedCallback tileClickedCallback) {
        super(viewport, batch);
        TiledMapTileLayer backgroundLayer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
        for (int x = 0; x < backgroundLayer.getWidth(); x++) {
            for (int y = 0; y < backgroundLayer.getHeight(); y++) {
                final Actor tileActor = new Actor();
                tileActor.setBounds(x * backgroundLayer.getTileWidth(), y * backgroundLayer.getTileHeight(), backgroundLayer.getTileWidth(), backgroundLayer.getTileHeight());
                tileActor.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        tileClickedCallback.onClick(tileActor);
                    }
                });
                addActor(tileActor);
            }
        }
    }
}