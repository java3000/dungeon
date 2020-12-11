package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.dungeon.helpers.Assets;
import ru.geekbrains.dungeon.helpers.ObjectPool;

public class BerryController  extends ObjectPool<Berry> {
    private TextureRegion berryTexture;

    public BerryController() {
        berryTexture = Assets.getInstance().getAtlas().findRegion("projectile");
    }

    @Override
    protected Berry newObject() {
        return new Berry(berryTexture);
    }

    public Berry activate(int x, int y) {
        return getActiveElement().activate(x, y);
    }

    public void update(float dt) {
        for (Berry b : getActiveList()) {
            b.update(dt);
        }
        checkPool();
    }

    public void render(SpriteBatch batch) {
        for (Berry b : getActiveList()) {
            b.render(batch);
        }
    }
}
