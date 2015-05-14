package com.spiritatlas.blockbunny.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.spiritatlas.blockbunny.main.Game;

public class Crystal extends B2DSprite {

    public Crystal(Body body) {

        super(body);

        Texture tex = Game.res.getTexture("crystal");
        TextureRegion[] sprites = TextureRegion.split(tex, 16, 16)[0];
        animation.setFrames(sprites, 1 / 30f);

        width = sprites[0].getRegionWidth();
        height = sprites[0].getRegionHeight();

    }

}
