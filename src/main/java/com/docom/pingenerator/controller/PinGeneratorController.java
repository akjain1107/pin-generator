package com.docom.pingenerator.controller;

import com.docom.pingenerator.model.*;
import com.docom.pingenerator.service.PinGenerateService;
import com.docom.pingenerator.util.Constants;
import com.docom.pingenerator.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/pin")
public class PinGeneratorController {
    @Autowired
    private PinGenerateService service;

    @RequestMapping("/generate")
    public ResponseEntity<Response> generatePin(@RequestBody MSISDNRequest request){
        System.out.println("MSISDN: "+request.getMsisdn());
        Validator.validateMSISDN(request.getMsisdn());
        Integer response = service.generatePin(request.getMsisdn());
        return prepareResponseEntity(request.getMsisdn(),response,HttpStatus.OK);
    }

    private ResponseEntity<Response> prepareResponseEntity(String msisdn, Integer response, HttpStatus status) {
        String message;
        switch (response){
            case 1:
                message =Constants.MSISDN+" "+ msisdn+" "+ Constants.RETRIES_MESSAGE;
                break;
            case 2:
                message =Constants.MSISDN+" "+ msisdn +" "+ Constants.ALREADY_HAS_3_NON_VALIDATED_PINS;
                break;
            case 3:
                message =Constants.PIN+" "+ Constants.VALIDATED;
                break;
            case 4:
                message =Constants.INVALID+" "+Constants.PIN;
                break;
            case 5:
                message =Constants.PIN_NOT_FOUND;
                break;
            default:
                message=String.valueOf(response);
                break;
        }
        return new ResponseEntity<Response>(new MSISDNResponse(message),status);

    }

    private ResponseEntity<Response> preparePinResponseEntity(String message, HttpStatus status){
        return new ResponseEntity<Response>(new PinResponse(message),status);
    }

    @RequestMapping("/validate")
    public ResponseEntity<Response> validate(@RequestBody PinRequest request){
        System.out.println("MSISDN: "+request.getMsisdn());
        System.out.println("Pin: "+request.getPin());
        Validator.validateMSISDN(request.getMsisdn());
        Validator.validatePin(request.getPin());
        Integer response = service.validate(request);
        return prepareResponseEntity(request.getMsisdn(),response,HttpStatus.OK);
    }
}
