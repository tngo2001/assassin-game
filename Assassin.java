// CS211 Thompson Ngo Final Project
// 13 March 2020
// This program models a game of Assassin. The game reads the names of the 
// initial kill ring from a file and puts them into a linked list in random order. 
// Then the game repeatedly prompts for the name of a person that has been killed. 
// The game continues until only one player remains and is declared the winner.

import java.util.ArrayList;

public class Assassin {
        private Node frontKR; // Represents the person at the front of the killing ring.
        private Node frontGY; // Represents the person at the front of the graveyard.
        // Adds a new person to the front of the killing ring if the list isn't initially 
        // empty. Throws an IllegalArgumentException if the list is initially empty.
        public Assassin(ArrayList<String> list) {
                if(list != null && !list.isEmpty()) {
                        for (int i = list.size() - 1; i >= 0; i--) {
                                frontKR = new Node(list.get(i), frontKR);
                        }
                } 
                else {
                        throw new IllegalArgumentException("List is empty/null.");
                }
        }
        // Prints the name(s) of the people in the graveyard if the graveyard isn't empty.
        public void printGY() {
                if (frontGY != null) {
                        Node assassinNode = frontGY;
                        printAssassin(assassinNode, false);
                }
        }
        // Prints the name(s) of the people in the killing ring if the game is active.
        public void printKR() {
                if (!gameOver()) {
                        Node assassinNode = frontKR;
                        printAssassin(assassinNode, true);
                }
        }
        // Gives the status of each player. Prints who the player is in
        // front of if the player is alive. Prints who they were killed 
        // by if they are dead.
        private void status(String s, String str, boolean isAlive) {
                System.out.print("     " + s);
                if (isAlive) {
                        System.out.println(" is in front of " + str);
                }
                else {
                        System.out.println(" was killed by " + str);
                }
        }
        // Calls status() method on the name of the person and their killer if they
        // are dead. If the person is alive, calls status() method on the name of that 
        // person and the next name on the killing ring/the name in of the person in front 
        // of the killing ring.
        private void printAssassin(Node assassinNode, boolean isAlive) {
                while (assassinNode != null) {
                        if (!isAlive) {
                                status(assassinNode.name, assassinNode.killer, false);
                                assassinNode = assassinNode.next;
                        } 
                        else {
                                String st = "";
                                if (assassinNode.next == null) {
                                        st = frontKR.name;
                                }
                                else {
                                        st = assassinNode.next.name;
                                }
                                status(assassinNode.name, st, true);
                                assassinNode = assassinNode.next;
                        }
                }
        }
        // Returns true if the killing ring contains the given name,
        // returns false otherwise.
        public boolean krContains(String name) {
                Node current = frontKR;
                while(current != null) {
                        if(current.name.equalsIgnoreCase(name)) {
                                return true;
                        }
                        current = current.next;
                }
                return false;
        }
        // Returns true if they graveyard contains the given name,
        // returns false otherwise.
        public boolean gyContains(String name) {
                Node current = frontGY;
                while(current != null) {
                        if(current.name.equalsIgnoreCase(name)) {
                                return true;
                        }
                        current = current.next;
                }
                return false;
        }
        // Represents the current state of the game.
        public boolean gameOver() {
                return (frontKR.next == null);
        }
        // Returns the name of the person in the front of the
        // killing ring if the game is over, returns null otherwise.
        public String winner() {
                if (gameOver()) {
                        return frontKR.name;
                }
                else {
                        return null;
                }
        }
        // If the game is active, the victim whose name was given gets
        // killed/moved to the graveyard and the name of their killer 
        // is revealed as well. If the game wasn't active to begin with,
        // an IllegalArgumentException is thrown.
        public void kill(String name) {
                if (!gameOver()) {
                        Node killerNode = killerName(name);
                        if (frontKR.name.equalsIgnoreCase(name)) {
                                Node victim = frontKR;
                                frontKR = victim.next;
                                sort(victim, killerNode);
                        } 
                        else {
                                Node victim = killerNode.next;
                                killerNode.next = victim.next;
                                sort(victim, killerNode);
                        }
                }
                else {
                        throw new IllegalArgumentException("Game Over.");
                } 
        }
        // Finds the name of the killer based on whose name is in front of the victim's name.
        private Node killerName(String victim) {
                Node node = frontKR;
                while (!(node == null) && !(node.next == null) && !(node.next.name.equalsIgnoreCase(victim))) {
                        node = node.next;
                }
                return node;
        }
        // Moves the victim's name to the front of the graveyard and gives
        // the name of the victim's killer.
        private void sort(Node victim, Node killer) {
                victim.killer = killer.name;
                victim.next = frontGY;
                frontGY = victim;
        }
}