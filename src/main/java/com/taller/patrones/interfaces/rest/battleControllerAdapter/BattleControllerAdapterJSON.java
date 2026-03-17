package com.taller.patrones.interfaces.rest.battleControllerAdapter;

import com.taller.patrones.application.BattleService;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public class BattleControllerAdapterJSON implements BattleControllerAdapter{

    public ResponseEntity<Map<String, Object>> startBattleFromExternal(@RequestBody Map<String, Object> body, BattleService battleService){
        String fighter1Name = (String) body.getOrDefault("fighter1_name", "Héroe");
        int fighter1Hp = ((Number) body.getOrDefault("fighter1_hp", 150)).intValue();
        int fighter1Atk = ((Number) body.getOrDefault("fighter1_atk", 25)).intValue();
        String fighter2Name = (String) body.getOrDefault("fighter2_name", "Dragón");
        int fighter2Hp = ((Number) body.getOrDefault("fighter2_hp", 120)).intValue();
        int fighter2Atk = ((Number) body.getOrDefault("fighter2_atk", 30)).intValue();

        var result = battleService.startBattleFromExternal(
                fighter1Name, fighter1Hp, fighter1Atk,
                fighter2Name, fighter2Hp, fighter2Atk
        );
        Battle battle = result.battle();

        return ResponseEntity.ok(Map.of(
                "battleId", result.battleId(),
                "player", toCharacterDto(battle.getPlayer()),
                "enemy", toCharacterDto(battle.getEnemy()),
                "currentTurn", battle.getCurrentTurn(),
                "battleLog", battle.getBattleLog(),
                "finished", battle.isFinished(),
                "playerAttacks", BattleService.PLAYER_ATTACKS,
                "lastDamage", 0,
                "lastDamageTarget", ""
        ));
    }

    private Map<String, Object> toCharacterDto(Character c) {
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
