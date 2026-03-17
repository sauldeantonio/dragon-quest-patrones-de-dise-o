package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.attackComposite.AttackComposite;

public class AttackPoisonStingFactory implements AttackFactory {

    @Override
    public AttackComposite createAttack() {
        return new Attack("Poison Sting", 20, Attack.AttackType.STATUS);
    }
}
