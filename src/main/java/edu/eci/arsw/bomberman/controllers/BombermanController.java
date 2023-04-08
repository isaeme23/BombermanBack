package edu.eci.arsw.bomberman.controllers;

import edu.eci.arsw.bomberman.services.BombermanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
