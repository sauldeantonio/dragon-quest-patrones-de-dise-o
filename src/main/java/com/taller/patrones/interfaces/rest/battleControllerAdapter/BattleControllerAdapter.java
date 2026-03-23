package com.taller.patrones.interfaces.rest.battleControllerAdapter;

import com.taller.patrones.application.BattleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface BattleControllerAdapter {
    //En interfaces no necesitas poner que es public, todos los métodos en una interfaz lo son por defecto.
    //Es el contrato con otras clases, es lo que prometes que van a tener expuesto.
    ResponseEntity<Map<String, Object>> startBattleFromExternal(@RequestBody Map<String, Object> body,
                                                                BattleService battleService);
}
