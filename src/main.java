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
    ArrayList<String> gameHistory=new ArrayList<>();

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
      } else if (input.toLowerCase().equals("history")) {
        if (gameHistory.size()>0) {
          printHistory(gameHistory);
        } else {
          printCentrally("You haven't played the game yet. To try it out, type 'play'.");
        }
    //if the first words of the command is add, followed by a space, then at least one other character, call add method
      } else if (firstWord(input).toLowerCase().equals("add") && input.length()>4) {
        if (input.substring(4).length()<60){
          if (isValidItem(input.substring(4))) {
            addItem(input.substring(4), list);
            System.out.println("\nYou have added '" + input.substring(4) + "' to the list");
          } else {
            System.out.println("\nUnfortunately I don't recognize that as a valid item. To use add, type 'add item name'. Note that typing numbers or typing consecutive spaces is invalid.\n");
          }
        } else {
          System.out.println("\nThat item is too long for me to process. Keep items under 60 characters long (including spaces).");
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
          String result=startGame(randItem);
          gameHistory.add(result);
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
    printCentrally("What would you like to do?");
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
    System.out.println("------------------------------------------");
    System.out.println("Type: 'delete n' to delete the nth item in the list.");
    System.out.println("Type: 'list' to list all items currently stored.");
    System.out.println("Type: 'add full item name' to add 'full item name' to the list.");
    System.out.println("Type: 'exit' if your list is complete.");
    System.out.println("Type: 'play' for a bonus game!");
  }

  public static void homeScreen() {
  //method to print out the layout of the home screen
    System.out.println("\n"+lineOfStars());
    printCentrally("Welcome to Dianna's Dinosaur & Donut Emporium's Inventory Tracker");
    System.out.println();
    printCentrally("The perfect place too keep track of items and have some fun!");
    System.out.println("\n");
    printCentrally("To see a list of commands, type 'help'.");
    System.out.println(lineOfStars()+"\n");
  }

  public static void printWin() {
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

  public static String currentStatus(String s, HashMap<Character,Boolean> h) {
    String t="";
    for (int i=0;i<s.length();i++) {
      char charAtI=s.charAt(i);
      if (charAtI==' ' && i!=(s.length()-1)) {
        t += "/ ";
      } else if (charAtI==' '){
        t+="  ";
      } else {
        if (h.get(charAtI)==true) {
          t+=(charAtI+" ");
        } else {
          t+="_ ";
        }
      }
    }
    return t;
  }

  public static String gameInput() {
    System.out.println();
    printCentrally("Which letter would you like to try?");
    System.out.println();
    Scanner input = new Scanner(System.in);
    String userInput = input.nextLine();

    if (userInput.length()==1) {
      char inputChar = userInput.toLowerCase().charAt(0);
      if (inputChar >= 97 && inputChar <= 122) {
        return userInput.toLowerCase();
      } else {
        return "typeIssue";
      }
    } else if (userInput.toLowerCase().equals("quit")) {
      return "quit";
    } else if (userInput.equalsIgnoreCase("history")){
      return "history";
    } else {
      return "lengthIssue";
    }
  }

  public static String startGame(String phrase) {
    //set counter: num of turns to return number of guessed taken, isWordGuessed to exit, letterGuessed to return error
    //if user tries to guess the same letter twice
    int numOfWrongGuesses=0;
    int numOfTurns=0;
    boolean isWordGuessed=false;
    ArrayList<Character> letterGuessed=new ArrayList<>();
    ArrayList<String> pastTurns=new ArrayList<>();
    String output="";

    //initialize game checker
    HashMap<Character,Boolean> hash=createLetterList(phrase);
    System.out.println("\n"+lineOfStars());
    printCentrally("WELCOME TO HANGMAN!");
    System.out.println(lineOfStars()+"\n");
    printCentrally("Aim: To guess the blank word below, one letter at a time!");
    printCentrally("------------------------");
    printCentrally("To try a letter: type it and hit the 'Enter' key");
    printCentrally("To view your previous moves: type 'history' and hit the 'Enter' key");
    printCentrally("To quit game: type 'quit' and hit the 'Enter' key");


    gameplay: while (!isWordGuessed && numOfWrongGuesses<8) {
      System.out.println();
      hangMan.displayHangman(numOfWrongGuesses);
      System.out.println();
      printCentrally(currentStatus(phrase, hash));
      System.out.println("");
      printCentrally("To try a letter, type it. Alternatively, you can view your history or exit the game!");

      String s=gameInput();
      if (s.equals("typeIssue")) {
        System.out.println("\nI didn't recognize that character. Please enter a valid letter.\n");
        addToHistory("Turn "+(++numOfTurns)+": You entered an invalid character",pastTurns);
      } else if (s.equals("lengthIssue")) {
        System.out.println("\nYour input must be precisely one character in length. Please try again.\n");
        addToHistory("Turn "+(++numOfTurns)+": You entered an unrecognized word",pastTurns);
      } else if (s.equals("quit")) {
        addToHistory("Turn "+(++numOfTurns)+": You quit the game",pastTurns);
        System.out.println(lineOfStars());
        printCentrally("Thank you for playing Hangman!");
        System.out.println(lineOfStars());
        output="Incomplete. You exited the game having made "+numOfWrongGuesses+" incorrect guesses.";
        break gameplay;
      } else if (s.equals("history")){
        printHistory(pastTurns);
        addToHistory("Turn "+(++numOfTurns)+": You viewed your game history",pastTurns);
      } else if (hash.containsKey(s.charAt(0))){
        if (hash.get(s.charAt(0))==false) {
          addToLetterHash(s, hash);
          letterGuessed.add(s.charAt(0));
          System.out.println("\nHurray! The words does contain at least one "+s+"\n");
          addToHistory("Turn "+(++numOfTurns)+": You made a successful guess - "+s,pastTurns);
          if (letterGuessed.size() == hash.size()) {
            isWordGuessed = true;
            System.out.println();
            printCentrally("Well done! You guessed the item!");
            printCentrally(currentStatus(phrase,hash));
            output="Win. You guessed the item, making "+numOfWrongGuesses+" incorrect guesses.";
          }
        } else {
          System.out.println("\nYou've already tried that letter. Try something else!\n");
          addToHistory("Turn "+(++numOfTurns)+": You tried a letter that you'd already tried - "+s,pastTurns);
        }
      } else {
        System.out.println("\nSadly not. There aren't any "+s+"'s in this one!\n");
        numOfWrongGuesses+=1;
        addToHistory("Turn "+(++numOfTurns)+": You made an unsuccessful guess - "+s,pastTurns);
      }
    }

    if (numOfWrongGuesses==8) {
      System.out.println("\nGame over. You've reached the maximum number of guesses. Better luck next time!\n");
      hangMan.displayHangman(8);
      output="Lose: Exceeded the maximum number of guesses.";
    }

    return output;
  }

  public static void printCentrally(String s) {
    int length=s.length();
    if (length<88 && length>0) {
      int blanks = 92 - length;
      int numSpaces = (int) blanks / 2;
      for (int i = 0; i < numSpaces; i++) {
        System.out.print(" ");
      }
      System.out.print(s+"\n");
    } else if (length==0) {
      System.out.println(("I can't print an empty string!\n"));
    } else {
      System.out.println(s+"\n");
    }
  }

  public static ArrayList<String> addToHistory(String s,ArrayList<String> a) {
    a.add(s);
    return a;
  }

  public static void  printHistory(ArrayList<String> a) {
    for (String s:a) {
      printCentrally(s);
    }
  }

}
