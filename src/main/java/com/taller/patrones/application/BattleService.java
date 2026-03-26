package com.taller.patrones.application;

import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.CharacterBuilder;
import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.AttackCommand;
import com.taller.patrones.domain.attack.BattleCommand;
import com.taller.patrones.infrastructure.combat.CombatEngine;
import com.taller.patrones.infrastructure.persistence.BattleRepository;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.UUID;

/**
 * Caso de uso: gestionar batallas.
 * <p>
 * Nota: Crea sus propias dependencias con new. Cada vez que necesitamos
 * un CombatEngine o BattleRepository, hacemos new aquí.
 */
public class BattleService {

    public static final List<String> PLAYER_ATTACKS =
            List.of("TACKLE", "SLASH", "FIREBALL", "ICE_BEAM", "POISON_STING", "THUNDER");
    public static final List<String> ENEMY_ATTACKS = List.of("TACKLE", "SLASH", "FIREBALL");
    private final CombatEngine combatEngine = new CombatEngine();
    private final BattleRepository battleRepository = BattleRepository.getInstance();
    private final Deque<BattleCommand> undoStack = new ArrayDeque<>();
    //Necesito una pila para guardar las acciones y así poder deshacer
    //¿Cömo será para poder también rehacer acciones? Ya tienes prácticamente toda la estructura, te falta un par de métodos y otra pila :)

    public BattleStartResult startBattle(String playerName, String enemyName) {
        Character player = new CharacterBuilder().name(playerName).maxHp(150).attack(25).defense(15).speed(20).build();

        Character enemy = new CharacterBuilder().name(enemyName).maxHp(120).attack(30).defense(10).speed(15).build();

        Battle battle = new Battle(player, enemy);
        String battleId = UUID.randomUUID().toString();
        battleRepository.save(battleId, battle);

        return new BattleStartResult(battleId, battle);
    }

    public Battle getBattle(String battleId) {
        return battleRepository.findById(battleId);
    }

    public void executePlayerAttack(String battleId, String attackName) {
        Battle battle = battleRepository.findById(battleId);
        if (battle == null || battle.isFinished() || !battle.isPlayerTurn()) return;

        Attack attack = combatEngine.createAttack(attackName);
        int damage = combatEngine.calculateDamage(battle.getPlayer(), battle.getEnemy(), attack);
        executeAttackCommand(battleId, battle, battle.getPlayer(), battle.getEnemy(), damage, attack);
        //En vez de applyDamage, uso mi command
    }

    public void executeEnemyAttack(String battleId, String attackName) {
        Battle battle = battleRepository.findById(battleId);
        if (battle == null || battle.isFinished() || battle.isPlayerTurn()) return;

        Attack attack = combatEngine.createAttack(attackName != null ? attackName : "TACKLE");
        int damage = combatEngine.calculateDamage(battle.getEnemy(), battle.getPlayer(), attack);
        executeAttackCommand(battleId, battle, battle.getPlayer(), battle.getEnemy(), damage, attack);
        //En vez de applyDamage, uso mi command
    }

    /**
     * Aquí es donde usamos el command
     */
    private void executeAttackCommand(String battleId, Battle battle, Character attacker, Character defender,
                                      int damage, Attack attack) {
        var command = new AttackCommand(battleId, battle, attacker, defender, attack, damage);
        command.execute();
        undoStack.push(command);
    }

    //Borro Apply Damage porque ya no lo uso


    /**
     * Así deshacemos ataques. Llamamos a este método desde el controller
     */
    public boolean undoLastAttack() {
        if (undoStack.isEmpty()) return false;
        var command = undoStack.pop();
        command.undo();
        return true;
    }

    public BattleStartResult startBattleFromExternal(String fighter1Name, int fighter1Hp, int fighter1Atk,
                                                     String fighter2Name, int fighter2Hp, int fighter2Atk) {
        Character player =
                new CharacterBuilder().name(fighter1Name).maxHp(fighter1Hp).attack(fighter1Atk).defense(10).speed(10)
                        .build();
        Character enemy =
                new CharacterBuilder().name(fighter2Name).maxHp(fighter2Hp).attack(fighter2Atk).defense(10).speed(10)
                        .build();

        Battle battle = new Battle(player, enemy);
        String battleId = UUID.randomUUID().toString();
        battleRepository.save(battleId, battle);
        return new BattleStartResult(battleId, battle);
    }

    public record BattleStartResult(String battleId, Battle battle) {
    }
}
