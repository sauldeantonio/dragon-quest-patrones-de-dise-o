package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.attackComposite.AttackComposite;

public class AttackTackleFactory implements AttackFactory {

    @Override
    public AttackComposite createAttack() {
        return new Attack("Tackle", 40, Attack.AttackType.NORMAL);
    }
}
