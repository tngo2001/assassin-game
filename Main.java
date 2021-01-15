// CS211 Thompson Ngo Final Project
// 13 March 2020
// This program demonstrates project 4 from chapter 
// 16 of Building Java Programs, 5th Edition.

import java.io.*;
import java.util.*;

public class Main {
        public static void main(String[] args) throws FileNotFoundException {
                // Input file in which the program gets the data from.
                File fileName = new File("file.txt");
                Scanner input = new Scanner(fileName);
                boolean rand = true;
                // Used for pseudo random generation.
                int i = 48;
                // Represents the list of names from the given file.
                Set<String> nameList = new TreeSet<String>();
                // Reads the given file and adds the individual names
                // to nameList.
                while (input.hasNextLine()) {
                        String name = input.nextLine().trim();
                        if (name.length() > 0) {
                                nameList.add(name);
                        }
                }
                // Puts the list of names from the given file into an ArrayList.
                ArrayList<String> list = new ArrayList<String>(nameList);
                // If rand is true, the list is sorted in a random order.
                if (rand) {
                        Random rnd = new Random();
                        Collections.shuffle(list, rnd);
                }
                // If rand is false, i is used as the starting point of a
                // sequence for pseudo random generation. Sorts list into 
                // a pseudo random order.
                else {
                        Random rnd = new Random(i);
                        Collections.shuffle(list, rnd);
                }
                // Assassin object that takes the list as a parameter.
                Assassin assassin = new Assassin(list);
                // Calls start() method on Assassin object.
                start(assassin);
        }
        // Calls game() method while the game is still going. When the
        // game is over, the winner and the list of losers (alomg with
        // who killed them) is printed.
        public static void start(Assassin assassin){
                Scanner input = new Scanner(System.in);
                while (!assassin.gameOver()) {
                        game(input, assassin);
                }
                System.out.println("------------------------------");
                System.out.println("Winner: " + assassin.winner());
                System.out.println("------------------------------");
                System.out.println("Losers:");
                assassin.printGY();
                System.out.println("------------------------------");
        }
        // Prompts the user for the name of the person that is to be
        // killed. If the name that is typed doesn't exist in the kill
        // ring or is already in the graveyard, nobody is killed.
        // Also prints the list of the current kill ring and graveyard.
        public static void game(Scanner input, Assassin assassin) {
                System.out.println("------------------------------");
                System.out.println("Kill Ring:");
                assassin.printKR();
                System.out.println("------------------------------");
                System.out.println("Graveyard:");
                assassin.printGY();
                System.out.println("------------------------------");
                System.out.print("Who to kill?: ");
                String name = input.nextLine().trim();
                if (assassin.gyContains(name)) {
                        System.out.println(name + " is already in the graveyard.");
                } 
                else if (!assassin.krContains(name)) {
                        System.out.println(name + " doesn't exist in the kill ring.");
                } 
                else {
                        assassin.kill(name);
                }
                System.out.println();
        }
}