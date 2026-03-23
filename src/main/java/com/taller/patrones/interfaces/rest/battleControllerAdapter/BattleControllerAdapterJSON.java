package com.taller.patrones.interfaces.rest.battleControllerAdapter;

import com.taller.patrones.application.BattleService;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Este nombre está bien? Este Adapter sirve para adaptar cualquier request json que recibamos? ¿O es sólo para
 * que los datos que vienen con nombre "Fighter" se puedan adaptar a nuestro modelo?
 * <p>
 * ¿También, esto es un Adapter del controller, o un adapter de datos? Revisa mi solución:
 * <p>
 * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/interfaces/Fighter1Fighter2CombatAdapter.java
 */
public class BattleControllerAdapterJSON implements BattleControllerAdapter {

    /**
     * Es interesante usar constante a la hora de trabajar con strings, así si algo cambia en el futuro, tocas en
     * un sólo sitio. También es más cómood para diseñar tests. (En este caso sólo lo usas aquí, pero yo por
     * ejemplo ya tengo la costumbre porque me parece buena práctica que ahorra tiempo).
     */
    private static final String FIGHTER1_NAME = "fighter1_name";
    private static final String FIGHTER1_HP = "fighter1_hp";
    private static final String FIGHTER1_ATK = "fighter1_atk";
    private static final String FIGHTER2_NAME = "fighter2_name";
    private static final String FIGHTER2_HP = "fighter2_hp";
    private static final String FIGHTER2_ATK = "fighter2_atk";

    public ResponseEntity<Map<String, Object>> startBattleFromExternal(@RequestBody Map<String, Object> body,
                                                                       BattleService battleService) {
        String fighter1Name = (String) body.getOrDefault(FIGHTER1_NAME, "Héroe");
        int fighter1Hp = ((Number) body.getOrDefault(FIGHTER1_HP, 150)).intValue();
        int fighter1Atk = ((Number) body.getOrDefault(FIGHTER1_ATK, 25)).intValue();
        String fighter2Name = (String) body.getOrDefault(FIGHTER2_NAME, "Dragón");
        int fighter2Hp = ((Number) body.getOrDefault(FIGHTER2_HP, 120)).intValue();
        int fighter2Atk = ((Number) body.getOrDefault(FIGHTER2_ATK, 30)).intValue();

        var result = battleService.startBattleFromExternal(
                fighter1Name, fighter1Hp, fighter1Atk,
                fighter2Name, fighter2Hp, fighter2Atk
        );
        Battle battle = result.battle();

        return ResponseEntity.ok(Map.of(
                "battleId", result.battleId(),
                "player", toCharacter(battle.getPlayer()),
                "enemy", toCharacter(battle.getEnemy()),
                "currentTurn", battle.getCurrentTurn(),
                "battleLog", battle.getBattleLog(),
                "finished", battle.isFinished(),
                "playerAttacks", BattleService.PLAYER_ATTACKS,
                "lastDamage", 0,
                "lastDamageTarget", ""
        ));
    }

    /**
     * Lo tengo mal también en mi solución y en el ejercicio, pero me he dado cuenta ahora de que no se debería llamar
     * con DTO al final porque no devolvemos un DTO, si no un mapa.
     */
    private Map<String, Object> toCharacter(Character c) {
        return Map.of(
                "name", c.getName(),
                "currentHp", c.getCurrentHp(),
                "maxHp", c.getMaxHp(),
                "hpPercentage", c.getHpPercentage(),
                "attack", c.getAttack(),
                "defense", c.getDefense(),
                "speed", c.getSpeed(),
                "alive", c.isAlive()
        );
    }
}
