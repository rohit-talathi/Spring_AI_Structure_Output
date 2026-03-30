package com.springai.strctured.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskController {

    @Autowired
	AskService askService ;

    @GetMapping("/bean-output-converter")
    ResponseEntity<Object> beanOutputConverter(@RequestParam(value = "movie") String movie) { 
        return new ResponseEntity<>(askService.returnStructuredResponse(movie), HttpStatus.OK);
    }
    
    @GetMapping("/list-output-converter")
    ResponseEntity<Object> listOutputConverter(@RequestParam(value = "movie") String movie) { 
        return new ResponseEntity<>(askService.returnListStructuredResponse(movie), HttpStatus.OK);
    }
 

    @GetMapping("/map-output-converter")
    ResponseEntity<Object> mapOutputConverter(@RequestParam(value = "movie") String movie) { 
        return new ResponseEntity<>(askService.returnMapStructuredResponse(movie), HttpStatus.OK);
    }
}
