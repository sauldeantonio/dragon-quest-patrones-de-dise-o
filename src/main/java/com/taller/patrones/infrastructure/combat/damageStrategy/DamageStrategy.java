package com.taller.patrones.infrastructure.combat.damageStrategy;

import com.taller.patrones.domain.attackComposite.AttackComposite;
import com.taller.patrones.domain.Character;

public interface DamageStrategy {
    int calculateDamage(com.taller.patrones.domain.Character attacker, Character defender, AttackComposite attack);
}
