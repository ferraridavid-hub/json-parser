package org.jitpack.practice;

public class UnexpectedTokenException extends Exception{
    public UnexpectedTokenException(String s) {
        super("Unexpected token: " + s);
    }
}
