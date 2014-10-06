// Neal Friedman
// CS 3210
// GetToken()

import java.io.*;
import java.util.*;


public class GetToken
{
    public static void main(String[] args)
    {
        HashMap<String, Integer> keywords = new HashMap<String, Integer>();
        buildKeywordMap(keywords);
        scanFile(keywords);
    }

    public static void scanFile(HashMap<String,Integer> keywords)
    {
        String delims = "((?=[-+*/;<>()]) | (?<=[\\s]))"; // ********** needs work here **********
        try
        {
            Scanner inFile = new Scanner(new FileReader("/home/neal/IdeaProjects/GetToken/token.dat"));
            while (inFile.hasNext())
            {
                String line = inFile.nextLine();
                String[] pString = line.split(delims);
                List<String> parsedStringList = Arrays.asList(pString);
                for (int i = 0; i < parsedStringList.size(); i++)
                {
                    if (parsedStringList.get(i).equals(":") && parsedStringList.get(i + 1).equals("="))
                    {
                        parsedStringList.set(i+1, ":=");
                        i++;
                    }
                    getToken(parsedStringList.get(i), keywords);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }

    }

    public static HashMap buildKeywordMap(HashMap<String,Integer> keywords)
    {
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

        return keywords;
    }

    public static void getToken(String token, HashMap<String,Integer> keywords)
    {

        System.out.println(token);

//        if (keywords.containsKey(token))
//        {
//            System.out.println(token + " " + keywords.get(token));
//        }
//        else if(token.equals(""))
//        {
//            System.out.println("Space 26");
//        }
//        else if(token.matches("[A-Z]+"))
//        {
//            System.out.println("identifier: " + token + " 28");
//        }
//        else if(token.matches ("[0-9]"))
//        {
//            System.out.println("number: "  + token + " 29" );
//        }
//        else
//            System.out.println("Unsolved Token");
    }
}
