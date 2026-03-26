package com.taller.patrones.domain.attack;

/**
 * Creas una itnerfaz para el command. "execute" es como se suele llamar al método del command. undo lo añades para deshacer. ¿Cómo?
 * Ahora lo miramos, Primero, paso a paso, y el primero es hacer la itnerfaz, luego ya pensarás cómo integrarla.
 */
public interface BattleCommand {

    void execute();

    void undo();
}
