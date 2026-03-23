package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.attack.Attack;

public interface AttackFactory {
    Attack createAttack();
}
