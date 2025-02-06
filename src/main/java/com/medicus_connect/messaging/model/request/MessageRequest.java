package com.medicus_connect.messaging.model.request;

import com.medicus_connect.messaging.model.common.EmailData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    private List<EmailData> emailDataList; // mailId doctor name, patient name, appointment time
    private String contentCode;
}
