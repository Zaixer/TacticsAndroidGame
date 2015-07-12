package com.prototype.game;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class TiledMapHelper {
    public static int getWidthInPixels(TiledMap map) {
        int width = getPixelCountOfMapDimension(map, DimensionType.WIDTH);
        return width;
    }

    public static int getHeightInPixels(TiledMap map) {
        int height = getPixelCountOfMapDimension(map, DimensionType.HEIGHT);
        return height;
    }

    private static int getPixelCountOfMapDimension(TiledMap map, DimensionType dimensionType) {
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
        MapProperties properties = map.getProperties();
        int tileCount = properties.get(tileCountPropertyName, Integer.class);
        int tilePixelCount = properties.get(tilePixelCountPropertyName, Integer.class);
        int pixelCountOfMapDimension = tileCount * tilePixelCount;
        return pixelCountOfMapDimension;
    }

    private enum DimensionType {
        WIDTH,
        HEIGHT
    }
}
