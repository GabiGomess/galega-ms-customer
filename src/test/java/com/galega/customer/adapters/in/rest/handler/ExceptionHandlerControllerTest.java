package com.galega.customer.adapters.in.rest.handler;

import com.galega.customer.adapters.in.rest.dto.ErrorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.BDDAssertions.then;

public class ExceptionHandlerControllerTest {

    @SuppressWarnings("null")
    @Test
    public void testHandleIllegalArgumentException() {
        // Given
        ExceptionHandlerController controller = new ExceptionHandlerController();
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");

        // When
        ResponseEntity<ErrorDTO> response = controller.handleIllegalArgumentException(exception);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(response.getBody().getMessage()).isEqualTo("Parameters or request body is invalid");
    }
}
