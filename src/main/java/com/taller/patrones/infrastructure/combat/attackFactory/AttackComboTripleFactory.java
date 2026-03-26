package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.AttackCombo;
import com.taller.patrones.domain.attack.SimpleAttack;

import java.util.LinkedList;

public class AttackComboTripleFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        LinkedList<SimpleAttack> simpleAttacks = new LinkedList<>();
        simpleAttacks.add((SimpleAttack) new AttackTackleFactory().createAttack());
        simpleAttacks.add((SimpleAttack) new AttackSlashFactory().createAttack());
        simpleAttacks.add((SimpleAttack) new AttackFireballFactory().createAttack());
        return new AttackCombo(simpleAttacks);
    }
}
