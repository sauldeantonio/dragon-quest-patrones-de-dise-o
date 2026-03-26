package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.SimpleAttack;

public class AttackThunderFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new SimpleAttack("Thunder", 90, SimpleAttack.AttackType.SPECIAL);
    }
}
