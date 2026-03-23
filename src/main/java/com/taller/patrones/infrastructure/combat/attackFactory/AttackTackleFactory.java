package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.SimpleAttack;

public class AttackTackleFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new SimpleAttack("Tackle", 40, SimpleAttack.AttackType.NORMAL);
    }
}
