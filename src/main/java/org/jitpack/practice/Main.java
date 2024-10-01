package org.jitpack.practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var keyboard = new Scanner(System.in);
        while(true) {
            var input = keyboard.nextLine();
            try {
                var parser = new Parser(new Lexer(input));
                parser.parse();
                System.out.println("Valid JSON.");
            } catch (UnexpectedTokenException e) {
                System.err.println("Invalid JSON.");
                System.err.println(e.getMessage());
            }
        }
    }
}