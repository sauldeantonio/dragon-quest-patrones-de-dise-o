package com.taller.patrones.domain.attack;

import java.util.LinkedList;

/**
 * Ibas por buen camino
 */
public class AttackCombo implements Attack {
    private LinkedList<SimpleAttack> simpleAttacks;

    public AttackCombo(LinkedList<SimpleAttack> simpleAttacks) {
        this.simpleAttacks = simpleAttacks;
    }

    @Override
    public int getBasePower() {
        int dmg = 0;
        for (Attack attack : simpleAttacks) {
            dmg += attack.getBasePower();
        }
        return dmg;
    }

    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Attack attack : simpleAttacks) {
            sb.append(attack.getName());
        }
        return sb.toString();
    }

    @Override
    public SimpleAttack.AttackType getType() {
        return simpleAttacks.getLast().getType();
    }

}
