package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.attackComposite.AttackComposite;

public class AttackMeteorFactory implements AttackFactory {

    @Override
    public AttackComposite createAttack() {
        return new Attack("Meteor", 120, Attack.AttackType.SPECIAL);
    }
}
