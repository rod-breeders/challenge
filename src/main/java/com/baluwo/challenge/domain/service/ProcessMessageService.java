package com.baluwo.challenge.domain.service;

import com.baluwo.challenge.domain.model.Message;

public interface ProcessMessageService {

    Message processMessage(String string);
}