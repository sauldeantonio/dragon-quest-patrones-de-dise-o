package com.taller.patrones.infrastructure.combat.damageStrategy;

import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.attack.Attack;

public interface DamageStrategy {
    int calculateDamage(com.taller.patrones.domain.Character attacker, Character defender, Attack attack);
}
