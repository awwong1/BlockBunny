package com.spiritatlas.blockbunny.states;

import static com.spiritatlas.blockbunny.handlers.B2DVars.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.spiritatlas.blockbunny.entities.B2DSprite;
import com.spiritatlas.blockbunny.handlers.Animation;
import com.spiritatlas.blockbunny.handlers.B2DVars;
import com.spiritatlas.blockbunny.handlers.Background;
import com.spiritatlas.blockbunny.handlers.GameButton;
import com.spiritatlas.blockbunny.handlers.GameStateManager;
import com.spiritatlas.blockbunny.main.Game;

public class Menu extends GameState {

    private boolean debug = false;

    private Background bg;
    private Animation animation;
    private GameButton playButton;

    private World world;
    private Box2DDebugRenderer b2dRenderer;

    private Array<B2DSprite> blocks;

    public Menu(GameStateManager gsm) {

        super(gsm);

        Texture tex = Game.res.getTexture("menu");
        bg = new Background(new TextureRegion(tex), cam, 1f);
        bg.setVector(-20, 0);

        tex = Game.res.getTexture("bunny");
        TextureRegion[] reg = new TextureRegion[4];
        for (int i = 0; i < reg.length; i++) {
            reg[i] = new TextureRegion(tex, i * 32, 0, 32, 32);
        }
        animation = new Animation(reg, 1 / 12f);

        tex = Game.res.getTexture("hud");
        playButton = new GameButton(new TextureRegion(tex, 0, 34, 58, 27), 160, 100, cam);

        cam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);

        world = new World(new Vector2(0, -9.8f * 5), true);
        //world = new World(new Vector2(0, 0), true);
        b2dRenderer = new Box2DDebugRenderer();

        createTitleBodies();

    }

    private void createTitleBodies() {

        // top platform
        BodyDef tpbdef = new BodyDef();
        tpbdef.type = BodyType.StaticBody;
        tpbdef.position.set(160 / PPM, 180 / PPM);
        Body tpbody = world.createBody(tpbdef);
        PolygonShape tpshape = new PolygonShape();
        tpshape.setAsBox(120 / PPM, 1 / PPM);
        FixtureDef tpfdef = new FixtureDef();
        tpfdef.shape = tpshape;
        tpfdef.filter.categoryBits = B2DVars.BIT_TOP_PLATFORM;
        tpfdef.filter.maskBits = B2DVars.BIT_TOP_BLOCK;
        tpbody.createFixture(tpfdef);
        tpshape.dispose();

        // bottom platform
        BodyDef bpbdef = new BodyDef();
        bpbdef.type = BodyType.StaticBody;
        bpbdef.position.set(160 / PPM, 130 / PPM);
        Body bpbody = world.createBody(bpbdef);
        PolygonShape bpshape = new PolygonShape();
        bpshape.setAsBox(120 / PPM, 1 / PPM);
        FixtureDef bpfdef = new FixtureDef();
        bpfdef.shape = bpshape;
        bpfdef.filter.categoryBits = B2DVars.BIT_BOTTOM_PLATFORM;
        bpfdef.filter.maskBits = B2DVars.BIT_BOTTOM_BLOCK;
        bpbody.createFixture(bpfdef);
        bpshape.dispose();

        Texture tex = Game.res.getTexture("hud");
        TextureRegion[] blockSprites = new TextureRegion[3];
        for (int i = 0; i < blockSprites.length; i++) {
            blockSprites[i] = new TextureRegion(tex, 58 + i * 5, 34, 5, 5);
        }
        blocks = new Array<B2DSprite>();

        int[][] spellBlock = {
                {1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
                {1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1}
        };
        int[][] spellBunny = {
                {1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0},
                {1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
        };

        // top blocks
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 29; col++) {
                BodyDef tbbdef = new BodyDef();
                tbbdef.type = BodyType.DynamicBody;
                tbbdef.fixedRotation = true;
                tbbdef.position.set((62 + col * 6 + col) / PPM, (270 - row * 6 + row) / PPM);
                Body tbbody = world.createBody(tbbdef);
                PolygonShape tbshape = new PolygonShape();
                tbshape.setAsBox(2f / PPM, 2f / PPM);
                FixtureDef tbfdef = new FixtureDef();
                tbfdef.shape = tbshape;
                tbfdef.filter.categoryBits = B2DVars.BIT_TOP_BLOCK;
                tbfdef.filter.maskBits = B2DVars.BIT_TOP_PLATFORM | B2DVars.BIT_TOP_BLOCK;
                tbbody.createFixture(tbfdef);
                tbshape.dispose();
                if (spellBlock[row][col] == 1) {
                    B2DSprite sprite = new B2DSprite(tbbody);
                    sprite.setAnimation(blockSprites[MathUtils.random(2)], 0);
                    blocks.add(sprite);
                }
            }
        }

        // bottom blocks
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 29; col++) {
                BodyDef bbbdef = new BodyDef();
                bbbdef.type = BodyType.DynamicBody;
                bbbdef.fixedRotation = true;
                bbbdef.position.set((62 + col * 6 + col) / PPM, (270 - row * 6 + row) / PPM);
                Body bbbody = world.createBody(bbbdef);
                PolygonShape bbshape = new PolygonShape();
                bbshape.setAsBox(2f / PPM, 2f / PPM);
                FixtureDef bbfdef = new FixtureDef();
                bbfdef.shape = bbshape;
                bbfdef.filter.categoryBits = B2DVars.BIT_BOTTOM_BLOCK;
                bbfdef.filter.maskBits = B2DVars.BIT_BOTTOM_PLATFORM | B2DVars.BIT_BOTTOM_BLOCK;
                bbbody.createFixture(bbfdef);
                bbshape.dispose();
                if (spellBunny[row][col] == 1) {
                    B2DSprite sprite = new B2DSprite(bbbody);
                    sprite.setAnimation(blockSprites[MathUtils.random(2)], 0);
                    blocks.add(sprite);
                }
            }
        }

    }

    public void handleInput() {

        // mouse/touch input
        if (playButton.isClicked()) {
            Game.res.getSound("crystal").play();
            gsm.setState(GameStateManager.LEVEL_SELECT);
        }

    }

    public void update(float dt) {

        handleInput();

        world.step(dt / 5, 8, 3);

        bg.update(dt);
        animation.update(dt);

        playButton.update(dt);

    }

    public void render() {

        sb.setProjectionMatrix(cam.combined);

        // draw background
        bg.render(sb);

        // draw button
        playButton.render(sb);

        // draw bunny
        sb.begin();
        sb.draw(animation.getFrame(), 146, 31);
        sb.end();

        // debug draw box2d
        if (debug) {
            cam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);
            b2dRenderer.render(world, cam.combined);
            cam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);
        }

        // draw title
        for (int i = 0; i < blocks.size; i++) {
            blocks.get(i).render(sb);
        }

    }

    public void dispose() {
        // everything is in the resource manager com.neet.blockbunny.handlers.Content
    }

}
