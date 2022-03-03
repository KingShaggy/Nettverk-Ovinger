package com.nettverk.mikkelof.oving5.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;

@RestController
@CrossOrigin
public class Oving5Controller {
    @PostMapping("/compileAndRun")
    public String compileAndRun(@RequestBody String code) throws IOException {
        FileWriter writer = new FileWriter("../resources/code.txt");
        String codeToFile = code.substring(1, code.length() - 1);
        writer.write(codeToFile);
        writer.close();
        return(code);
    }
}
