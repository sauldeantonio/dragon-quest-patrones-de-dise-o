package com.taller.patrones.infrastructure.combat;

import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.SimpleAttack;
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
     * Está bien, es un factory y estás desacoplando el código, pero echale un vistazo a lo que hicimos en clase.
     * Si subimos el switch unos cuantos niveles y aquí sólo recibimos la factory, queda todo un poquitito más limpio.
     * Pero este es otro approach correcto.
     * <p>
     * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/f9a87c7856a0218e5c6e76532527fb3cb3222a29/src/main/java/com/taller/patrones/interfaces/CombatFacade.java#L19
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
            case "COMBO TRIPLE" -> new AttackComboTripleFactory().createAttack();
            default -> new SimpleAttack("Golpe", 30, SimpleAttack.AttackType.NORMAL);
        };
    }

    /**
     * Bien. Como vimos en clase, un caso raro para usar strategy pero al menos has podido repasar cómo hacerlo
     */
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        return switch (attack.getType()) {
            case NORMAL -> new NormalDamageStrategy().calculateDamage(attacker, defender, attack);
            case SPECIAL -> new SpecialDamageStrategy().calculateDamage(attacker, defender, attack);
            case STATUS -> new StatusDamageStrategy().calculateDamage(attacker, defender, attack);
            case CRITIC -> new CriticalDamageStrategy().calculateDamage(attacker, defender, attack);
        };
    }
}
