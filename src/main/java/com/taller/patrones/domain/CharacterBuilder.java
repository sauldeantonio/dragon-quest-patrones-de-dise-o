package com.taller.patrones.domain;

public class CharacterBuilder {
    private String name;
    private int currentHp;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;
    private String equipment;
    private CharacterClass characterClass;

    public CharacterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder currentHp(int currentHp) {
        this.currentHp = currentHp;
        return this;
    }

    public CharacterBuilder maxHp(int maxHp) {
        this.maxHp = maxHp;
        return this;
    }

    public CharacterBuilder attack(int attack) {
        this.attack = attack;
        return this;
    }

    public CharacterBuilder defense(int defense) {
        this.defense = defense;
        return this;
    }

    public CharacterBuilder speed(int speed) {
        this.speed = speed;
        return this;
    }

    public CharacterBuilder equipment(String equipamiento) {
        this.equipment = equipamiento;
        return this;
    }

    public CharacterBuilder charclass(CharacterClass charclass) {
        this.characterClass = charclass;
        return this;
    }

    public Character build(){
        return new Character(this.name,this.maxHp, this.attack, this.defense, this.speed, this.characterClass, this.equipment);
    }
}
