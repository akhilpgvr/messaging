package com.medicus_connect.messaging.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailData {

    private String mailId;
    private String patientName;
    private String doctorName;
    private Date appointDate;
    private LocalTime appointTime;
    private LocalTime newAppointTime;
}
