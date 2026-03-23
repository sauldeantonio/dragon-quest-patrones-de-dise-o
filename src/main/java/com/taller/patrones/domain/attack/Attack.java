package com.taller.patrones.domain.attack;

/**
 * Interfaz attack con los métodos que tiene un Attack normal. He decidido llamarlo Attack y luego las que heredan
 * SimpleAttack y AttackCombo porque así se entiende. Todos son ataques, la diferencia es el número.
 */
public interface Attack {

    String getName();

    int getBasePower();

    SimpleAttack.AttackType getType();
}
