package com.kirik.ttcraft.main.util;

public class TTCraftCommandException extends Exception {
    private static final long serialversionUID = 1L;

    private char color = '5';

    public TTCraftCommandException(String message) {
        super(message);
    }

    public TTCraftCommandException(Throwable cause) {
        super(cause);
    }

    public TTCraftCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public TTCraftCommandException setColor(char color) {
        this.color = color;
        return this;
    }

    public char getColor() {
        return color;
    }
}
