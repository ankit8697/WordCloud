/**
 * @author Ankit Sanghi
 * @author Ryan Becker
 * This is the Binary Search Tree that contains Nodes that contain a word and its count.
 * It is sorted alphabetically by word
 */

import java.util.*;

public class WordCountMap {

  /**
   * This is a private Node class used to store the words and their counts
   */
  private class Node {
    private String word;
    private int count;
    private Node left;
    private Node right;

    /**
     * Constructor for the Node class
     * @param word  Word to be contained by the Node
     * @param count Number of times the word has been encountered
     */
    Node(String word, int count) {
      this.word = word;
      this.count = count;
      this.left = null;
      this.right = null;
    }
  }

  Node root;

  /**
   * If the specified word is already in this WordCountMap, then its
   * count is increased by one. Otherwise, the word is added to this map
   * with a count of 1.
   * @param word Word to be inserted into the binary tree
   */
  public void incrementCount(String word) {
    root = add(root, word);
  }

  /**
   * This is a helper method for incrementCount(String word)
   * This method iterates through the binary tree, adding the new Node if it isn't already present, or increments the count of the Node containing the word to be added
   * @param  n Current root node being looked at
   * @param  s Word to be inserted or who's count is to be incremented
   * @return Root node of current tree after all changes or additions are made
   */
  private Node add(Node n, String s) {
    if (n == null) {
      n = new Node(s,1);
      return n;
    }

    else {
      if (n.word.compareToIgnoreCase(s) > 0) {
        n.left = add(n.left, s);
      }
      else if (n.word.compareToIgnoreCase(s) < 0) {
        n.right = add(n.right, s);
      }
      else {
        n.count++;
      }
      return n;
    }
  }

   /**
   * @return an ArrayList of WordCount objects, one per word stored in this WordCountMap, sorted in decreasing order by count.
   */
  public ArrayList<WordCount> getWordCountsByCount() {
    ArrayList<WordCount> list = new ArrayList<>();
    list = inOrder(list, root);
    // This sort method is a built-in method in ArrayList
    // The null in the parameter tells the sort method to use the compareTo method built inside the WordCount objects 
    list.sort(null);
    // This method reverses the list
    Collections.reverse(list);
    return list;
  }

   /**
   * @return a list of WordCount objects, one per word stored in this WordCountMap, sorted alphabetically by word.
   */
  public ArrayList<WordCount> getWordCountsByWord() {
    ArrayList<WordCount> list = new ArrayList<>();
    list = inOrder(list, root);
    return list;
  }

  /**
   * Traverses the binary tree in order, adding the elements to a list in alphabetical order
   * @param list An ArrayList to store the WordCount objects encountered
   * @param n    The current root Node being looked at
   * @return     A list of WordCount objects sorted in alphabetical order
   */
  private ArrayList<WordCount> inOrder(ArrayList<WordCount> list, Node n) {
    if (n == null) {
      return list;
    }
    else {
      inOrder(list, n.left);
      list.add(new WordCount(n.word, n.count));
      inOrder(list, n.right);
    }
    return list;
  }
}
