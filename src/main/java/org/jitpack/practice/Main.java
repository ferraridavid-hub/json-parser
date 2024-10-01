package org.jitpack.practice;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        var inputStream = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder input = new StringBuilder();
        String line;
        while ((line = inputStream.readLine()) != null) {
            input.append(line).append("\n");
        }
        try {
            var parser = new Parser(new Lexer(input.toString()));
            parser.parse();
            System.out.println("Valid JSON.");
        } catch (UnexpectedTokenException e) {
            System.err.println("Invalid JSON.");
            System.err.println(e.getMessage());
        }
    }
}