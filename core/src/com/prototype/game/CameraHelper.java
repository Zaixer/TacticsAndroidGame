package com.prototype.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraHelper {
    public static float getOffsetX(OrthographicCamera camera) {
        float cameraOffsetX = camera.position.x - camera.viewportWidth / 2f;
        return cameraOffsetX;
    }

    public static float getOffsetY(OrthographicCamera camera) {
        float cameraOffsetY = camera.position.y - camera.viewportHeight / 2f;
        return cameraOffsetY;
    }
}
