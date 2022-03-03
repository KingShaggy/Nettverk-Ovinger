package com.nettverk.mikkelof.oving5.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Oving5Controller {
    @PostMapping("/compileAndRun")
    public String compileAndRun(@RequestBody String code){
        System.out.println(code);
        return(code);
    }
}
