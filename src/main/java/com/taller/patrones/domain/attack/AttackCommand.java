package com.taller.patrones.domain.attack;


import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;

/**
 * Creas una clase que implemente la interfaz y los métodos de esta. Después de leer esta clase, vete a BattleService para ver
 * cómo se ejecutaría
 */
public class AttackCommand implements BattleCommand {

    //Leyendo la información que necesitas en el execute, vas añadiendo aquí los campos necesarios, que luego introducirás
    //en el constrcutor
    private final String battleId;
    private final Battle battle;
    private final Character attacker;
    private final Character defender;
    private final Attack attack;
    private final int damage;

    private final int previousLastDamage;
    private final String previousLastDamageTarget;
    private final boolean battleWasFinished;

    public AttackCommand(String battleId, Battle battle, Character attacker, Character defender,
                         Attack attack, int damage) {
        this.battleId = battleId;
        this.battle = battle;
        this.attacker = attacker;
        this.defender = defender;
        this.attack = attack;
        this.damage = damage;

        this.previousLastDamage = battle.getLastDamage();
        this.previousLastDamageTarget = battle.getLastDamageTarget() != null ? battle.getLastDamageTarget() : "";
        this.battleWasFinished = battle.isFinished();
    }

    /**
     * Aquí pegamos la lógica de turnos, que es la de hacer/recibir daño, cambiar turno y comprobar si el
     * combate ha acabado. Osea, un copiar y pegar. ¿Cómo sabes qué copair y pegar? En este caso, te lo ponía el enunciado.
     * El applyDamage() de BattleService. Aquí está toda la lógica de ejecutar un turno. No cambiamos nada.
     */
    @Override
    public void execute() {
        defender.takeDamage(damage);
        String target = defender == battle.getPlayer() ? "player" : "enemy";
        battle.setLastDamage(damage, target);
        battle.log(attacker.getName() + " usa " + attack.getName() + " y hace " + damage + " de daño a " +
                defender.getName());
        battle.switchTurn();
//En este metodo es donde igual te interesa avisar a los observers que se suscriban a tu clase para que notifiquen de cambios,
        //ya que es aquí donde se cambia el turno, se ve el daño hecho y se sabe si la partida ha terminado. Mira mi código:
        //https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/domain/AttackCommand.java
        if (!defender.isAlive()) {
            battle.finish(attacker.getName());
        }
    }

    /**
     * Esta es la función para deshacer... ¿Qué necesitamos? Deshacer las 3 cosas que hicimos antes:
     * - deshacer daño
     * - cambiar turno
     * - Si la partida está terminada, desfinalizarla
     * - EXTRA: Si escribías logs, borrar la última entrada
     */
    @Override
    public void undo() {
        defender.heal(
                damage); //Nueva función para curar el daño hecho. EL defender fue el último en recibir daño, daño que tienes guardado... se cura
        battle.switchTurn(); //Cambias el turno. Como solo hay dos personajez, con usar la función de switch que ya tienes sirve
        battle.setLastDamage(previousLastDamage,
                previousLastDamageTarget); //tienes que almacenar el lastDamage real porque lo pide battle

        if (battleWasFinished) {
            battle.unfinish(); //Si estaba terminada la batalla, se reinicia
        }
        battle.removeLastLogEntry(); //Eliminamos el último log
    }
    //Con esto ya lo tienes todo. AL hacer un command ten en cuenta que necesitar tener toda la info de tus métodos en la clase.
    //Así que para hacerlo, simplemente mueve el código que te interesa y ve cpoco a poco añadiendo campos (con cabeza, claro).
    //Como la idea es que el comportamiento del programa no cambie, si ejecutas test y acabn en verde, o al ejecutar todo está igual,
    //es que vas por buen camino.
}
