import java.util.ArrayList;

/**
 * Created by matthewtduffin on 30/06/2016.
 */
public class hangMan {
  public hangMan() {
  }

  public static void displayHangman(int i) {
    ArrayList<String> hang=newHangman();
    for (int j=0;j<i;j++) {
      hang=nextHangman(j+1,hang);
    }
    printHangman(hang);
  }

  public static ArrayList<String> newHangman() {
    ArrayList<String> hangman=new ArrayList<>();
    hangman.add("* * * * * * * * * * *");
    for (int i=1;i<11;i++) {
      hangman.add("*                   *");
    }
    hangman.add("* * * * * * * * * * *");
    hangman.add(10,"*   =============   *");
    return hangman;
  }

  public static ArrayList<String> nextHangman(int numWrong,ArrayList<String> hangman) {
    switch(numWrong) {
      case 1:
        for (int i=2;i<=9;i++) {
          hangman.remove(i);
          hangman.add(i,"*   |               *");
        }
        break;
      case 2:
        hangman.remove(2);
        hangman.add(2,"*   + - - - +       *");
        break;
      case 3:
        hangman.remove(3);
        hangman.add(3,"*   |       |       *");
        break;
      case 4:
        hangman.remove(4);
        hangman.add(4,"*   |       O       *");
        break;
      case 5:
        for (int i=5;i<7;i++) {
          hangman.remove(i);
          hangman.add(i, "*   |       |       *");
        }
        break;
      case 6:
        hangman.remove(5);
        hangman.add(5,"*   |      _|_      *");
        break;
      case 7:
        hangman.remove(7);
        hangman.add(7,"*   |      / \\      *");
        break;
      case 8:
        for (int i=8;i<10;i++) {
          hangman.remove(i);
          hangman.add(5, "*   |               *");
        }
    }

    return hangman;
  }

  public static void printHangman(ArrayList<String> hangman) {
    for (String s:hangman) {
      main.printCentrally(s);
    }
    System.out.println();
  }


}
