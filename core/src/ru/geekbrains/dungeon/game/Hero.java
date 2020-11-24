package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.Gdx;
import ru.geekbrains.dungeon.helpers.Assets;
import ru.geekbrains.dungeon.game.GameController;

public class Hero extends Unit {
    private String name;
    //2
    private int coins;
    //5
    private int round;

    public Hero(GameController gc) {
        super(gc, 1, 1, 10);
        this.name = "Sir Mullih";
        this.hpMax = 100;
        this.hp = this.hpMax;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
        //2
        this.coins = 0;
        //5
        this.round = 0;
    }

    public void update(float dt) {
        super.update(dt);
        if (Gdx.input.justTouched() && canIMakeAction()) {
            Monster m = gc.getUnitController().getMonsterController().getMonsterInCell(gc.getCursorX(), gc.getCursorY());
            if (m != null && canIAttackThisTarget(m)) {
                attack(m);
            } else {
                goTo(gc.getCursorX(), gc.getCursorY());
            }
        }
        //3
        hpAlpha = (hp >= hpMax) ? 0.2f : 1.0f;
    }

    public String getName() {
        return name;
    }

    //2
    public int getCoins() {
        return coins;
    }

    //2
    public void setCoins(int coins) {
        this.coins = coins;
    }
}
