package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.SimpleAttack;

public class AttackSlashFactory implements AttackFactory {

    @Override
    public Attack createAttack() {
        return new SimpleAttack("Slash", 55, SimpleAttack.AttackType.NORMAL);
    }
}
