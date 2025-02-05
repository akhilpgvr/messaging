package com.medicus_connect.messaging.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    List<String> emailIds; // mailId doctor name, patient name, appointment time
    String messageTypeCode;
}
