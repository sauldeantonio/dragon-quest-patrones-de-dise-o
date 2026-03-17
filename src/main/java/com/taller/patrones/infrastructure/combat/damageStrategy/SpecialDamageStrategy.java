package com.taller.patrones.infrastructure.combat.damageStrategy;

import com.taller.patrones.domain.attackComposite.AttackComposite;
import com.taller.patrones.domain.Character;

public class SpecialDamageStrategy implements DamageStrategy{

    @Override
    public int calculateDamage(Character attacker, Character defender, AttackComposite attack) {
        int raw = attacker.getAttack() * attack.getBasePower() / 100;
        int effectiveDef = defender.getDefense() / 2;
        return Math.max(1, raw - effectiveDef);
    }
}
