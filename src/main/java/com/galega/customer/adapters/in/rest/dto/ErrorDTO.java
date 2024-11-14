package com.galega.customer.adapters.in.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

    private String message;

    // public ErrorDTO(String message) {
    //     this.message = message;
    // }

    // public String getMessage() {
    //     return message;
    // }
}
