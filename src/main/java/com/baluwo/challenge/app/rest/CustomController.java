package com.baluwo.challenge.app.rest;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baluwo.challenge.app.api.MessageApi;
import com.baluwo.challenge.domain.model.Message;
import com.baluwo.challenge.domain.service.ProcessMessageService;

@RestController
@RequestMapping(path = "/custom")
public class CustomController {

    @Autowired
    private ProcessMessageService messageService;

    @PostMapping("/echo")
    public ResponseEntity<?> echo(@RequestBody MessageApi message) {
        return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), OK);
    }
}