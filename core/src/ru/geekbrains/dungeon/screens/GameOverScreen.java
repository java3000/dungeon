package ru.geekbrains.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import ru.geekbrains.dungeon.helpers.Assets;

public class GameOverScreen extends AbstractScreen{

    private BitmapFont font72;

    public GameOverScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        font72 = Assets.getInstance().getAssetManager().get("fonts/font72.ttf");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font72.draw(batch, "GAME OVER", 0, 500, 1280, Align.center, false);
        batch.end();
    }
}
