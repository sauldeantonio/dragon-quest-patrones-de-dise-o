package com.taller.patrones.domain;

/**
 * Representa un personaje en combate.
 */
public class Character {
    private final String name;
    private int currentHp;
    private final int maxHp;
    private final int attack;
    private final int defense;
    private final int speed;
    private String equipment;
    private CharacterClass charclass;
    //equipamiento, buffos temporales, clase (guerrero/mago

    public Character(String name, int maxHp, int attack, int defense, int speed, CharacterClass characterClass, String equipment) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.charclass = characterClass;
        this.equipment=equipment;
    }

    public String getName() { return name; }
    public int getCurrentHp() { return currentHp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }

    public String getEquipment() {
        return equipment;
    }

    public CharacterClass getCharclass() {
        return charclass;
    }

    public void takeDamage(int damage) {
        this.currentHp = Math.max(0, currentHp - damage);
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public double getHpPercentage() {
        return maxHp > 0 ? (double) currentHp / maxHp * 100 : 0;
    }
}


