package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.SimpleAttack;

public class AttackMeteorFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new SimpleAttack("Meteor", 120, SimpleAttack.AttackType.SPECIAL);
    }
}
