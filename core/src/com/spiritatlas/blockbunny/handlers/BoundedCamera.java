package com.spiritatlas.blockbunny.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * OrthographicCamera that cannot go beyond specified rectangle.
 */
public class BoundedCamera extends OrthographicCamera {

    private float xmin;
    private float xmax;
    private float ymin;
    private float ymax;

    public BoundedCamera() {
        this(0, 0, 0, 0);
    }

    public BoundedCamera(float xmin, float xmax, float ymin, float ymax) {
        super();
        setBounds(xmin, xmax, ymin, ymax);
    }

    public void setBounds(float xmin, float xmax, float ymin, float ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public void setPosition(float x, float y) {
        setPosition(x, y, 0);
    }

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
        fixBounds();
    }

    private void fixBounds() {
        if (position.x < xmin + viewportWidth / 2) {
            position.x = xmin + viewportWidth / 2;
        }
        if (position.x > xmax - viewportWidth / 2) {
            position.x = xmax - viewportWidth / 2;
        }
        if (position.y < ymin + viewportHeight / 2) {
            position.y = ymin + viewportHeight / 2;
        }
        if (position.y > ymax - viewportHeight / 2) {
            position.y = ymax - viewportHeight / 2;
        }
    }

}
