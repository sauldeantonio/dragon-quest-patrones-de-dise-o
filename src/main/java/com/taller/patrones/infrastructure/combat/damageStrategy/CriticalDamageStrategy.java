package com.taller.patrones.infrastructure.combat.damageStrategy;

import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.attack.Attack;

public class CriticalDamageStrategy implements DamageStrategy {

    @Override
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        int probability = (int) (Math.random() * 10);
        int raw = 0;
        if (probability < 2)
            raw = (int) (attacker.getAttack() * attack.getBasePower() * 1.5 / 100);
        return Math.max(0, raw);
    }
}
