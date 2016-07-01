import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by matthewtduffin on 30/06/2016.
 */
public class playGame {

  public static void main(String[] args) {
    int result=startGame("the theatre");
  }

  public static HashMap<Character,Boolean> createLetterList(String phrase) {
    HashMap<Character,Boolean> map = new HashMap<>();
    for (int i=0;i<phrase.length();i++) {
      if (phrase.charAt(i)!=' ') {
        map.put(phrase.charAt(i),false);
      }
    }
    return map;
  }

  public static HashMap<Character,Boolean> addToLetterHash(String letter, HashMap<Character,Boolean> hash) {
    hash.put(letter.charAt(0),true);
    return hash;
  }

  public static void printCurrentStatus(String s, HashMap<Character,Boolean> h) {
    for (int i=0;i<s.length();i++) {
      char charAtI=s.charAt(i);
      if (charAtI==' ') {
        System.out.print("/ ");
      } else {
        if (h.get(charAtI)==true) {
          System.out.print(charAtI+" ");
        } else {
          System.out.print("_ ");
        }
      }
    }

  }

  public static String gameInput() {
    System.out.println("\n\nWhich letter would you like to try?");
    Scanner input = new Scanner(System.in);
    String userInput = input.nextLine();

    if (userInput.length()==1) {
      char inputChar=userInput.toLowerCase().charAt(0);
      if (inputChar>=97 && inputChar<=122) {
        return userInput.toLowerCase();
      } else {
        return "typeIssue";
      }
    } else {
      return "lengthIssue";
    }
  }

  public static int startGame(String phrase) {
    //set counter: num of turns to return number of guessed taken, isWordGuessed to exit, letterGuessed to return error
    //if user tries to guess the same letter twice
    int numOfWrongGuesses=0;
    boolean isWordGuessed=false;
    ArrayList<Character> letterGuessed=new ArrayList<>();

    //initialize game checker
    HashMap<Character,Boolean> hash=createLetterList(phrase);


    while (!isWordGuessed && numOfWrongGuesses<8) {
      printCurrentStatus(phrase, hash);
      String s=gameInput();
      if (s.equals("typeIssue")) {
        System.out.println("\nI didn't recognize that character. Please enter a valid letter.\n");
      } else if (s.equals("lengthIssue")) {
        System.out.println("\nYour input must be precisely one character in length. Please try again.\n");
      } else if (hash.containsKey(s.charAt(0))){
        if (hash.get(s.charAt(0))==false) {
          addToLetterHash(s, hash);
          letterGuessed.add(s.charAt(0));
          System.out.println("\nHurray! The words does contain at least one "+s+"\n");
          if (letterGuessed.size() == hash.size()) {
            isWordGuessed = true;
            System.out.println("\nWell done! You guessed the item!\n");
            printCurrentStatus(phrase,hash);
          }
        } else {
          System.out.println("\nYou've already tried that letter. Try something else!\n");
        }
      } else {
        System.out.println("\nSadly not. There aren't any "+s+"'s in this one!\n");
        numOfWrongGuesses+=1;
        System.out.println("\nYou've guessed a wrong letter "+numOfWrongGuesses+" times so far.");
      }
    }

    if (numOfWrongGuesses==8) {
      System.out.println("\nGame over. You've reached the maximum number of guesses. Better luck next time!\n");
    } else  {

    }

    return numOfWrongGuesses;
  }
}
