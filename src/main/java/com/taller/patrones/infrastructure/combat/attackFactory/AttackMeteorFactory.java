package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;

public class AttackMeteorFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new Attack("Meteor", 120, Attack.AttackType.SPECIAL);
    }
}
