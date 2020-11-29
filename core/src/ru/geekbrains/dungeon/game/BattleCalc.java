package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.math.MathUtils;
import ru.geekbrains.dungeon.game.units.Unit;

public class BattleCalc {
    public static int attack(Unit attacker, Unit target) {
        //5
        int out = attacker.getWeapon().getDamage();

        out -= target.getStats().getDefence();
        out -= target.getArmour().getDefense();

        if (out < 0 || target.getArmour().isReflects(attacker.getWeapon().getType())) {
            out = 0;
        }
        return out;
    }

    public static int checkCounterAttack(Unit attacker, Unit target) {
        if (MathUtils.random() < 0.5f) {
            int amount = attack(target, attacker);
            return amount;
        }
        return 0;
    }
}
