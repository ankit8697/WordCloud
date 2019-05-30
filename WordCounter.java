/**
 * This class accepts text files and a command as input, and processes the text file according to the inputted command
 * @author Ankit Sanghi
 * @author Ryan Becker
 */

import java.util.*;
import java.io.*;

public class WordCounter {

  // ArrayList made to store the stopWords from the StopWords.txt file
  ArrayList<String> stopWords = new ArrayList<>();

 /**
  * This method processes the file inputted and creates a binary tree with all the words in the file
  * @param  fileName Name of the file to be processed
  * @return Binary Search Tree containing all the words in the text file
  */
  public WordCountMap processFile(String fileName) throws FileNotFoundException {
    File f = new File(fileName);
    // Checking if the file exists
    if (!f.exists()) {
      System.out.println("This file does not exist. Try again");
      // Exiting the program
      System.exit(0);
    }
    Scanner sc = new Scanner(f);
    WordCountMap wcm = new WordCountMap();
    String curLine;
    // Checking if the line has any words
    while (sc.hasNext()) {

      // Converting the line to lowercase and storing it in curLine
      curLine = sc.nextLine().toLowerCase();

      // Splitting curLine using any number of whitespaces
      String curArr[] = curLine.split("\\s+");

      for (int i = 0; i < curArr.length; i++) {
        // Checking if the current word is a stop word
        if (!stopWords.contains(curArr[i])) {
          // Removing digits and non-words
          curArr[i] = curArr[i].replaceAll("\\d", "");
          curArr[i] = curArr[i].replaceAll("\\W", "");
          // If the word is not an empty string, add it to the binary tree
          if (!curArr[i].isEmpty()) {
            wcm.incrementCount(curArr[i]);
          }
        }
      }
    }
    sc.close();
    return wcm;
  }

  /**
   * This method prints all the words along with their counts alphabetically
   * @param fileName Name of the file to be read through and processed
   */
  public void alphabetical(WordCountMap wcm) {
    ArrayList<WordCount> list = wcm.getWordCountsByWord();
    for(int i = 0; i < list.size(); i++) {
      // Checking if the current word is a stop word
      if (!stopWords.contains(list.get(i).word)) {
        System.out.println(list.get(i));
      }
    }
  }

  /**
   * This method prints the words that have the highest count
   * @param wcm Binary Search Tree object containing all the words of a file
   */
  public void frequency(WordCountMap wcm) {
    ArrayList<WordCount> list = wcm.getWordCountsByCount();
    for(int i = 0; i < list.size(); i++) {
      // Checking if the current word is a stop word
      if (!stopWords.contains(list.get(i).word)) {
        System.out.println(list.get(i));
      }
    }
  }

  /**
   * Create an HTML file containing the word cloud for a text file
   * @param fileName The file that is to be used to make the word cloud
   * @param wcm      The binary tree object containing the words from the file
   */
  public void cloud(String fileName, WordCountMap wcm, int size) {
    String fixedTitle = fileName.replace(".txt", "");
    String htmlTitle = fileName.replace(".txt", ".html");
    WordCloudMaker cloudMaker = new WordCloudMaker();

    ArrayList<WordCount> list = wcm.getWordCountsByCount();
    ArrayList<WordCount> requiredList = new ArrayList<>();

    // i iterates through the while loop
    int i = 0;
    // j iterates through the ArrayList list
    int j = 0;
    while (i < size) {
      // Checking if the word is a stop word
      if (!stopWords.contains(list.get(j).word)) {
        requiredList.add(list.get(j));
        i++;
      }
      j++;
    }
    cloudMaker.createWordCloudHTML(fixedTitle, requiredList, htmlTitle);
  }

  /**
   * Adds the stopwords from StopWords.txt to the stopWords ArrayList
   * @throws FileNotFoundException If the StopWords.txt file is removed, this method throws an error
   */
  private void addStopWords() throws FileNotFoundException {
    File stopWordsFile = new File("StopWords.txt");
    Scanner sc = new Scanner(stopWordsFile);
    while (sc.hasNext()) {
      stopWords.add(sc.nextLine());
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    WordCounter wc = new WordCounter();
    WordCountMap wcm;
    String command = args[0];
    String fileName = args[1];
    int size = 0;
    if (args.length == 3) {
      size = Integer.parseInt(args[2]);
    }
    // Adding stop words to the arraylist
    wc.addStopWords();
    // Processes the file and stores a binary search tree containing all the words in the file in the variable wcm
    wcm = wc.processFile(fileName);
    //
    if (command.equals("alphabetical")) {
      wc.alphabetical(wcm);
    }
    else if (command.equals("frequency")) {
      wc.frequency(wcm);
    }
    else if (command.equals("cloud")) {
      wc.cloud(fileName, wcm, size);
    }
    else {
      System.out.println("Invalid command, please try again.");
    }
  }
}
