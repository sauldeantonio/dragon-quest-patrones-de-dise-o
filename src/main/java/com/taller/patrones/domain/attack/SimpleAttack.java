package com.taller.patrones.domain.attack;

/**
 * Representa un ataque que puede ejecutar un personaje.
 */
public class SimpleAttack implements Attack {

    private final String name;
    private final int basePower;
    private final AttackType type;

    public SimpleAttack(String name, int basePower, AttackType type) {
        this.name = name;
        this.basePower = basePower;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getBasePower() {
        return basePower;
    }

    public AttackType getType() {
        return type;
    }

    public enum AttackType {
        NORMAL, SPECIAL, CRITIC, STATUS
    }
}
