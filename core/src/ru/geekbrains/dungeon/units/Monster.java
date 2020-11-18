package ru.geekbrains.dungeon.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.dungeon.GameController;

import java.util.Random;

public class Monster extends Unit {
    public boolean isActive() {
        return hp > 0;
    }

    public Monster(TextureAtlas atlas, GameController gc) {
        super(gc, 5, 2, 10);
        this.texture = atlas.findRegion("monster");
        this.textureHp = atlas.findRegion("hp");
        this.hp = -1;
    }

    public void activate(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.hpMax = 10;
        this.hp = hpMax;
    }

    public void update(float dt) {
        //4
        //отвечаем только когда наших бьют
        if (Gdx.input.justTouched() && (gc.getCursorX() == cellX && gc.getCursorY() == cellY)) {
            //25% = 1/4, то есть random 0-3
            if (new Random().nextInt(4) == 0) {
                gc.getHero().takeDamage(1);
            }
        }
    }
}
