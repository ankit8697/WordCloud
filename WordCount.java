/**
 * The WordCount class is used to store the word and its count.
 * @author Ankit Sanghi
 * @author Ryan Becker
 */

public class WordCount implements Comparable<WordCount> {
   public String word;
   public int count;

   /**
    * @param word String to be stored in the WordCount object
    * @param count Number of times this word has appeared
    */
   WordCount(String word, int count) {
     this.word = word;
     this.count = count;
   }

   /**
    * This method compares the count of the given WordCount object with the count of the current WordCount object
    * @param  comparison WordCount object to be compared to the current WordCount object
    * @return            A positive value, a negative value, or zero if the count of comparison is lesser than, greater than, or equal to the count of the current WordCount object respectively
    */
   public int compareTo(WordCount comparison) {
     if (count > comparison.count) {
       return 1;
     }
     else if (count < comparison.count) {
       return -1;
     }
     else {
       return 0;
     }
   }

   /**
    * Prints the relavent information present in the WordCount object
    * @return The string representation of the WordCount object
    */
   public String toString() {
     return (word + ": " + count);
   }
}
