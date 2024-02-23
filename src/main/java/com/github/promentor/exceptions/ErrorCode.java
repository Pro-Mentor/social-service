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
    },

    /**
     * This code indicates the given input not valid.
     */
    INPUT_VALIDATION_ERROR("E-0001") {
        @Override
        public String toString() { return "The given input is not valid"; }
    },

    /**
     * This code indicates the given id is not a valid ID
     */
    INVALID_OBJECT("E-0002") {
        @Override
        public String toString() { return "Given ID is not valid"; }
    },

    /**
     * This code indicates the authorization issue.
     */
    AUTHENTICATION_FAILED("E-0003") {
        @Override
        public String toString() { return "Authorization Failed"; }
    },

    /**
     * This code indicates the forbidden issue.
     */
    FORBIDDEN("E-0004") {
        @Override
        public String toString() { return "Forbidden. Don't have access"; }
    },

    /**
     * This code indicates that the requested post not found
     */
    POST_NOT_FOUND("E-0010") {
        @Override
        public String toString() { return "Post Not Found"; }
    },

    /**
     * This code indicates that the requested post is not a requested user post
     */
    NOT_POST_OWNER("E-0010") {
        @Override
        public String toString() { return "Don't have access to modify the post."; }
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
