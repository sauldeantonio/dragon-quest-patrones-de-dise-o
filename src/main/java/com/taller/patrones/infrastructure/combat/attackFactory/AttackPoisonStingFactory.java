package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.SimpleAttack;

public class AttackPoisonStingFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new SimpleAttack("Poison Sting", 20, SimpleAttack.AttackType.STATUS);
    }
}
