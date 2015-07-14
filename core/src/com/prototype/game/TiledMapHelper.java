package com.prototype.game;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class TiledMapHelper {
    public static int getWidthInPixels(TiledMap map) {
        int widthInPixels = getPixelCountOfMapDimension(map, DimensionType.WIDTH);
        return widthInPixels;
    }

    public static int getHeightInPixels(TiledMap map) {
        int heightInPixels = getPixelCountOfMapDimension(map, DimensionType.HEIGHT);
        return heightInPixels;
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
