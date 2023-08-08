package com.pragma.powerup.infrastructure.out.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "traceability")
@Data
public class Traceability {

    @Id
    private String id;
    private String orderId;
    private String customerId;
    private String customerEmail;
    private String date;
    private String lastStatus;
    private String newStatus;
    private String employeeId;
    private String employeeEmail;
}
