package com.medicus_connect.messaging.controller;

import com.medicus_connect.messaging.model.request.MessageRequest;
import com.medicus_connect.messaging.service.MessagingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private MessagingService messagingService;

    @Operation(summary = "Api for sending delay and cancellation messages", description = "")
    @GetMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestParam MessageRequest messageRequest) {

        log.info("Calling MessagingService to send message");
        return new ResponseEntity<>(messagingService.sendMessage(messageRequest), HttpStatus.OK);
    }
}
