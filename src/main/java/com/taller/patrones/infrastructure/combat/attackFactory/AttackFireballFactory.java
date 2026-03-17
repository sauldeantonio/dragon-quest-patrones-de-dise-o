package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.attackComposite.AttackComposite;

public class AttackFireballFactory implements AttackFactory {

    @Override
    public AttackComposite createAttack() {
        return new Attack("Fireball", 80, Attack.AttackType.SPECIAL);
    }
}
