import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.*;

/**
 * Created by matthewtduffin on 29/06/2016.
 */
public class main {

  public static void main(String[] args) {

    //***declare an empty arrayList of Strings to hold info
    ArrayList<String> list = new ArrayList<>();
    //***declare a boolean to know when to terminate the program
    boolean isReadyToQuit=false;

//while loop containing main game play
    while (!isReadyToQuit) {
      //start by showing home screen to user
      homeScreen();
      //accept user input
      String input = getUserInput();
    //check to see if user wants to exit, if so change boolean checker and print exit messages
      if (input.equals("exit")) {
        isReadyToQuit=true;
        exitTime(list);
    //check to see if the user needs help
      } else if (input.toLowerCase().equals("help")) {
        helpHer();
    //check to see if the user wants to list all current items
      } else if (input.toLowerCase().equals("list")) {
        listItems(list);
    //if the first words of the command is add, followed by a space, then at least one other character, call add method
      } else if (firstWord(input).toLowerCase().equals("add") && input.length()>4) {
        if (isValidItem(input.substring(4))) {
          addItem(input.substring(4), list);
          System.out.println("\nYou have added '"+input.substring(4)+"' to the list");
        } else {
          System.out.println("\nUnfortunately I don't recognize that as a valid item. To use add, type 'add item name'. Note that typing numbers or typing consecutive spaces is invalid.\n");
        }
    //if the first word is delete, followed by a space, then the remainder of the request in parse-able to an int
      } else if (firstWord(input).toLowerCase().equals("delete") && isInteger(input.substring(7))) {
        int a = Integer.parseInt(input.substring(7));
        //if the integer written is a valid reference the delete it, otherwise return error
        if (a > 0 && a <= list.size()) {
          deleteItem(a - 1, list);
        } else {
          System.out.println();
          System.out.println("Unfortunately I can't find an item number " + a + ". Are you you sure you didn't mean a different item?");
          System.out.println();
        }
    //if the user types play, initialize the game
      } else if (input.toLowerCase().equals("play")) {
        String randItem=randomItem(list);
        if (randItem.equals("  ")) {
          System.out.println("\nI can't play the game until you add some items to the inventory!\n");
        } else {
          int result=startGame(randItem);
        }
    //if the command received is any other form - I'm not going to try and process it
      } else {
        System.out.println();
        System.out.println("Unfortunately I didn't understand your request. For more information type 'help'");
        System.out.println();
      }
    }

  }

  public static void exitTime(ArrayList<String> list) {
  //method to exit the Inventory Tracker and print the final list
    System.out.println(lineOfStars());
    System.out.println("\nThank you for using Inventory Tracker. Your completed inventory list is:\n");
    listItems(list);
    System.out.println(lineOfStars());
  }

  public static boolean isInteger(String s) {
  //method to check if a given string is parse-able to an int
    if (s.length()==0) {
      return false;
    } else {
      try {
        Integer.parseInt(s);
      } catch(Exception e) {
        return false;
      }
      return true;
    }
  }

  public static String getUserInput() {
  //method to save user input as a string
    for (int i=1;i<=33;i++) {
      System.out.print(" ");
    }
    System.out.println("What would you like to do?");
    Scanner input = new Scanner(System.in);
    String userInput = input.nextLine();

    return userInput;
  }

  public static String firstWord(String input) {
  //method to return the first word of any string that contain a " " character
    if (input.contains(" ")) {
      String[] parts = input.split(" ");
      return parts[0];
    } else {
      return "void";
    }
  }

  public static void addItem(String item, ArrayList<String> oldList) {
  //method to add an item to the arrayList
      oldList.add(item);
  }

  public static boolean isValidItem(String item) {
    ArrayList<Integer> blankSpaceLocation=new ArrayList<>();

    for (int i=0;i<item.length();i++) {
      char inputChar=item.toLowerCase().charAt(i);
      if ((inputChar>=97 && inputChar<=122)) {
        //do nothing
      } else if (inputChar==' ') {
        if (i==0) {
          return false;
        }
        blankSpaceLocation.add(i);
        if (blankSpaceLocation.contains(i-1)) {
          return false;
        }
      } else {
        return false;
      }
    }
  return true;
  }

  public static void listItems(ArrayList<String> list) {
  //method to print out items in the list - if non-empty!
    System.out.println();
    if (list.size()>0) {
      Collections.sort(list);
      int counter=0;
      for (String i : list) {
        counter++;
        System.out.print("        Item # "+counter+": "+i.substring(0, 1).toUpperCase());
        if (i.length() > 1) {
          System.out.println(i.substring(1).toLowerCase());
        } else {
          System.out.println();
        }
      }
    } else {
      System.out.println("Your list currently contains no items.");
    }
    System.out.println();
  }

  public static String randomItem(ArrayList<String> list) {
    if (list.size()>0) {
      int index = (int) (Math.random() * list.size());
      return list.get(index);
    } else {
      return "  ";
    }
  }

  public static void deleteItem(int num, ArrayList<String> oldList) {
  //method to delete the item at index num item from oldList
    oldList.remove(num);
  }

  public static void helpHer() {
  //method to display help message to user
    System.out.println();
    System.out.println("Please enter one of the following options: ");
    System.out.println("Type: 'Delete n' to delete the nth item in the list.");
    System.out.println("Type: 'List' to list all items currently stored.");
    System.out.println("Type: 'Add full item name' to add 'full item name' to the list.");
    System.out.println("Type: 'exit' if your list is complete.");
    System.out.println();
  }

  public static void homeScreen() {
  //method to print out the layout of the home screen
    System.out.println("\n"+lineOfStars());
    System.out.println("Hello there! Welcome to Dianna's Dinosaur & Donut Emporium's Inventory Tracker\n");
    System.out.println("Please update the list to reflect the latest shipment\n\n");
    System.out.println("When the list is up to date, type 'exit'. To see a list of my other commands, type 'help'.");
    System.out.println(lineOfStars()+"\n");
  }

///game stuff below here

  public static String lineOfStars() {
  return "*******************************************************************************************";
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
    System.out.println("\n"+lineOfStars());
    System.out.println("*                                   WELCOME TO HANGMAN!                                   *");
    System.out.println(lineOfStars());

    while (!isWordGuessed && numOfWrongGuesses<8) {
      printCurrentStatus(phrase, hash);
      System.out.println("\n\nBRIEF OVERVIEW OF INSTRUCTION\n");

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

  public static void printCentrally(String s) {
    int length=s.length();
    if (length<80 && length>0) {
      int blanks = 92 - length;
      int numSpaces = (int) blanks / 2;
      for (int i = 0; i < numSpaces; i++) {
        System.out.print(" ");
      }
      System.out.print(s+"\n\n");
    } else if (length==0) {
      System.out.println(("I can't print an empty string!\n"));
    } else {
      System.out.println(s+"\n");
    }
  }

}
