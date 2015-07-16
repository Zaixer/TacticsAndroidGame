package com.prototype.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;

public class TiledMapCameraMover {
    private static final float PAN_SPEED_FACTOR = 0.5f;
    private final OrthographicCamera camera;
    private final int mapWidthInPixels;
    private final int mapHeightInPixels;

    public TiledMapCameraMover(OrthographicCamera camera, TiledMap map) {
        this.camera = camera;
        mapWidthInPixels = getWidthInPixels(map);
        mapHeightInPixels = getHeightInPixels(map);
    }

    public void panCamera(float deltaX, float deltaY) {
        camera.translate(-deltaX * PAN_SPEED_FACTOR, -deltaY * PAN_SPEED_FACTOR);
        ensureThatCameraIsWithinMap(camera);
    }

    private void ensureThatCameraIsWithinMap(Camera camera) {
        camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2, mapWidthInPixels - camera.viewportWidth / 2);
        camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2, mapHeightInPixels - camera.viewportHeight / 2);
    }

    private int getWidthInPixels(TiledMap map) {
        int widthInPixels = getPixelCountOfMapDimension(map, DimensionType.WIDTH);
        return widthInPixels;
    }

    private int getHeightInPixels(TiledMap map) {
        int heightInPixels = getPixelCountOfMapDimension(map, DimensionType.HEIGHT);
        return heightInPixels;
    }

    private int getPixelCountOfMapDimension(TiledMap map, DimensionType dimensionType) {
        String tileCountPropertyName;
        String tilePixelCountPropertyName;
        switch (dimensionType) {
            case WIDTH:
                tileCountPropertyName = "width";
                tilePixelCountPropertyName = "tilewidth";
                break;
            case HEIGHT:
                tileCountPropertyName = "height";
                tilePixelCountPropertyName = "tileheight";
                break;
            default:
                return 0;
        }
        int tileCount = map.getProperties().get(tileCountPropertyName, Integer.class);
        int tilePixelCount = map.getProperties().get(tilePixelCountPropertyName, Integer.class);
        int pixelCountOfMapDimension = tileCount * tilePixelCount;
        return pixelCountOfMapDimension;
    }

    private enum DimensionType {
        WIDTH,
        HEIGHT
    }
}
