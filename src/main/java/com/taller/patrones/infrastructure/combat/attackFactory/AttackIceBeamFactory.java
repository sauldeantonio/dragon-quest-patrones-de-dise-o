package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.SimpleAttack;

public class AttackIceBeamFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new SimpleAttack("Ice Beam", 70, SimpleAttack.AttackType.SPECIAL);
    }
}
