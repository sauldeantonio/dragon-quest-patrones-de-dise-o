package com.taller.patrones.domain.attackComposite;

import com.taller.patrones.domain.Attack;

import java.util.Deque;
import java.util.LinkedList;

public class AttackComplex implements AttackComposite {
    private LinkedList<Attack> attacks;

    public AttackComplex(LinkedList<Attack> attacks) {
        this.attacks=attacks;
    }

    @Override
    public int getBasePower() {
        int dmg = 0;
        for (AttackComposite attack : attacks) {
            dmg += attack.getBasePower();
        }
        return dmg;
    }

    public String getName() {
        StringBuilder sb=new StringBuilder();
        for (AttackComposite attack : attacks) {
            sb.append(attack.getName());
        }
        return sb.toString();
    }

    @Override
    public Attack.AttackType getType() {
        return attacks.getLast().getType();
    }

}
