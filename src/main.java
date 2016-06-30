import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by matthewtduffin on 29/06/2016.
 */
public class main {
  public static void main(String[] args) {

    //***declare an empty arrayList of Strings to hold info
    ArrayList<String> list = new ArrayList<>();
    //***declare a boolean to know when to terminate the program
    boolean isReadyToQuit=false;

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
        addItem(input.substring(4),list);
      //if the first word is delete, followed by a space, then the remainder of the request in parse-able to an int
      } else if (firstWord(input).toLowerCase().equals("delete") && isInteger(input.substring(7))) {
        int a=Integer.parseInt(input.substring(7));
        //if the integer written is a valid reference the delete it, otherwise return error
        if (a>0 && a<=list.size()) {
          deleteItem(a-1,list);
        } else {
          System.out.println();
          System.out.println("Unfortunately I can't find an item number "+a+". Are you you sure you didn't mean a different item?");
          System.out.println();
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
    System.out.println("********");
    System.out.println("Thank you for using Inventory Tracker. Your completed inventory list is:");
    System.out.println();
    listItems(list);
    System.out.println("********");
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

  public static void listItems(ArrayList<String> list) {
  //method to print out items in the list - if non-empty!
    System.out.println();
    if (list.size()>0) {
      Collections.sort(list);
      int counter=0;
      for (String i : list) {
        counter++;
        System.out.print("Item # "+counter+": "+i.substring(0, 1).toUpperCase());
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
    System.out.println("****************************************");
    System.out.println("Hello there! Welcome to Dianna's Dinosaur & Donut Emporium's Inventory Tracker");
    System.out.println();
    System.out.println("Please update the list to reflect the latest shipment");
    System.out.println();
    System.out.println();
    System.out.println("When the list is up to date, type 'exit'. To see a list of my other commands, type 'help'.");
    System.out.println("****************************************");
    System.out.println();
  }
}