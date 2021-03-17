package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> arr = new ArrayList<>();
        int words = 1;
        int sentences = 1;
        int ilosc2 = 0;
        File file = new File(args[0]);
        String text = "";
        Scanner sca = new Scanner(file);
        while (sca.hasNext()) {
            text = sca.nextLine();
        }
        sca.close();
        System.out.println("The text is:");
        System.out.println(text);
        System.out.println();

        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                words++;
            }
            if (chars[i] == '.') {
                arr.add(i);

            }
            if (chars[i] == '!') {
                arr.add(i);

            }
            if (chars[i] == '?') {
                arr.add(i);

            }
        }
        System.out.println("Words:" + " " + words);


        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) + 2 > text.length()) {
                break;
            } else {
                if (text.substring(arr.get(i) + 1, arr.get(i) + 2).matches(" ") &&
                        text.substring(arr.get(i) + 2, arr.get(i) + 3).matches("[A-Z_0-9]")) {
                    sentences++;
                }
            }
        }
        System.out.println("Sentences:" + " " + sentences);
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ')
                ilosc2++;
        }
        System.out.println("Characters:" + " " + ilosc2);

        int letters = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ' &&
                    text.charAt(i) != '!' &&
                    text.charAt(i) != '?' &&
                    text.charAt(i) != '.' &&
                    text.charAt(i) != ',' &&
                    text.charAt(i) != '-' &&
                    text.charAt(i) != ';' &&
                    text.charAt(i) != '0' &&
                    text.charAt(i) != '1' &&
                    text.charAt(i) != '2' &&
                    text.charAt(i) != '3' &&
                    text.charAt(i) != '4' &&
                    text.charAt(i) != '5' &&
                    text.charAt(i) != '6' &&
                    text.charAt(i) != '7' &&
                    text.charAt(i) != '8' &&
                    text.charAt(i) != '9')
                letters++;
        }


        String[] s = text.split(" ");
        ArrayList<Integer> ilvol = new ArrayList<Integer>();


        for (int i = 0; i < s.length; i++) {
            ilvol.add(countWithRegex(s[i]));
        }


        double score = (((4.71 * ilosc2) / words) + ((0.5 * words) / sentences)) - 21.43;
        double sc = (Math.round(score * 100)) / 100.0;


        int syllables = -1;
        for (int i = 0; i < ilvol.size(); i++) syllables = syllables + ilvol.get(i);

        System.out.println("Syllables:" + " " + syllables);


        double score1 = (((0.39 * words) / sentences) + ((11.8 * syllables) / words)) - 15.59;
        double sc1 = (Math.round(score1 * 100)) / 100.0;


        int ilpol = 1;
        for (int i = 0; i < ilvol.size(); i++) {
            if (ilvol.get(i) > 2) {
                ilpol++;
            }

        }
        System.out.println("Polysyllables:" + " " + ilpol);

        double score2 = (1.043 * (Math.sqrt(ilpol * (30.0 / sentences)))) + 3.1291;
        double sc2 = (Math.round(score2 * 100)) / 100.0;


        double L = (ilosc2 * 100.0) / words;
        double S = (sentences * 100.0) / words;

        double score3 = 0.0588 * L - 0.296 * S - 15.8;
        double sc3 = (Math.round(score3 * 100)) / 100.0;

        String ari = "Automated Readability Index:";
        String fk = "Flesch-Kincaid readability tests:";
        String smog = "Simple Measure of Gobbledygook:";
        String cl = "Coleman–Liau index:";

        String comment = "";
        String comment1 = "";
        String comment2 = "";
        String comment3 = "";


        if (score >= 0 && score <= 0.5) {
            comment = "(about 5 year olds).";
        } else if (score > 0.5 && score <= 1.5) {
            comment = "(about 6 year olds).";
        } else if (score > 1.5 && score <= 2.5) {
            comment = "(about 7 year olds).";
        } else if (score > 2.5 && score <= 3.5) {
            comment = "(about 9 year olds).";
        } else if (score > 3.5 && score <= 4.5) {
            comment = "(about 10 year olds).";
        } else if (score > 4.5 && score <= 5.5) {
            comment = "(about 11 year olds).";
        } else if (score > 5.5 && score <= 6.5) {
            comment = "(about 12 year olds).";
        } else if (score > 6.5 && score <= 7.5) {
            comment = "(about 13 year olds).";
        } else if (score > 7.5 && score <= 8.5) {
            comment = "(about 14 year olds).";
        } else if (score > 8.5 && score <= 9.5) {
            comment = "(about 15 year olds).";
        } else if (score > 9.5 && score <= 10.5) {
            comment = "(about 16 year olds).";
        } else if (score > 10.5 && score <= 11.5) {
            comment = "(about 17 year olds).";
        } else if (score > 11.5 && score <= 12.5) {
            comment = "(about 18 year olds).";
        } else if (score > 12.5 && score <= 13.5) {
            comment = "(about 24 year olds).";
        } else {
            comment = "(about 24+ year olds).";
        }

        if (score1 >= 0 && score1 <= 0.5) {
            comment1 = "(about 5 year olds).";
        } else if (score1 > 0.5 && score1 <= 1.5) {
            comment1 = "(about 6 year olds).";
        } else if (score1 > 1.5 && score1 <= 2.5) {
            comment1 = "(about 7 year olds).";
        } else if (score1 > 2.5 && score1 <= 3.5) {
            comment1 = "(about 9 year olds).";
        } else if (score1 > 3.5 && score1 <= 4.5) {
            comment1 = "(about 10 year olds).";
        } else if (score1 > 4.5 && score1 <= 5.5) {
            comment1 = "(about 11 year olds).";
        } else if (score1 > 5.5 && score1 <= 6.5) {
            comment1 = "(about 12 year olds).";
        } else if (score1 > 6.5 && score1 <= 7.5) {
            comment1 = "(about 13 year olds).";
        } else if (score1 > 7.5 && score1 <= 8.5) {
            comment1 = "(about 14 year olds).";
        } else if (score1 > 8.5 && score1 <= 9.5) {
            comment1 = "(about 15 year olds).";
        } else if (score1 > 9.5 && score1 <= 10.5) {
            comment1 = "(about 16 year olds).";
        } else if (score1 > 10.5 && score1 <= 11.5) {
            comment1 = "(about 17 year olds).";
        } else if (score1 > 11.5 && score1 <= 12.5) {
            comment1 = "(about 18 year olds).";
        } else if (score1 > 12.5 && score1 <= 13.5) {
            comment1 = "(about 24 year olds).";
        } else {
            comment1 = "(about 24+ year olds).";
        }

        if (score2 >= 0 && score2 <= 0.5) {
            comment2 = "(about 5 year olds).";
        } else if (score2 > 0.5 && score2 <= 1.5) {
            comment2 = "(about 6 year olds).";
        } else if (score2 > 1.5 && score2 <= 2.5) {
            comment2 = "(about 7 year olds).";
        } else if (score2 > 2.5 && score2 <= 3.5) {
            comment2 = "(about 9 year olds).";
        } else if (score2 > 3.5 && score2 <= 4.5) {
            comment2 = "(about 10 year olds).";
        } else if (score2 > 4.5 && score2 <= 5.5) {
            comment2 = "(about 11 year olds).";
        } else if (score2 > 5.5 && score2 <= 6.5) {
            comment2 = "(about 12 year olds).";
        } else if (score2 > 6.5 && score2 <= 7.5) {
            comment2 = "(about 13 year olds).";
        } else if (score2 > 7.5 && score2 <= 8.5) {
            comment2 = "(about 14 year olds).";
        } else if (score2 > 8.5 && score2 <= 9.5) {
            comment2 = "(about 15 year olds).";
        } else if (score2 > 9.5 && score2 <= 10.5) {
            comment2 = "(about 16 year olds).";
        } else if (score2 > 10.5 && score2 <= 11.5) {
            comment2 = "(about 17 year olds).";
        } else if (score2 > 11.5 && score2 <= 12.5) {
            comment2 = "(about 18 year olds).";
        } else if (score2 > 12.5 && score2 <= 13.5) {
            comment2 = "(about 24 year olds).";
        } else {
            comment2 = "(about 24+ year olds).";
        }

        if (score3 >= 0 && score3 <= 0.5) {
            comment3 = "(about 5 year olds).";
        } else if (score3 > 0.5 && score3 <= 1.5) {
            comment3 = "(about 6 year olds).";
        } else if (score3 > 1.5 && score3 <= 2.5) {
            comment3 = "(about 7 year olds).";
        } else if (score3 > 2.5 && score3 <= 3.5) {
            comment3 = "(about 9 year olds).";
        } else if (score3 > 3.5 && score3 <= 4.5) {
            comment3 = "(about 10 year olds).";
        } else if (score3 > 4.5 && score3 <= 5.5) {
            comment3 = "(about 11 year olds).";
        } else if (score3 > 5.5 && score3 <= 6.5) {
            comment3 = "(about 12 year olds).";
        } else if (score3 > 6.5 && score3 <= 7.5) {
            comment3 = "(about 13 year olds).";
        } else if (score3 > 7.5 && score3 <= 8.5) {
            comment3 = "(about 14 year olds).";
        } else if (score3 > 8.5 && score3 <= 9.5) {
            comment3 = "(about 15 year olds).";
        } else if (score3 > 9.5 && score3 <= 10.5) {
            comment3 = "(about 16 year olds).";
        } else if (score3 > 10.5 && score3 <= 11.5) {
            comment3 = "(about 17 year olds).";
        } else if (score3 > 11.5 && score3 <= 12.5) {
            comment3 = "(about 18 year olds).";
        } else if (score3 > 12.5 && score3 <= 13.5) {
            comment3 = "(about 24 year olds).";
        } else {
            comment3 = "(about 24+ year olds).";
        }


        Scanner scan = new Scanner(System.in);
        String choice;
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):" + " ");
        choice = scan.next();
        switch (choice) {
            case "ARI":
                System.out.println(ari + " " + sc + " " + comment);
                break;
            case "FK":
                System.out.println(fk + " " + sc1 + " " + comment1);
                break;
            case "SMOG":
                System.out.println(smog + " " + sc2 + " " + comment2);
                break;
            case "CL":
                System.out.println(cl + " " + sc3 + " " + comment3);
                break;
            case "all":
                System.out.println(ari + " " + sc + " " + comment);
                System.out.println(fk + " " + sc1 + " " + comment1);
                System.out.println(smog + " " + sc2 + " " + comment2);
                System.out.println(cl + " " + sc3 + " " + comment3);

                double average;
                String digits = comment.substring(7, 9);
                String digits1 = comment1.substring(7, 9);
                String digits2 = comment2.substring(7, 9);
                String digits3 = comment3.substring(7, 9);
                int dig = Integer.parseInt(digits);
                int dig1 = Integer.parseInt(digits1);
                int dig2 = Integer.parseInt(digits2);
                int dig3 = Integer.parseInt(digits3);

                int suma = dig + dig1 + dig2 + dig3;
                average = suma / 4.0;
                System.out.println();
                System.out.println("This text should be understood in average by" + " " + average + " " + "year olds.");
                break;
        }


        if (Math.floor(score) == 0 || Math.floor(score1) == 0 || Math.floor(score2) == 0 || Math.floor(score3) == 0) {
            comment = "about 5 year olds.";
        } else if (Math.ceil(score) == 1 || Math.ceil(score1) == 1 || Math.ceil(score2) == 1 || Math.ceil(score3) == 1) {
            comment = "about 5 year olds.";
        } else if (Math.floor(score) == 1 || Math.floor(score1) == 1 || Math.floor(score2) == 1 || Math.floor(score3) == 1) {
            comment = "about 6 year olds.";
        } else if (Math.ceil(score) == 2 || Math.ceil(score1) == 2 || Math.ceil(score2) == 2 || Math.ceil(score3) == 2) {
            comment = "about 6 year olds.";
        } else if (Math.floor(score) == 2 || Math.floor(score1) == 2 || Math.floor(score2) == 2 || Math.floor(score3) == 2) {
            comment = "about 7 year olds.";
        } else if (Math.ceil(score) == 3 || Math.ceil(score1) == 3 || Math.ceil(score2) == 3 || Math.ceil(score3) == 3) {
            comment = "about 7 year olds.";
        } else if (Math.floor(score) == 3 || Math.floor(score1) == 3 || Math.floor(score2) == 3 || Math.floor(score3) == 3) {
            comment = "about 9 year olds.";
        } else if (Math.ceil(score) == 4 || Math.ceil(score1) == 4 || Math.ceil(score2) == 4 || Math.ceil(score3) == 4) {
            comment = "about 9 year olds.";
        } else if (Math.floor(score) == 4 || Math.floor(score1) == 4 || Math.floor(score2) == 4 || Math.floor(score3) == 4) {
            comment = "about 10 year olds.";
        } else if (Math.ceil(score) == 5 || Math.ceil(score1) == 5 || Math.ceil(score2) == 5 || Math.ceil(score3) == 5) {
            comment = "about year 10 olds.";
        } else if (Math.floor(score) == 5 || Math.floor(score1) == 5 || Math.floor(score2) == 5 || Math.floor(score3) == 5) {
            comment = "about year 11 olds.";
        } else if (Math.ceil(score) == 6 || Math.ceil(score1) == 6 || Math.ceil(score2) == 6 || Math.ceil(score3) == 6) {
            comment = "about year 11 olds.";
        } else if (Math.floor(score) == 6 || Math.floor(score1) == 6 || Math.floor(score2) == 6 || Math.floor(score3) == 6) {
            comment = "about 12 year olds.";
        } else if (Math.ceil(score) == 7 || Math.ceil(score1) == 7 || Math.ceil(score2) == 7 || Math.ceil(score3) == 7) {
            comment = "about 12 year olds.";
        } else if (Math.floor(score) == 7 || Math.floor(score1) == 7 || Math.floor(score2) == 7 || Math.floor(score3) == 7) {
            comment = "about 13 year olds.";
        } else if (Math.ceil(score) == 8 || Math.ceil(score1) == 8 || Math.ceil(score2) == 8 || Math.ceil(score3) == 8) {
            comment = "about 13 year olds.";
        } else if (Math.floor(score) == 8 || Math.floor(score1) == 8 || Math.floor(score2) == 8 || Math.floor(score3) == 8) {
            comment = "about 14 year olds.";
        } else if (Math.ceil(score) == 9 || Math.ceil(score1) == 9 || Math.ceil(score2) == 9 || Math.ceil(score3) == 9) {
            comment = "about 14 year olds.";
        } else if (Math.floor(score) == 9 || Math.floor(score1) == 9 || Math.floor(score2) == 9 || Math.floor(score3) == 9) {
            comment = "about 15 year olds.";
        } else if (Math.ceil(score) == 10 || Math.ceil(score1) == 10 || Math.ceil(score2) == 10 || Math.ceil(score3) == 10) {
            comment = "about 15 year olds.";
        } else if (Math.floor(score) == 10 || Math.floor(score1) == 10 || Math.floor(score2) == 10 || Math.floor(score3) == 10) {
            comment = "about 16 year olds.";
        } else if (Math.ceil(score) == 11 || Math.ceil(score1) == 11 || Math.ceil(score2) == 11 || Math.ceil(score3) == 11) {
            comment = "about 16 year olds.";
        } else if (Math.floor(score) == 11 || Math.floor(score1) == 11 || Math.floor(score2) == 11 || Math.floor(score3) == 11) {
            comment = "about 17 year olds.";
        } else if (Math.ceil(score) == 12 || Math.ceil(score1) == 12 || Math.ceil(score2) == 12 || Math.ceil(score3) == 12) {
            comment = "about 17 year olds.";
        } else if (Math.floor(score) == 12 || Math.floor(score1) == 12 || Math.floor(score2) == 12 || Math.floor(score3) == 12) {
            comment = "about 18 year olds.";
        } else if (Math.ceil(score) == 13 || Math.ceil(score1) == 13 || Math.ceil(score2) == 13 || Math.ceil(score3) == 13) {
            comment = "about 18 year olds.";
        } else if (Math.floor(score) == 13 || Math.floor(score1) == 13 || Math.floor(score2) == 13 || Math.floor(score3) == 13) {
            comment = "about 24 year olds.";
        } else if (Math.ceil(score) == 14 || Math.ceil(score1) == 14 || Math.ceil(score2) == 14 || Math.ceil(score3) == 14) {
            comment = "about 24 year olds.";
        } else {
            comment = "about 24+ year olds.";

        }


    }

    private static int countWithRegex(String word) {
        String i = "(?i)[aiouy][aeiouy]*|e[aeiouy]*(?!d?\\b)";
        Matcher m = Pattern.compile(i).matcher(word);
        int count = 0;

        while (m.find()) {
            count++;
        }

        return Math.max(count, 1);
    }
}



