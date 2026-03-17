package com.taller.patrones.infrastructure.combat.damageStrategy;

import com.taller.patrones.domain.attackComposite.AttackComposite;
import com.taller.patrones.domain.Character;

public class StatusDamageStrategy implements DamageStrategy{

    @Override
    public int calculateDamage(Character attacker, Character defender, AttackComposite attack) {
        return 0;
    }
}
