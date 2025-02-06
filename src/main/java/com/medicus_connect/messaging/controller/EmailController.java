package com.medicus_connect.messaging.controller;

import com.medicus_connect.messaging.model.request.MessageRequest;
import com.medicus_connect.messaging.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendText")
    public String sendTextEmail(@RequestBody MessageRequest messageRequest) {

        log.info("Calling email service");
        emailService.sendTextEmail(messageRequest);
        return "Text email sent successfully!";
    }

    @PostMapping("/sendHtml")
    public String sendHtmlEmail(@RequestParam String to,
                                @RequestParam String subject,
                                @RequestParam String htmlContent) {
        emailService.sendHtmlEmail(to, subject, htmlContent);
        return "HTML email sent successfully!";
    }

    @PostMapping("/sendWithAttachment")
    public String sendEmailWithAttachment(@RequestParam String to,
                                          @RequestParam String subject,
                                          @RequestParam String body,
                                          @RequestParam String filePath) {
        emailService.sendEmailWithAttachment(to, subject, body, filePath);
        return "Email with attachment sent successfully!";
    }
}
