package com.taller.patrones.domain;

import com.taller.patrones.domain.attackComposite.AttackComposite;

/**
 * Representa un ataque que puede ejecutar un personaje.
 */
public class Attack implements AttackComposite {

    private final String name;
    private final int basePower;
    private final AttackType type;

    public Attack(String name, int basePower, AttackType type) {
        this.name = name;
        this.basePower = basePower;
        this.type = type;
    }

    public String getName() { return name; }
    public int getBasePower() { return basePower; }
    public AttackType getType() { return type; }

    public enum AttackType {
        NORMAL, SPECIAL, CRITIC, STATUS
    }
}
