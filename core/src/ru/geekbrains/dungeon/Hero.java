package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private ProjectileController projectileController;
    private Vector2 position;
    private TextureRegion texture;
    //1
    private boolean isSuperShoot;
    //3
    private Vector2 direction;

    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(100, 100);
        this.direction = new Vector2(100, 0);
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
    }

    public void update(float dt) {

        //1
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            isSuperShoot = !isSuperShoot;
        }

        //1
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            shoot();
        }
        move(dt);
    }

    public void shoot() {
        //1
        //3
        Vector2 tmp = direction.cpy().nor();
        if (!isSuperShoot) {
            System.out.println("x = " + tmp.x + " y = " + tmp.y);
            System.out.println("px = " + position.x + " py = " + position.y);
            projectileController.activate(position.x, position.y, 200 * tmp.x, 200 * tmp.y);
        } else {
            projectileController.activate(position.x, position.y, 200 * tmp.x, 10 * tmp.y);
            projectileController.activate(position.x, position.y, 200 * tmp.x, -10 * tmp.y);
        }
    }

    public void move(float dt) {

        //2
        Vector2 tmp = new Vector2(0.0f, 0.0f);
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && position.y <= Gdx.graphics.getHeight()) {
            tmp.set(0, 100);
            direction = tmp.cpy();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && position.y - this.texture.getRegionHeight() > 0.0f) {
            tmp.set(0, -100);
            direction = tmp.cpy();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && position.x <= Gdx.graphics.getWidth()) {
            tmp.set(100, 0);
            direction = tmp.cpy();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && position.x - this.texture.getRegionWidth() > 0.0f) {
            tmp.set(-100, 0);
            direction = tmp.cpy();
        }
        position.mulAdd(tmp, dt);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - this.texture.getRegionWidth(), position.y - this.texture.getRegionHeight());
    }
}
