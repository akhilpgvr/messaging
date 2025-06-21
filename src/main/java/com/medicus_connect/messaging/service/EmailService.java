package com.medicus_connect.messaging.service;

import com.medicus_connect.messaging.configuration.ConfigurationProperties;
import com.medicus_connect.messaging.exceptions.InvalidContentCodeException;
import com.medicus_connect.messaging.model.common.EmailData;
import com.medicus_connect.messaging.model.request.MessageRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    private ConfigurationProperties configurationProperties;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String getDate(Date date) {



        // Define the required format
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd");

        // Format and print the extracted part
        return formatter.format(date);
    }

    public String[] getSubjectAndBody(String[] subBody, String contentCode, EmailData metadata) {

        log.info("Fetching subject and content for {}", contentCode);
        switch(contentCode){

            case "success":
                subBody = configurationProperties.getSuccess().split("-");
                subBody[1] = subBody[1].replace("{customer_name}", metadata.getPatientName()).replace("{doctor_name}", metadata.getDoctorName()).replace("{appointment_date}", getDate(metadata.getAppointDate())).replace("{appointment_time}", metadata.getAppointTime());
                return subBody;
            case "cancellation":
                subBody = configurationProperties.getCancellation().split("-");
                subBody[1] = subBody[1].replace("{customer_name}", metadata.getPatientName()).replace("{doctor_name}", metadata.getDoctorName()).replace("{appointment_date}", getDate(metadata.getAppointDate())).replace("{appointment_time}", metadata.getAppointTime());
                return subBody;
            case "delay":
                subBody = configurationProperties.getDelay().split("-");
                subBody[1] = subBody[1].replace("{customer_name}", metadata.getPatientName()).replace("{doctor_name}", metadata.getDoctorName()).replace("{appointment_date}", getDate(metadata.getAppointDate())).replace("{appointment_time}", metadata.getAppointTime()).replace("{new_appointment_time}", metadata.getNewAppointTime());
                return subBody;
            case "alert":
                subBody = configurationProperties.getAlert().split("-");
                subBody[1] = subBody[1].replace("{location}", metadata.getLocation()).replace("{vehicle_no}", metadata.getVehicleNo());
                return subBody;
            default:
                throw new InvalidContentCodeException("Entered code "+contentCode+" is invalid");
        }

    }
    // Simple Text Email
    public void sendTextEmail(MessageRequest request) {

        request.getEmailDataList().forEach(i->{
            try {
                String[] subBody = new String[2];
                subBody = getSubjectAndBody(subBody, request.getContentCode(), i);
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(i.getMailId());
                helper.setSubject(subBody[0]);
                helper.setText(subBody[1]);
                log.info("Sending Email to "+i.getMailId()+" for "+subBody[0]);
                mailSender.send(message);
                System.out.println("Text Email sent successfully!");
            } catch (MessagingException e) {
                log.error("Error in sending Email: {}",e.getStackTrace());
                System.err.println("Failed to send email: " + e.getMessage());
            }
        });

    }

    // HTML Email
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // `true` enables HTML content

            mailSender.send(message);
            System.out.println("HTML Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    // Email with Attachment
    public void sendEmailWithAttachment(String to, String subject, String body, String filePath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            // Attach file
            helper.addAttachment(new File(filePath).getName(), new File(filePath));

            mailSender.send(message);
            System.out.println("Email with Attachment sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
