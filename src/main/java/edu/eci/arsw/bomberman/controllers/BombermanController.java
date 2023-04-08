package edu.eci.arsw.bomberman.controllers;

import edu.eci.arsw.bomberman.services.BombermanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/board")
public class BombermanController {

    @Autowired
    BombermanServices bombermanServices;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBlueprints(){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bombermanServices.getBoard(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> setPlayerToPositionRight(@RequestBody String player){
        try {
            //registrar dato
            bombermanServices.setPlayerToPositionRight(player);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> setPlayerToPositionLeft(@RequestBody String player){
        try {
            //registrar dato
            bombermanServices.setPlayerToPositionLeft(player);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> setPlayerToPositionUp(@RequestBody String player){
        try {
            //registrar dato
            bombermanServices.setPlayerToPositionUp(player);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> setPlayerToPositionDown(@RequestBody String player){
        try {
            //registrar dato
            bombermanServices.setPlayerToPositionDown(player);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> setPlayer1Name(@RequestBody String player){
        try {
            //registrar dato
            bombermanServices.setPlayerName(player);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error ",HttpStatus.FORBIDDEN);
        }

    }



    /* Guardar nombre de computador y que cuando se oprima una flecha en el computador se mueva a,l que tenga asignado
    solamente.
    Preguntar donde se tienen guardados los nombres para usarlos
     */

}
