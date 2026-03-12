package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;

public class AttackFireballFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new Attack("Fireball", 80, Attack.AttackType.SPECIAL);
    }
}
