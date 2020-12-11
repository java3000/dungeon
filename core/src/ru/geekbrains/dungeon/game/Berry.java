package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import ru.geekbrains.dungeon.helpers.Poolable;

@Getter
public class Berry implements Poolable {
    private final TextureRegion texture;
    private Vector2 position;
    private boolean active;
    private int cellX;
    private int cellY;

    public Berry(TextureRegion texture) {
        this.texture = texture;
        this.position = new Vector2(0,0);
        this.active = false;
        this.cellX = 0;
        this.cellY = 0;
    }

    public void update(float dt) {
        //
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public Berry activate(int x, int y) {
        this.active = true;
        this.cellX = x;
        this.cellY = y;
        position.set(x * GameMap.CELL_SIZE + MathUtils.random(50.0f),
                y * GameMap.CELL_SIZE + MathUtils.random(50.0f));
        return this;
    }

    public void deactivate() {
        active = false;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
