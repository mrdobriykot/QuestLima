package com.javarush.quest.torkel.exception;

public class AppException extends RuntimeException {

    private final String source;

    public AppException(String message) {
        super(message);
        StackTraceElement throwLine = this.getStackTrace()[2];
        source = "%s:%d".formatted(throwLine.getClassName(), throwLine.getLineNumber());
    }

    @Override
    public String toString() {
        return source + getMessage();
    }


}
