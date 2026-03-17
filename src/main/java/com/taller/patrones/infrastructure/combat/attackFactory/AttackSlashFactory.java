package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.attackComposite.AttackComposite;

public class AttackSlashFactory implements AttackFactory {

    @Override
    public AttackComposite createAttack() {
        return new Attack("Slash", 55, Attack.AttackType.NORMAL);
    }
}
