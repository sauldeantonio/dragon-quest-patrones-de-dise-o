package com.taller.patrones.interfaces.rest.battleControllerAdapter;

import com.taller.patrones.application.BattleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface BattleControllerAdapter {
    public ResponseEntity<Map<String, Object>> startBattleFromExternal(@RequestBody Map<String, Object> body, BattleService battleService);
}
