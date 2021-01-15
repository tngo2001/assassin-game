// CS211 Thompson Ngo Final Project
// 13 March 2020
// *Note*: I did not write this code, I only made a slight modification to the code and just 
// used it as a starting point to complete this project. 
// Source: https://courses.cs.washington.edu/courses/cse143/10wi/homework/4/AssassinNode.java
// Modifications made to the code: Just the name of the class, so I wouldn't have to type
// "AssassinNode" every time I referred to it.
// CSE 143, Winter 2010, Marty Stepp, Homework 4: Assassin
// Each AssassinNode object represents a single node in a linked list
// for a game of Assassin.

public class Node {
        public String name; // This is a person's name.
        public String killer; // Name of who killed this person (null if alive).
        public Node next; // Next node in the list (null if none).
        // Constructs a new node to store the given name and no next node.
        public Node(String name) {
                this(name, null);
        }
        // Constructs a new node to store the given name and a reference
        // to the given next node.
        public Node(String name, Node next) {
                this.name = name;
                this.killer = null;
                this.next = next;
        }
}