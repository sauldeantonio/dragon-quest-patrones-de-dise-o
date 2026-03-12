package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;

public class AttackIceBeamFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new Attack("Ice Beam", 70, Attack.AttackType.SPECIAL);
    }
}
