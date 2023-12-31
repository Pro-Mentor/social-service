package com.github.promentor.exceptions;

/**
 * Contains all application specific error codes.
 */
public enum ErrorCode {

    /**
     * This code is used for unknown errors.
     */
    UNKNOWN("E-0000") {
        @Override
        public String toString() {
            return "Unknown Error";
        }
    };

    private final String errorCode;

    /**
     * Creates a new instance of ErrorCode enum.
     *
     * @param errorCode A string containing the error code.
     */
    ErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the error code.
     *
     * @return A string containing the error code.
     */
    public String getValue() {
        return this.errorCode;
    }

}
