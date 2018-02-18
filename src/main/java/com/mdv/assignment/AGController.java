package com.mdv.assignment;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AGController {
   
    private Simulator simulator;
    
    @Autowired
    public void setSimulator(Simulator sim) {
        this.simulator = sim;
    }
         
    @RequestMapping("/")
    String home() {
        return "<b>Spring App Running OK</b><br> access twitter feed by using <a href=http://localhost:8080/simulate>Simulate</a>";
    }
    
    @RequestMapping("/simulate")
    String simulate() {
        try {
            simulator.simulate();
            return simulator.getBrowserFeed();
        } catch (IOException ex) {
            Logger.getLogger(AGController.class.getName()).log(Level.SEVERE,"*FILE ERROR* " + ex.toString());
            return "*FILE ERROR* " + ex.toString();
        }
    }
    
}
