package com.taller.patrones.infrastructure.combat.damageStrategy;

import com.taller.patrones.domain.attackComposite.AttackComposite;
import com.taller.patrones.domain.Character;

public class NormalDamageStrategy implements DamageStrategy {

    @Override
    public int calculateDamage(Character attacker, Character defender, AttackComposite attack) {
        int raw = attacker.getAttack() * attack.getBasePower() / 100;
        return Math.max(1, raw - defender.getDefense());
    }
}
