package edu.eci.arsw.bomberman.controllers;

import edu.eci.arsw.bomberman.services.BombermanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/board")
public class BombermanController {

    @Autowired
    BombermanServices bombermanServices;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBoardFront(){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bombermanServices.getBoard(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/players")
    public ResponseEntity<?> getPlayer(){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bombermanServices.getplayers(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/players/{name}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateNamePlayer(@PathVariable String name){
        try {
            //registrar dato
            bombermanServices.setPlayerName(name);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(path = "/players/{playerName}/{movement}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMovement(@PathVariable String playerName, @PathVariable String movement){
        try {
            //registrar dato
            bombermanServices.movement(playerName, movement);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.FORBIDDEN);
        }

    }
}
