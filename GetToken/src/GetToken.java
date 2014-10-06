/*
Neal Friedman
CS 3210
GetToken()
This program reads in a code file and splits it into tokens.
These tokens are categorized by certain types and each type
is associated with a number. All tokens are printed out to
the terminal in order along with their accompanying number.
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;


public class GetToken {
    public static void main(String[] args) {
        HashMap<String, Integer> keywords = new HashMap<String, Integer>();
        buildKeywordMap(keywords);
        scanFile(keywords);
    }

    // Scan and parse the file into tokens
    public static void scanFile(HashMap<String, Integer> keywords) {
        String line;
        try {
            Scanner inFile = new Scanner(new FileReader("token.dat"));
            while (inFile.hasNext()) {
                line = inFile.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    String token = "";
                    char c = line.charAt(i);

                    switch (c) {
                        case ' ':
                            token = "SPACE";
                            break;
                        case '+':
                            token = "+";
                            break;
                        case '*':
                            token = "*";
                            break;
                        case '/':
                            token = "/";
                            break;
                        case '-':
                            token = "-";
                            break;
                        case ':':
                            if (line.charAt(i + 1) == '=') {
                                token = ":=";
                                i++;
                            }
                            break;
                        case '=':
                            token = "=";
                            break;
                        case '<':
                            if (line.charAt(i + 1) == '=') {
                                token = "<=";
                                i++;
                                break;
                            }
                            if (line.charAt(i + 1) == '>') {
                                token = "<>";
                                i++;
                                break;
                            } else {
                                token = "<";
                                break;
                            }
                        case '>':
                            if (line.charAt(i + 1) == '=') {
                                token = "<=";
                                i++;
                                break;
                            } else {
                                token = "<";
                                break;
                            }

                        case '(':
                            token = "(";
                            break;
                        case ')':
                            token = ")";
                            break;
                        case '.':
                            token = ".";
                            break;
                        case ';':
                            token = ";";
                            break;
                        case '"':
                            i++;
                            c = line.charAt(i);
                            while (c != '"') {
                                token += c;
                                i++;
                                c = line.charAt(i);
                            }
                            i++;
                            token = '"' + token + '"';
                            break;
                    }

                    while (((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || c == '.') && i < line.length()) {
                        token += Character.toString(c);
                        if (i == line.length() - 1)
                            break;
                        i++;
                        c = line.charAt(i);
                        if (c == ' ')
                            i--;
                    }
                    getToken(token, keywords); //Match the token to the map and return the token
                }
                getToken("EOLN", keywords); //Reached end of line.
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    //Hashmap of possible keywords and token names
    public static void buildKeywordMap(HashMap<String, Integer> keywords) {
        keywords.put("IF", 1);
        keywords.put("THEN", 2);
        keywords.put("ELSE", 3);
        keywords.put("FI", 4);
        keywords.put("LOOP", 5);
        keywords.put("BREAK", 6);
        keywords.put("READ", 7);
        keywords.put("PRINT", 8);
        keywords.put("AND", 9);
        keywords.put("OR", 10);
        keywords.put(".", 11);
        keywords.put(")", 12);
        keywords.put("(", 13);
        keywords.put("/", 14);
        keywords.put("*", 15);
        keywords.put("-", 16);
        keywords.put("+", 17);
        keywords.put("<>", 18);
        keywords.put(">", 19);
        keywords.put(">=", 20);
        keywords.put("=", 21);
        keywords.put("<=", 22);
        keywords.put("<", 23);
        keywords.put(":=", 24);
        keywords.put(";", 25);
        keywords.put("SPACE", 26);
        keywords.put("EOLN", 27);
        keywords.put("identifiers", 28);
        keywords.put("numbers", 29);
        keywords.put("string", 30);
        keywords.put("END", 31);
    }

    // Receives token and matches it to its token type
    public static void getToken(String token, HashMap<String, Integer> keywords) {

        if (keywords.containsKey(token)) {
            System.out.println(token + " " + keywords.get(token));
        } else if (token.matches("[A-Z]+")) {
            System.out.println(token + " 28");
        } else if (token.matches("\\d*\\.?\\d+")) {
            System.out.println(token + " 29");
        } else if (token.matches("\"(.*?)\"")) {
            token = token.substring(1, token.length() - 1);
            System.out.println(token + " 30");
        } else
            System.out.println("************ Unsolved Token ***************");
    }
}
