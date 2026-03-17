package com.taller.patrones.infrastructure.combat.attackFactory;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.attackComposite.AttackComplex;
import com.taller.patrones.domain.attackComposite.AttackComposite;

import java.util.Deque;
import java.util.LinkedList;

public class AttackComboTripleFactory implements  AttackFactory{

    @Override
    public AttackComposite createAttack() {
        LinkedList<Attack> attacks=new LinkedList<>();
        attacks.add((Attack) new AttackTackleFactory().createAttack());
        attacks.add((Attack) new AttackSlashFactory().createAttack());
        attacks.add((Attack) new AttackFireballFactory().createAttack());
        return new AttackComplex(attacks);
    }
}
