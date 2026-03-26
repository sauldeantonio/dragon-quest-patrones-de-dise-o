package com.taller.patrones.interfaces;

import com.taller.patrones.application.BattleService;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.infrastructure.combat.attackFactory.*;
import com.taller.patrones.interfaces.rest.battleControllerAdapter.BattleControllerAdapter;
import com.taller.patrones.interfaces.rest.battleControllerAdapter.BattleControllerAdapterJSON;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * El ejercicio 8 básicamente te pedía simplificar el controller. Ahora mismo, en él tienes la creación de mapas,
 * los parseos, lógica para elegir ataques... en un controller, lo mejor es tener la lógica mínima, básicamente:
 * - recibir información
 * - mandarla a algún método
 * - devolver respuesta
 * <p>
 * No siempre es así, pero es lo deseable, así que eso lo vas a hacer con el facade. Este es un copia y pega (adaptado) del mío, puedes ver
 * cómo queda mi controller aquí:
 * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/interfaces/rest/BattleController.java
 * ¿Queda más limpio, no? ¿Te gusta? Puedes decir que no, pero lo que no puedes decir es que no se entienda un poquito mejor y se note más ordenado.
 */
public class CombatFacade {

    public static final List<String> PLAYER_ATTACKS =
            List.of("TACKLE", "SLASH", "FIREBALL", "ICE_BEAM", "POISON_STING", "THUNDER");
    public static final List<String> ENEMY_ATTACKS = List.of("TACKLE", "SLASH", "FIREBALL");
    private final BattleService battleService = new BattleService();
    private Map<String, Attack> attacks;

    /**
     * Voy a adaptar estos métodos a tu código, pero te recomiendo ver mi solución detenidamente. Si te animas, puedes clonar
     * mi rama e ir mirando tranquilamente, o incluso irte al commit antes de este ejercicio 8 (tengo 1 commit por ejercicio) y ver
     * si puedes reintentarlo:
     * <p>
     * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/interfaces/CombatFacade.java
     */
    public CombatFacade() {
        this.attacks = Map.of("TACKLE", new AttackTackleFactory().createAttack(),
                "SLASH", new AttackSlashFactory().createAttack(),
                "FIREBALL", new AttackFireballFactory().createAttack(),
                "ICE_BEAM", new AttackIceBeamFactory().createAttack(),
                "POISON_STING", new AttackPoisonStingFactory().createAttack(),
                "THUNDER", new AttackThunderFactory().createAttack(),
                "METEOR", new AttackMeteorFactory().createAttack());
    }

    /**
     * Inicia una batalla con nombres por defecto.
     */
    public BattleService.BattleStartResult startBattle(String playerName, String enemyName) {
        return battleService.startBattle(playerName, enemyName);
    }

    /**
     * Inicia una batalla con datos externos (formato fighter1_*, fighter2_*).
     * La conversion se hace internamente.
     */
    public ResponseEntity<Map<String, Object>> startBattleFromExternal(Map<String, Object> body) {
        BattleControllerAdapter adapter = new BattleControllerAdapterJSON();
        return adapter.startBattleFromExternal(body,
                battleService);
    }

    public Battle getBattle(String battleId) {
        return battleService.getBattle(battleId);
    }

    /**
     * Ejecuta un ataque. Decide automaticamente si es turno del jugador o del enemigo.
     */
    public void executeAttack(String battleId, String attackName) {
        Battle battle = battleService.getBattle(battleId);
        if (battle == null || battle.isFinished()) return;

        if (battle.isPlayerTurn()) {
            battleService.executePlayerAttack(battleId, attackName);
        } else {
            battleService.executeEnemyAttack(battleId, attackName);
        }
    }

    /**
     * Ejecuta el turno del enemigo
     */
    public void executeEnemyTurn(String battleId) {
        Battle battle = battleService.getBattle(battleId);
        if (battle == null || battle.isFinished() || battle.isPlayerTurn()) return;

        String attack = ENEMY_ATTACKS.get((int) (Math.random() * ENEMY_ATTACKS.size()));
        battleService.executeEnemyAttack(battleId, attack);
    }

    /**
     * Para hacer el undo del command, así BattleController sólo llama a métodos de la Facade y queda todo más sencillo.
     */
    public boolean undo() {
        return battleService.undoLastAttack();
    }
}
