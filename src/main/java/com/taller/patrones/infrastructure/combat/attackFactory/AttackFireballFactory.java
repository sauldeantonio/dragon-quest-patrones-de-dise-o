package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.SimpleAttack;

public class AttackFireballFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new SimpleAttack("Fireball", 80, SimpleAttack.AttackType.SPECIAL);
    }
}
