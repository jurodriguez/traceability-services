package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TraceabilityRequestDto {
    @NotBlank(message = "The order id is required")
    private String orderId;
    @NotBlank(message = "The customer id is required")
    private String customerId;
    @NotBlank(message = "The customer email is required")
    private String customerEmail;
    @NotBlank(message = "The date is required")
    private String date;
    private String lastStatus;
    @NotBlank(message = "The new status is required")
    private String newStatus;
    private String employeeId;
    private String employeeEmail;
}
