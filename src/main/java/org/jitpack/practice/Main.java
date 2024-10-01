package org.jitpack.practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = null;
        try (var keyboard = new Scanner(System.in)){
            input = keyboard.nextLine();
            var parser = new Parser(new Lexer(input));
            parser.parse();
            System.out.println("Json Object " + input + " is valid.");
        } catch (UnexpectedTokenException e) {
            System.err.println("Json Object " + input + " is invalid.");
            System.err.println(e.getMessage());
        }
    }
}