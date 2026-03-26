package com.taller.patrones.infrastructure.persistence;

import com.taller.patrones.domain.Battle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Buen singleton
 */
public class BattleRepository {

    private static final Map<String, Battle> battles = new ConcurrentHashMap<>();
    private static BattleRepository instance;

    private BattleRepository() {
    }

    public static BattleRepository getInstance() {
        if (instance == null)
            instance = new BattleRepository();
        return instance;
    }

    public void save(String id, Battle battle) {
        battles.put(id, battle);
    }

    public Battle findById(String id) {
        return battles.get(id);
    }

    public void remove(String id) {
        battles.remove(id);
    }
}
