package io.github.robhinds.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import io.github.robhinds.actors.Background;
import io.github.robhinds.actors.Cloud;
import io.github.robhinds.actors.Enemy;
import io.github.robhinds.actors.Ground;
import io.github.robhinds.actors.Runner;
import io.github.robhinds.actors.Score;
import io.github.robhinds.utils.BodyUtils;
import io.github.robhinds.utils.Constants;
import io.github.robhinds.utils.WorldUtils;

public class GameStage extends Stage implements ContactListener {

    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private World world;
    private Ground ground;
    private Runner runner;
    private Score score;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private Rectangle screenLeftSide;
    private Rectangle screenRightSide;
    private Vector3 touchPoint;

    public GameStage() {
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        setUpWorld();
        setupCamera();
        setupTouchControlAreas();
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        setUpGround();
        createCloud();
        setUpScore();
        setUpRunner();
        createEnemy();
    }

    private void createCloud() {
        addActor(new Cloud());
    }

    private void setUpBackground() {
        addActor(new Background());
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world));
        addActor(runner);
    }

    private void setUpScore() {
        Rectangle scoreBounds = new Rectangle(2,
                getCamera().viewportHeight * 62 / 64, getCamera().viewportWidth / 4,
                getCamera().viewportHeight / 8);
        score = new Score(scoreBounds);
        addActor(score);
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenLeftSide = new Rectangle(
                0,
                0,
                getCamera().viewportWidth / 2,
                getCamera().viewportHeight
        );
        screenRightSide = new Rectangle(
                getCamera().viewportWidth/2,
                0,
                getCamera().viewportWidth / 2,
                getCamera().viewportHeight
        );
        Gdx.input.setInputProcessor(this);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void updateEnemies(Body body) {
        if (!BodyUtils.bodyInBounds(body)) {
            createEnemy();
            world.destroyBody(body);
        }
    }

    private void updateRunner(Body body) {
        if (!BodyUtils.bodyInBounds(body)) {
            setUpRunner();
            score.setScore(0f);
            world.destroyBody(body);
        } else if (!BodyUtils.runnerIsCentered(body)) {
            if (body.getPosition().x > Constants.RUNNER_X) {
                body.applyLinearImpulse(Constants.RUNNER_RUN_BACK_LINEAR_IMPULSE, body.getWorldCenter(), true);
            } else {
                body.applyLinearImpulse(Constants.RUNNER_RUN_FORWARD_LINEAR_IMPULSE, body.getWorldCenter(), true);
            }
        }
    }

    private void createEnemy() {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
        addActor(enemy);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (BodyUtils.bodyIsEnemy(body)) {
                updateEnemies(body);
            }
        }
        for (Body body : bodies) {
            if (BodyUtils.bodyIsRunner(body)) {
                updateRunner(body);
            }
        }

        // Fixed timestep
        accumulator += delta;
        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        //TODO: Implement interpolation
    }

    @Override public boolean touchDown(int x, int y, int pointer, int button) {
        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);

        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            runner.jump();
        } else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
            //runner.dodge();
            runner.jump();
        }

        return super.touchDown(x, y, pointer, button);
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (runner.isDodging()) {
            runner.stopDodge();
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }

    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }
    private boolean leftSideTouched(float x, float y) { return screenLeftSide.contains(x, y); }

    /**
     * Helper function to get the actual coordinates in my world
     * @param x
     * @param y
     */
    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    @Override public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))) {
            runner.hit();
        } else if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {
            runner.landed();
        }
    }

    @Override public void preSolve(Contact contact, Manifold oldManifold) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        if (((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b)))
                        && runner.isInvincible()) {
            contact.setEnabled(false);
        }
    }

    @Override public void endContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))) {
            runner.setInvincible(false);
        }
    }

    @Override public void postSolve(Contact contact, ContactImpulse impulse) { }
}