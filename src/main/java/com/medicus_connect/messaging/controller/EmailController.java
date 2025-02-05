package com.medicus_connect.messaging.controller;

import com.medicus_connect.messaging.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendText")
    public String sendTextEmail(@RequestParam String to,
                                @RequestParam String subject,
                                @RequestParam String body) {
        emailService.sendTextEmail(to, subject, body);
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
