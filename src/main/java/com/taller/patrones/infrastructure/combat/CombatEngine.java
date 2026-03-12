package com.taller.patrones.infrastructure.combat;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.Character;
import com.taller.patrones.infrastructure.combat.attackFactory.*;
import com.taller.patrones.infrastructure.combat.damageStrategy.CriticalDamageStrategy;
import com.taller.patrones.infrastructure.combat.damageStrategy.NormalDamageStrategy;
import com.taller.patrones.infrastructure.combat.damageStrategy.SpecialDamageStrategy;
import com.taller.patrones.infrastructure.combat.damageStrategy.StatusDamageStrategy;

/**
 * Motor de combate. Calcula daño y crea ataques.
 * <p>
 * Nota: Esta clase crece cada vez que añadimos un ataque nuevo o un tipo de daño distinto.
 */
public class CombatEngine {

    /**
     * Crea un ataque a partir de su nombre.
     * Cada ataque nuevo requiere modificar este método.
     */
    public Attack createAttack(String name) {
        String n = name != null ? name.toUpperCase() : "";
        return switch (n) {
            case "TACKLE" -> new AttackTackleFactory().createAttack();
            case "SLASH" -> new AttackSlashFactory().createAttack();
            case "FIREBALL" -> new AttackFireballFactory().createAttack();
            case "ICE_BEAM" -> new AttackIceBeamFactory().createAttack();
            case "POISON_STING" -> new AttackPoisonStingFactory().createAttack();
            case "THUNDER" -> new AttackThunderFactory().createAttack();
            case "METEOR" -> new AttackMeteorFactory().createAttack();
            default -> new Attack("Golpe", 30, Attack.AttackType.NORMAL);
        };
    }

    /**
     * Calcula el daño según el tipo de ataque.
     * Cada fórmula nueva (ej. crítico, veneno con tiempo) requiere modificar este switch.
     */
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        return switch (attack.getType()) {
            case NORMAL -> new NormalDamageStrategy().calculateDamage(attacker,defender,attack);
            case SPECIAL -> new SpecialDamageStrategy().calculateDamage(attacker,defender,attack);
            case STATUS -> new StatusDamageStrategy().calculateDamage(attacker,defender,attack);
            case CRITIC -> new CriticalDamageStrategy().calculateDamage(attacker,defender,attack);
        };
    }
}
