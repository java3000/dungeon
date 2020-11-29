package ru.geekbrains.dungeon.game;

import lombok.Getter;

import java.util.Objects;

//5
@Getter
public class Armour {
    public enum Type {
        //броня != снаряжению
        REGULAR, SUPER, PUPER, SUPERPUPER
    }

    Armour.Type type;
    Weapon.Type[] reflection;
    int defense;

    public Armour(Armour.Type type, int defense, Weapon.Type[] reflection) {
        this.type = type;
        this.defense = defense;
        this.reflection = reflection;
    }

    public boolean isReflects(Weapon.Type type){
        for(Weapon.Type weapon : this.reflection){
            if (weapon.equals(type)) return true;
        }
        return false;
    }
}
