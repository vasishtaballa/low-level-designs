package dev.vasishta.lld.lms.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LMSException extends RuntimeException {

    public LMSException(String message) {
        super(message);
        log.error("Exception occurred: {}", message);
    }

    public LMSException(String message, Throwable cause) {
        super(message, cause);
        log.error("Exception occurred: {} and cause is: {}", message, cause);
    }
}
