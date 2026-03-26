package com.taller.patrones.infrastructure.combat.damageStrategy;

import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.attack.Attack;

public class StatusDamageStrategy implements DamageStrategy {

    @Override
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        return 0;
    }
}
