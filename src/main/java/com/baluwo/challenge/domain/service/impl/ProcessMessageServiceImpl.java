package com.baluwo.challenge.domain.service.impl;

import org.springframework.stereotype.Service;

import com.baluwo.challenge.domain.model.Message;
import com.baluwo.challenge.domain.service.ProcessMessageService;

@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

    @Override
    public Message processMessage(String message) {
        return new Message(message);
    }
}