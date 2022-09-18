package javaBasic02;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("������� ���� �� �����:");
        //������� ����, �������� C:\Users\User_name\path...\src\javaBasic02\input.txt
        //� ����� nput.txt ���� ������ �����
        String input = scanner.nextLine().strip();
        String file;
        try {
            file = Files.readString(Path.of(input));
        } catch (IOException exception) {
            System.out.println("������: ���� �� ������ ��� ���� ����� �����������" + exception);
            return;
        }
        String[] words = file.split("[^a-zA-Z]");
        TreeMap<String, Integer> wordStat = new TreeMap<>();
        int wordsAmount = 0;
        for (String word : words) {
            if (word.isBlank()) {
                continue;
            }
            wordsAmount++;
            String lowercaseWord = word.toLowerCase();
            Integer wordCount = wordStat.getOrDefault(lowercaseWord, 0);
            wordStat.put(lowercaseWord, wordCount + 1);
        }
        if (wordStat.isEmpty()) {
            System.out.println("������: � ����� ��� ����!");
            return;
        }

        System.out.println("����� � �����:");
        System.out.println(wordStat.keySet());

        System.out.println("����� ���� � �����: " + wordsAmount);
        System.out.println();
        System.out.println("����������:");
        for (Map.Entry<String, Integer> entry : wordStat.entrySet()) {
            String word = entry.getKey();
            Integer count = entry.getValue();
            System.out.printf("%s:      ���������� - %3d, ������� - %.2f%%\n", word, count, (count/(double)wordsAmount));
        }

        TreeSet<String> maxWords = new TreeSet<>();
        int max = -1;
        for (String word : wordStat.keySet()) {
            Integer wordCount = wordStat.get(word);
            if (wordCount > max) {
                max = wordCount;
                maxWords.clear();
                maxWords.add(word);
            } else if (wordCount == max) {
                maxWords.add(word);
            }
            
        }
        System.out.println();
        System.out.println("���� ����� ����������� �����: ");
        System.out.println(maxWords);
    }
}