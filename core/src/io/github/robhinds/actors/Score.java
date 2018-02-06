package io.github.robhinds.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Score extends Actor {

    private float score;
    private float bestScore;
    private int multiplier;
    private Rectangle bounds;
    private BitmapFont font;

    public Score(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        bestScore = 0;
        score = 0;
        multiplier = 5;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 28;
        font = generator.generateFont(parameter);
        font.setColor(.21f, .22f, .21f, 1f);
        generator.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        score += multiplier * delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (getScore() == 0) {
            return;
        }
        String output = "Top Score: " + String.format("%d", (int) Math.floor(bestScore)) + "\nScore: " + String.format("%d", getScore());
        font.draw(batch, output, bounds.x, bounds.y, bounds.width, Align.right, true);
    }

    public int getScore() {
        return (int) Math.floor(score);
    }

    public void setScore(float s) {
        bestScore = score;
        score = s;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

}