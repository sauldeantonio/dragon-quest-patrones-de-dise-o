package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.attackComposite.AttackComposite;

public class AttackIceBeamFactory implements AttackFactory {

    @Override
    public AttackComposite createAttack() {
        return new Attack("Ice Beam", 70, Attack.AttackType.SPECIAL);
    }
}
