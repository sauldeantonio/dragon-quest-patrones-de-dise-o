package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.attackComposite.AttackComposite;

public class AttackThunderFactory implements AttackFactory {

    @Override
    public AttackComposite createAttack() {
        return new Attack("Thunder", 90, Attack.AttackType.SPECIAL);
    }
}
